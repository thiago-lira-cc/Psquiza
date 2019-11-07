package Psquiza;

import java.util.Map;

/**
 * Representacao de uma pesquisa.
 * Cada pesquisa tem uma descricao,um campo de interesse,um status(ativado ou nao) e um codigo unico.
 * 
 * @author Iele Passos
 *
 */
public class Pesquisa{
	/**
	 * A descricao da pesquisa.
	 */
	private String descricao;
	/**
	 * O campo de interesse da pesquisa.
	 */
	private String campoDeInteresse;
	/**
	 * O status da pesquisa, que comeca ativado.
	 */
	private boolean ativado;
	/**
	 * O codigo unico, gerado no cadastro, da pesquisa.
	 */
	private String codigo;
	/**
	 * Problema associado a pesquisa
	 */
	private Problema problema;
	/**
	 * Objetivos associados a pesquisa
	 */
	private Map<String, Objetivo> objetivos;
	private boolean isAssociada;
	
	/**
	 * Constroi uma pesquisa no sistema.
	 * @param descricao
	 * @param campoDeInteresse
	 * @param codigo
	 */
	public Pesquisa(String descricao,String campoDeInteresse, String codigo) {
		this.descricao = descricao;
		this.campoDeInteresse = campoDeInteresse;
		this.ativado = true;
		this.codigo = codigo;
		this.isAssociada = false;
	}
	
	public boolean isAssociada() {
		return isAssociada;
	}

	public void setAssociada(boolean isAssociada) {
		this.isAssociada = isAssociada;
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
	
	public boolean associaProblema(Problema p) {
		if (isAtivado()==false) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
		if (isAssociada()==true) {
			throw new IllegalArgumentException("Pesquisa ja associada a um problema.");
		}
		this.problema = p;
		setAssociada(true);
		return true;
	}
	
	public boolean desassociaProblema(Problema prob) {
		if (isAtivado()==false) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
		this.problema = null;
		setAssociada(false);
		return true;
	}

	public boolean associaObjetivo(Objetivo o) {
		if (isAtivado()==false) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
		if (o.isAssociado()==true) {
			throw new IllegalArgumentException("Objetivo ja associado a uma pesquisa.");
		}
		o.setAssociado(true);
		objetivos.put(o.getCodigo(), o);
		return true;
			
	}

	public boolean desassociaObjetivo(Objetivo o) {
		if (isAtivado()==false) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
		objetivos.remove(o.getCodigo());
		return true;
	}
	
	/**
	 * Representacao String de uma pesquisa no sistema.
	 * @return a representacao de uma pesquisa no formato "codigo - descrica - campo de interesse"
	 */
	@Override
	public String toString() {
		return codigo + " - " + descricao +" - "+ campoDeInteresse;
	}

	public String getCodigo() {
		return this.codigo;
	}

}
