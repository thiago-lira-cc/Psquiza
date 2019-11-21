package Psquiza;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Representacao de uma pesquisa.
 * Cada pesquisa tem uma descricao,um campo de interesse,um status(ativado ou nao) e um codigo unico.
 * 
 * @author Iele Passos
 *
 */
public class Pesquisa implements Comparable<Pesquisa>, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1263203464494175378L;
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
	 * Quantidade de objetivos de uma pesquisa
	 */
	private int qtdObjetivos;
	/**
	 * verifica se uma pesquisa ja esta associada
	 * a um problema
	 */
	private boolean isAssociada;
	/**
	 * atividades associadas a pesquisa
	 */
	private Map<String, Atividade> atividades;
	/**
	 * Verifica se a atividade ja foi associada a uma pesquisa.
	 */
	private boolean ativAssociada;
	
	private Map<String,Pesquisador> pesquisadores;
	
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
		this.qtdObjetivos = 0;
		this.ativAssociada = false;
		this.atividades = new LinkedHashMap<>();
		this.pesquisadores = new LinkedHashMap<>();
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
		qtdObjetivos++;
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
		qtdObjetivos-=1;
		return true;
	}
	/**
	 * Gera uma lista de objetivos ordenada pelo maior ID
	 * @return uma lista de objetivos ordenada
	 */
	public List<Objetivo> listaObjetivos() {
		List<Objetivo> lista = new ArrayList<>();
		for (Objetivo o : this.objetivos.values()) {
			lista.add(o);
		}
		Collections.sort(lista);
		Collections.reverse(lista);
		return lista;
	}
	
	/**
	 * Metodo que associa uma atividade a uma pesquisa
	 * @param a
	 * @return
	 */
	public boolean associaAtividade(Atividade a) {
		if(atividades.containsKey(a.getCodigo())) {
			return false;
		}
		this.atividades.put(a.getCodigo(), a);
		a.setAtivAssociada(true);
		return true;
		
	}

	/**
	 * Metodo que desassocia uma atividad ede uma pesquisa
	 * @param a
	 * @return
	 */
	public boolean desassociaAtividade(Atividade a) {
		if(!atividades.containsKey(a.getCodigo())) {
			return false;
		}
		atividades.remove(a.getCodigo());
		a.setAtivAssociada(false);
		return true;
		
	}
	
	/**
	 * Retorna a atividade mais antiga associada
	 * @return String do codigo da atividade
	 */
	public String atividadeMaisAntiga() {
		for (Atividade atividade : this.atividades.values()) {
			if (atividade.contaItensPendentes()>0) {
				return atividade.getCodigo();
			}
		}
		throw new IllegalArgumentException("Pesquisa sem atividades com pendencias.");
	}
	/**
	 * Retorna a atividade de maior duracao
	 * @return String do codigo da atividade
	 */
	public String atividadeMaiorDuracao() {
		Atividade maior = null;
		boolean ehPrimeiro = true;
		for (Atividade atividade : this.atividades.values()) {
			if (ehPrimeiro) {
				maior = atividade;
				ehPrimeiro = false;
			}else {
				if (maior.getDuracao()<atividade.getDuracao()) {
					maior = atividade;
				}
			}
		}
		return maior.getCodigo();
	}
	/**
	 * Retorna a atividade de maior risco
	 * @return String do codigo da atividade
	 */
	public String atividadeMaiorRisco() {
		for (Atividade atividade : this.atividades.values()) {
			if (atividade.getNivelDeRisco().equals("ALTO")) {
				return atividade.getCodigo();
			}
		}
		for (Atividade atividade : this.atividades.values()) {
			if (atividade.getNivelDeRisco().equals("MEDIO")) {
				return atividade.getCodigo();
			}
		}
		for (Atividade atividade : this.atividades.values()) {
			if (atividade.getNivelDeRisco().equals("BAIXO")) {
				return atividade.getCodigo();
			}
		}
		throw new IllegalArgumentException("Atividade sem risco.");
	}
	/**
	 * Retorna a atividade que tem menos pendencias
	 * @return String do codigo da atividade
	 */
	public String atividadeMenosPendencias() {
		Atividade menoresPendencias = null;
		boolean ehPrimeiro = true;
		for (Atividade atividade : this.atividades.values()) {
			if (ehPrimeiro) {
				if (atividade.contaItensPendentes()>0) {
					menoresPendencias = atividade;
					ehPrimeiro = false;
				}
				
			}else {
				if (atividade.contaItensPendentes()>0) {
					if (atividade.contaItensPendentes()<menoresPendencias.contaItensPendentes()) {
						menoresPendencias = atividade;
					}
				}
			}
		}
		return menoresPendencias.getCodigo();
	}

	public int getQtdObjetivos() {
		return qtdObjetivos;
	}
	
	public boolean isAtivAssociada() {
		return ativAssociada;
	}
	
	public Map<String, Atividade> getAtividades() {
		return atividades;
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
	
	public String getCodigo() {
		return this.codigo;
	}
	
	/**
	 * Representacao String de uma pesquisa no sistema.
	 * @return a representacao de uma pesquisa no formato "codigo - descrica - campo de interesse"
	 */
	@Override
	public String toString() {
		return codigo + " - " + descricao +" - "+ campoDeInteresse;
	}

	@Override
	public int compareTo(Pesquisa p) {
		return this.codigo.compareTo(p.getCodigo());
	}
	
	public boolean associaPesquisador(String emailPesquisador, Pesquisador pesquisador) {
		if (this.isAtivado() == false) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		} if (this.getPesquisadores().containsKey(emailPesquisador)) {
			return false;
		}
		pesquisadores.put(emailPesquisador, pesquisador);
		return true;
	}

	public Map<String, Pesquisador> getPesquisadores() {
		return this.pesquisadores;
	}

	

	public boolean desassociaPesquisador(String emailPesquisador) {
		if (this.isAtivado()) {
			if (this.getPesquisadores().containsKey(emailPesquisador)) {
				pesquisadores.remove(emailPesquisador);
				return true;
			} else {
				return false;
			}
			
		} else {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
	}
	
	/**
	 * Gera uma String de resumo de uma pesquisa
	 * @param idPesquisa
	 * @return o resumo
	 */
	public String geraResumo(String idPesquisa) {
		String result = "- Pesquisa: " + toString();
		result +=System.lineSeparator() + "   - Pesquisadores:"+System.lineSeparator();
		for (Pesquisador pesquisador : this.pesquisadores.values()) {
			result += "   - "+pesquisador.toString()+System.lineSeparator();
		}
		result += "- Problema:"+System.lineSeparator()+"   - "+this.problema.toString()+System.lineSeparator();
		result += "- Objetivos:"+System.lineSeparator();
		for (Objetivo o : this.objetivos.values()) {
			result += "   - "+o.toString()+System.lineSeparator();
		}
		result += "- Atividades:"+System.lineSeparator();
		for (Atividade a : this.atividades.values()) {
			result += a.resumeAtividade();
		}
		return result;
	}
	
	/**
	 * Gera uma String com os resultados da pesquisa
	 * @return os resultados
	 */
	public String resumeResultados() {
		String result = "- Pesquisa: " + toString();
		if (atividades.size()>0) {
			result += System.lineSeparator() + "   - Resultados:"+System.lineSeparator()+"    ";
			for (Atividade a : this.atividades.values()) {
				result += a.resultadosAtividade();
			}
		}
		return result;
	}
	
}
