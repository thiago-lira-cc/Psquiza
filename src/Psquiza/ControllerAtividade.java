package Psquiza;

import java.util.HashMap;
import java.util.Map;

public class ControllerAtividade {
	private Map <String, Atividade> atividades;
	private Excecoes excecoes;
	private int contCodigo;
	
	
	public ControllerAtividade() {
		this.atividades = new HashMap<String, Atividade>();
		this.excecoes = new Excecoes();
	}
	
	
	public String geraCodigo() {
		this.contCodigo++ ;
		return "A" + contCodigo;
	}


	public void cadastraAtividade(String descricao, String nivelRisco, String descricaoRisco) {
		excecoes.verificaString(descricao, "Campo Descricao nao pode ser nulo ou vazio.");
		excecoes.verificaString(nivelRisco, "Campo nivelRisco nao pode ser nulo ou vazio.");
		excecoes.verificaString(descricaoRisco, "Campo descricaoRisco nao pode ser nulo ou vazio.");
		excecoes.verificaNivelRisco(nivelRisco, "Valor invalido do nivel do risco.");
		String codigo = geraCodigo();
		if (!atividades.containsKey(codigo)) {
			Atividade atividade = new Atividade (descricao, nivelRisco, descricaoRisco,codigo);
			atividades.put(codigo, atividade);
		}
	}


	public void apagaAtividade(String codigo) {
		excecoes.verificaString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		if (atividades.containsKey(codigo)) {
			atividades.remove(codigo);
		} else {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
	}

	public void cadastraItem(String codigo, String item) {
		excecoes.verificaString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		excecoes.verificaString(item, "Item nao pode ser nulo ou vazio.");
		if (atividades.containsKey(codigo)) {
			atividades.get(codigo).cadastraItem(item);
		} else {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
		
	}
	
	public String exibeAtividade(String codigo) {
		excecoes.verificaString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		if (atividades.containsKey(codigo)) {
			 return atividades.get(codigo).exibeAtividade();
		} else {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
		
	}


	
	
	
}
