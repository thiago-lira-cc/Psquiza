package Psquiza;
/**
 * Classe que representa uma busca no sistema
 * Tem como caracteristicas codigo, descricao e identificador - interseccao de codigo e descricao.
 * @author Thiago Lira
 *
 */
public class Busca implements Comparable<Busca>{
	private String codigo;
	private String descricao;
	private String identi;
	/**
	 * Costrutor da classe
	 * @param codigo
	 * @param descricao
	 */
	public Busca(String codigo, String descricao) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.identi = codigo+": "+descricao;
	}
	/**
	 * Metodo que compara Buscas pelo identificador dela
	 */
	@Override
	public int compareTo(Busca o) {
		return this.identi.toLowerCase().compareTo(o.getIdenti().toLowerCase());
	}
	/**
	 * Representação textual de uma Busca no formato "codigo: descricao | "
	 */
	@Override
	public String toString() {
		return identi+ " | ";
	}
	/*
	 * GETTER's
	 */
	public String getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getIdenti() {
		return identi;
	}
}
