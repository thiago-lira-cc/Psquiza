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
	
	@Test void cadastraProblema(){
		
	// teste Cadastra Descricao Vazia
		try {
			controleProblema.cadastraProblema("", 3);
			fail();
		} catch (IllegalArgumentException m) {
			
		}
	
	
	// teste Cadastra Viabilidade Invalida
		try {
			controleProblema.cadastraProblema("Fome em cuba", -1);
			fail();
		} catch (IllegalArgumentException m ) {
			
		}
	}

}