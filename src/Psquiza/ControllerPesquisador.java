package Psquiza;

import java.util.HashMap;
import java.util.Map;
/**
 * Controller responsavel pelas operacoes nos pesquisadores
 * @author Thiago Lira
 *
 */
public class ControllerPesquisador {
	/**
	 * Instancia a classe de excecoes
	 */
	private Excecoes excecoes = new Excecoes();
	/**
	 * Mapa de pesquisadores identificados pelo email
	 */
	private Map<String, Pesquisador> pesquisadores;
	/**
	 * Constroi a classo instanciando um novo HashMap
	 */
	public ControllerPesquisador() {
		this.pesquisadores = new HashMap<String, Pesquisador>();
	}
	/**
	 * Metodo responsavel pelo cadastro de pesquisadores
	 * @param nome
	 * @param funcao
	 * @param biografia
	 * @param email
	 * @param foto
	 */
	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String foto) {
		excecoes.verificaString(nome, "Campo nome nao pode ser nulo ou vazio.");
		excecoes.verificaString(funcao, "Campo funcao nao pode ser nulo ou vazio.");
		excecoes.verificaString(biografia, "Campo biografia nao pode ser nulo ou vazio.");
		excecoes.verificaString(email, "Campo email nao pode ser nulo ou vazio.");
		excecoes.verificaString(foto, "Campo fotoURL nao pode ser nulo ou vazio.");
		
		excecoes.verificaEmail(email, "Formato de email invalido.");
		excecoes.verificaURL(foto, "Formato de foto invalido.");
		
		if (!pesquisadores.containsKey(email)) {
			Pesquisador pesquisador = new Pesquisador(nome, funcao, biografia, email, foto);
			this.pesquisadores.put(email, pesquisador);
		}
		
	}
	
	/**
	 * Metodo responsavel por retornar uma representacao textual de um pesquisador
	 * @param email
	 * @return String no formato "Nome (Funcao) - Biografia - Email - FotoURL"
	 */
	public String exibePesquisador(String email) {
		excecoes.verificaString(email, "Campo email nap pode ser vazio ou nulo.");
		excecoes.verificaEmail(email, "Formato de email invalido.");
		if (pesquisadores.containsKey(email)) {
			return pesquisadores.get(email).toString();
		}
		throw new IllegalArgumentException("Pesquisador nao encontrado");
	}
	/**
	 * Metodo responavel por alterar os dados de um pesquisador atraves de seu email
	 * @param email
	 * @param atributo
	 * @param novoValor
	 */
	public void alteraPesquisador(String email, String atributo, String novoValor) {
		excecoes.verificaString(email, "Campo email nao pode ser vazio ou nulo.");
		excecoes.verificaString(atributo, "Campo atributo nao pode ser vazio ou nulo.");
		
		excecoes.verificaEmail(email, "Formato de email invalido.");
		
		if (pesquisadores.containsKey(email)) {
			switch (atributo) {
			case "nome":
				excecoes.verificaString(novoValor, "Campo nome nao pode ser nulo ou vazio.");
				pesquisadores.get(email).setNome(novoValor);
				break;
			case "funcao":
				excecoes.verificaString(novoValor, "Campo funcao nao pode ser nulo ou vazio.");
				pesquisadores.get(email).setFuncao(novoValor);
				break;
			case "biografia":
				excecoes.verificaString(novoValor, "Campo biografia nao pode ser nulo ou vazio.");
				pesquisadores.get(email).setBiografia(novoValor);
				break;
			case "fotoURL":
				excecoes.verificaString(novoValor, "Campo fotoURL nao pode ser nulo ou vazio.");
				excecoes.verificaURL(novoValor, "Formato de foto invalido.");
				pesquisadores.get(email).setFoto(novoValor);
				break;
			case "email":
				excecoes.verificaString(novoValor, "Campo email nao pode ser nulo ou vazio.");
				excecoes.verificaEmail(novoValor, "Formato de email invalido.");
				Pesquisador pesquisadorAuxiliar = pesquisadores.get(email);
				pesquisadorAuxiliar.setEmail(novoValor);
				pesquisadores.remove(email);
				pesquisadores.put(novoValor, pesquisadorAuxiliar);
				break;
			default:
				throw new IllegalArgumentException("Atributo invalido.");
			}
		}else {
			throw new IllegalArgumentException("Pesquisador nao encontrado");
		}
		
	}
	/**
	 * Método responsavel por ativar um pesquisadpr desativado
	 * @param email
	 */
	public void ativaPesquisador(String email) {
		excecoes.verificaString(email, "Email nao pode ser vazio ou nulo.");
		excecoes.verificaEmail(email, "Formato de email invalido.");
		if (pesquisadores.containsKey(email)) {
			if (!pesquisadores.get(email).isStatus()) {
				pesquisadores.get(email).setStatus(true);
			}else {
				throw new IllegalArgumentException("Pesquisador ja ativado.");
			}
		}else {
			throw new IllegalArgumentException("Pesquisador nao encontrado");
		}
	}

	/**
	 * Metodo responsavel por desativar um pesquisador ativado
	 * @param email
	 */
	public void desativaPesquisador(String email) {
		excecoes.verificaString(email, "Formato de email invalido.");
		excecoes.verificaEmail(email, "Formato de email invalido.");
		if (pesquisadores.containsKey(email)) {
			if (pesquisadores.get(email).isStatus()) {
				pesquisadores.get(email).setStatus(false);
			}else {
				throw new IllegalArgumentException("Pesquisador inativo.");
			}
		}else {
			throw new IllegalArgumentException("Pesquisador nao encontrado");
		}
	}
	/**
	 * Método que verifica se um pesquisador eh ativo ou nao
	 * @param email
	 * @return true ou false
	 */
	public boolean pesquisadorEhAtivado(String email) {
		excecoes.verificaString(email, "Email nao pode ser vazio ou nulo.");
		excecoes.verificaEmail(email, "Formato de email invalido.");
		if (pesquisadores.containsKey(email)) {
			return pesquisadores.get(email).isStatus();
		}
		throw new IllegalArgumentException("Pesquisador nao encontrado");
	}

}
