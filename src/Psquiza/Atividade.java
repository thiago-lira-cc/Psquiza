package Psquiza;

public class Atividade {
	private String descricao;
	private String nivelRisco;
	private String descricaoRisco;
	private String codigo;
	
	public Atividade(String descricao, String nivelRisco, String descricaoRisco, String codigo) {
		this.descricao = descricao;
		this.nivelRisco = nivelRisco;
		this.descricaoRisco = descricaoRisco;
		this.codigo = codigo;
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
	
	

}
