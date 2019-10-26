package Psquiza;

import java.util.HashMap;
import java.util.Map;

public class ControllerPesquisador {
	
	private Excessoes excessoes = new Excessoes();
	private Map<String, Pesquisador> pesquisadores;
	
	public ControllerPesquisador() {
		this.pesquisadores = new HashMap<String, Pesquisador>();
	}

	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String foto) {
		excessoes.verificaStringPesquisador(nome, "Campo nome nao pode ser nulo ou vazio.");
		excessoes.verificaStringPesquisador(funcao, "Campo funcao nao pode ser nulo ou vazio.");
		excessoes.verificaStringPesquisador(biografia, "Campo biografia nao pode ser nulo ou vazio.");
		excessoes.verificaStringPesquisador(email, "Campo email nao pode ser nulo ou vazio.");
		excessoes.verificaStringPesquisador(foto, "Campo fotoURL nao pode ser nulo ou vazio.");
		
		excessoes.verificaEmail(email, "Formato de email invalido.");
		excessoes.verificaURL(foto, "Formato de foto invalido.");
		
		if (!pesquisadores.containsKey(email)) {
			Pesquisador pesquisador = new Pesquisador(nome, funcao, biografia, email, foto);
			this.pesquisadores.put(email, pesquisador);
		}
		
	}

	public void desativaPesquisador(String email) {
		excessoes.verificaStringPesquisador(email, "Formato de email invalido.");
		excessoes.verificaEmail(email, "Formato de email invalido.");
	}

}
