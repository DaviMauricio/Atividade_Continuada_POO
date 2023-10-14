package br.gov.cesarschool.poo.bonusvendas.testes;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TesteMediatorsString {

	public static boolean ehNuloOuBranco(String str) {
        if (str == null) {
            return true;
        }

        String strSemEspacos = str.trim();

        return strSemEspacos.isEmpty();
    }

    @Test
    public void testEhNuloOuBrancoComStringNula() {
        String str = null;
        assertTrue(ehNuloOuBranco(str));
    }

    @Test
    public void testEhNuloOuBrancoComStringVazia() {
        String str = "";
        assertTrue(ehNuloOuBranco(str));
    }

    @Test
    public void testEhNuloOuBrancoComStringApenasEspacos() {
        String str = "   ";
        assertTrue(ehNuloOuBranco(str));
    }

    @Test
    public void testEhNuloOuBrancoComStringNaoNulaENaoVazia() {
        String str = "Texto não vazio";
        assertFalse(ehNuloOuBranco(str));
    }
}
