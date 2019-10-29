package Psquiza;

public class Problema {
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

}
