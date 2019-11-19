package Psquiza;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
	
	public String listaPesquisas(String ordem) {
		return controlePesquisa.listaPesquisas(ordem);
	}
	
	public boolean associaAtividade(String codigoPesquisa, String codigoAtividade) {
		return controlePesquisa.associaAtividade(codigoPesquisa, codigoAtividade, controleAtividade);
		
	}
	public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
		return controlePesquisa.desassosciaAtividade(codigoPesquisa, codigoAtividade, controleAtividade);
	}
	public void  executaAtividade(String codigoAtividade,int item,int duracao) {
		controleAtividade.executaAtividade(codigoAtividade,item,duracao);
	}
	public int cadastraResultado(String codigoAtividade, String resultado) {
		return controleAtividade.cadastraResultado(codigoAtividade, resultado);
	}
	public boolean removeResultado(String codigoAtividade,int numeroDoResultado) {
		return controleAtividade.removeResultado(codigoAtividade, numeroDoResultado);
	}
	public String listaResultados(String codigoAtividade) {
		return controleAtividade.listaResultados(codigoAtividade);
	}

	public int getDuracao(String codigoAtividade) {
		return controleAtividade.getDuracao(codigoAtividade);
	}
	
	public void configuraEstrategia(String estrategia) {
		controlePesquisa.configuraEstrategia(estrategia);
	}
	public String proximaAtividade(String codigoPesquisa) {
		return controlePesquisa.proximaAtividade(codigoPesquisa);
	}
	public void defineProximaAtividade(String idPrecedente, String idSubsquente) {
		controleAtividade.defineProximaAtividade(idPrecedente, idSubsquente);
	}
	public void tiraProximaAtividade(String idPrecedente) {
		controleAtividade.tiraProximaAtividade(idPrecedente);
	}
	public int contaProximos(String idPrecedente) {
		return controleAtividade.contaProximos(idPrecedente);
	}
	
	public boolean associaPesquisador(String idPesquisa, String emailPesquisador) {
		excecoes.verificaString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		excecoes.verificaString(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");
		Pesquisador pesquisador = controlePesquisador.verificaBuscaPesquisador(emailPesquisador);
		return controlePesquisa.associaPesquisador(idPesquisa, pesquisador);
	}

	public boolean desassociaPesquisador(String idPesquisa, String emailPesquisador) {
		excecoes.verificaString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		excecoes.verificaString(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");
		Pesquisador pesquisador = controlePesquisador.verificaBuscaPesquisador(emailPesquisador);
		return controlePesquisa.desassociaPesquisador(idPesquisa, pesquisador);
	}

	public void cadastraEspecialidadeProfessor(String email, String formacao, String unidade, String data) {
		excecoes.verificaString(email, "Campo email nao pode ser nulo ou vazio.");
		excecoes.verificaString(formacao, "Campo formacao nao pode ser nulo ou vazio.");
		excecoes.verificaString(unidade, "Campo unidade nao pode ser nulo ou vazio.");
		excecoes.verificaString(data, "Campo data nao pode ser nulo ou vazio.");
		controlePesquisador.cadastraEspecialidadeProfessor(email, formacao, unidade, data);
	}
	
	/**
	 * Exporta o resumo de uma pesquisa em arquivo texto
	 * a partir do seu id
	 * @param idPesquisa
	 * @throws IOException
	 */
	public void gravarResumo(String idPesquisa) throws IOException {
		String resumo = controlePesquisa.geraResumo(idPesquisa);
		File f = new File("_"+idPesquisa+".txt");
		FileWriter fw = new FileWriter(f);
		fw.write(resumo);
		fw.close();
		
	}
	
	/**
	 * Exporta os resultados de uma pesquisa em arquivo texto
	 * a partir do seu id
	 * @param idPesquisa
	 * @throws IOException
	 */
	public void gravarResultados(String idPesquisa) throws IOException {
		String resultado = controlePesquisa.resumeResultados(idPesquisa);
		File f = new File(idPesquisa+"-Resultados.txt");
		FileWriter fw = new FileWriter(f);
		fw.write(resultado);
		fw.close();
	}

	public String pegaProximo(String idAtividade, int enesimaAtividade) {
		return controleAtividade.pegaProximo(idAtividade, enesimaAtividade);
	}

	public String pegaMaiorRisco(String idAtividade) {
		return controleAtividade.pegaMaiorRisco(idAtividade);
	}
}

