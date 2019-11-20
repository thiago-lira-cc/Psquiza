package Psquiza;

import java.io.IOException;

import easyaccept.EasyAccept;

public class Facade {
	
	private ControllerPsquiza controlePsquiza;

	public static void main(String[] args) {
		args = new String[] {"Psquiza.Facade", "easyaccept/use_case_1.txt", "easyaccept/use_case_2.txt",
				   "easyaccept/use_case_3.txt", "easyaccept/use_case_4.txt",
				   "easyaccept/use_case_5.txt", "easyaccept/use_case_6.txt",
				   "easyaccept/use_case_7.txt", "easyaccept/use_case_8.txt",
				   "easyaccept/use_case_9.txt", "easyaccept/use_case_10.txt",
				   "easyaccept/use_case_11.txt", "easyaccept/use_case_12SALVAR.txt"};
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
	
	public boolean desassociaProblema(String idPesquisa) {
		return controlePsquiza.desassociaProblema(idPesquisa);
	}
	
	public boolean associaObjetivo(String idPesquisa, String idObjetivo) {
		return controlePsquiza.associaObjetivo(idPesquisa, idObjetivo);
	}
	
	public boolean desassociaObjetivo(String idPesquisa, String idObjetivo) {
		return controlePsquiza.desassociaObjetivo(idPesquisa, idObjetivo);
	}
	
	public String listaPesquisas(String ordem) {
		return controlePsquiza.listaPesquisas(ordem);
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
	public boolean associaAtividade(String codigoPesquisa,String codigoAtividade) {
		return controlePsquiza.associaAtividade(codigoPesquisa, codigoAtividade);
	}
	public boolean desassociaAtividade(String codigoPesquisa,String codigoAtividade) {
		return controlePsquiza.desassociaAtividade(codigoPesquisa, codigoAtividade);
	}
	public void executaAtividade(String codigoAtividade, int item, int duracao) {
		controlePsquiza.executaAtividade(codigoAtividade, item, duracao);
	}
	public int cadastraResultado(String codigoAtividade,String resultado) {
		return controlePsquiza.cadastraResultado(codigoAtividade, resultado);
	}
	public boolean removeResultado(String codigoAtividade,int numeroDoResultado) {
		return controlePsquiza.removeResultado(codigoAtividade, numeroDoResultado);
	}
	public String listaResultados(String codigoAtividade) {
		return controlePsquiza.listaResultados(codigoAtividade);
	}
	public int getDuracao(String codigoAtividade) {
		return controlePsquiza.getDuracao(codigoAtividade);
	}
	public void defineProximaAtividade(String idPrecedente, String idSubsquente) {
		controlePsquiza.defineProximaAtividade(idPrecedente, idSubsquente);
	}
	public void configuraEstrategia(String estrategia) {
		controlePsquiza.configuraEstrategia(estrategia);
	}
	public String proximaAtividade(String codigoPesquisa) {
		return controlePsquiza.proximaAtividade(codigoPesquisa);
	}
	public void tiraProximaAtividade(String idPrecedente) {
		controlePsquiza.tiraProximaAtividade(idPrecedente);
	}
	public int contaProximos(String idPrecedente) {
		return controlePsquiza.contaProximos(idPrecedente);
	}
	public String pegaProximo(String idAtividade, int enesimaAtividade) {
		return controlePsquiza.pegaProximo(idAtividade, enesimaAtividade);
	}
	public String pegaMaiorRiscoAtividades(String idAtividade) {
		return controlePsquiza.pegaMaiorRisco(idAtividade);
	}
	
	public boolean associaPesquisador(String idPesquisa, String emailPesquisador) {
		return controlePsquiza.associaPesquisador(idPesquisa,emailPesquisador);
	}
	
	public boolean desassociaPesquisador(String idPesquisa, String emailPesquisador) {
		return controlePsquiza.desassociaPesquisador(idPesquisa, emailPesquisador);
	}
	
	public void cadastraEspecialidadeProfessor(String email, String formacao, String unidade, String data) {
		controlePsquiza.cadastraEspecialidadeProfessor(email,formacao,unidade,data);
	}
	
	public void gravarResumo(String idPesquisa) throws IOException {
		controlePsquiza.gravarResumo(idPesquisa);
	}
	
	public void gravarResultados(String idPesquisa) throws IOException {
		controlePsquiza.gravarResultados(idPesquisa);
	}
	public void salvar() {
		this.controlePsquiza.salva();
	}
	public void carregar() {
		this.controlePsquiza.carrega();
	}
	
	public void cadastraEspecialidadeAluno(String email, int semestre, double IEA) {
		controlePsquiza.cadastraEspecialidadeAluno(email, semestre, IEA);
	}
	public String listaPesquisadores(String tipo) {
		return controlePsquiza.listaPesquisadores(tipo);
	}
	
	
}