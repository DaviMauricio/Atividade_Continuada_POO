package testes_davi;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import br.gov.cesarschool.poo.bonusvendas.negocio.geral.ValidadorCPF;

public class TesteMediatorsValidadorCpf {

    @Test
    public void testEhCpfValidoCpfValido() {
        // CPF válido
        String cpf = "12345678909";
        assertTrue(ValidadorCPF.ehCpfValido(cpf));
    }

    @Test
    public void testEhCpfValidoCpfInvalido() {
        // CPF inválido
        String cpf = "00000000000";
        assertFalse(ValidadorCPF.ehCpfValido(cpf));
    }

    @Test
    public void testEhCpfValidoCpfComLetras() {
        // CPF com letras
        String cpf = "A1234567890";
        assertFalse(ValidadorCPF.ehCpfValido(cpf));
    }

    @Test
    public void testEhCpfValidoCpfComCaracteresEspeciais() {
        // CPF com caracteres especiais
        String cpf = "123.456.789-09";
        assertFalse(ValidadorCPF.ehCpfValido(cpf));
    }

    @Test
    public void testEhCpfValidoCpfCom11Caracteres() {
        // CPF com 11 caracteres, mas inválido
        String cpf = "12345678901";
        assertFalse(ValidadorCPF.ehCpfValido(cpf));
    }

    @Test
    public void testEhCpfValidoCpfComMenosDe11Caracteres() {
        // CPF com menos de 11 caracteres
        String cpf = "123456";
        assertFalse(ValidadorCPF.ehCpfValido(cpf));
    }

    @Test
    public void testEhCpfValidoCpfNulo() {
        // CPF nulo
        String cpf = null;
        assertFalse(ValidadorCPF.ehCpfValido(cpf));
    }
    
    @Test
    public void testValidacaoDeDigitosVerificadoresCPFValido() {
        // Suponhamos que "38561470829" é um CPF válido de acordo com a regra
        String cpf = "70980350417";
        assertTrue(ValidadorCPF.ehCpfValido(cpf));
    }

    @Test
    public void testValidacaoDeDigitosVerificadoresCPFInvalido() {
        // Suponhamos que "38561470820" é um CPF inválido (dígitos verificadores alterados)
        String cpf = "38561470820";
        assertFalse(ValidadorCPF.ehCpfValido(cpf));
    }

}
