package Testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Psquiza.ControllerObjetivo;

class TestControllerObjetivo {

	private ControllerObjetivo controleObjetivo;


	@BeforeEach
	public void novoControle() {
	controleObjetivo = new ControllerObjetivo();
	controleObjetivo.cadastraObjetivo("GERAL", "Provar que a terra eh plana utilizando uma regua", 5, 1);
	}


	@Test
	public void testObjCadastrado() {
		assertEquals(false,controleObjetivo.objetivoJaCadastrado("O2"));
		
	}
	@Test
	public void testCadastraObjetivo() {
	assertEquals("O2",controleObjetivo.cadastraObjetivo("GERAL", "Provar que a terra nao eh plana utilizando um globo", 5, 5));


	//Campos de pesquisa nulos ou vazios
	try {
	controleObjetivo.cadastraObjetivo(null, "Testar a inteligencia de animais marinhos usando oleo", 3, 2);
	}
	catch(IllegalArgumentException m) {

	}
	try {
	controleObjetivo.cadastraObjetivo("GERAL", null, 3, 2);
	}
	catch(IllegalArgumentException m) {

	}
	try {
	controleObjetivo.cadastraObjetivo(" ", "Testar a inteligencia de animais marinhos usando oleo", 3, 2);
	}
	catch(IllegalArgumentException m) {

	}
	try {
	controleObjetivo.cadastraObjetivo("GERAL"," ", 3, 2);
	}
	catch(IllegalArgumentException m) {
	}
	//Valores invalidos
	try {
	controleObjetivo.cadastraObjetivo("GERAL", "Testar a inteligencia de animais marinhos usando oleo", 10, 2);
	}
	catch(IllegalArgumentException m) {
	}
	try {
	controleObjetivo.cadastraObjetivo("GERAL", "Testar a inteligencia de animais marinhos usando oleo", 3, 0);
	}
	catch(IllegalArgumentException m) {
	}
	//tipo invalido
	try {
	controleObjetivo.cadastraObjetivo("MINSTROLOUCO", "Testar a inteligencia de animais marinhos usando oleo", 10, 2);
	}
	catch(IllegalArgumentException m) {
	}
	
	}
	@Test
	public void testExibeObj() {
		controleObjetivo.cadastraObjetivo("GERAL", "Obsevar a sexualidade alheia via tuiiiter", 1, 2);
		assertEquals("O3",controleObjetivo.cadastraObjetivo("GERAL", "Obsevar a sexualidade alheia via tuiiiter", 1, 2));
		assertEquals("O3 - GERAL - Obsevar a sexualidade alheia via tuiiiter - 3",controleObjetivo.exibeObjetivo("O3"));
	}
}

	
