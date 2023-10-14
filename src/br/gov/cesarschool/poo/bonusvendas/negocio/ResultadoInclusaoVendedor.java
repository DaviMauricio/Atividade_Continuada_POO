package br.gov.cesarschool.poo.bonusvendas.negocio;

public class ResultadoInclusaoVendedor {

    private Long numeroCaixaDeBonus;
    private String mensagemErroValidacao;

    public long getNumeroCaixaDeBonus() {
        return numeroCaixaDeBonus != null ? numeroCaixaDeBonus : 0L;
    }

    public String getMensagemErroValidacao() {
        return mensagemErroValidacao;
    }

    public void setNumeroCaixaDeBonus(Long numeroCaixaDeBonus) {
        this.numeroCaixaDeBonus = numeroCaixaDeBonus;
    }

    public void setMensagemErroValidacao(String mensagemErroValidacao) {
        this.mensagemErroValidacao = mensagemErroValidacao;
    }
}