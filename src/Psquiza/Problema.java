package Psquiza;

import java.io.Serializable;

public class Problema implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2454714748010116287L;
	private String codigo;
	private String descricao;
	private int viabilidade;
	
	public Problema(String codigo, String descricao, int viabilidade) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.viabilidade = viabilidade;
	}
	
	public String toString() {
		return codigo + " - " + descricao + " - " + viabilidade;
	}

	public String getDescricao() {
		return this.descricao;
	}
	
	public String getCodigo() {
		return this.codigo;
	}

}
