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
	
	/**
	 * Metodo responsavel por cadastrar uma pesquisa no sistema.
	 * @param descricao
	 * @param campoDeInteresse
	 * @return
	 */
	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		excecoes.verificaString(descricao, "Descricao nao pode ser nula ou vazia.");
		excecoes.verificaString(campoDeInteresse,"Formato do campo de interesse invalido.");
		excecoes.verificaCampo(campoDeInteresse, "Formato do campo de interesse invalido.");
		String codigo = campoDeInteresse.substring(0, 3).toUpperCase() + "1";
		int cont = 1;
		while(pesquisas.containsKey(codigo)) {
			cont += 1;
			codigo = codigo.substring (0, codigo.length() - 1);
			codigo += cont;
		}
		Pesquisa p = new Pesquisa(descricao, campoDeInteresse, codigo);
		pesquisas.put(codigo, p);
		return codigo;
	}
	/**
	 * Metodo responsavel por editar uma pesquisa no sistema.
	 * @param codigo
	 * @param conteudo
	 * @param novoConteudo
	 */
	public void alteraPesquisa(String codigo, String conteudo, String novoConteudo) {
		if(pesquisas.containsKey(codigo)) {
			if(pesquisas.get(codigo).isAtivado()== true) {
				
				switch(conteudo) {
				
				case "DESCRICAO" :
					excecoes.verificaString(novoConteudo, "Descricao nao pode ser nula ou vazia.");
					pesquisas.get(codigo).setDescricao(novoConteudo);
					break;
				
				case "CAMPO":
					excecoes.verificaString(novoConteudo, "Formato do campo de interesse invalido.");
					pesquisas.get(codigo).setCampoDeInteresse(novoConteudo);
					break;
				
				default:
					throw new IllegalArgumentException("Nao e possivel alterar esse valor de pesquisa.");	
				}
			}else {
				throw new IllegalArgumentException("Pesquisa desativada.");
			}
		}else {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
	}
	
	/**
	 * Metodo responsavel por encerrar uma pesquisa.
	 * @param codigo
	 * @param motivo
	 */
	public void encerraPesquisa(String codigo, String motivo) {
		excecoes.verificaString(motivo, "Motivo nao pode ser nulo ou vazio.");
		if(pesquisas.containsKey(codigo)) {
			if(pesquisas.get(codigo).isAtivado() == true) {
				pesquisas.get(codigo).setAtivado(false);
			}else{
				throw new IllegalArgumentException("Pesquisa desativada.");
			}
		}else{
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
	}
	
	/**
	 * Metodo responsavel por ativar uma pesquisa.
	 * @param codigo
	 */
	public void ativaPesquisa(String codigo) {
		if(pesquisas.containsKey(codigo)) {
			if(pesquisas.get(codigo).isAtivado() == false) {
				pesquisas.get(codigo).setAtivado(true);
			} else {
				throw new IllegalArgumentException("Pesquisa ja ativada.");			
			} 
		}else {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
	}
	
	/**
	 * Metodo responsavel por exibir uma pesquisa.
	 * @param codigo
	 * @return
	 */
	public String exibePesquisa(String codigo) {
		if(pesquisas.containsKey(codigo)) {
			return pesquisas.get(codigo).toString();
		}
		throw new IllegalArgumentException("Pesquisa nao encontrada.");
	}
	
	/**
	 * Metodo responsavel por verificar o status de uma pesquisa.
	 * @param codigo
	 * @return
	 */
	public boolean pesquisaEhAtiva(String codigo) {
		excecoes.verificaString(codigo, "Codigo nao pode ser nulo ou vazio.");
		if(pesquisas.containsKey(codigo)) {
			return pesquisas.get(codigo).isAtivado();
		}
		throw new IllegalArgumentException("Pesquisa nao encontrada.");
	}
}