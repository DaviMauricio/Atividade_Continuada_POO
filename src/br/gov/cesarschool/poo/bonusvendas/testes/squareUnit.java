package br.gov.cesarschool.poo.bonusvendas.testes;

import static org.junit.Assert.*;
import org.junit.Test;

import br.gov.cesarschool.poo.bonusvendas.entidade.geral.UnitTesting;

public class squareUnit {

	@Test
	public void test() {
		UnitTesting obj1 = new UnitTesting();
		int output_f = obj1.square(4);
		assertEquals(16,output_f);
	}

}
