package testes_davi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.gov.cesarschool.poo.bonusvendas.dao.LancamentoBonusDAO;
import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonus;

import java.time.LocalDateTime;



public class TesteDaoLancaBonus {

	 private LancamentoBonus lancamento;
	    private LancamentoBonusDAO lancamentoDAO;

	    private static final String BRANCO = ""; // Define BRANCO como uma constante.

	    @BeforeEach
	    public void setUp() {
	        lancamento = new LancamentoBonus(1, 100.0, LocalDateTime.now()); // Cria uma nova instância de LancamentoBonus para cada teste.
	        lancamentoDAO = new LancamentoBonusDAO();
	    }

	    @Test
	    public void testIncluir() {
	        // Verifica se o lançamento não é nulo.
	        assertNotNull(lancamento);

	        // Tenta incluir o lançamento no banco de dados.
	        assertTrue(lancamentoDAO.incluir(lancamento));

	        // Tenta incluir o mesmo lançamento novamente (deve retornar false).
	        assertFalse(lancamentoDAO.incluir(lancamento));

	        // Tenta buscar o lançamento incluído.
	        LancamentoBonus lancamentoBuscado = lancamentoDAO.buscar(BRANCO + lancamento.getNumeroCaixaDeBonus());
	        assertNotNull(lancamentoBuscado);
	        assertEquals(lancamento.getNumeroCaixaDeBonus(), lancamentoBuscado.getNumeroCaixaDeBonus());
	    }

	    @Test
	    public void testAlterar() {
	        // Verifica se o lançamento não é nulo.
	        assertNotNull(lancamento);

	        // Tenta incluir o lançamento no banco de dados.
	        assertTrue(lancamentoDAO.incluir(lancamento));

	        // Cria um novo lançamento com um valor alterado.
	        LancamentoBonus lancamentoAlterado = new LancamentoBonus(lancamento.getNumeroCaixaDeBonus(), 200.0, LocalDateTime.now());

	        // Tenta alterar o lançamento no banco de dados.
	        assertTrue(lancamentoDAO.alterar(lancamentoAlterado));

	        // Tenta buscar o lançamento alterado.
	        LancamentoBonus lancamentoBuscado = lancamentoDAO.buscar(BRANCO + lancamento.getNumeroCaixaDeBonus());
	        assertNotNull(lancamentoBuscado);
	        assertEquals(200.0, lancamentoBuscado.getValor(), 0.001);
	    }

	    @Test
	    public void testExcluir() {
	        // Verifica se o lançamento não é nulo.
	        assertNotNull(lancamento);

	        // Tenta incluir o lançamento no banco de dados.
	        assertTrue(lancamentoDAO.incluir(lancamento));

	        // Tenta excluir o lançamento do banco de dados.
	        assertTrue(lancamentoDAO.excluir(BRANCO + lancamento.getNumeroCaixaDeBonus()));

	        // Tenta buscar o lançamento excluído (deve retornar nulo).
	        assertNull(lancamentoDAO.buscar(BRANCO + lancamento.getNumeroCaixaDeBonus()));
	    }

}
