package br.gov.cesarschool.poo.bonusvendas.entidade;

import java.io.Serializable;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Endereco;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Sexo;
import java.time.LocalDate;
import java.time.Period;

public class Vendedor implements Serializable
{
	private String cpf;
	private String nomeCompleto;
	private Sexo sexo;
	private LocalDate dataNascimento; 
	private double renda;
	private Endereco endereco; 
    private static final long serialVersionUID = 1L;

	
	public Vendedor(String cpf, String nomeCompleto, Sexo sexo, LocalDate dataNascimento, double renda,
			Endereco endereco) {
		super();
		this.cpf = cpf;
		this.nomeCompleto = nomeCompleto;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.renda = renda;
		this.endereco = endereco;
	}

	public String getCpf() {
		return cpf;
	}


	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public double getRenda() {
		return renda;
	}

	public void setRenda(double renda) {
		this.renda = renda;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public int calcularIdade() {
	    LocalDate hoje = LocalDate.now(); 
	    Period periodo = Period.between(dataNascimento, hoje); 
	    return periodo.getYears();
	}
}