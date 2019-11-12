package Psquiza;

import java.util.HashMap;
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
	/**
	 * verifica se uma pesquisa ja esta associada
	 * a um problema
	 */
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
		this.objetivos = new HashMap<>();
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
	/**
	 * Associa um problema a uma pesquisa. Cada pesquisa
	 * so pode estar associada a um unico problema
	 * @param p problema
	 * @return se a associacao foi bem sucedida
	 */
	public boolean associaProblema(Problema p) {
		if (isAtivado()==false) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		} else if (this.problema==p) {
			return false;
		} else if (isAssociada()==true) {
			throw new IllegalArgumentException("Pesquisa ja associada a um problema.");
		}
		this.problema = p;
		setAssociada(true);
		return true;
	}
	/**
	 * desassocia umm problema de uma pesquisa
	 * @return se a desassociacao foi bem sucedida
	 */
	public boolean desassociaProblema() {
		if (isAtivado()==false) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		} else if (isAssociada()==false) {
			return false;
		}
		this.problema = null;
		setAssociada(false);
		return true;
	}
	/**
	 * associa um objetivo a uma pesquisa. Cada objetivo so
	 * pode estar associado a uma pesquisa, no entanto, uma pesquisa
	 * pode estar associada a varios objetivos
	 * @param idObjetivo
	 * @param o objetivo
	 * @return se a associacao foi bem sucedida
	 */
	public boolean associaObjetivo(String idObjetivo, Objetivo o) {
		if (isAtivado()==false) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		} else if (objetivos.containsKey(idObjetivo)) {
			return false;
		} else if (o.isAssociado()==true) {
			throw new IllegalArgumentException("Objetivo ja associado a uma pesquisa.");
		}
		o.setAssociado(true);
		objetivos.put(idObjetivo, o);
		return true;
			
	}
	/**
	 * desassocia um objetivo de uma pesquisa
	 * @param o objetivo
	 * @return se a desassociacao foi bem sucedida
	 */
	public boolean desassociaObjetivo(Objetivo o) {
		if (isAtivado()==false) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		} else if (!objetivos.containsKey(o.getCodigo())) {
			return false;
		}
		o.setAssociado(false);
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
