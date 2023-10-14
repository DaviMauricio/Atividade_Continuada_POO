package testes_davi; 

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Endereco;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Sexo;
import br.gov.cesarschool.poo.bonusvendas.negocio.VendedorMediator;
import br.gov.cesarschool.poo.bonusvendas.negocio.ResultadoInclusaoVendedor;


public class TesteMediatorsVendedor {

    @Test
    public void testIncluirVendedorValido() {
        VendedorMediator mediator = VendedorMediator.getInstance();
        
        Vendedor vendedor = new Vendedor("12345678901", "Nome Completo", Sexo.MASCULINO, LocalDate.of(1980, 1, 1), 1000.0, 
            new Endereco("Rua Exemplo", 123, "Cidade Exemplo", "Estado Exemplo", "Pais Exemplo", null, null));
        
        ResultadoInclusaoVendedor resultado = mediator.incluir(vendedor);
        
        assertNotNull(resultado);
        assertEquals(0, resultado.getNumeroCaixaDeBonus());
        assertNull(resultado.getMensagemErroValidacao());
    }

    @Test
    public void testIncluirVendedorInvalido() {
        VendedorMediator mediator = VendedorMediator.getInstance();
        
        // Vendedor com CPF inválido
        Vendedor vendedor = new Vendedor("12345", "Nome Completo", Sexo.MASCULINO, LocalDate.of(2000, 1, 1), 1000.0, 
            new Endereco("Rua Exemplo", 123, "Cidade Exemplo", "Estado Exemplo", "Pais Exemplo", null, null));
        
        ResultadoInclusaoVendedor resultado = mediator.incluir(vendedor);
        
        assertNotNull(resultado);
        assertEquals(0, resultado.getNumeroCaixaDeBonus());
        assertNotNull(resultado.getMensagemErroValidacao());
        
        // Vendedor com dados em branco
        vendedor = new Vendedor("12345678901", "", null, LocalDate.of(2000, 1, 1), -1000.0, 
            new Endereco("", -123, "Cidade Exemplo", "Estado Exemplo", "Pais Exemplo", null, null));
        
        resultado = mediator.incluir(vendedor);
        
        assertNotNull(resultado);
        assertEquals(0, resultado.getNumeroCaixaDeBonus());
        assertNotNull(resultado.getMensagemErroValidacao());
    }

    @Test
    public void testAlterarVendedorValido() {
        VendedorMediator mediator = VendedorMediator.getInstance();
        
        Vendedor vendedor = new Vendedor("12345678901", "Nome Completo", Sexo.MASCULINO, LocalDate.of(1980, 1, 1), 1000.0, 
            new Endereco("Rua Exemplo", 123, "Cidade Exemplo", "Estado Exemplo", "Pais Exemplo", null, null));
        
        ResultadoInclusaoVendedor resultadoInclusao = mediator.incluir(vendedor);
        
        assertNotNull(resultadoInclusao);
        assertEquals(0, resultadoInclusao.getNumeroCaixaDeBonus());
        assertNull(resultadoInclusao.getMensagemErroValidacao());
        
        vendedor.setNomeCompleto("Novo Nome");
        String mensagemErro = mediator.alterar(vendedor);
        
        assertNull(mensagemErro);
    }

    @Test
    public void testBuscarVendedorExistente() {
        VendedorMediator mediator = VendedorMediator.getInstance();
        
        Vendedor vendedor = new Vendedor("12345678901", "Nome Completo", Sexo.MASCULINO, LocalDate.of(1980, 1, 1), 1000.0, 
            new Endereco("Rua Exemplo", 123, "Cidade Exemplo", "Estado Exemplo", "Pais Exemplo", null, null));
        
        mediator.incluir(vendedor);
        
        Vendedor vendedorBuscado = mediator.buscar("12345678901");
        
        assertNotNull(vendedorBuscado);
        assertEquals("Nome Completo", vendedorBuscado.getNomeCompleto());
    }

    @Test
    public void testBuscarVendedorNaoExistente() {
        VendedorMediator mediator = VendedorMediator.getInstance();
        
        Vendedor vendedor = mediator.buscar("11111111111");
        
        assertNull(vendedor);
    }
}

