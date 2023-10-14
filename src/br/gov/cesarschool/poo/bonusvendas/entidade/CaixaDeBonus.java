package br.gov.cesarschool.poo.bonusvendas.entidade;
import java.io.Serializable;
import java.time.LocalDateTime;

public class CaixaDeBonus implements Serializable {

    private long numero;
    private double saldo;
    private LocalDateTime dataHoraAtualizacao;
    private static final long serialVersionUID = 1L;


    public CaixaDeBonus (long numero){
        this.numero = numero;
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

    public void creditar(double valor) { //adicionar double ao saldo e atualizar com a data atual
        saldo += valor;
        dataHoraAtualizacao = LocalDateTime.now();
    }

    public void debitar(double valor) {//retirar double ao saldo e atualizar com a data atual
        saldo -= valor;
        dataHoraAtualizacao = LocalDateTime.now();
    }
}