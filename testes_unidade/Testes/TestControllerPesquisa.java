package Testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Psquiza.ControllerPesquisa;

class TestControllerPesquisa {

private ControllerPesquisa controlePesquisa;
	
	@BeforeEach
	public void novoControllerPesquisador() {
		controlePesquisa = new ControllerPesquisa();
		controlePesquisa.cadastraPesquisa("Pesquisa gastronomica.", "PCC - Primeira Comida Cozinhada");
	}

	@Test
	public void testCadastraPesquisa() {
		assertEquals("PCC2", controlePesquisa.cadastraPesquisa("Pesquisa em fisica e computacao", "PCC - Particulas Computaveis Confusas"));
		/**
		 * Teste com descricao vazia
		 */
		try {
			controlePesquisa.cadastraPesquisa("", "TalTalZeZe");
			assertFalse(false);
		}
		catch(IllegalArgumentException m) {
			
		}
		/**
		 * Teste com interesse vazio
		 */
		try {
			controlePesquisa.cadastraPesquisa("CV", "");
			assertFalse(false);
		}
		catch(IllegalArgumentException m) {
			
		}
		/**
		 * Teste com descricao nula
		 */
		try {
			controlePesquisa.cadastraPesquisa(null, "TalTalZeZe");
			assertFalse(false);
		}
		catch(IllegalArgumentException m) {
			
		}
		/**
		 * teste com interesse nulo
		 */
		try {
			controlePesquisa.cadastraPesquisa("BlaBlaBla", null);
			assertFalse(false);
		}
		catch(IllegalArgumentException m) {
			
		}
	}
	
	@Test
	public void testAlteraPesquisa() {
		try {
			controlePesquisa.alteraPesquisa("PCC3", "jfnsdf", "hghf");
			assertFalse(false);
		}
		catch(IllegalArgumentException m) {
			
		}
		
		try {
			controlePesquisa.alteraPesquisa("PCC1", "GBFBF", "hghf");
			assertFalse(false);
		}
		catch(IllegalArgumentException m) {
			
		}
		
		try {
			controlePesquisa.alteraPesquisa("PCC1", "DESCRICAO", null);
			assertFalse(false);
		}
		catch(IllegalArgumentException m) {
			
		}
		
		assertEquals("PCC1 - Pesquisa gastronomica. - PCC - Primeira Comida Cozinhada", controlePesquisa.exibePesquisa("PCC1"));
		controlePesquisa.alteraPesquisa("PCC1", "DESCRICAO", "Aquele esquema q c sabe");
		assertEquals("PCC1 - Aquele esquema q c sabe - PCC - Primeira Comida Cozinhada", controlePesquisa.exibePesquisa("PCC1"));
		
		assertEquals("PCC1 - Aquele esquema q c sabe - PCC - Primeira Comida Cozinhada", controlePesquisa.exibePesquisa("PCC1"));
		controlePesquisa.alteraPesquisa("PCC1", "CAMPO", "AES - Aquele esquema q c sabe");
		assertEquals("PCC1 - Aquele esquema q c sabe - AES - Aquele esquema q c sabe", controlePesquisa.exibePesquisa("PCC1"));
		
	}
	
	@Test
	public void testExibePesquisa() {
		assertEquals("PCC1 - Pesquisa gastronomica. - PCC - Primeira Comida Cozinhada", controlePesquisa.exibePesquisa("PCC1"));
	
		try {
			controlePesquisa.exibePesquisa("");
			assertFalse(false);
		}
		catch(IllegalArgumentException m) {
			
		}
		
		try {
			controlePesquisa.exibePesquisa(null);
			assertFalse(false);
		}
		catch(IllegalArgumentException m) {
			
		}
		
		try {
			controlePesquisa.exibePesquisa("tal3");
			assertFalse(false);
		}
		catch(IllegalArgumentException m) {
			
		}
	}
	
	@Test
	public void testPesquisaEhAtiva() {
		assertEquals(true, controlePesquisa.pesquisaEhAtiva("PCC1"));
		controlePesquisa.encerraPesquisa("PCC1", "A casa caiu");
		assertEquals(false, controlePesquisa.pesquisaEhAtiva("PCC1"));
		
		try {
			controlePesquisa.exibePesquisa("");
			assertFalse(false);
		}
		catch(IllegalArgumentException m) {
			
		}
		
		try {
			controlePesquisa.exibePesquisa(null);
			assertFalse(false);
		}
		catch(IllegalArgumentException m) {
			
		}
		
		try {
			controlePesquisa.exibePesquisa("AES1");
			assertFalse(false);
		}
		catch(IllegalArgumentException m) {
			
		}
	}
	
	@Test
	public void testEncerraPesquisa() {
		assertEquals(true, controlePesquisa.pesquisaEhAtiva("PCC1"));
		controlePesquisa.encerraPesquisa("PCC1", "PF - Patrulha das Férias - caiu em cima");
		assertEquals(false, controlePesquisa.pesquisaEhAtiva("PCC1"));
		
		try {
			controlePesquisa.encerraPesquisa("", "sem tempo irmao");
			assertFalse(false);
		}
		catch(IllegalArgumentException m) {
			
		}
		
		try {
			controlePesquisa.encerraPesquisa(null, "sem tempo irmao");
			assertFalse(false);
		}
		catch(IllegalArgumentException m) {
			
		}
		
		try {
			controlePesquisa.encerraPesquisa("PCC1", "");
			assertFalse(false);
		}
		catch(IllegalArgumentException m) {
			
		}
		
		try {
			controlePesquisa.encerraPesquisa("PCC1", null);
			assertFalse(false);
		}
		catch(IllegalArgumentException m) {
			
		}
	}
	
	@Test
	public void testAtivaPesquisa() {
		try {
			controlePesquisa.ativaPesquisa("PCC1");
			assertFalse(false);
		}
		catch(IllegalArgumentException m) {
			
		}
		
		controlePesquisa.encerraPesquisa("PCC1", "De novo isso?");
		assertEquals(false, controlePesquisa.pesquisaEhAtiva("PCC1"));
		controlePesquisa.ativaPesquisa("PCC1");
		assertEquals(true, controlePesquisa.pesquisaEhAtiva("PCC1"));
		
	}
}
