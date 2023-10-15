package br.gov.cesarschool.poo.bonusvendas.negocio;

import java.time.LocalDate;

import br.gov.cesarschool.poo.bonusvendas.daos.VendedorDAO;
import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Endereco;
import br.gov.cesarschool.poo.bonusvendas.negocio.geral.StringUtil;
import br.gov.cesarschool.poo.bonusvendas.negocio.geral.ValidadorCPF;

public class VendedorMediator {
	private static VendedorMediator instance;
    private VendedorDAO repositorioVendedor;
    private AcumuloResgateMediator caixaDeBonusMediator;

    private VendedorMediator() {
        this.repositorioVendedor = new VendedorDAO();
        this.caixaDeBonusMediator = AcumuloResgateMediator.getInstance();
    }

    public static VendedorMediator getInstance() {
        if (instance == null) {
            instance = new VendedorMediator();
        }
        return instance;
    }

    public ResultadoInclusaoVendedor incluir(Vendedor vendedor) {
        String erro = validar(vendedor);
        if (erro != null) {
            return new ResultadoInclusaoVendedor();
        }

        if (!repositorioVendedor.incluir(vendedor)) {
            return new ResultadoInclusaoVendedor();
        }

        long numeroCaixa = caixaDeBonusMediator.gerarCaixaDeBonus(vendedor);
        return new ResultadoInclusaoVendedor();
    }

    public String alterar(Vendedor vendedor) {
        String erro = validar(vendedor);
        if (erro == null) {
            return erro;
        }

        if (!repositorioVendedor.alterar(vendedor)) {
            return "Erro ao alterar vendedor no repositório.";
        }

        return null;
    }

    private String validar(Vendedor vendedor) {
        if (vendedor == null) {
            return "Vendedor não informado";
        }

        if (StringUtil.ehNuloOuBranco(vendedor.getCpf())) {
			return "CPF nao informado";
		}else if(ValidadorCPF.ehCpfValido(vendedor.getCpf()) == false) {
			return "CPF invalido";
		}

        if (StringUtil.ehNuloOuBranco(vendedor.getNomeCompleto())) {
            return "Nome completo não informado ou é uma string vazia";
        }

        if ((vendedor.getSexo() == null)) {
            return "Sexo não informado";
        }

        if (vendedor.getDataNascimento() == null) {
            return "Data de nascimento não informada";
        }

        LocalDate minDate = LocalDate.now().minusYears(17);
        if (vendedor.getDataNascimento().isAfter(minDate)) {
            return "Data de nascimento inválida";
        }

        if (vendedor.getRenda() < 0) {
            return "Renda menor que zero";
        }

        Endereco endereco = vendedor.getEndereco();
        if (endereco == null) {
            return "Endereço não informado";
        }

        if (endereco.getNumero() < 0) {
            return "Número de endereço menor que zero";
        }

        if (StringUtil.ehNuloOuBranco(endereco.getLogradouro()) || endereco.getLogradouro().length() < 4) {
            return "Logradouro não informado ou possui menos de 4 caracteres";
        }

        if (StringUtil.ehNuloOuBranco(endereco.getCidade())) {
            return "Cidade não informada";
        }

        if (StringUtil.ehNuloOuBranco(endereco.getEstado())) {
            return "Estado não informado";
        }

        if (StringUtil.ehNuloOuBranco(endereco.getPais())) {
            return "País não informado";
        }

        return null;
    }


    public Vendedor buscar(String cpf) {
        return repositorioVendedor.buscar(cpf);
    }
}

