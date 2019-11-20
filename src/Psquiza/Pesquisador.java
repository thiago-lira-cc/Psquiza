package Psquiza;

import java.io.Serializable;

/**
 * Representacao de um pesquisador que tem nome, funcao, biografia, email, FotoURL e um status -ativado ou desativado
 * @author Thiago Lira
 *
 */
public class Pesquisador implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7814018579836109936L;
	/**
	 * NOme do pesquisador
	 */
	private String nome;
	/**
	 * Funcao do pesquisador
	 */
	private String funcao;
	/**
	 * Biografia do pesquisador
	 */
	private String biografia;
	/**
	 * Email do pesquisador
	 */
	private String email;
	/**
	 * URL da foto do pesquisador
	 */
	private String foto;
	/**
	 * Status do pesquisador
	 */
	private boolean ativado;
	
	private Especialidade especialidade;
	/**
	 * Constroi o pesquisador de acordo com os parametros passados
	 * @param nome
	 * @param funcao
	 * @param biografia
	 * @param email
	 * @param foto
	 */
	public Pesquisador(String nome, String funcao, String biografia, String email, String foto) {
		this.nome = nome;
		this.funcao = funcao;
		this.biografia = biografia;
		this.email = email;
		this.foto = foto;
		this.ativado = true;
		this.especialidade = null;
		
	}
	
	public boolean isStatus() {
		return ativado;
	}

	public void setStatus(boolean status) {
		this.ativado = status;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getEmail() {
		return email;
	}
	/**
	 * Cria uma representacao textual de um pesquisador no formato "Nome (Funcao) - Biografia - Email - FotoURL"
	 */
	@Override
	public String toString() {
		return nome + " (" + funcao + ") - " + biografia + " - " + email
				+ " - " + foto;
	}

	public void cadastraEspecialidadeProfessor(String formacao, String unidade, String data) {
		this.especialidade = new Professor(formacao,unidade,data);
		
	}
}
