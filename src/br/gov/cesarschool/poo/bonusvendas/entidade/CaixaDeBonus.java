package br.gov.cesarschool.poo.bonusvendas.entidade;

import java.time.LocalDateTime;

import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;

public class CaixaDeBonus extends Registro
{
	private static final long serialVersionUID = 1L;
	private long numero; 
	private double saldo; 
	private LocalDateTime dataHoraAtualizacao;
	public CaixaDeBonus(long numero) {
		super();
		this.numero = numero;
		dataHoraAtualizacao = LocalDateTime.now();
	}
	public long getNumero() {
		return numero;
	}
	public double getSaldo() {
		return saldo;
	}
	public LocalDateTime getDataHoraAtualizacao() {
		return dataHoraAtualizacao;
	}
	public void creditar(double valor) {
		saldo = saldo + valor;
		dataHoraAtualizacao = LocalDateTime.now();
	}
	public void debitar(double valor) {
		saldo = saldo - valor;
		dataHoraAtualizacao = LocalDateTime.now();
	}
	@Override
	public String getIdUnico() {
		// TODO Auto-generated method stub
		// id unico = numero da caixa
		return getNumero() + "";
	}

}
