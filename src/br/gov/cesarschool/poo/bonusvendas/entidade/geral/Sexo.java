package br.gov.cesarschool.poo.bonusvendas.entidade.geral;


public enum Sexo {

	MASCULINO(1,"Masculino"),
	FEMININO(2,"Feminino");

	private int codigo;
	private String descricao;
	
	
	private Sexo( int codigo,String descricao) { 
		this.descricao = descricao;
		this.codigo = codigo;
	}
	
	
	public String getDescricao() {
		return descricao;
	}

	public int getCodigo() {
		return codigo;
	}
	

}
