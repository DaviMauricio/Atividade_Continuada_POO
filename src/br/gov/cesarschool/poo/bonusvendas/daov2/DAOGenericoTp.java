package br.gov.cesarschool.poo.bonusvendas.daov2;

import java.io.Serializable;
import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoJaExistente;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoNaoExistente;

public class DAOGenericoTp<T> extends Registro{
    private CadastroObjetos cadastro;
    private String nomeEntidade;

    public DAOGenericoTp(Class<?> tipo, String nomeEntidade) {
        this.cadastro = new CadastroObjetos(tipo);
        this.nomeEntidade = nomeEntidade;
    }

    public void incluir(T reg) throws ExcecaoObjetoJaExistente {
        T regBusca = buscar(reg.getIdUnico());
        if (regBusca != null) {
            throw new ExcecaoObjetoJaExistente(nomeEntidade + " ja existente");
        } else {
            cadastro.incluir(reg, reg.getIdUnico());
        }         
    }
    
    public void alterar(T reg) throws ExcecaoObjetoNaoExistente {
        T regBusca = buscar(reg.getIdUnico());
        if (regBusca == null) {
            throw new ExcecaoObjetoNaoExistente(nomeEntidade + " nao existente");
        } else {
            cadastro.alterar(reg, reg.getIdUnico());
        }       
    }
    
    public T buscar(String id) throws ExcecaoObjetoNaoExistente {
        T reg = (T) cadastro.buscar(id);
        if (reg == null) {
            throw new ExcecaoObjetoNaoExistente(nomeEntidade + " nao existente");
        }
        return reg;
    }
    
    public T[] buscarTodos() {
        Serializable[] rets = cadastro.buscarTodos(Registro.class);
        T[] registros = new T[rets.length];
        for(int i = 0; i < rets.length; i++) {
            registros[i] = (T)rets[i];
        }
        return registros;
    }

	@Override
	public String getIdUnico() {
		// TODO Auto-generated method stub
		return null;
	} 
}