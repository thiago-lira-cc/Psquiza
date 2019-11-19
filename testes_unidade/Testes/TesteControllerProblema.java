package Testes;

import static org.junit.Assert.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Psquiza.ControllerProblema;

class TesteControllerProblema {

	private ControllerProblema controleProblema;
	
	
	@BeforeEach
	public void novoControllerProblema() {
		controleProblema = new ControllerProblema();
		controleProblema.cadastraProblema("Freezer desligado", 5);
		controleProblema.cadastraProblema("Fome em cuba", 3);
	}
	
	
	@Test
	public void testCadastraDescricaoVazia() {
		try {
			controleProblema.cadastraProblema("", 3);
			fail();
		} catch (IllegalArgumentException m) {
			
		}
	}
	
	@Test
	public void testCadastraViabilidadeInvalida() {
		try {
			controleProblema.cadastraProblema("Fome em cuba", -1);
			fail();
		} catch (IllegalArgumentException m ) {
			
		}
	}

}