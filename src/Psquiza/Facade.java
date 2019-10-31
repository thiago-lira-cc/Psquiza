package Psquiza;

import easyaccept.EasyAccept;

public class Facade {
	private ControllerPesquisa controlePesquisa;
	private ControllerPesquisador controlePesquisador;
	private ControllerProblema controleProblema;
	private ControllerObjetivo controleObjetivo;

	public static void main(String[] args) {
		args = new String[] {"Psquiza.Facade", "testes_aceitacao/use_case_1.txt", "testes_aceitacao/use_case_2.txt",
							"testes_aceitacao/use_case_3.txt", "testes_aceitacao/use_case_4.txt"};
		EasyAccept.main(args);
	}
	
	public Facade() {
		this.controlePesquisa = new ControllerPesquisa();
		this.controlePesquisador = new ControllerPesquisador();
		this.controleProblema = new ControllerProblema();
		this.controleObjetivo = new ControllerObjetivo();
	}
	
	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		return controlePesquisa.cadastraPesquisa(descricao, campoDeInteresse);
	}
	
	public void alteraPesquisa(String codigo, String conteudo, String novoConteudo) {
		controlePesquisa.alteraPesquisa(codigo, conteudo, novoConteudo);
	}
	
	public void encerraPesquisa(String codigo, String motivo) {
		controlePesquisa.encerraPesquisa(codigo, motivo);
	}
	public void ativaPesquisa(String codigo) {
		controlePesquisa.ativaPesquisa(codigo);
	}
	public String exibePesquisa(String codigo) {
		return controlePesquisa.exibePesquisa(codigo);
	}
	public boolean pesquisaEhAtiva(String codigo) {
		return controlePesquisa.pesquisaEhAtiva(codigo);
	}
	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String foto) {
		controlePesquisador.cadastraPesquisador(nome, funcao, biografia, email, foto);
	}
	
	public void desativaPesquisador(String email) {
		controlePesquisador.desativaPesquisador(email);
	}
	
	public String exibePesquisador(String email) {
		return controlePesquisador.exibePesquisador(email);
	}
	
	public void alteraPesquisador(String email, String atributo, String novoValor) {
		controlePesquisador.alteraPesquisador(email, atributo, novoValor);
	}
	
	public void ativaPesquisador(String email) {
		controlePesquisador.ativaPesquisador(email);
	}
	
	public boolean pesquisadorEhAtivo(String email) {
		return controlePesquisador.pesquisadorEhAtivado(email);
	}
	
	public String cadastraProblema(String descricao, int viabilidade) {
		return controleProblema.cadastraProblema(descricao, viabilidade);
	}
		
	public String exibeProblema(String codigo) {
		return controleProblema.exibeProblema(codigo);
	}
	
	public void apagarProblema(String codigo) {
		controleProblema.apagarProblema(codigo);
	}
	
	public String cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
		return controleObjetivo.cadastraObjetivo(tipo, descricao, aderencia, viabilidade);
	}
	
	public String exibeObjetivo(String codigo) {
		return controleObjetivo.exibeObjetivo(codigo);
	}
	
	public void apagarObjetivo(String codigo) {
		controleObjetivo.apagarObjetivo(codigo);
	}
}
