package Testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Psquiza.Busca;
import Psquiza.ControllerPsquiza;

class TesteBusca {

	private ControllerPsquiza controlePsquiza = new ControllerPsquiza();
	
	@BeforeEach
	public void novasEntidades() {
		//controlePsquiza = ;
		controlePsquiza.cadastraPesquisa("Um estudo sobre o uso do twitter para pronunciamentos presidencias.", "twitter,presidente");
		controlePsquiza.cadastraPesquisador("Merli Bergeron", "professor", "Interessado na importancia do pensamento critico na sociedade.", "sulley@2001", "https://gatinho");
		controlePsquiza.cadastraProblema("A problematica envolvida nas declaracoes do atual presidente", 5);
		controlePsquiza.cadastraObjetivo("GERAL", "Mostrar como um discurso pode validar determinados atos na sociedade.", 3, 5);
		controlePsquiza.cadastraAtividade("Monitoramento do uso do twitter por parte de figuras do governo.", "BAIXO", "Por se tratar de apenas um monitoramento, o risco nao e elevado.");
	}
	@Test
	public void testeBuscaTermo() {
		assertEquals("TWI1: Um estudo sobre o uso do twitter para pronunciamentos presidencias. | A1: Monitoramento do uso do twitter por parte de figuras do governo.",controlePsquiza.busca("uso"));
		assertEquals("sulley@2001: Interessado na importancia do pensamento critico na sociedade. | O1: Mostrar como um discurso pode validar determinados atos na sociedade.",controlePsquiza.busca("sociedade"));
		assertEquals("",controlePsquiza.busca("birolirolies"));
		
		//teste campo de termo nulo ou vazio
		try{
			controlePsquiza.busca(null);
		}
		catch(IllegalArgumentException m) {	
			
		}
		
		try {
			controlePsquiza.busca(" ");		
		}
		catch(IllegalArgumentException m) {
			
		}
	}
	@Test
	public void testeBuscaEspcifica() {
		assertEquals("TWI1: Um estudo sobre o uso do twitter para pronunciamentos presidencias.",controlePsquiza.busca("twitter", 1));
		//teste campo termo nulo ou vazio 
		try {
			controlePsquiza.busca(null, 1);
		}
		catch(IllegalArgumentException m) {
			
		}
		
		try {
			controlePsquiza.busca("", 1);
		}
		catch(IllegalArgumentException m) {
			
		}
		//resulado negativo
		try {
			controlePsquiza.busca("twiter", -2);
		}
		catch(IllegalArgumentException m) {
			
		}
		//entidade nao encontrada
		try {
			controlePsquiza.busca("twitter", 50);
		}
		catch(IllegalArgumentException m) {
			
		}
		
	}
	@Test
	public void testeContaResult() {
		assertEquals(3,controlePsquiza.contaResultadosBusca("twitter"));
		//teste campo termo nulo ou vazio 
		try {
			controlePsquiza.contaResultadosBusca(null);
		}
		catch(IllegalArgumentException m) {
			
		}
		try {
			controlePsquiza.contaResultadosBusca(" ");
		}
		catch(IllegalArgumentException m) {
			
		}
		//teste resultado nao encontrado
		try {
			controlePsquiza.contaResultadosBusca("xuxu");
		}
		catch(IllegalArgumentException m) {
			
		}
	}
}
	
