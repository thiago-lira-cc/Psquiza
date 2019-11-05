package Psquiza;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Controller responsavel pelas operacoes nos pesquisadores
 * @author Thiago Lira
 *
 */
public class ControllerPesquisador {
	/**
	 * Instancia a classe de excessoes
	 */
	private Excecoes excessoes = new Excecoes();
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
		excessoes.verificaString(nome, "Campo nome nao pode ser nulo ou vazio.");
		excessoes.verificaString(funcao, "Campo funcao nao pode ser nulo ou vazio.");
		excessoes.verificaString(biografia, "Campo biografia nao pode ser nulo ou vazio.");
		excessoes.verificaString(email, "Campo email nao pode ser nulo ou vazio.");
		excessoes.verificaString(foto, "Campo fotoURL nao pode ser nulo ou vazio.");
		
		excessoes.verificaEmail(email, "Formato de email invalido.");
		excessoes.verificaURL(foto, "Formato de foto invalido.");
		
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
		excessoes.verificaString(email, "Campo email nap pode ser vazio ou nulo.");
		excessoes.verificaEmail(email, "Formato de email invalido.");
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
		excessoes.verificaString(email, "Campo email nao pode ser vazio ou nulo.");
		excessoes.verificaString(atributo, "Atributo nao pode ser vazio ou nulo.");
		
		excessoes.verificaEmail(email, "Formato de email invalido.");
		
		if (pesquisadores.containsKey(email)) {
			switch (atributo) {
			case "NOME":
				excessoes.verificaString(novoValor, "Campo nome nao pode ser nulo ou vazio.");
				pesquisadores.get(email).setNome(novoValor);
				break;
			case "FUNCAO":
				excessoes.verificaString(novoValor, "Campo funcao nao pode ser nulo ou vazio.");
				pesquisadores.get(email).setFuncao(novoValor);
				break;
			case "BIOGRAFIA":
				excessoes.verificaString(novoValor, "Campo biografia nao pode ser nulo ou vazio.");
				pesquisadores.get(email).setBiografia(novoValor);
				break;
			case "FOTO":
				excessoes.verificaString(novoValor, "Campo fotoURL nao pode ser nulo ou vazio.");
				excessoes.verificaURL(novoValor, "Formato de foto invalido.");
				pesquisadores.get(email).setFoto(novoValor);
				break;
			case "EMAIL":
				excessoes.verificaString(novoValor, "Campo email nao pode ser nulo ou vazio.");
				excessoes.verificaEmail(novoValor, "Formato de email invalido.");
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
		excessoes.verificaString(email, "Email nao pode ser vazio ou nulo.");
		excessoes.verificaEmail(email, "Formato de email invalido.");
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
		excessoes.verificaString(email, "Formato de email invalido.");
		excessoes.verificaEmail(email, "Formato de email invalido.");
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
		excessoes.verificaString(email, "Email nao pode ser vazio ou nulo.");
		excessoes.verificaEmail(email, "Formato de email invalido.");
		if (pesquisadores.containsKey(email)) {
			return pesquisadores.get(email).isStatus();
		}
		throw new IllegalArgumentException("Pesquisador nao encontrado");
	}
	public Map<String, Pesquisador> getPesquisadores() {
		return this.pesquisadores;
	}
	public List<Busca> buscaPesquisador(String termo) {
		List<Busca> resultados = new ArrayList<Busca>();
		for (Pesquisador pesquisador : pesquisadores.values()) {
			if (pesquisador.getBiografia().toLowerCase().contains(termo.toLowerCase())) {
				Busca busca = new Busca(pesquisador.getEmail(), pesquisador.getBiografia());
				resultados.add(busca);
			}
		}
		Collections.sort(resultados);
		return resultados;
	}

}
