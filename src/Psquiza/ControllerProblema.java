package Psquiza;

import java.util.HashMap;
import java.util.Map;

public class ControllerProblema {
	private Map<String, Problema> problemas;
	private Excecoes excecoes;
	private int contCodigo;
	
	public ControllerProblema() {
		this.problemas = new HashMap<>();
		this.excecoes = new Excecoes();
		this.contCodigo = 0;
	}
	
	public String geraCodigo() {
		int valor = contCodigo+1;
		return "P"+valor;
	}
	
	public boolean problemaJaCadastrado(String codigo) {
		return problemas.containsKey(codigo);
	}
	
	public Problema pegaProblema(String codigo) {
		return problemas.get(codigo);
	}

	public String cadastraProblema(String descricao, int viabilidade) {
		excecoes.verificaString(descricao, "");
		excecoes.verificaValor(viabilidade, "");
		String codigo = geraCodigo();
		Problema p = new Problema(codigo, descricao, viabilidade);
		if (problemaJaCadastrado(codigo)) {
			this.problemas.put(codigo, p);
			return codigo;
		} else {
			throw new IllegalArgumentException("");
		}
	}

	public String exibeProblema(String codigo) {
		if (problemaJaCadastrado(codigo)) {
			return pegaProblema(codigo).toString();
		} 
		throw new IllegalArgumentException("");
	}

	public void apagarProblema(String codigo) {
		if (problemaJaCadastrado(codigo)) {
			problemas.remove(codigo);
		}
		throw new IllegalArgumentException("");
	}

}
