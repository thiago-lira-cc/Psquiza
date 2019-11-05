package Psquiza;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
		contCodigo++;
		return "P"+contCodigo;
	}
	
	public boolean problemaJaCadastrado(String codigo) {
		return problemas.containsKey(codigo);
	}
	
	public Problema pegaProblema(String codigo) {
		return problemas.get(codigo);
	}

	public String cadastraProblema(String descricao, int viabilidade) {
		excecoes.verificaString(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		excecoes.verificaValor(viabilidade, "Valor invalido de viabilidade.");
		
		String codigo = geraCodigo();
		Problema p = new Problema(codigo, descricao, viabilidade);
		if (problemaJaCadastrado(codigo)==false) {
			this.problemas.put(codigo, p);
			return codigo;
		} else {
			throw new IllegalArgumentException("Problema ja existe");
		}
	}

	public String exibeProblema(String codigo) {
		excecoes.verificaString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		if (problemaJaCadastrado(codigo)) {
			return pegaProblema(codigo).toString();
		} 
		throw new IllegalArgumentException("Problema nao encontrado");
	}

	public void apagarProblema(String codigo) {
		excecoes.verificaString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		if (problemaJaCadastrado(codigo)) {
			problemas.remove(codigo);
		} else {
			throw new IllegalArgumentException("Problema nao encontrado");
		}
		
	}

	public Map<String, Problema> getProblemas() {
		return this.problemas;
	}

	public List<Busca> buscaProblema(String termo) {
		List<Busca> resultados = new ArrayList<Busca>();
		for (Problema problema : problemas.values()) {
			if (problema.getDescricao().toLowerCase().contains(termo.toLowerCase())) {
				Busca busca = new Busca(problema.getCodigo(), problema.getDescricao());
				resultados.add(busca);
			}
		}
		Collections.sort(resultados);
		return resultados;
	}

}
