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
 * Controller responsavel por gerenciar operacoes
 * sobre objetivos
 * @author Jose Felix
 *
 */

public class ControllerObjetivo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7955807656918874477L;
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

		
		Objetivo o = new Objetivo(codigo, tipo, descricao, aderencia, viabilidade);
		objetivos.put(codigo, o);
		return codigo;
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

	/**
	 * Metodo que verifica se entre os objetivos cadastrados existe o termo 
	 * @param termo
	 * @return List com os resultados
	 */
	public List<Busca> busca(String termo) {
		List<Busca> resultados = new ArrayList<Busca>();
		for (Objetivo objetivo : objetivos.values()) {
			if (objetivo.getDescricao().toLowerCase().contains(termo.toLowerCase())) {
				Busca busca = new Busca(objetivo.getCodigo(), objetivo.getDescricao());
				resultados.add(busca);
			}
		}
		Collections.sort(resultados);
		return resultados;
	}
	/**
	 * Metodo responsavel por realizar o salvamento dos dados em um arquivo.
	 */
	public void salvar() {
		ObjectOutputStream os;
		try {
			os = new ObjectOutputStream(new FileOutputStream("./Logger/cObjetivo.txt"));
			os.writeObject(this.objetivos);
			os.writeObject(this.contCodigo);
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
			os = new ObjectInputStream(new FileInputStream("./Logger/cObjetivo.txt"));
			this.objetivos = (Map<String, Objetivo>) os.readObject();
			this.contCodigo = (int) os.readObject();
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Arquivo nao existe no sistema.");
		} catch (IOException e) {
			System.out.println("Algum erro ocorre...");
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Alguma coisa no sistema mudou");
		}
	}

}
