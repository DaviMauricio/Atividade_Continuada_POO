package br.gov.cesarschool.poo.bonusvendas.daos; // dando erro

import java.io.Serializable;
import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonus;

public class LancamentoBonusDAO implements Serializable{
    private CadastroObjetos cadastro = new CadastroObjetos(LancamentoBonus.class);
    private static final String BRANCO = "";
    private static final long serialVersionUID = 1L;
    
    public boolean incluir(LancamentoBonus lancamento) {
        LancamentoBonus lancamentoBusca = buscar(BRANCO + lancamento.getNumeroCaixaDeBonus());
        if (lancamentoBusca == null) {
            return false;
        } else {
            cadastro.incluir(lancamento, BRANCO + lancamento.getNumeroCaixaDeBonus());
            return true;
        }
    }

    public boolean alterar(LancamentoBonus lancamento) {
        LancamentoBonus lancamentoBusca = buscar(BRANCO + lancamento.getNumeroCaixaDeBonus());
        if (lancamentoBusca == null) {
            return false;
        } else {
            cadastro.alterar(lancamento, BRANCO + lancamento.getNumeroCaixaDeBonus());
            return true;
        }
    }

    public LancamentoBonus buscar(String identificadorUnico) {
        return (LancamentoBonus) cadastro.buscar(identificadorUnico);
    }

    public LancamentoBonus[] buscarTodos() {
        Serializable[] rets = cadastro.buscarTodos(LancamentoBonus.class);
        LancamentoBonus[] lancamentos = new LancamentoBonus[rets.length];
        for (int i = 0; i < rets.length; i++) {
            lancamentos[i] = (LancamentoBonus) rets[i];
        }
        return lancamentos;
    }

    public boolean excluir(String identificadorUnico) {
        LancamentoBonus lancamentoBusca = buscar(identificadorUnico);
        if (lancamentoBusca == null) {
            return false;
        } else {
            cadastro.excluir(identificadorUnico);
            return true;
        }
    }
}