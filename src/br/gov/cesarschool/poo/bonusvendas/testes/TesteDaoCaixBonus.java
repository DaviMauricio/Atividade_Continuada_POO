package br.gov.cesarschool.poo.bonusvendas.testes;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.gov.cesarschool.poo.bonusvendas.entidade.CaixaDeBonus;

import java.time.LocalDateTime;//?
import org.junit.jupiter.api.Test;//?

class TesteDaoCaixBonus {

	private CaixaDeBonus caixa;

    @BeforeEach
    public void setUp() {
        caixa = new CaixaDeBonus(1); // Cria uma nova instância de CaixaDeBonus para cada teste.
    }

    @Test
    public void testCredito() {
        // Verifica se o caixa não é nulo.
        assertNotNull(caixa);

        // Verifica o saldo inicial (deve ser 0.0).
        assertEquals(0.0, caixa.getSaldo());

        // Realiza a operação de crédito.
        caixa.creditar(100.0);

        // Verifica se o saldo foi atualizado corretamente.
        assertEquals(100.0, caixa.getSaldo(), 0.001); // O terceiro argumento é a precisão aceitável para números de ponto flutuante.

        // Verifica se a dataHoraAtualizacao foi atualizada.
        assertNotNull(caixa.getDataHoraAtualizacao());
    }

    @Test
    public void testDebito() {
        // Verifica se o caixa não é nulo.
        assertNotNull(caixa);

        // Verifica o saldo inicial (deve ser 0.0).
        assertEquals(0.0, caixa.getSaldo());

        // Realiza a operação de débito.
        caixa.debitar(50.0);

        // Verifica se o saldo foi atualizado corretamente.
        assertEquals(-50.0, caixa.getSaldo(), 0.001); // O terceiro argumento é a precisão aceitável para números de ponto flutuante.

        // Verifica se a dataHoraAtualizacao foi atualizada.
        assertNotNull(caixa.getDataHoraAtualizacao());
    }

}
