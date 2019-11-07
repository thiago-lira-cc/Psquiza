package Psquiza;

import easyaccept.EasyAccept;

public class Facade {
	
	private ControllerPsquiza controlePsquiza;

	public static void main(String[] args) {
		args = new String[] {"Psquiza.Facade", "testes_aceitacao/use_case_1.txt", "testes_aceitacao/use_case_2.txt",
				   "testes_aceitacao/use_case_3.txt", "testes_aceitacao/use_case_4.txt",
				   "testes_aceitacao/use_case_5.txt", "testes_aceitacao/use_case_6.txt",
				   "testes_aceitacao/use_case_7.txt", "testes_aceitacao/use_case_8.txt"};
		EasyAccept.main(args);
	}
	
	public Facade() {
		this.controlePsquiza = new ControllerPsquiza();
	}
	
	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		return controlePsquiza.cadastraPesquisa(descricao, campoDeInteresse);
	}
	
	public void alteraPesquisa(String codigo, String conteudo, String novoConteudo) {
		controlePsquiza.alteraPesquisa(codigo, conteudo, novoConteudo);
	}
	
	public void encerraPesquisa(String codigo, String motivo) {
		controlePsquiza.encerraPesquisa(codigo, motivo);
	}
	public void ativaPesquisa(String codigo) {
		controlePsquiza.ativaPesquisa(codigo);
	}
	public String exibePesquisa(String codigo) {
		return controlePsquiza.exibePesquisa(codigo);
	}
	public boolean pesquisaEhAtiva(String codigo) {
		return controlePsquiza.pesquisaEhAtiva(codigo);
	}
	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String foto) {
		controlePsquiza.cadastraPesquisador(nome, funcao, biografia, email, foto);
	}
	
	public void desativaPesquisador(String email) {
		controlePsquiza.desativaPesquisador(email);
	}
	
	public String exibePesquisador(String email) {
		return controlePsquiza.exibePesquisador(email);
	}
	
	public void alteraPesquisador(String email, String atributo, String novoValor) {
		controlePsquiza.alteraPesquisador(email, atributo, novoValor);
	}
	
	public void ativaPesquisador(String email) {
		controlePsquiza.ativaPesquisador(email);
	}
	
	public boolean pesquisadorEhAtivo(String email) {
		return controlePsquiza.pesquisadorEhAtivado(email);
	}
	
	public String cadastraProblema(String descricao, int viabilidade) {
		return controlePsquiza.cadastraProblema(descricao, viabilidade);
	}
		
	public String exibeProblema(String codigo) {
		return controlePsquiza.exibeProblema(codigo);
	}
	
	public void apagarProblema(String codigo) {
		controlePsquiza.apagarProblema(codigo);
	}
	
	public String cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
		return controlePsquiza.cadastraObjetivo(tipo, descricao, aderencia, viabilidade);
	}
	
	public String exibeObjetivo(String codigo) {
		return controlePsquiza.exibeObjetivo(codigo);
	}
	
	public void apagarObjetivo(String codigo) {
		controlePsquiza.apagarObjetivo(codigo);
	}
	
	public boolean associaProblema(String idPesquisa, String idProblema) {
		return controlePsquiza.associaProblema(idPesquisa, idProblema);
	}
	
	public boolean desassociaProblema(String idPesquisa, String idProblema) {
		return controlePsquiza.desassociaProblema(idPesquisa, idProblema);
	}
	
	public boolean associaObjetivo(String idPesquisa, String idObjetivo) {
		return controlePsquiza.associaObjetivo(idPesquisa, idObjetivo);
	}
	
	public boolean desassociaObjetivo(String idPesquisa, String idObjetivo) {
		return controlePsquiza.desassociaObjetivo(idPesquisa, idObjetivo);
	}
	
	public void cadastraAtividade(String descricao, String nivelRisco,String descricaoRisco) {
		controlePsquiza.cadastraAtividade(descricao, nivelRisco, descricaoRisco);
	}
	
	public void apagaAtividade(String codigo) {
		controlePsquiza.apagaAtividade(codigo);
	}
	
	public void cadastraItem(String codigo, String item) {
		controlePsquiza.cadastraItem(codigo, item);
	}
	
	public String exibeAtividade(String codigo) {
		return controlePsquiza.exibeAtividade(codigo);
	}
	
	public String busca(String termo) {
		return controlePsquiza.busca(termo); 
	}
	
	public String busca(String termo, int numeroDoResultado) {
		return controlePsquiza.busca(termo, numeroDoResultado); 
	}
	
	public int contaResultadosBusca(String termo) {
		return controlePsquiza.contaResultadosBusca(termo);
	}
	
	public int contaItensPendentes(String codigo) {
		return controlePsquiza.contaItensPendentes(codigo);
	}
	public int contaItensRealizados(String codigo) {
		return controlePsquiza.contaItensRealizados(codigo);
	}
}