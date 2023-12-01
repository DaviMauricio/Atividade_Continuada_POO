package br.gov.cesarschool.poo.bonusvendas.daov2;
import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoJaExistente;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoNaoExistente;

import java.io.Serializable;

public class DAOGenerico {
    private CadastroObjetos cadastro;
    private String nomeEntidade;

    public DAOGenerico(Class<?> tipo, String nomeEntidade) {
        this.cadastro = new CadastroObjetos(tipo);
        this.nomeEntidade = nomeEntidade;
    }

    public void incluir(Registro reg) throws ExcecaoObjetoJaExistente {
       
    	///mudeiii issooooo daqui 
    	Registro regBusca = null;
		try {
			regBusca = buscar(reg.getIdUnico());
		} catch (ExcecaoObjetoNaoExistente e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//// ate aquiiiii
        if (regBusca != null) {
            throw new ExcecaoObjetoJaExistente(nomeEntidade + " ja existente");
        } else {
            cadastro.incluir(reg, reg.getIdUnico());
        }         
    }
    
    public void alterar(Registro reg) throws ExcecaoObjetoNaoExistente {
        Registro regBusca = buscar(reg.getIdUnico());
        if (regBusca == null) {
            throw new ExcecaoObjetoNaoExistente(nomeEntidade + " nao existente");
        } else {
            cadastro.alterar(reg, reg.getIdUnico());
        }       
    }
    
    public Registro buscar(String id) throws ExcecaoObjetoNaoExistente {
        Registro reg = (Registro) cadastro.buscar(id);
        if (reg == null) {
            throw new ExcecaoObjetoNaoExistente(nomeEntidade + " nao existente");
        }
        return reg;
    }
    
    public Registro[] buscarTodos() {
        Serializable[] rets = cadastro.buscarTodos(Registro.class);
        Registro[] registros = new Registro[rets.length];
        for(int i = 0; i < rets.length; i++) {
            registros[i] = (Registro)rets[i];
        }
        return registros;
    } 
}
