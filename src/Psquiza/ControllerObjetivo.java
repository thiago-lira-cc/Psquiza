package Psquiza;
import java.util.HashMap;
import java.util.Map;
/**
 * Controller responsavel por gerenciar operacoes
 * sobre objetivos
 * @author Jose Felix
 *
 */

public class ControllerObjetivo {
	/**
	 * Mapa de objetivos identificados pelo codigo
	 */
	private Map<String, Objetivo> objetivos;
	/**
	 * Instancia a classe de excecoes
	 */
	private Excecoes excecoes;
	/**
	 * Valor inteiro utilizado para gerar o codigo
	 * identificador de um objetivo
	 */
	private int contCodigo;
	/**
	 * Constroi um Controller de objetivos no sistema
	 */

	public ControllerObjetivo() {
		this.objetivos = new HashMap<>();
		this.excecoes = new Excecoes();
	}
	/**
	 * Gera um codigo identificador de um objetivo
	 * @return o codigo identificador
	 */
	public String geraCodigo() {
		contCodigo++;
		return "O"+contCodigo;
	}
	/**
	 * Verifica se um objetivo ja esta cadastrado ou nao
	 * a partir de seu codigo
	 * @param codigo
	 * @return se o objetivo esta cadastrado ou nao
	 */
	public boolean objetivoJaCadastrado(String codigo) {
		return objetivos.containsKey(codigo);
	}
	/**
	 * Metodo responsavel por acessar um objetivo a partir
	 * do seu codigo identificador
	 * @param codigo
	 * @return o objetivo
	 */
	public Objetivo pegaObjetivo(String codigo) {
		return objetivos.get(codigo);
	}
	/**
	 * Metodo responsavel por cadastrar um objetivo identificado
	 * por seu codigo
	 * @param tipo
	 * @param descricao
	 * @param aderencia
	 * @param viabilidade
	 * @return o codigo identificador
	 */
	public String cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
		excecoes.verificaString(tipo, "Campo tipo nao pode ser nulo ou vazio.");
		excecoes.verificaTipo(tipo, "Valor invalido de tipo.");
		excecoes.verificaString(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		excecoes.verificaValor(aderencia, "Valor invalido de aderencia");
		excecoes.verificaValor(viabilidade, "Valor invalido de viabilidade.");
		String codigo = geraCodigo();

		if (objetivoJaCadastrado(codigo)==false) {
			Objetivo o = new Objetivo(codigo, tipo, descricao, aderencia, viabilidade);
			objetivos.put(codigo, o);
			return codigo;
		} else {
			throw new IllegalArgumentException("Objetivo ja existe");
		}
	}

	/**
	 * Metodo responsavel por imprimir um objetivo a partir
	 * do seu codigo identificador
	 * @param codigo
	 * @return uma representacao em String do objetivo
	 */

	public String exibeObjetivo(String codigo) {
		excecoes.verificaString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		if (objetivoJaCadastrado(codigo)) {
			return pegaObjetivo(codigo).toString();
		}
		throw new IllegalArgumentException("Objetivo nao encontrado");
	}
	/**
	 * Metodo responsavel por apagar um objetivo a partir
	 * do seu codigo identificador
	 * @param codigo
	 */

	public void apagarObjetivo(String codigo) {
		excecoes.verificaString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		if (objetivoJaCadastrado(codigo)) {
			objetivos.remove(codigo);
		} else {
			throw new IllegalArgumentException("Objetivo nao encontrado");
		}


	}

}
