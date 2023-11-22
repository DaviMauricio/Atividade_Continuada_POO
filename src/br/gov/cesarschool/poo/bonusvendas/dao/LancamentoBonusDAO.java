package br.gov.cesarschool.poo.bonusvendas.dao;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonus;

public class LancamentoBonusDAO
{
	//private CadastroObjetos cadastro = new CadastroObjetos(LancamentoBonus.class);
	private DAOGenerico dao;

	public LancamentoBonusDAO() {
		this.dao = new DAOGenerico(LancamentoBonus.class);
	}
	
	
	public boolean incluir(LancamentoBonus lancamento) {
		return dao.incluir(lancamento);
	 
	}
	
	public boolean alterar(LancamentoBonus lancamento) {
		return dao.alterar(lancamento);
				
	}
	
	public LancamentoBonus buscar(String codigo) {
		return (LancamentoBonus)dao.buscar(codigo);
	}
	
	public LancamentoBonus[] buscarTodos() {
		Registro[] registros = dao.buscarTodos();
		LancamentoBonus[] lancamentos = new LancamentoBonus[registros.length];
		for(int i=0; i<registros.length; i++) {
			lancamentos[i] = (LancamentoBonus)registros[i];
		}
		return lancamentos;
	} 
	
	// obterIdUnico(LancamentoBonus lancamento)nao é mais necessário 
	
}
