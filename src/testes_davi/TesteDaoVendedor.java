package testes_davi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.gov.cesarschool.poo.bonusvendas.dao.VendedorDAO;
import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Endereco;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Sexo;
import java.time.LocalDate;


class TesteDaoVendedor {

    private VendedorDAO vendedorDAO;

    @BeforeEach
    public void setUp() {
        vendedorDAO = new VendedorDAO();
    }

    @Test
    public void testIncluirVendedor() {
        Vendedor vendedor = new Vendedor("12345678900", "João da Silva", Sexo.MASCULINO, LocalDate.of(1985, 5, 15), 5000.0, new Endereco("Rua A", 123, "Apto 1", "12345-678", "Cidade", "Estado", "País"));
        
        assertTrue(vendedorDAO.incluir(vendedor));
    }

    @Test
    public void testBuscarVendedor() {
        Vendedor vendedor = new Vendedor("12345678900", "João da Silva", Sexo.MASCULINO, LocalDate.of(1985, 5, 15), 5000.0, new Endereco("Rua A", 123, "Apto 1", "12345-678", "Cidade", "Estado", "País"));
        vendedorDAO.incluir(vendedor);

        Vendedor vendedorEncontrado = vendedorDAO.buscar("12345678900");
        assertNotNull(vendedorEncontrado);
        assertEquals("12345678900", vendedorEncontrado.getCpf());
        assertEquals("João da Silva", vendedorEncontrado.getNomeCompleto());
        // Continue verificando outras propriedades do vendedor, se necessário.
    }

    @Test
    public void testAlterarVendedor() {
        Vendedor vendedor = new Vendedor("12345678900", "João da Silva", Sexo.MASCULINO, LocalDate.of(1985, 5, 15), 5000.0, new Endereco("Rua A", 123, "Apto 1", "12345-678", "Cidade", "Estado", "País"));
        vendedorDAO.incluir(vendedor);

        Vendedor vendedorModificado = new Vendedor("12345678900", "João Novo", Sexo.FEMININO, LocalDate.of(1990, 3, 20), 6000.0, new Endereco("Rua B", 456, "Apto 2", "54321-876", "Outra Cidade", "Outro Estado", "Outro País"));
        
        assertTrue(vendedorDAO.alterar(vendedorModificado));

        Vendedor vendedorEncontrado = vendedorDAO.buscar("12345678900");
        assertNotNull(vendedorEncontrado);
        assertEquals("12345678900", vendedorEncontrado.getCpf());
        assertEquals("João Novo", vendedorEncontrado.getNomeCompleto());
        assertEquals(Sexo.FEMININO, vendedorEncontrado.getSexo());
        // Continue verificando outras propriedades do vendedor, se necessário.
    }

    @Test
    public void testBuscarTodosVendedores() {
        // Crie e inclua alguns vendedores no DAO.

        Vendedor vendedor1 = new Vendedor("12345678900", "Vendedor 1", Sexo.MASCULINO, LocalDate.of(1990, 1, 1), 3000.0, new Endereco("Rua A", 123, "Apto 1", "12345-678", "Cidade", "Estado", "País"));
        Vendedor vendedor2 = new Vendedor("98765432100", "Vendedora 2", Sexo.FEMININO, LocalDate.of(1995, 2, 2), 4000.0, new Endereco("Rua B", 456, "Apto 2", "54321-876", "Outra Cidade", "Outro Estado", "Outro País"));
        vendedorDAO.incluir(vendedor1);
        vendedorDAO.incluir(vendedor2);

        Vendedor[] vendedores = vendedorDAO.buscarTodos(null);//analisar isso
        assertEquals(2, vendedores.length);
    }
}
