package Psquiza;

import java.util.List;

public class ControllerPsquiza {
	private Excecoes excecoes;
	private ControllerPesquisa controlePesquisa;
	private ControllerPesquisador controlePesquisador;
	private ControllerProblema controleProblema;
	private ControllerObjetivo controleObjetivo;
	private ControllerAtividade controleAtividade;
	
	public ControllerPsquiza() {
		this.excecoes = new Excecoes();
		this.controlePesquisa = new ControllerPesquisa();
		this.controlePesquisador = new ControllerPesquisador();
		this.controleProblema = new ControllerProblema();
		this.controleObjetivo = new ControllerObjetivo();
		this.controleAtividade =  new ControllerAtividade();
	}

	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		return this.controlePesquisa.cadastraPesquisa(descricao, campoDeInteresse);
	}

	public void alteraPesquisa(String codigo, String conteudo, String novoConteudo) {
		this.controlePesquisa.alteraPesquisa(codigo, conteudo, novoConteudo);
	}

	public void encerraPesquisa(String codigo, String motivo) {
		this.controlePesquisa.encerraPesquisa(codigo, motivo);
	}

	public void ativaPesquisa(String codigo) {
		this.controlePesquisa.ativaPesquisa(codigo);
	}

	public String exibePesquisa(String codigo) {
		return this.controlePesquisa.exibePesquisa(codigo);
	}

	public boolean pesquisaEhAtiva(String codigo) {
		return this.controlePesquisa.pesquisaEhAtiva(codigo);
	}

	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String foto) {
		this.controlePesquisador.cadastraPesquisador(nome, funcao, biografia, email, foto);
	}

	public void desativaPesquisador(String email) {
		this.controlePesquisador.desativaPesquisador(email);
	}

	public String exibePesquisador(String email) {
		return this.controlePesquisador.exibePesquisador(email);
	}

	public void alteraPesquisador(String email, String atributo, String novoValor) {
		this.controlePesquisador.alteraPesquisador(email, atributo, novoValor);
	}

	public void ativaPesquisador(String email) {
		this.controlePesquisador.ativaPesquisador(email);
	}

	public boolean pesquisadorEhAtivado(String email) {
		return this.controlePesquisador.pesquisadorEhAtivado(email);
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
	
	public void cadastraAtividade(String descricao, String nivelRisco,String descricaoRisco) {
		controleAtividade.cadastraAtividade(descricao, nivelRisco, descricaoRisco);
	}
	
	public void apagaAtividade(String codigo) {
		controleAtividade.apagaAtividade(codigo);
	}
	
	public void cadastraItem(String codigo, String item) {
		controleAtividade.cadastraItem(codigo, item);
	}
	
	public String exibeAtividade(String codigo) {
		return controleAtividade.exibeAtividade(codigo);
	}
	
	public String busca(String termo) {
		excecoes.verificaString(termo, "Campo termo nao pode ser nulo ou vazio.");
		
		String strFinal = "";
		/*
		 * Pesquisa
		 */
		List<Busca> listaDePesquisas = controlePesquisa.buscaPesquisa(termo);
		
		for (int i = listaDePesquisas.size()-1; i >=0; i--) {
			strFinal += listaDePesquisas.get(i).toString()+ " | ";
		}
		/*
		 * Pesquisador
		 */
		List<Busca> listaDePesquisadores = controlePesquisador.buscaPesquisador(termo);
		
		for (int i = listaDePesquisadores.size()-1; i >=0; i--) {
			strFinal += listaDePesquisadores.get(i).toString()+ " | ";
		}
		/*
		 * Problema
		 */
		List<Busca> listaDeProblemas = controleProblema.buscaProblema(termo);
		
		for (int i = listaDeProblemas.size()-1; i >=0; i--) {
			strFinal += listaDeProblemas.get(i).toString()+ " | ";
		}
		/*
		 * Objetivo
		 */
		
		List<Busca> listaDeObjetivos = controleObjetivo.buscaObjetivo(termo);
		
		for (int i = listaDeObjetivos.size()-1; i >=0; i--) {
			strFinal += listaDeObjetivos.get(i).toString()+ " | ";
		}
		/*
		 * Atividade
		 */
		
		List<Busca> listaDeAtividade = controleAtividade.buscaAtividade(termo);
		
		for (int i = listaDeAtividade.size()-1; i >=0; i--) {
			strFinal += listaDeAtividade.get(i).toString()+ " | ";
		}
		
		strFinal = strFinal.substring(0, strFinal.length()-3);
		return strFinal;
	}
}
