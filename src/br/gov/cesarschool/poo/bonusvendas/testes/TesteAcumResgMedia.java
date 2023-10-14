package br.gov.cesarschool.poo.bonusvendas.testes;


import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Endereco;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Sexo;
import br.gov.cesarschool.poo.bonusvendas.negocio.AcumuloResgateMediator;
import br.gov.cesarschool.poo.bonusvendas.entidade.TipoResgate;

public class TesteAcumResgMedia {

	private AcumuloResgateMediator mediator;
    private Vendedor vendedor;
    private long numeroCaixaDeBonus;

    @BeforeEach
    void setUp() {
        mediator = AcumuloResgateMediator.getInstance();
        Vendedor vendedor = new Vendedor("12345678910", "Nome Completo", Sexo.MASCULINO, LocalDate.of(1990, 1, 1), 5000, new Endereco("Rua Exemplo", 123, "Apto 456", "12345-678", "Cidade Exemplo", "Estado Exemplo", "Pa�s Exemplo"));
        
        // Você vai precisar ajustar conforme seu construtor
        // ... [Inicialize o Vendedor como apropriado]
    }

    @Test
    void testGerarCaixaDeBonus() {
        numeroCaixaDeBonus = mediator.gerarCaixaDeBonus(vendedor);
        assertTrue(numeroCaixaDeBonus > 0, "A caixa de bônus não foi criada corretamente");
    }

    @Test
    void testAcumularBonus() {
        numeroCaixaDeBonus = mediator.gerarCaixaDeBonus(vendedor);
        assertNull(mediator.acumularBonus(numeroCaixaDeBonus, 1000), "Não deve retornar mensagem de erro");
        assertEquals("Valor menor ou igual a zero", mediator.acumularBonus(numeroCaixaDeBonus, -1), "Deve retornar mensagem de erro para valor negativo");
        assertEquals("Caixa de bonus inexistente", mediator.acumularBonus(0, 1000), "Deve retornar mensagem de erro para caixa inexistente");
    }

    @Test
    void testResgatar() {
        numeroCaixaDeBonus = mediator.gerarCaixaDeBonus(vendedor);
        mediator.acumularBonus(numeroCaixaDeBonus, 1000);

        // Resgate bem-sucedido
        assertNull(mediator.resgatar(numeroCaixaDeBonus, 100, TipoResgate.PRODUTO), "Não deve retornar mensagem de erro");

        // Resgate com valor zero
        assertEquals("Valor menor ou igual a zero", mediator.resgatar(numeroCaixaDeBonus, 0, TipoResgate.PRODUTO), "Deve retornar mensagem de erro para valor zero");

        // Resgate com caixa inexistente
        assertEquals("Caixa de bonus inexistente", mediator.resgatar(0, 100, TipoResgate.PRODUTO), "Deve retornar mensagem de erro para caixa inexistente");

        // Resgate com valor maior do que o saldo
        assertEquals("Saldo insuficiente", mediator.resgatar(numeroCaixaDeBonus, 2000, TipoResgate.PRODUTO), "Deve retornar mensagem de erro para saldo insuficiente");
    }


}
