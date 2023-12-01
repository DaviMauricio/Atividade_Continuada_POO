package br.gov.cesarschool.poo.bonusvendas.negociov2;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import br.gov.cesarschool.poo.bonusvendas.util.Ordenadora;
import br.gov.cesarschool.poo.bonusvendas.dao.VendedorDAO;
import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ErroValidacao;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoJaExistente;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoNaoExistente;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoValidacao;
import br.gov.cesarschool.poo.bonusvendas.negocio.ComparadorVendedorNome;
import br.gov.cesarschool.poo.bonusvendas.negocio.ComparadorVendedorRenda;
import br.gov.cesarschool.poo.bonusvendas.negocio.geral.StringUtil;
import br.gov.cesarschool.poo.bonusvendas.negocio.geral.ValidadorCPF;

public class VendedorMediator {
	private static VendedorMediator instancia;
	public static VendedorMediator getInstancia() {
		if (instancia == null) {
			instancia = new VendedorMediator();
		}
		return instancia;
	}
	private VendedorDAO repositorioVendedor;
	private AcumuloResgateMediator caixaDeBonusMediator;
	private VendedorMediator() {
		repositorioVendedor = new VendedorDAO();
		caixaDeBonusMediator = AcumuloResgateMediator.getInstancia();
	}
	public void validar(Vendedor vendedor) throws ExcecaoValidacao {
		ArrayList<ErroValidacao> erros = new ArrayList<>();
		
		if (StringUtil.ehNuloOuBranco(vendedor.getCpf())) {
			erros.add(new ErroValidacao(1, "CPF nao informado"));
		}
		if (!ValidadorCPF.ehCpfValido(vendedor.getCpf())) {
			erros.add(new ErroValidacao(2, "CPF invalido"));
		}
		if (StringUtil.ehNuloOuBranco(vendedor.getNomeCompleto())) {
			erros.add(new ErroValidacao(3, "Nome completo nao informado"));
		}
		if (vendedor.getSexo() == null) {
			erros.add(new ErroValidacao(4, "Sexo nao informado"));
		}
		if (vendedor.getDataNascimento() == null) {
			erros.add(new ErroValidacao(5, "Data de nascimento nao informada"));
		}
		if (dataNascimentoInvalida(vendedor.getDataNascimento())) {
			erros.add(new ErroValidacao(6, "Data de nascimento invalida"));
		}
		if (vendedor.getRenda() < 0) {
			erros.add(new ErroValidacao(7, "Renda menor que zero"));
		}
		if (vendedor.getEndereco() == null) {
			erros.add(new ErroValidacao(8, "Endereco nao informado"));
		}
		if (StringUtil.ehNuloOuBranco(vendedor.getEndereco().getLogradouro())) {
			erros.add(new ErroValidacao(9, "Logradouro nao informado"));
		}
		if (vendedor.getEndereco().getLogradouro().length() < 4) {
			erros.add(new ErroValidacao(10, "Logradouro tem menos de 04 caracteres"));
		}
		if (vendedor.getEndereco().getNumero() < 0) {
			erros.add(new ErroValidacao(11, "Numero menor que zero"));
		}
		if (StringUtil.ehNuloOuBranco(vendedor.getEndereco().getCidade())) {
			erros.add(new ErroValidacao(12, "Cidade nao informada"));
		}
		if (StringUtil.ehNuloOuBranco(vendedor.getEndereco().getEstado())) {
			erros.add(new ErroValidacao(13, "Estado nao informado"));
		}
		if (StringUtil.ehNuloOuBranco(vendedor.getEndereco().getPais())) {
			erros.add(new ErroValidacao(14, "Pais nao informado"));
		}
		
		if (!erros.isEmpty()) {
			throw new ExcecaoValidacao(erros);
		}
	}
	
	public long incluir(Vendedor vendedor) throws ExcecaoObjetoJaExistente, ExcecaoValidacao {
		validar(vendedor);
		
		boolean ret = repositorioVendedor.incluir(vendedor);
		if (!ret) {
			throw new ExcecaoObjetoJaExistente("Vendedor ja existente");
		}
		
		long numeroCaixaBonus = caixaDeBonusMediator.gerarCaixaDeBonus(vendedor);
		if (numeroCaixaBonus == 0) {
			throw new ExcecaoValidacao("Caixa de bonus nao foi gerada");
		}
		
		return numeroCaixaBonus;
	}
	
	public void alterar(Vendedor vendedor) throws ExcecaoObjetoNaoExistente, ExcecaoValidacao {
		validar(vendedor);
		
		boolean ret = repositorioVendedor.alterar(vendedor);
		if (!ret) {
			throw new ExcecaoObjetoNaoExistente("Vendedor inexistente");
		}
	}
	
	public Vendedor[] gerarListagemClienteOrdenadaPorNome() {
		Vendedor[] vendedores = repositorioVendedor.buscarTodos();
		Ordenadora.ordenar(vendedores, ComparadorVendedorNome.getInstance());
		return vendedores;
	}
	
	public Vendedor[] gerarListagemClienteOrdenadaPorRenda() {
		Vendedor[] vendedores = repositorioVendedor.buscarTodos();
		Ordenadora.ordenar(vendedores, ComparadorVendedorRenda.getInstance());	
		return vendedores;
	}

	private boolean dataNascimentoInvalida(LocalDate dataNasc) {
		long yearsDifference = ChronoUnit.YEARS.between(dataNasc, LocalDate.now());
		return yearsDifference < 17;
	}
}


