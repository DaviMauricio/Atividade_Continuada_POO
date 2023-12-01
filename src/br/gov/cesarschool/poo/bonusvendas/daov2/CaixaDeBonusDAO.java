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

import br.gov.cesarschool.poo.bonusvendas.entidade.CaixaDeBonus;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoJaExistente;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoNaoExistente;


public class CaixaDeBonusDAO {
	private static final String BRANCO = "";
	private DAOGenerico dao;
	
	public CaixaDeBonusDAO() {
        this.dao = new DAOGenerico(CaixaDeBonus.class, "Caixa");
    }
	
	public void incluir(CaixaDeBonus caixaBonus) throws ExcecaoObjetoJaExistente {
		dao.incluir(caixaBonus);		 
	}
	
	public void alterar(CaixaDeBonus caixaBonus) throws ExcecaoObjetoNaoExistente {
		dao.alterar(caixaBonus);
	}
	
	public CaixaDeBonus buscar(long codigo) throws ExcecaoObjetoNaoExistente {
		return (CaixaDeBonus)dao.buscar(BRANCO + codigo);
	}
	
	public CaixaDeBonus[] buscarTodos() {
		Registro[] registros = dao.buscarTodos();
		CaixaDeBonus[] caixaBonus = new CaixaDeBonus[registros.length];
		for(int i=0; i<registros.length; i++) { 
			caixaBonus[i] = (CaixaDeBonus)registros[i];
		}
		return caixaBonus;
	} 
}
