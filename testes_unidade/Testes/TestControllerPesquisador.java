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
		controlePesquisador.cadastraPesquisador("Biroliro", "externo", "Interessado na fiscalizacao da vida sexual alheia(via twitter)", "bozo@2018", "https://bolsolixo");
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
		assertEquals("Biroliro (externo) - Interessado na fiscalizacao da vida sexual alheia(via twitter) - bozo@2018 - https://bolsolixo", controlePesquisador.exibePesquisador("bozo@2018"));
	}
	
	@Test
	public void exibeEmailInvalido() {
		try {
			controlePesquisador.exibePesquisador("lala@land.com");
			assertFalse(false);
		}
		catch(IllegalArgumentException m) {
			
		}
	}
	
	@Test
	public void exibeEmailVazio() {
		try {
			controlePesquisador.exibePesquisador("");
			assertFalse(false);
		}
		catch(IllegalArgumentException m) {
			
		}
	}
	
	
	// altera pesquisador
	
	@Test
	public void alteraPesquisador() {
		assertEquals("Biroliro (externo) - Interessado na fiscalizacao da vida sexual alheia(via twitter) - bozo@2018 - https://bolsolixo", controlePesquisador.exibePesquisador("bozo@2018"));
		controlePesquisador.alteraPesquisador("bozo@2018", "FOTO", "https://elenao");
		assertEquals("Biroliro (externo) - Interessado na fiscalizacao da vida sexual alheia(via twitter) - bozo@2018 - https://elenao", controlePesquisador.exibePesquisador("bozo@2018"));
	}
	
	@Test
	public void alteraEmailVazio() {
		try {
			controlePesquisador.alteraPesquisador("","FUNCAO","professor");
			assertFalse(false);
		}
		catch(IllegalArgumentException m) {
			
		}
	}
	
	@Test
	public void alteraAtributoInvalido(){
		try {
			controlePesquisador.alteraPesquisador("bozo@2018", "BOCA", "calada");
			assertFalse(false);
		}
		catch(IllegalArgumentException m) {
			
		}
	}
	@Test
	public void alteraEmailInvalido() {
		try {
			controlePesquisador.alteraPesquisador("bozo.lixoso@elenao.com", "FUNCAO", "aluno");
			assertFalse(false);
		}
		catch(IllegalArgumentException m){
			
		}
	}
	
	@Test
	public void alteraAtributoVazio() {
		try {
			controlePesquisador.alteraPesquisador("bozo@2018", "", "aluno");
			assertFalse(false);
		}
		catch(IllegalArgumentException m) {
			
		}
	}
	@Test
	public void alteraNovoValorVazio() {
		try {
			controlePesquisador.alteraPesquisador("bozo@2018", "FUNCAO", "");
			assertFalse(false);
		}
		catch(IllegalArgumentException m) {
			
		}
	}
	
	// status pesquisador 
	@Test
	public void testaPesquisadorEhAtivado() {
		controlePesquisador.desativaPesquisador("bozo@2018");
		assertEquals(true, controlePesquisador.pesquisadorEhAtivado("crazy.monkey@2000"));
		assertEquals(false,controlePesquisador.pesquisadorEhAtivado("bozo@2018"));
	}
	
	@Test 
	public void verificaEmail() {
		try {
			controlePesquisador.pesquisadorEhAtivado("biroloro@socorrodeus.com");
			assertFalse(false);
		}
		catch (IllegalArgumentException m) {
			
		}
		
		try {
			controlePesquisador.pesquisadorEhAtivado("");
			assertFalse(false);
		}
		catch (IllegalArgumentException m){
			
		}
	}
}

	