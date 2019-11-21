package Psquiza;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Atividade implements Comparable<Atividade>, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8493702775882752428L;
	private String descricao;
	private String nivelRisco;
	private String descricaoRisco;
	private String codigo;
	private HashMap<Integer, Item> itens;
	private boolean ativAssociada;
	private Map<Integer,String> resultados;
	private int numeroDoResultado;
	private int duracao;
	private Atividade proxima;
	
	/**
	 * Construtor da classe
	 * @param descricao
	 * @param nivelRisco
	 * @param descricaoRisco
	 * @param codigo
	 */
	public Atividade(String descricao, String nivelRisco, String descricaoRisco, String codigo) {
		this.descricao = descricao;
		this.nivelRisco = nivelRisco;
		this.descricaoRisco = descricaoRisco;
		this.codigo = codigo;
		this.itens = new HashMap <Integer, Item>();
		this.ativAssociada = false;
		this.resultados =new HashMap<Integer,String>();
		this.proxima = null;
	}

	public void setProxima(Atividade proxima) {
		this.proxima = proxima;
	}

	public Atividade getProxima() {
		return proxima;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atividade other = (Atividade) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	/**
	 * Metodo responsavel por gerar indices 
	 * @return
	 */
	public Integer geraIndice() {
		return itens.size() + 1;
	}
	/**
	 * Metodo responsavel por cadastrar um item no sistema
	 * @param item
	 */
	public void cadastraItem(String item) {
		Integer indice = geraIndice();
		if (!itens.containsKey(indice)) {
			Item novoItem = new Item(item, indice);
			itens.put(indice, novoItem);
		}
	}
	/**
	 * Metodo responsavel pela exibicao de atividades
	 * @return uma lista de atividades
	 */
	public String exibeAtividade() {
		String res = "";
		if (itens.size() != 0) {
			res += this.toString() + " | ";
		} else {
			res += this.toString();
		}
		
		int cont = 0;
		List<Item>  listaItens =  new ArrayList<>(itens.values());
		Collections.sort(listaItens);
		for (Item item : listaItens) {
			if (cont < listaItens.size() - 1) {
				res += item.toString() + " | ";
				cont++;
			} else {
				res += item.toString();
			}
		}
		return res;
	}

	/**
	 * Representacao em String 
	 */
	@Override
	public String toString() {
		return this.descricao + " (" + this.nivelRisco + " - " + this.descricaoRisco + ")";
	}

	public String getDescricao() {
		return this.descricao;
	}

	public String getDescricaoRisco() {
		return this.descricaoRisco;
	}

	public String getCodigo() {
		return codigo;
	}
	/**
	 * Metodo responsavel por contar os itens que ainda nao foram realizados
	 * @return retorna a quantidade de itens nao realizados
	 */
	public int contaItensPendentes() {
		int cont = 0;
		for (Item item : itens.values()) {
			if (item.getStatus().equals("PENDENTE")) {
				cont++;
			}
		}
		return cont;
		
	}
	/**
	 * Metodo responsavel por contar os itens ja realizados
	 * @return retoena a quantidade de itens realizados
	 */
	public int contaItensRealizados() {
		int cont = 0;
		for (Item item : itens.values()) {
			if (item.getStatus().equals("REALIZADO")) {
				cont++;
			}
		}
		return cont;
	}

	@Override
	public int compareTo(Atividade o) {
		return this.codigo.compareTo(o.getCodigo());
	}
	public boolean isAtivAssociada() {
		return ativAssociada;
	}

	public void setAtivAssociada(boolean ativAssociada) {
		this.ativAssociada = ativAssociada;
	}
	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}
	/**
	 * Metoro responsavel por executar um item
	 * @param item
	 * @param duracao
	 */
	public void executaItem(int item, int duracao) {
		if(item >itens.size() || item <= 0) {
			throw new IllegalArgumentException("Item nao encontrado.");
		}
		this.itens.get(item).realizar();
		this.duracao += duracao;
	}
	/**
	 * Metodo responsavel por cadastrar um resultado no sistema
	 * @param resultado
	 * @return retorna o numero do resultado
	 */
	public int cadastraResultado(String resultado) {
		resultados.put(++numeroDoResultado,resultado);
		return numeroDoResultado;
	}
	/**
	 * Metodo responsavel por remover um resultado cadastrado
	 * @param numeroDoResultado o numero do resultado
	 * @return retorna se a remocao ocorreu
	 */
	public boolean removeResultado(int numeroDoResultado) {
		if(resultados.containsKey(numeroDoResultado)){
			this.resultados.remove(numeroDoResultado);
			return true;
		}
		throw new IllegalArgumentException("Resultado nao encontrado.");
	}
	/**
	 * Metodo responavel por listar os resultados
	 * @return retorna uma lista de resultados
	 */
	public String listaResultados() {
		String retorna = "";
		for(String resultado:this.resultados.values()) {
			retorna += resultado +" | ";
		}
		return retorna.substring(0, retorna.length()-3);
	}
	
	public String getNivelDeRisco() {
		return this.nivelRisco;
	}
	/**
	 * Gera um resumo da atividade a partir do seu toString()
	 * e do toString() de cada um de seus itens
	 * @return resumo
	 */
	public String resumeAtividade() {
		String resul = "";
		int cont = 1;
		resul += "- "+toString()+System.lineSeparator()+"   ";
		for (Item i : this.itens.values()) {
			resul += "- "+i.getStatus()+" - ITEM"+cont+System.lineSeparator();
			cont++;
		}
		return resul;
	}
	/**
	 * Gera os resultados de cada atividade
	 * @return os resultados da atividade
	 */
	public String resultadosAtividade() {
		String resul = "- "+this.descricao+System.lineSeparator();
		return resul+representaResultados();
	}
	
	/**
	 * Gera um resumo dos resultados de uma atividade, de acordo com
	 * seus itens e resultados
	 * @return os resultados
	 */
	public String representaResultados() {
		String resul = "";
		int cont = 1;
		for (Item i : this.itens.values()) {
			if (i.getStatus().equals("REALIZADO")) {
				resul += "   - ITEM"+cont+" - "+(duracao/contaItensRealizados())+System.lineSeparator();
				cont++;
			}
		}
		for (String r : this.resultados.values()) {
			resul += "   - "+r+System.lineSeparator();
		}
		return resul;
	}
	
	public String pegaMaiorRisco(String risco) {
		if (proxima!=null&&!(proxima.temRiscoMaior(risco).equals(""))) {
			return proxima.pegaMaiorRisco(this.temRiscoMaior(risco));
		}
		return this.codigo;
	}
	private String temRiscoMaior(String risco) {
		if (risco.equals("BAIXO")) {
			if (this.nivelRisco.equals("BAIXO")||this.nivelRisco.equals("MEDIO")||this.nivelRisco.equals("ALTO")) {
				return this.nivelRisco;
			}
			if (this.proxima==null) {
				return "";
			}
		}
		if (risco.equals("MEDIO")) {
			if (this.nivelRisco.equals("MEDIO")||this.nivelRisco.equals("ALTO")) {
				return this.nivelRisco;
			}
			if (this.proxima==null) {
				return "";
			}
		}
		else {
			if (this.nivelRisco.equals("ALTO")) {
				return this.nivelRisco;
			}
			if (this.proxima==null) {
				return "";
			}
		}
		return this.proxima.temRiscoMaior(risco);
	}
}
		

	


