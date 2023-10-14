package br.gov.cesarschool.poo.bonusvendas.testes;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import br.gov.cesarschool.poo.bonusvendas.negocio.ResultadoInclusaoVendedor;

public class TesteResultIncluVendedor {

	 @Test
	    public void testGetNumeroCaixaDeBonus() {
		 ResultadoInclusaoVendedor suaInstancia = new ResultadoInclusaoVendedor(); // Substitua 'SuaClasse' pelo nome da sua classe real
	        long numeroEsperado = 42; // Defina o número que você espera
	        suaInstancia.setNumeroCaixaDeBonus(numeroEsperado);
	        
	        assertEquals(numeroEsperado, suaInstancia.getNumeroCaixaDeBonus());
	    }

	    @Test
	    public void testGetMensagemErroValidacao() {
	    	ResultadoInclusaoVendedor suaInstancia = new ResultadoInclusaoVendedor(); // Substitua 'SuaClasse' pelo nome da sua classe real
	        String mensagemEsperada = "Mensagem de erro esperada"; // Defina a mensagem que você espera
	        suaInstancia.setMensagemErroValidacao(mensagemEsperada);

	        assertEquals(mensagemEsperada, suaInstancia.getMensagemErroValidacao());
	    }

	    @Test
	    public void testGetNumeroCaixaDeBonusContrario() {
	    	ResultadoInclusaoVendedor suaInstancia = new ResultadoInclusaoVendedor(); // Substitua 'SuaClasse' pelo nome da sua classe real
	        long numeroEsperado = 42; // Defina o número que você espera
	        suaInstancia.setNumeroCaixaDeBonus(numeroEsperado);
	        
	        long numeroDiferente = 100; // Defina um número diferente

	        assertNotEquals(numeroDiferente, suaInstancia.getNumeroCaixaDeBonus());
	    }

	    @Test
	    public void testGetMensagemErroValidacaoContrario() {
	    	ResultadoInclusaoVendedor suaInstancia = new ResultadoInclusaoVendedor(); // Substitua 'SuaClasse' pelo nome da sua classe real
	        String mensagemEsperada = "Mensagem de erro esperada"; // Defina a mensagem que você espera
	        suaInstancia.setMensagemErroValidacao(mensagemEsperada);

	        String mensagemDiferente = "Outra mensagem"; // Defina uma mensagem diferente

	        assertNotEquals(mensagemDiferente, suaInstancia.getMensagemErroValidacao());
	    }
}
