package Psquiza;

import java.util.HashMap;
import java.util.Map;

public class ControllerObjetivo {
	private Map<String, Objetivo> objetivos;
	private Excecoes excecoes;
	
	public ControllerObjetivo() {
		this.objetivos = new HashMap<>();
		this.excecoes = new Excecoes();
	}

	public String cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
		// TODO Auto-generated method stub
		return null;
	}

	public String exibeObjetivo(String codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	public void apagarObjetivo(String codigo) {
		
		
	}

}
