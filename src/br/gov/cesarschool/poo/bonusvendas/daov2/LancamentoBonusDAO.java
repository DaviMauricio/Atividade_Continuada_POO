/*Alterar classes VendedorDAO, CaixaDeBonusDAO e LancamentoBonusDAO
(do pacote br.gov.cesarschool.poo.bonusvendas.daov2)
➔ Alterar os construtores para instanciar o DAOGenerico passando como parâmetro
adicional os nomes das entidades, que são, para VendedorDAO, CaixaDeBonusDAO e
LancamentoBonusDAO, respectivamente: Vendedor, Caixa e Lancamento.
➔ Método buscar:
o Deve declarar que lança ExcecaoObjetoNaoExistente.
➔ Método incluir:
o Deve declarar retorno void.
o Deve declarar que lança ExcecaoObjetoJaExistente.
o Não deve retornar nada.
➔ Método alterar:
o Deve declarar retorno void.
o Deve declarar que lança ExcecaoObjetoNaoExistente.
o Não deve retornar nada.*/

package br.gov.cesarschool.poo.bonusvendas.daov2;

import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoJaExistente;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoNaoExistente;
import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonus;

public class LancamentoBonusDAO {
	private DAOGenerico dao;

	public LancamentoBonusDAO() {
		this.dao = new DAOGenerico(LancamentoBonus.class, "Lancamento");
	}

	public void incluir(LancamentoBonus lancamento) throws ExcecaoObjetoJaExistente {
		dao.incluir(lancamento);
	}

	public void alterar(LancamentoBonus lancamento) throws ExcecaoObjetoNaoExistente {
		dao.alterar(lancamento);
	}

	public LancamentoBonus buscar(String codigo) throws ExcecaoObjetoNaoExistente {
		return (LancamentoBonus) dao.buscar(codigo);
	}

	public LancamentoBonus[] buscarTodos() {
		Registro[] registros = dao.buscarTodos();
		LancamentoBonus[] lancamentos = new LancamentoBonus[registros.length];
		for (int i = 0; i < registros.length; i++) {
			lancamentos[i] = (LancamentoBonus) registros[i];
		}
		return lancamentos;
	}
}
