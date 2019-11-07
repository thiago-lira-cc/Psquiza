package Psquiza;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Atividade {
	private String descricao;
	private String nivelRisco;
	private String descricaoRisco;
	private String codigo;
	private HashMap<Integer, Item> itens;
	
	public Atividade(String descricao, String nivelRisco, String descricaoRisco, String codigo) {
		this.descricao = descricao;
		this.nivelRisco = nivelRisco;
		this.descricaoRisco = descricaoRisco;
		this.codigo = codigo;
		this.itens = new HashMap <Integer, Item>();
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
	
	

}
