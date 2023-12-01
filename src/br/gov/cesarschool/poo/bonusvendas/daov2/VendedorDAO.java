package br.gov.cesarschool.poo.bonusvendas.daov2;

import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoJaExistente;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoNaoExistente;

public class VendedorDAO {
	private DAOGenerico dao;

	public VendedorDAO() {
		this.dao = new DAOGenerico(Vendedor.class,"Vendedor");
	}

	public boolean incluir(Vendedor vend) throws ExcecaoObjetoJaExistente {
		try {
			dao.incluir(vend);
			return true;
		} catch (ExcecaoObjetoJaExistente e) {
			throw e;
		}
	}

	public void alterar(Vendedor vend) throws ExcecaoObjetoNaoExistente {
		try {
			dao.alterar(vend);
		} catch (ExcecaoObjetoNaoExistente e) {
			throw e;
		}
	}

	public Vendedor buscar(String cpf) throws ExcecaoObjetoNaoExistente {
		try {
			return (Vendedor) dao.buscar(cpf);
		} catch (ExcecaoObjetoNaoExistente e) {
			throw e;
		}
	}

	public Vendedor[] buscarTodos() {
		Registro[] registros = dao.buscarTodos();
		Vendedor[] vends = new Vendedor[registros.length];
		for (int i = 0; i < registros.length; i++) {
			vends[i] = (Vendedor) registros[i];
		}
		return vends;
	}
}
