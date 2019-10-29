package Psquiza;

import java.util.HashMap;
import java.util.Map;

public class ControllerPesquisa {

	private Excecoes excecoes = new Excecoes();
	/**
	 * Mapa de pesquisadores identificados pelo codigo
	 */
	private Map<String, Pesquisa> pesquisas;
	/**
	 * Constroi a classe instanciando um novo HashMap
	 */
	public ControllerPesquisa() {
		this.pesquisas = new HashMap<String, Pesquisa>();
	}
	
	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		excecoes.verificaString(descricao, "Descricao nao pode ser nula ou vazia.");
		String codigo = campoDeInteresse.substring(0, 2).toUpperCase() + "1";
		int cont = 1;
		while(pesquisas.containsKey(codigo)) {
			cont +=1;
			codigo.substring(-1);
			codigo += cont;
		}
		Pesquisa p = new Pesquisa(descricao, campoDeInteresse,codigo);
		pesquisas.put(codigo, p);
		return codigo;
	}
	
	public void alteraPesquisa(String codigo, String conteudo, String novoConteudo) {
		if(pesquisas.containsKey(codigo)) {
			switch(conteudo) {
			
			case "DESCRICAO" :
				excecoes.verificaString(conteudo, "n pd ser vazio");
				pesquisas.get(codigo).setDescricao(novoConteudo);
				break;
			
			case "CAMPO":
				excecoes.verificaString(conteudo, "n pd ser vazio xuxu");
				pesquisas.get(codigo).setCampoDeInteresse(novoConteudo);
				break;
			
			default:
				throw new IllegalArgumentException("Nao e possivel alterar esse valor de pesquisa.");
			}
		}
	}
	
	public void encerraPesquisa(String codigo, String motivo) {
		if(pesquisas.containsKey(codigo)) {
			if(pesquisas.get(codigo).isAtivado() == true) {
				pesquisas.get(codigo).setAtivado(false);
			}else{
				throw new IllegalArgumentException("A pesquisa ja foi encerrada");
			}
		}else{
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
	}
	
	public void ativaPesquisa(String codigo) {
		if(pesquisas.containsKey(codigo)) {
			if(pesquisas.get(codigo).isAtivado() == false) {
				pesquisas.get(codigo).setAtivado(true);
			}else{
				throw new IllegalArgumentException("Pesquisa ja ativada.");
			}			
		}else {
			throw new IllegalArgumentException("Pesquisa nao encontrada."); 
		}
		
	}
	
	public String exibePesquisa(String codigo) {
		if(pesquisas.containsKey(codigo)) {
			return pesquisas.get(codigo).toString();
		}
		throw new IllegalArgumentException("pesquisa nao encontrada.");
	}
	
	public boolean pesquisaEhAtiva(String codigo) {
		if(pesquisas.containsKey(codigo)) {
			return pesquisas.get(codigo).isAtivado();
		}
		throw new IllegalArgumentException("Pesquisa nao encontrada");
	}
}
