package Psquiza;

/**
 * Representacao de um problema, que tem um codigo
 * identificador, uma descricao e um valor de viabilidade
 * (um inteiro de 1 a 5).
 * @author Jose Felix
 */
public class Problema {
	/**
	 * Codigo identificador do problema
	 */
	private String codigo;
	/**
	 * Descricao do problema
	 */
	private String descricao;
	/**
	 * Viabilidade do problema
	 */
	private Integer viabilidade;
	
	/**
	 * Constroi um problema no sistema
	 * @param codigo
	 * @param descricao
	 * @param viabilidade
	 */
	public Problema(String codigo, String descricao, int viabilidade) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.viabilidade = viabilidade;
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
		Problema other = (Problema) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	/**
	 * Cria uma representacao textual de um problema, no formato
	 * "Codigo - Descricao - Viabilidade".
	 */
	@Override
	public String toString() {
		return codigo + " - " + descricao + " - " + viabilidade;
	}

}
