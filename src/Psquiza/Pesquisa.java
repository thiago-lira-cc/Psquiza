package Psquiza;

public class Pesquisa {
	
	private String descricao;
	private String campoDeInteresse;
	private boolean ativado;
	private String codigo;
	
	public Pesquisa(String descricao,String campoDeInteresse, String codigo) {
		this.descricao = descricao;
		this.campoDeInteresse = campoDeInteresse;
		this.ativado = true;
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCampoDeInteresse() {
		return campoDeInteresse;
	}

	public void setCampoDeInteresse(String campoDeInteresse) {
		this.campoDeInteresse = campoDeInteresse;
	}

	public boolean isAtivado() {
		return ativado;
	}

	public void setAtivado(boolean ativado) {
		this.ativado = ativado;
	}
	
	@Override
	public String toString() {
		return codigo + " - " + descricao +" - "+ campoDeInteresse;
	}

}
