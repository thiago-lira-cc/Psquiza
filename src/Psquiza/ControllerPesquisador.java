package Psquiza;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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
public class ControllerPesquisador implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -17980619286674685L;
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
		excessoes.verificaString(email, "Campo email nao pode ser nulo ou vazio.");
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
			case "SEMESTRE":
				excessoes.verificaString(novoValor, "Campo semestre nao pode ser nulo ou vazio.");
				excessoes.verificaNumero(Integer.parseInt(novoValor), "Atributo semestre com formato invalido.");
				if (pesquisadores.get(email).getFuncao().equals("estudante")) {
					pesquisadores.get(email).alteraEspecialidadeAluno(atributo,novoValor);
				} else {
					throw new IllegalArgumentException("Pesquisador nao compativel com a especialidade.");
				}
			case "IEA":
				excessoes.verificaString(novoValor, "Campo IEA nao pode ser nulo ou vazio.");
				excessoes.verificaIEA(Double.parseDouble(novoValor), "Atributo IEA com formato invalido.");
				if (pesquisadores.get(email).getFuncao().equals("estudante")) {
					pesquisadores.get(email).alteraEspecialidadeAluno(atributo, novoValor);
				} else {
					throw new IllegalArgumentException("Pesquisador nao compativel com a especialidade.");
				}
			case "FORMACAO":
				excessoes.verificaString(novoValor, "Campo formacao nao pode ser nulo ou vazio.");
				if (pesquisadores.get(email).getFuncao().equals("professor")) {
					pesquisadores.get(email).alteraEspecialidadeProfessor(atributo, novoValor);
				}
			case "UNIDADE":
				excessoes.verificaString(novoValor, "Campo formacao nao pode ser nulo ou vazio.");
				if (pesquisadores.get(email).getFuncao().equals("professor")) {
					pesquisadores.get(email).alteraEspecialidadeProfessor(atributo, novoValor);
				}
			case "DATA" :
				excessoes.verificaString(novoValor, "Campo data nao pode ser vazio ou nulo");
				excessoes.verficaDataValida(novoValor, "Atributo data com formato invalido.");
				if (pesquisadores.get(email).getFuncao().equals("professor")) {
					pesquisadores.get(email).alteraEspecialidadeProfessor(atributo, novoValor);
				}
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

	/**
	 * Metodo que verifica se entre os pesquisadores cadastrados existe o termo 
	 * @param termo
	 * @return List com os resultados
	 */
	
	public List<Busca> busca(String termo) {
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
	
	public Pesquisador verificaBuscaPesquisador(String emailPesquisador) {
		if (!pesquisadores.containsKey(emailPesquisador)) {
			throw new IllegalArgumentException("Pesquisadora nao encontrada.");
		}else {
			return pesquisadores.get(emailPesquisador);
		}
	}
	public void cadastraEspecialidadeProfessor(String email, String formacao, String unidade, String data) {
		if (pesquisadores.containsKey(email)) {
			if (pesquisadores.get(email).getFuncao().equals("professor")) {
				pesquisadores.get(email).cadastraEspecialidadeProfessor(formacao, unidade, data);
			} else {
				throw new IllegalArgumentException("Pesquisador nao compativel com a especialidade.");
			}
		} else {
			throw new IllegalArgumentException("Pesquisadora nao encontrada.");
		}
	}
	/**
	 * Metodo responsavel por realizar o salvamento dos dados em um arquivo.
	 */
	public void salvar() {
		ObjectOutputStream os;
		try {
			os = new ObjectOutputStream(new FileOutputStream("./Logger/cPesquisador.txt"));
			os.writeObject(this.pesquisadores);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Arquivo nao existe no sistema.");
		} catch (IOException e) {
			System.out.println("Algum erro ocorreu...");
		}
	}
	/**
	 * Metodo responsavel por salvar os dados em um arquivo.
	 */
	@SuppressWarnings({ "unchecked", "resource" })
	public void carregar() {
		ObjectInputStream os;
		try {
			os = new ObjectInputStream(new FileInputStream("./Logger/cPesquisador.txt"));
			this.pesquisadores = (Map<String, Pesquisador>) os.readObject();
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Arquivo nao existe no sistema.");
		} catch (IOException e) {
			System.out.println("Algum erro ocorre...");
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Alguma coisa no sistema mudou");
		}
	}
	public void cadastraEspecialidadeAluno(String email, int semestre, double iEA) {
		if (pesquisadores.containsKey(email)) {
			if (pesquisadores.get(email).getFuncao().equals("estudante")) {
				pesquisadores.get(email).cadastraEspecialidadeAluno(email, semestre, iEA);
			} else {
				throw new IllegalArgumentException("Pesquisador nao compativel com a especialidade.");
			}
		} else {
			throw new IllegalArgumentException("Pesquisadora nao encontrada.");
		}	
	}

}
