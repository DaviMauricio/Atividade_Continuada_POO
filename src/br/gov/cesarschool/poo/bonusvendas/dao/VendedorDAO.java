package br.gov.cesarschool.poo.bonusvendas.dao;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;


public class VendedorDAO {
	//private CadastroObjetos cadastro = new CadastroObjetos(Vendedor.class); 
	private DAOGenerico dao; 
	
	public VendedorDAO() {
		this.dao = new DAOGenerico(Vendedor.class);
	}
	
	
	public boolean incluir(Vendedor vend) {
		return dao.incluir(vend);
			 
	}
	public boolean alterar(Vendedor vend) {
		
		return dao.alterar(vend);	
	}
	public Vendedor buscar(String cpf) { 
		return (Vendedor)dao.buscar(cpf);
	}
	public Vendedor[] buscarTodos() {
		Registro[] registros = dao.buscarTodos();
		Vendedor[] vends = new Vendedor[registros.length];
		for(int i=0; i<registros.length; i++) {
			vends[i] = (Vendedor)registros[i];
		}
		return vends;
	} 

}
