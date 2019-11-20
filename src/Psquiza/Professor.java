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
	
}
