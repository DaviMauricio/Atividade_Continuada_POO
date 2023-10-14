package br.gov.cesarschool.poo.bonusvendas.daos;

import java.io.Serializable;
import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import br.gov.cesarschool.poo.bonusvendas.entidade.CaixaDeBonus;

public class CaixaDeBonusDAO
{
    private static final String BRANCO = "";
    private CadastroObjetos cadastro = new CadastroObjetos(CaixaDeBonus.class);

    public boolean incluir(CaixaDeBonus caixa) {
        CaixaDeBonus caixaBusca = buscar(caixa.getNumero());
        if (caixaBusca != null) {
            return false;
        } else {
            cadastro.incluir(caixa, BRANCO + caixa.getNumero());
            return true;
        }
    }

    public boolean alterar(CaixaDeBonus caixa) {
        CaixaDeBonus caixaBusca = buscar(caixa.getNumero());
        if (caixaBusca == null) {
            return false;
        } else {
            cadastro.alterar(caixa, BRANCO + caixa.getNumero());
            return true;
        }
    }

    public CaixaDeBonus buscar(long numero) {
        return (CaixaDeBonus) cadastro.buscar(BRANCO + numero);
    }

    public boolean excluir(long numero) {
        CaixaDeBonus caixaBusca = buscar(numero);
        if (caixaBusca == null) {
            return false;
        } else {
            cadastro.excluir(BRANCO + numero);
            return true;
        }
    }

    public CaixaDeBonus[] buscarTodos() {
        Serializable[] rets = cadastro.buscarTodos(CaixaDeBonus.class);
        CaixaDeBonus[] caixas = new CaixaDeBonus[rets.length];
        for (int i = 0; i < rets.length; i++) {
            caixas[i] = (CaixaDeBonus) rets[i];
        }
        return caixas;
    }
}
