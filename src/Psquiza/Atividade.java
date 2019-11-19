package Psquiza;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Atividade implements Comparable<Atividade>{
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

	public Integer geraIndice() {
		return itens.size() + 1;
	}
	
	public void cadastraItem(String item) {
		Integer indice = geraIndice();
		if (!itens.containsKey(indice)) {
			Item novoItem = new Item(item, indice);
			itens.put(indice, novoItem);
		}
	}

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
	
	public int contaItensPendentes() {
		int cont = 0;
		for (Item item : itens.values()) {
			if (item.getStatus().equals("PENDENTE")) {
				cont++;
			}
		}
		return cont;
		
	}

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
	
	public void executaItem(int item, int duracao) {
		if(item >itens.size() || item <= 0) {
			throw new IllegalArgumentException("Item nao encontrado.");
		}
		this.itens.get(item).realizar();
		this.duracao += duracao;
	}

	public int cadastraResultado(String resultado) {
		resultados.put(++numeroDoResultado,resultado);
		return numeroDoResultado;
	}
	public boolean removeResultado(int numeroDoResultado) {
		if(resultados.containsKey(numeroDoResultado)){
			this.resultados.remove(numeroDoResultado);
			return true;
		}
		throw new IllegalArgumentException("Resultado nao encontrado.");
	}

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
			resul += "- "+i.getStatus()+" - ITEM"+cont;
			cont++;
		}
		return resul;
	}
	/**
	 * Gera os resultados de cada atividade
	 * @return os resultados da atividade
	 */
	public String resultadosAtividade() {
		String resul = "- Resultados:"+System.lineSeparator()+"    - "
				+this.descricao+System.lineSeparator();
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
				resul += "   - ITEM"+cont+" - "+(duracao/itens.size())+System.lineSeparator();
				cont++;
			}
		}
		for (String r : this.resultados.values()) {
			resul += "   - "+r+System.lineSeparator();
		}
		return resul;
	}
}
		

	


