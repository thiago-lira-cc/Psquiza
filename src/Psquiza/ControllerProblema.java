package Psquiza;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller responsavel por gerenciar as operacoes
 * sobre problemas
 * @author Jose Felix
 *
 */
public class ControllerProblema {
	/**
	 * Mapa de problemas identificados pelo codigo
	 */
	private Map<String, Problema> problemas;
	/**
	 * Instancia a classe de excecoes
	 */
	private Excecoes excecoes;
	/**
	 * Valor inteiro utilizado para gerar o codigo
	 * de cada problema
	 */
	private int contCodigo;
	/**
	 * Constroi um Controller de problemas
	 * no sistema
	 */
	public ControllerProblema() {
		this.problemas = new HashMap<>();
		this.excecoes = new Excecoes();
		this.contCodigo = 0;
	}
	/**
	 * Gera um codigo identificador de um
	 * problema
	 * @return o codigo identificador
	 */
	public String geraCodigo() {
		contCodigo++;
		return "P"+contCodigo;
	}
	/**
	 * Verifica se um problema ja esta cadastrado
	 * @param codigo
	 * @return se o problema esta cadastrado ou nao
	 */
	public boolean problemaJaCadastrado(String codigo) {
		return problemas.containsKey(codigo);
	}
	/**
	 * Metodo responsavel por acessar um problema 
	 * ja cadastrado no sistema a partirdo seu 
	 * codigo identificador
	 * @param codigo
	 * @return o problema
	 */
	public Problema pegaProblema(String codigo) {
		return problemas.get(codigo);
	}
	
	/**
	 * Metodo responsavel por cadastrar um problema no sistema
	 * @param descricao
	 * @param viabilidade
	 * @return o codigo identificador do problema
	 */
	public String cadastraProblema(String descricao, Integer viabilidade) {
		excecoes.verificaString(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		excecoes.verificaInteiro(viabilidade, "Campo viabilidade nao pode ser nulo ou vazio.");
		excecoes.verificaValor(viabilidade, "Valor invalido de viabilidade.");
		
		String codigo = geraCodigo();
		if (!problemaJaCadastrado(codigo)) {
			Problema p = new Problema(codigo, descricao, viabilidade);
			this.problemas.put(codigo, p);
			return codigo;
		} else {
			throw new IllegalArgumentException("Problema ja existe");
		}
	}
	/**
	 * Metodo responsavel por imprimir um problema a partir
	 * do seu codigo identificador
	 * @param codigo
	 * @return uma representacao em String do problema
	 */
	public String exibeProblema(String codigo) {
		excecoes.verificaString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		
		if (problemaJaCadastrado(codigo)) {
			return pegaProblema(codigo).toString();
		} 
		throw new IllegalArgumentException("Problema nao encontrado");
	}
	/**
	 * Metodo responsavel por apagar um problema
	 * a partir do seu codigo identificador
	 * @param codigo
	 */
	public void apagarProblema(String codigo) {
		excecoes.verificaString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		
		if (problemaJaCadastrado(codigo)) {
			problemas.remove(codigo);
		}
		throw new IllegalArgumentException("Problema nao encontrado");
	}

}
