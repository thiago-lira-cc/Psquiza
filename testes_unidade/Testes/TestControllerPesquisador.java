package Testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Psquiza.ControllerPesquisador;

class TestControllerPesquisador {
	
	private ControllerPesquisador controlePesquisador;
	
	@BeforeEach
	public void novoControllerPesquisador() {
		controlePesquisador = new ControllerPesquisador();
		controlePesquisador.cadastraPesquisador("Macaco Louco","externo", "Interessado nos efeitos do elemento X nos seres vivos","crazy.monkey@2000", "http://loko");
	}
	
	//cadastro pesquisador
	@Test
	public void testCadastraNomeVazio() {
		try {
			controlePesquisador.cadastraPesquisador(" ", "professor", "Interessado nos efeitos da cafeina no coracao.", "tatata@1973", "http://tesouro");
			assertFalse(false);
		}
		catch(IllegalArgumentException m) {
			
		}
	}
	@Test
	public void testCadastraFuncaoVazia() {
		try {
			controlePesquisador.cadastraPesquisador("Girafales", "", "Interessado nos efeitos da cafeina no coracao.", "tatata@1973", "http://tesouro");
			assertFalse(false);
		}
		catch(IllegalArgumentException m) {
			
		}
	}
	@Test
	public void testCadastraBioVazia() {
		try {
			controlePesquisador.cadastraPesquisador("Girafales", "professor", "  ", "tatata@1973", "http://tesouro");
			assertFalse(false);
		}
		catch(IllegalArgumentException m) {
			
		}
	}
	
	@Test
	public void testCadastraEmailVazio() {
		try {
			controlePesquisador.cadastraPesquisador("Girafales", "professor", "Interessado nos efeitos da cafeina no coracao.", "  ", "http://tesouro");
			assertFalse(false);
		}
		catch(IllegalArgumentException m) {
			
		}
	}
	
	@Test
	public void testCadastraFtVazia() {
		try {
			controlePesquisador.cadastraPesquisador("Girafales", "professor", "Interessado nos efeitos da cafeina no coracao.", "tatata@1973", "");
			assertFalse(false);
		}
		catch(IllegalArgumentException m) {
			
		}
	}
	@Test 
	public void cadastraEmailInvalido() {
		try {
			controlePesquisador.cadastraPesquisador("Girafales", "professor", "Interessado nos efeitos da cafeina no coracao.", "tatata@mail.com", "http;//tesouro");
			assertFalse(false);
		}
		catch(IllegalArgumentException m) {
			
		}
	}
	@Test 
	public void cadastraFtInvalida() {
		try {
			controlePesquisador.cadastraPesquisador("Girafales", "profesor", "Interessado nos efeitos da cafeina no coracao.", "tatata@1973", "http/linguica");
			assertFalse(false);
		}
		catch(IllegalArgumentException m) {
			
		}
	}
	//exibicao pesquisador
	
	@Test
	public void testaExibePesquisador() {
		assertEquals("Macaco Louco (externo) - Interessado nos efeitos do elemento X nos seres vivos - crazy.monkey@2000 - http://loko", controlePesquisador.exibePesquisador("crazy.monkey@2000"));
	}
	
	// status pesquisador 
	@Test
	public void testaPesquisadorEhAtivado() {
		assertEquals(true, controlePesquisador.pesquisadorEhAtivado("crazy.monkey@2000"));
		// desativar pesquisa p testar
	}
}

	