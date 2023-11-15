package br.gov.cesarschool.poo.bonusvendas.dao;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;

public class DAOGenerico
{
	CadastroObjetos cadastro;
	
	public DAOGenerico(CadastroObjetos cadastro) {
		// revisar
		this.cadastro = cadastro;
	}
	
	// falta complementar
	boolean incluir(Registro reg) {
		return true;
	}
	
	boolean alterar(Registro reg) {
		return true;
	}
	
	Registro buscar(String id) {
		return;
	}
	
	Registro[] buscarTodos() {
		return;
	}
}
