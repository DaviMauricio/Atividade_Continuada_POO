/*Alterar classe AcumuloResgateMediator
(do pacote br.gov.cesarschool.poo.bonusvendas.negociov2)
➔ Imports: trocar br.gov.cesarschool.poo.bonusvendas.dao.CaixaDeBonusDAO por
br.gov.cesarschool.poo.bonusvendas.daov2.CaixaDeBonusDAO.
➔ Imports: trocar br.gov.cesarschool.poo.bonusvendas.dao.LancamentoBonusDAO por
br.gov.cesarschool.poo.bonusvendas.daov2.LancamentoBonusDAO.
➔ Método gerarCaixaDeBonus:
o Deve declarar que lança ExcecaoObjetoJaExistente.
o Deve ser adaptado para não mais retornar zero se o retorno da inclusão no
DAO for false, pois agora este método do DAO já lança
ExcecaoObjetoJaExistente se não conseguir incluir uma caixa de bônus.
o Se der tudo certo, deve retornar o número da caixa de bônus gerado.

➔ Métodos acumularBonus e resgatar:
o Devem declarar retorno void.
o Devem declarar que lançam ExcecaoObjetoNaoExistente e
ExcecaoValidacao.
o Os retornos de mensagens de erro das validações devem ser substituídos
por lançamentos de ExccecaoValidacao, instanciada com as mensagens de
erro especificas atualmente retornadas (usar o construtor que inicializa o
atributo message).
o A busca da caixa de bônus já lança ExcecaoObjetoNaoExistente, portanto, a
mensagem “Caixa de bonus inexistente” antes retornada deve ser
descartada.
o Na inclusão do lançamento, deve ser tratada (catch) a
ExcecaoObjetoJaExistente que o método incluir do DAO declara lançar. Em
tese, esta exceção nunca vai ocorrer, mas, como ela está declarada, deve ser
tratada. O código do catch para esta exceção deve lançar ExcecaoValidacao
com a mensagem “Inconsistencia no cadastro de lancamento”.
o Se der tudo certo, não devem retornar nada.*/
package br.gov.cesarschool.poo.bonusvendas.negociov2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.gov.cesarschool.poo.bonusvendas.dao.CaixaDeBonusDAO;
import br.gov.cesarschool.poo.bonusvendas.daov2.LancamentoBonusDAO;
import br.gov.cesarschool.poo.bonusvendas.entidade.CaixaDeBonus;
import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonus;
import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonusCredito;
import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonusDebito;
import br.gov.cesarschool.poo.bonusvendas.entidade.TipoResgate;
import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoJaExistente;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoNaoExistente;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoValidacao;
import br.gov.cesarschool.poo.bonusvendas.negocio.ComparadorCaixaDeBonusSaldoDec;
import br.gov.cesarschool.poo.bonusvendas.util.Ordenadora;
import br.gov.cesarschool.poo.bonusvendas.util.Comparador;
public class AcumuloResgateMediator {
	private static final String CAIXA_DE_BONUS_INEXISTENTE = "Caixa de bonus inexistente";
	private static final String VALOR_MENOR_OU_IGUAL_A_ZERO = "Valor menor ou igual a zero";
	private static AcumuloResgateMediator instancia;
	public static AcumuloResgateMediator getInstancia() {
		if (instancia == null) {
			instancia = new AcumuloResgateMediator();
		}
		return instancia;
	}
	private CaixaDeBonusDAO repositorioCaixaDeBonus;
	private LancamentoBonusDAO repositorioLancamento;
	private AcumuloResgateMediator() {
		repositorioCaixaDeBonus = new CaixaDeBonusDAO();
		repositorioLancamento = new LancamentoBonusDAO();
	}
	public long gerarCaixaDeBonus(Vendedor vendedor) throws ExcecaoObjetoJaExistente {
		LocalDate dataAtual = LocalDate.now();
		DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		long numero = Long.parseLong(vendedor.getCpf().substring(0, 9) + 
				dataAtual.format(customFormatter));
		CaixaDeBonus caixa = new CaixaDeBonus(numero);
		boolean ret = repositorioCaixaDeBonus.incluir(caixa);
		if (ret) {
			return numero;
		} else {
			throw new ExcecaoObjetoJaExistente("Inconsistencia no cadastro de caixa de bonus");
		}		 
	}
	public void acumularBonus(long numeroCaixaDeBonus, double valor) throws ExcecaoObjetoNaoExistente, ExcecaoValidacao {
		if (valor <= 0) {
			throw new ExcecaoValidacao(VALOR_MENOR_OU_IGUAL_A_ZERO);
		} 
		CaixaDeBonus caixa = repositorioCaixaDeBonus.buscar(numeroCaixaDeBonus);
		if (caixa == null) {
			throw new ExcecaoObjetoNaoExistente(CAIXA_DE_BONUS_INEXISTENTE);
		} 
		caixa.creditar(valor);
		repositorioCaixaDeBonus.alterar(caixa);
		LancamentoBonusCredito lancamento = new LancamentoBonusCredito(numeroCaixaDeBonus, valor, LocalDateTime.now());
		try {
			repositorioLancamento.incluir(lancamento);
		} catch (ExcecaoObjetoJaExistente e) {
			throw new ExcecaoValidacao("Inconsistencia no cadastro de lancamento");
		}
	}
	public void resgatar(long numeroCaixaDeBonus, double valor, TipoResgate tipoResgate) throws ExcecaoObjetoNaoExistente, ExcecaoValidacao {
		if (valor <= 0) {
			throw new ExcecaoValidacao(VALOR_MENOR_OU_IGUAL_A_ZERO);
		} 
		CaixaDeBonus caixa = repositorioCaixaDeBonus.buscar(numeroCaixaDeBonus);
		if (caixa == null) {
			throw new ExcecaoObjetoNaoExistente(CAIXA_DE_BONUS_INEXISTENTE);
		} 
		if (caixa.getSaldo() < valor) {
			throw new ExcecaoValidacao("Saldo insuficiente");
		}
		caixa.debitar(valor);
		repositorioCaixaDeBonus.alterar(caixa);
		LancamentoBonusDebito lancamento = new LancamentoBonusDebito(numeroCaixaDeBonus, valor, LocalDateTime.now(), tipoResgate);
		try {
			repositorioLancamento.incluir(lancamento);
		} catch (ExcecaoObjetoJaExistente e) {
			throw new ExcecaoValidacao("Inconsistencia no cadastro de lancamento");
		}
	}
	public CaixaDeBonus[] listaCaixaDeBonusPorSaldoMaior(double saldoInicial) {
		CaixaDeBonus[] todasAsCaixas = repositorioCaixaDeBonus.buscarTodos();
		
		CaixaDeBonus[] caixasFiltradas = Arrays.stream(todasAsCaixas)
                .filter(caixa -> caixa.getSaldo() >= saldoInicial)
                .toArray(CaixaDeBonus[]::new);
		
		Ordenadora.ordenar(caixasFiltradas, ComparadorCaixaDeBonusSaldoDec.getInstance());
		
		return caixasFiltradas;
	}
	
	public LancamentoBonus[] listaLancamentosPorFaixaData(LocalDate d1, LocalDate d2) {
        LancamentoBonus[] todosOsLancamentos = repositorioLancamento.buscarTodos();

        List<LancamentoBonus> lancamentosFiltrados = new ArrayList<>();

        for (LancamentoBonus lancamento : todosOsLancamentos) {
            LocalDate dataHoraLancamento = lancamento.getDataHoraLancamento().toLocalDate();
            if (!dataHoraLancamento.isBefore(d1) && !dataHoraLancamento.isAfter(d2)) {
                lancamentosFiltrados.add(lancamento);
            }
        }
		return todosOsLancamentos;
	}
}
