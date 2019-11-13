package Psquiza;

import java.util.ArrayList;
import java.util.Collections;
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
	
	public boolean associaProblema(String idPesquisa, String idProblema) {
		return controlePesquisa.associaProblema(idPesquisa, idProblema, controleProblema);
	}
	
	public boolean desassociaProblema(String idPesquisa) {
		return controlePesquisa.desassociaProblema(idPesquisa, controleProblema);
	}
	
	public boolean associaObjetivo(String idPesquisa, String idObjetivo) {
		return controlePesquisa.associaObjetivo(idPesquisa, idObjetivo, controleObjetivo);
	}
	
	public boolean desassociaObjetivo(String idPesquisa, String idObjetivo) {
		return controlePesquisa.desassociaObjetivo(idPesquisa, idObjetivo, controleObjetivo);
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
	
	public int contaItensPendentes(String codigo) {
		return controleAtividade.contaItensPendentes(codigo);
	}
	
	public int contaItensRealizados(String codigo) {
		return controleAtividade.contaItensRealizados(codigo);
	}

	/**
	 * Metodo que q busca um termo por todo o sistema
	 * @param termo
	 * @return String do codigo e do campo em que o termo foi achado em uma ordem especifica
	 */
	public String busca(String termo) {
		excecoes.verificaString(termo, "Campo termo nao pode ser nulo ou vazio.");
		
		String strFinal = "";
		
		List<Busca> resultados = Pesquisa(termo);
		for (Busca busca : resultados) {
			strFinal += busca.toString();
		}
		if (strFinal.length()==0) {
			return "";
		}
		strFinal = strFinal.substring(0, strFinal.length()-3);
		return strFinal;
	}
	/**
	 * Metodo que busca um termo especifico no sistema e retorna o resultado na posicao passada como parametro
	 * @param termo
	 * @param numeroDoResultado
	 * @return String do codigo e campo do resultado desejado
	 */
	public String busca(String termo, int numeroDoResultado) {
		excecoes.verificaString(termo, "Campo termo nao pode ser nulo ou vazio.");
		excecoes.verificaNumeroDoResultado(numeroDoResultado, "Numero do resultado nao pode ser negativo");
		List<Busca> resultados = Pesquisa(termo);
		
		if (resultados.size()==0||numeroDoResultado>resultados.size()) {
			throw new IllegalArgumentException("Entidade nao encontrada.");
		}
		
		return resultados.get(numeroDoResultado-1).toString().substring(0, resultados.get(numeroDoResultado-1).toString().length()-3);
	}
	/**
	 * Metodo que mostra quantos resultados foram achados
	 * @param termo
	 * @return int do numero de rsultados
	 */
	public int contaResultadosBusca(String termo) {
		excecoes.verificaString(termo, "Campo termo nao pode ser nulo ou vazio.");
		List<Busca> resultados = Pesquisa(termo);
		if (resultados.size()==0) {
			throw new IllegalArgumentException("Nenhum resultado encontrado");
		}
		return resultados.size();
	}
	/**
	 * Metodo que é usado pelos outros metodos para fazer a busca
	 * @param termo
	 * @return List dos resultados achados
	 */
	private List<Busca> Pesquisa(String termo){
		List<Busca> resultados = new ArrayList<Busca>();
		
		List<Busca> listaDePesquisas = controlePesquisa.busca(termo);
		Collections.reverse(listaDePesquisas);
		resultados.addAll(listaDePesquisas);
		
		List<Busca> listaDePesquisadores = controlePesquisador.busca(termo);
		Collections.reverse(listaDePesquisadores);
		resultados.addAll(listaDePesquisadores);
		
		List<Busca> listaDeProblemas = controleProblema.busca(termo);
		Collections.reverse(listaDeProblemas);
		resultados.addAll(listaDeProblemas);
		
		List<Busca> listaDeObjetivos = controleObjetivo.busca(termo);
		Collections.reverse(listaDeObjetivos);
		resultados.addAll(listaDeObjetivos);
		
		List<Busca> listaDeAtividade = controleAtividade.busca(termo);
		Collections.reverse(listaDeAtividade);
		resultados.addAll(listaDeAtividade);
		
		return resultados;
	}
}
