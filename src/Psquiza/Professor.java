package Psquiza;

import java.io.Serializable;

public class Professor implements Especialidade, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8579078548171159782L;
	private String formacao;
	private String unidade;
	private String data;
	
	public Professor(String formacao, String unidade, String data) {
		this.formacao = formacao;
		this.unidade = unidade;
		this.data = data;
	}

	@Override
	public String toString() {
		return this.formacao + " - " + this.unidade + " - " + this.data;
		
	}

	@Override
	public void setSemestre(int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setIEA(double parseDouble) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFormacao(String novoValor) {
		this.formacao = novoValor;
		
	}

	@Override
	public void setUnidade(String novoValor) {
		this.unidade = novoValor;
		
	}

	@Override
	public void setData(String novoValor) {
		this.data = novoValor;
		
	}
	
}
