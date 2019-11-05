package Psquiza;

public class Busca implements Comparable<Busca>{
	private String codigo;
	private String descricao;
	private String identi;
	
	public Busca(String codigo, String descricao) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.identi = codigo+": "+descricao;
	}

	@Override
	public int compareTo(Busca o) {
		return this.identi.toLowerCase().compareTo(o.getIdenti().toLowerCase());
	}

	@Override
	public String toString() {
		return identi;
	}

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
