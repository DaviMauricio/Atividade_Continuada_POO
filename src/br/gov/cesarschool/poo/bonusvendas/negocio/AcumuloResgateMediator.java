package br.gov.cesarschool.poo.bonusvendas.negocio;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.gov.cesarschool.poo.bonusvendas.daos.CaixaDeBonusDAO;
import br.gov.cesarschool.poo.bonusvendas.daos.LancamentoBonusDAO;
import br.gov.cesarschool.poo.bonusvendas.entidade.CaixaDeBonus;
import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonus;
import br.gov.cesarschool.poo.bonusvendas.entidade.TipoResgate;
import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;

public class AcumuloResgateMediator {

    private CaixaDeBonusDAO repositorioCaixaDeBonus;
    private LancamentoBonusDAO repositorioLancamento;
    private static AcumuloResgateMediator instance;

    private AcumuloResgateMediator() {
        this.repositorioCaixaDeBonus = new CaixaDeBonusDAO();
        this.repositorioLancamento = new LancamentoBonusDAO();
    }

    public static synchronized AcumuloResgateMediator getInstance() {
        if (instance == null) {
            instance = new AcumuloResgateMediator();
        }
        return instance;
    }

    public long gerarCaixaDeBonus(Vendedor vendedor) {
        LocalDate today = LocalDate.now();
        String caixaIdStr = vendedor.getCpf() + today.getYear() + String.format("%02d", today.getMonthValue()) + String.format("%02d", today.getDayOfMonth());
        long caixaId = Long.parseLong(caixaIdStr);
        
        CaixaDeBonus novaCaixa = new CaixaDeBonus(caixaId);
        boolean wasInserted = repositorioCaixaDeBonus.incluir(novaCaixa);
        
        if(wasInserted) {
            return caixaId;
        } else {
            return 0;
        }
    }

    public String acumularBonus(long numeroCaixaDeBonus, double valor) {
        if (valor <= 0) {
            return "Valor menor ou igual a zero";
        }
        
        CaixaDeBonus caixa = repositorioCaixaDeBonus.buscar(numeroCaixaDeBonus);
        if (caixa == null) {
            return "Caixa de bonus inexistente";
        }
        
        caixa.creditar(valor);
        repositorioCaixaDeBonus.alterar(caixa);

        LancamentoBonus lancamento = new LancamentoBonus(numeroCaixaDeBonus, valor, null);
        
        repositorioLancamento.incluir(new LancamentoBonus(numeroCaixaDeBonus, valor, LocalDateTime.now()));
        
        return null;
    }

    public String resgatar(long numeroCaixaDeBonus, double valor, TipoResgate tipo) {
        if (valor <= 0) {
            return "Valor menor ou igual a zero";
        }
        
        CaixaDeBonus caixa = repositorioCaixaDeBonus.buscar(numeroCaixaDeBonus);
        if (caixa == null) {
            return "Caixa de bonus inexistente";
        }

        if (caixa.getSaldo() < valor) {
            return "Saldo insuficiente";
        }
        
        caixa.debitar(valor);
        repositorioCaixaDeBonus.alterar(caixa);

        LancamentoBonus lancamento = new LancamentoBonus(numeroCaixaDeBonus, valor, null);
        
        repositorioLancamento.incluir(new LancamentoBonus(numeroCaixaDeBonus, valor, LocalDateTime.now()));
        
        return null;
    }
}