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
 * sobre atividades.
 * @author 
 *
 */
public class ControllerAtividade implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7077844415305687847L;
	private Map <String, Atividade> atividades;
	private Excecoes excecoes;
	private int contCodigo;
	
	/**
	 * Constroi um controle de atividades.
	 */
	public ControllerAtividade() {
		this.atividades = new HashMap<String, Atividade>();
		this.excecoes = new Excecoes();
	}
	
	/**
	 * Gera codigo de identificacao de uma atividade.
	 * @return retorna o codigo
	 */
	public String geraCodigo() {
		this.contCodigo++ ;
		return "A" + contCodigo;
	}
	
	public Atividade pegaCodigo(String codigo) {
		return atividades.get(codigo);
	}
	/**
	 * Verifica se uma atividade existe ou nao no sistema.
	 * @param codigo o codigo a ser verificado
	 * @return retorna se existe ou nao.
	 */
	public boolean ativExiste(String codigo) {
		if(atividades.containsKey(codigo)) {
			return true;
		}
		return false;	
	}
	/**
	 * Cadastra uma atividade no sistema.
	 * @param descricao
	 * @param nivelRisco
	 * @param descricaoRisco
	 * @return retorna o codigo da atividade.
	 */
	public String cadastraAtividade(String descricao, String nivelRisco, String descricaoRisco) {
		excecoes.verificaString(descricao, "Campo Descricao nao pode ser nulo ou vazio.");
		excecoes.verificaString(nivelRisco, "Campo nivelRisco nao pode ser nulo ou vazio.");
		excecoes.verificaString(descricaoRisco, "Campo descricaoRisco nao pode ser nulo ou vazio.");
		excecoes.verificaNivelRisco(nivelRisco, "Valor invalido do nivel do risco.");
		String codigo = geraCodigo();
		if (!atividades.containsKey(codigo)) {
			Atividade atividade = new Atividade (descricao, nivelRisco, descricaoRisco,codigo);
			atividades.put(codigo, atividade);
			
		}
		return codigo;
	}

	/**
	 * Apaga uma atividade do sistema
	 * @param codigo codigo a ser apagado
	 */
	public void apagaAtividade(String codigo) {
		excecoes.verificaString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		if (atividades.containsKey(codigo)) {
			atividades.remove(codigo);
		} else {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
	}
	/**
	 * Cadastra um item no sistema
	 * @param codigo
	 * @param item
	 */
	public void cadastraItem(String codigo, String item) {
		excecoes.verificaString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		excecoes.verificaString(item, "Item nao pode ser nulo ou vazio.");
		if (atividades.containsKey(codigo)) {
			atividades.get(codigo).cadastraItem(item);
		} else {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
		
	}
	
	/**
	 * Exibe uma atividade existente no sistema.
	 * @param codigo
	 * @return
	 */
	public String exibeAtividade(String codigo) {
		excecoes.verificaString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		if (atividades.containsKey(codigo)) {
			 return atividades.get(codigo).exibeAtividade();
		} else {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
		
	}
	
	/**
	 * Metodo que verifica se entre as atividades cadastradas existe o termo 
	 * @param termo
	 * @return List com os resultados
	 */
	public List<Busca> busca(String termo) {
		List<Busca> resultados = new ArrayList<Busca>();
		List<Atividade> atividades = getAtividades();
		Collections.sort(atividades);
		for (Atividade atividade : atividades) {
			if (atividade.getDescricao().toLowerCase().contains(termo.toLowerCase())
				&& atividade.getDescricaoRisco().toLowerCase().contains(termo.toLowerCase())) {
				
				Busca busca1 = new Busca(atividade.getCodigo(), atividade.getDescricaoRisco());
				resultados.add(busca1);
				
				Busca busca2 = new Busca(atividade.getCodigo(), atividade.getDescricao());
				resultados.add(busca2);
				
			}else if(atividade.getDescricao().toLowerCase().contains(termo.toLowerCase())){
				Busca busca = new Busca(atividade.getCodigo(), atividade.getDescricao());
				resultados.add(busca);
			}else if (atividade.getDescricaoRisco().toLowerCase().contains(termo.toLowerCase())) {
				Busca busca = new Busca(atividade.getCodigo(), atividade.getDescricaoRisco());
				resultados.add(busca);
			}

		}

		return resultados;
	}

	private List<Atividade> getAtividades() {
		List<Atividade> atividades = new ArrayList<Atividade>();
		for (Atividade atividade : this.atividades.values()) {
			atividades.add(atividade);
		}
		return atividades;
	}

	/**
	 * Metodo que conta os itens pendentes no sistema.
	 * @param codigo
	 * @return
	 */
	public int contaItensPendentes(String codigo) {
		excecoes.verificaString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		if(atividades.containsKey(codigo)) {
			 return atividades.get(codigo).contaItensPendentes();
		} else {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
	}

	/**
	 * Metodo que conta os itens ja realizados no sistema.
	 * @param codigo
	 * @return
	 */
	public int contaItensRealizados(String codigo) {
		excecoes.verificaString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		if(atividades.containsKey(codigo)) {
			 return atividades.get(codigo).contaItensRealizados();
		} else {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
	}

	/**
	 * Metodo que executa uma atividade
	 * @param codigoAtividade
	 * @param item
	 * @param duracao
	 */
	public void executaAtividade(String codigoAtividade, int item, int duracao) {
		excecoes.verificaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		excecoes.verificaItemDuracao(item, "Item nao pode ser nulo ou negativo.");
		excecoes.verificaItemDuracao(duracao, "Duracao nao pode ser nula ou negativa.");
		if(atividades.get(codigoAtividade).isAtivAssociada()==false) {
			throw new IllegalArgumentException("Atividade sem associacoes com pesquisas.");
		}
		this.atividades.get(codigoAtividade).executaItem(item,duracao);
		
	}
	/**
	 * Metodo que cadastra o resultado de uma atividade
	 * @param codigoAtividade
	 * @param resultado
	 * @return 
	 */
	public int cadastraResultado(String codigoAtividade,String resultado) {
		excecoes.verificaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		excecoes.verificaString(resultado, "Resultado nao pode ser nulo ou vazio.");
		return this.atividades.get(codigoAtividade).cadastraResultado(resultado);
	}
	/**
	 * Metodo que remove um resulltado ja cadastrado.
	 * @param codigoAtividade
	 * @param numeroDoResultado
	 * @return
	 */
	public boolean removeResultado(String codigoAtividade, int numeroDoResultado) {
		excecoes.verificaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		excecoes.verificaItemDuracao(numeroDoResultado, "numeroResultado nao pode ser nulo ou negativo.");
		if(atividades.containsKey(codigoAtividade)) {
			return this.atividades.get(codigoAtividade).removeResultado(numeroDoResultado);
		}else {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
		
	}
	/**
	 * Metodo que lista os resultados cadastrados
	 * @param codigoAtividade
	 * @return
	 */
	public String listaResultados(String codigoAtividade) {
		excecoes.verificaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		if(atividades.containsKey(codigoAtividade)) {
			return this.atividades.get(codigoAtividade).listaResultados();
		}
		throw new IllegalArgumentException("Atividade nao encontrada");
	}
	public int getDuracao(String codigoAtividade) {
		excecoes.verificaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		if(atividades.containsKey(codigoAtividade)) {
			return this.atividades.get(codigoAtividade).getDuracao();
		}else {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}		
	}
	/**
	 * Metodo que defini uma proxima atividade a ser realizada
	 * @param idPrecedente
	 * @param idSubsquente
	 */
	public void defineProximaAtividade(String idPrecedente, String idSubsquente) {
		excecoes.verificaString(idPrecedente, "Atividade nao pode ser nulo ou vazio.");
		excecoes.verificaString(idSubsquente, "Atividade nao pode ser nulo ou vazio.");
		Atividade precedente = atividades.get(idPrecedente);
		Atividade subsequente = atividades.get(idSubsquente);
		if (this.atividades.containsKey(idPrecedente)&&this.atividades.containsKey(idSubsquente)) {
			if (precedente.getProxima()==null) {	
				List<Atividade> ativids = new ArrayList<>();
				ativids.addAll(atividades.values());
				Collections.sort(ativids, new ComparadorAtividade());
				for (Atividade atividade : ativids) {
					if (valido(atividade, precedente)) {
						if (valido(atividade, subsequente)) {
							throw new IllegalArgumentException("Criacao de loops negada.");
							
						}else {
							precedente.setProxima(subsequente);
							break;
						}
					}
					
				}
				
			}else {
				throw new IllegalArgumentException("Atividade ja possui uma subsequente.");
			}
		}else {
			throw new IllegalArgumentException("Atividade nao encontrada.");
		}
	}
	/**
	 * Metodo que retorna se a proxima atividade eh valida
	 * @param origem
	 * @param proximo
	 * @return
	 */
	public boolean valido(Atividade origem, Atividade proximo) {
		while (origem!=null) {
			if (origem.equals(proximo)) {
				return true;
			}
			origem = origem.getProxima();
		}
		return false;
	}
	/**
	 * remove a proxima atividade
	 * @param idPrecedente
	 */
	public void tiraProximaAtividade(String idPrecedente) {
		excecoes.verificaString(idPrecedente, "Atividade nao pode ser nulo ou vazio.");
		if (atividades.containsKey(idPrecedente)) {
			atividades.get(idPrecedente).setProxima(null);
		}else {
			throw new IllegalArgumentException("Atividade nao encontrada.");
		}
	}
	/**
	 * Conta quantas atividades tem depois de uma especifica
	 * @param idPrecedente
	 * @return
	 */
	public int contaProximos(String idPrecedente) {
		excecoes.verificaString(idPrecedente, "Atividade nao pode ser nulo ou vazio.");
		if(atividades.containsKey(idPrecedente)) {
			if (atividades.get(idPrecedente).getProxima()==null) {
				return 0;
			}else {
				return 1 + contaProximos(atividades.get(idPrecedente).getProxima().getCodigo());
			}
		}
		throw new IllegalArgumentException("Atividade inexistente.");
	}

	/**
	 * Metodo que busca um atividade de acordo com uma posicao enesima a partir da atual
	 * @param idAtividade
	 * @param enesimaAtividade
	 * @return String do codigo da atividade
	 */
	public String pegaProximo(String idAtividade, int enesimaAtividade) {
		excecoes.verificaString(idAtividade, "Atividade nao pode ser nulo ou vazio.");
		excecoes.verificaNumero(enesimaAtividade, "EnesimaAtividade nao pode ser negativa ou zero.");
		if (atividades.containsKey(idAtividade)) {
			return pegaProximo(idAtividade, 0,  enesimaAtividade);
		}
		throw new IllegalArgumentException("Atividade inexistente.");
	}
	/**
	 * Metodo recursivo que verifica se ja chegou na posicao desejado
	 * @param idAtividade
	 * @param pos
	 * @param enesimaAtividade
	 * @return String do codigo da atividade
	 */
	private String pegaProximo(String idAtividade, int pos, int enesimaAtividade) {
		if (pos==enesimaAtividade) {
			return atividades.get(idAtividade).getCodigo();
		}
		if (atividades.get(idAtividade).getProxima()==null) {
			throw new IllegalArgumentException("Atividade inexistente.");
		}
		return pegaProximo(atividades.get(idAtividade).getProxima().getCodigo(), pos+1, enesimaAtividade);
	}
	
	/**
	 * Metoque que retorna a atividade de maior risco dentro da sequencia da atividade passada no parametro
	 * @param idAtividade
	 * @return
	 */
	public String pegaMaiorRisco(String idAtividade) {
		excecoes.verificaString(idAtividade, "Atividade nao pode ser nulo ou vazio.");
		if (!atividades.containsKey(idAtividade)) {
			throw new IllegalArgumentException("Atividade nao encontrada.");
		}else if(atividades.get(idAtividade).getProxima()==null) {
			throw new IllegalArgumentException("Nao existe proxima atividade.");
		}
		return atividades.get(idAtividade).pegaMaiorRisco(atividades.get(idAtividade).getProxima().getNivelDeRisco());
	}
	/**
	 * Metodo responsavel por realizar o salvamento dos dados em um arquivo.
	 */
	public void salvar() {
		ObjectOutputStream os;
		try {
			os = new ObjectOutputStream(new FileOutputStream("./Logger/cAtividade.txt"));
			os.writeObject(this.atividades);
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
			os = new ObjectInputStream(new FileInputStream("./Logger/cAtividade.txt"));
			this.atividades = (Map<String, Atividade>) os.readObject();
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
