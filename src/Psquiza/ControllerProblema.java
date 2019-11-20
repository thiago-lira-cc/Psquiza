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

public class ControllerProblema implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1696630821053147116L;
	private Map<String, Problema> problemas;
	private Excecoes excecoes;
	private int contCodigo;
	
	public ControllerProblema() {
		this.problemas = new HashMap<>();
		this.excecoes = new Excecoes();
		this.contCodigo = 0;
	}
	
	public String geraCodigo() {
		contCodigo++;
		return "P"+contCodigo;
	}
	
	public boolean problemaJaCadastrado(String codigo) {
		return problemas.containsKey(codigo);
	}
	
	public Problema pegaProblema(String codigo) {
		return problemas.get(codigo);
	}

	public String cadastraProblema(String descricao, int viabilidade) {
		excecoes.verificaString(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		excecoes.verificaValor(viabilidade, "Valor invalido de viabilidade.");
		
		String codigo = geraCodigo();
		Problema p = new Problema(codigo, descricao, viabilidade);
		this.problemas.put(codigo, p);
		return codigo;
	}

	public String exibeProblema(String codigo) {
		excecoes.verificaString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		if (problemaJaCadastrado(codigo)) {
			return pegaProblema(codigo).toString();
		} 
		throw new IllegalArgumentException("Problema nao encontrado");
	}

	public void apagarProblema(String codigo) {
		excecoes.verificaString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		if (problemaJaCadastrado(codigo)) {
			problemas.remove(codigo);
		} else {
			throw new IllegalArgumentException("Problema nao encontrado");
		}
		
	}

	/**
	 * Metodo que verifica se entre os problemas cadastrados existe o termo 
	 * @param termo
	 * @return List com os resultados
	 */
	public List<Busca> busca(String termo) {
		List<Busca> resultados = new ArrayList<Busca>();
		for (Problema problema : problemas.values()) {
			if (problema.getDescricao().toLowerCase().contains(termo.toLowerCase())) {
				Busca busca = new Busca(problema.getCodigo(), problema.getDescricao());
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
			os = new ObjectOutputStream(new FileOutputStream("./Logger/cProblema.txt"));
			os.writeObject(this.problemas);
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
			os = new ObjectInputStream(new FileInputStream("./Logger/cProblema.txt"));
			this.problemas = (Map<String, Problema>) os.readObject();
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
