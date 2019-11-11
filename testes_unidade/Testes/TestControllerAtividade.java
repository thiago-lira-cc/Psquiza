package Testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Psquiza.ControllerAtividade;

class TestControllerAtividade {

	private ControllerAtividade controleAtividade;
	
	@BeforeEach
	public void novoControllerAtividade() {
		controleAtividade = new ControllerAtividade();
		controleAtividade.cadastraAtividade("CPP - Cozinhar com Panela de Pressao", "MEDIO", "A panela pode explodir");
		controleAtividade.cadastraAtividade("Salvar Fiona", "ALTO", "Um dragao guarda a torre");
		controleAtividade.cadastraItem("A3", "Encontrar o Burro");
	}
	
	@Test
	public void testCadastraAtividade() {
		assertEquals("A2", controleAtividade.cadastraAtividade("Roubar a formula do hamburguer de siri", "ALTO", "Seguranca do Siri Cascudo"));
		/**
		 * Teste com descricao vazia
		 */
		try {
			controleAtividade.cadastraAtividade("", "BAIXO", "lALALA");
			assertFalse(false);
		} catch (IllegalArgumentException m) {
			
		}
		/**
		 * Teste com nivel do risco vazio
		 */
		try {
			controleAtividade.cadastraAtividade("Cantarolar", "", "Que bonita sua roupa");
			assertFalse(false);
		} catch (IllegalArgumentException m) {
			
		}
		/**
		 * Teste com descricao do risco vazia
		 */
		try {
			controleAtividade.cadastraAtividade("Cantarolar", "BAIXO", "");
			assertFalse(false);
		} catch (IllegalArgumentException m) {
			
		}
		/**
		 * Teste com descricao nula
		 */
		try {
			controleAtividade.cadastraAtividade(null, "BAIXO", "LALALA");
			assertFalse(false);
		} catch (IllegalArgumentException m) {
			
		}
		/**
		 * Teste com nivel do risco nulo
		 */
		try {
			controleAtividade.cadastraAtividade("Cantarolar", null, "Que bonita sua roupa");
			assertFalse(false);
		} catch (IllegalArgumentException m) {
			
		}
		/**
		 * Teste com descricao do risco nula
		 */
		try {
			controleAtividade.cadastraAtividade("Cantarolar", "BAIXO", null);
			assertFalse(false);
		} catch (IllegalArgumentException m) {
			
		}
	}
	
	@Test
	public void testApagaAtividade() {
		/**
		 * Teste com codigo vazio
		 */
		try {
			controleAtividade.apagaAtividade("");
			assertFalse(false);
		} catch (IllegalArgumentException m) {
			
		}
		/**
		 * Teste com codigo nulo
		 */
		try {
			controleAtividade.apagaAtividade(null);
			assertFalse(false);
		} catch (IllegalArgumentException m) {
			
		}
		/**
		 * Teste com atividade inexistente
		 */
		try {
			controleAtividade.apagaAtividade("A20");
			assertFalse(false);
		} catch (IllegalArgumentException m) {
			
		}
	}
	
	@Test
	public void testCadastraItem() {
		/**
		 * Teste com codigo vazio
		 */
		try {
			controleAtividade.cadastraItem("", "I1");
			assertFalse(false);
		} catch (IllegalArgumentException m) {
			
		}
		/**
		 * Teste com item vazio
		 */
		try {
			controleAtividade.cadastraItem("A2", "");
			assertFalse(false);
		} catch (IllegalArgumentException m) {
			
		}
		/**
		 * Teste com codigo nulo
		 */
		try {
			controleAtividade.cadastraItem(null, "I1");
			assertFalse(false);
		} catch (IllegalArgumentException m) {
			
		}
		/**
		 * Teste com item nulo
		 */
		try {
			controleAtividade.cadastraItem("A2", null);
			assertFalse(false);
		} catch (IllegalArgumentException m) {
			
		}
		/**
		 * Teste com codigo inexistente
		 */
		try {
			controleAtividade.cadastraItem("A50", "I2");
			assertFalse(false);
		} catch (IllegalArgumentException m) {
			
		}
	}
	
	@Test
	public void testExibeAtividade() {
		assertEquals("Salvar Fiona (ALTO - Um dragao guarda a torre) | PENDENTE - Encontrar o Burro", controleAtividade.exibeAtividade("A3"));
		/**
		 * Teste com codigo vazio
		 */
		try {
			controleAtividade.exibeAtividade("");
			assertFalse(false);
		} catch (IllegalArgumentException m) {
			
		}
		/**
		 * Teste com codigo nulo
		 */
		try {
			controleAtividade.exibeAtividade(null);
			assertFalse(false);
		} catch (IllegalArgumentException m) {
			
		}
		/**
		 * Teste com codigo inexistente
		 */
		try {
			controleAtividade.exibeAtividade("A70");
			assertFalse(false);
		} catch (IllegalArgumentException m) {
			
		}
	}
	
	@Test
	public void testContaItensPendentes() {
		assertEquals(1, controleAtividade.contaItensPendentes("A3"));
		/**
		 * Teste com codigo vazio
		 */
		try {
			controleAtividade.contaItensPendentes("");
			assertFalse(false);
		} catch (IllegalArgumentException m) {
			
		}
		/**
		 * Teste com codigo nulo
		 */
		try {
			controleAtividade.contaItensPendentes(null);
			assertFalse(false);
		} catch (IllegalArgumentException m) {
			
		}
		/**
		 * Teste com codigo inexistente
		 */
		try {
			controleAtividade.contaItensPendentes("A81");
			assertFalse(false);
		} catch (IllegalArgumentException m) {
			
		}
	}
	
	@Test
	public void testContaItensRealizados() {
		assertEquals(0, controleAtividade.contaItensRealizados("A3"));
		/**
		 * Teste com codigo vazio
		 */
		try {
			controleAtividade.contaItensRealizados("");
			assertFalse(false);
		} catch (IllegalArgumentException m) {
			
		}
		/**
		 * Teste com codigo nulo
		 */
		try {
			controleAtividade.contaItensRealizados(null);
			assertFalse(false);
		} catch (IllegalArgumentException m) {
			
		}
		/**
		 * Teste com codigo inexistente
		 */
		try {
			controleAtividade.contaItensRealizados("A81");
			assertFalse(false);
		} catch (IllegalArgumentException m) {
			
		}
	}
}
