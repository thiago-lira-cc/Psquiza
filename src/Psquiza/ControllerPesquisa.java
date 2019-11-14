package Psquiza;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerPesquisa implements Services{

	private Excecoes excecoes = new Excecoes();
	/**
	 * Mapa de pesquisadores identificados pelo codigo
	 */
	private Map<String, Pesquisa> pesquisas;
	/**
	 * Constroi a classe instanciando um novo HashMap
	 */
	public ControllerPesquisa() {
		this.pesquisas = new HashMap<String, Pesquisa>();
	}
	
	/**
	 * Metodo responsavel por cadastrar uma pesquisa no sistema.
	 * @param descricao
	 * @param campoDeInteresse
	 * @return
	 */
	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		excecoes.verificaString(descricao, "Descricao nao pode ser nula ou vazia.");
		excecoes.verificaString(campoDeInteresse,"Formato do campo de interesse invalido.");
		excecoes.verificaCampo(campoDeInteresse, "Formato do campo de interesse invalido.");
		String codigo = campoDeInteresse.substring(0, 3).toUpperCase() + "1";
		int cont = 1;
		while(pesquisas.containsKey(codigo)) {
			cont += 1;
			codigo = codigo.substring (0, codigo.length() - 1);
			codigo += cont;
		}
		Pesquisa p = new Pesquisa(descricao, campoDeInteresse, codigo);
		pesquisas.put(codigo, p);
		return codigo;
	}
	/**
	 * Metodo responsavel por editar uma pesquisa no sistema.
	 * @param codigo
	 * @param conteudo
	 * @param novoConteudo
	 */
	public void alteraPesquisa(String codigo, String conteudo, String novoConteudo) {
		if(pesquisas.containsKey(codigo)) {
			if(pesquisas.get(codigo).isAtivado()== true) {
				
				switch(conteudo) {
				
				case "DESCRICAO" :
					excecoes.verificaString(novoConteudo, "Descricao nao pode ser nula ou vazia.");
					pesquisas.get(codigo).setDescricao(novoConteudo);
					break;
				
				case "CAMPO":
					excecoes.verificaString(novoConteudo, "Formato do campo de interesse invalido.");
					pesquisas.get(codigo).setCampoDeInteresse(novoConteudo);
					break;
				
				default:
					throw new IllegalArgumentException("Nao e possivel alterar esse valor de pesquisa.");	
				}
			}else {
				throw new IllegalArgumentException("Pesquisa desativada.");
			}
		}else {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
	}
	
	/**
	 * Metodo responsavel por encerrar uma pesquisa.
	 * @param codigo
	 * @param motivo
	 */
	public void encerraPesquisa(String codigo, String motivo) {
		excecoes.verificaString(motivo, "Motivo nao pode ser nulo ou vazio.");
		if(pesquisas.containsKey(codigo)) {
			if(pesquisas.get(codigo).isAtivado() == true) {
				pesquisas.get(codigo).setAtivado(false);
			}else{
				throw new IllegalArgumentException("Pesquisa desativada.");
			}
		}else{
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
	}
	
	/**
	 * Metodo responsavel por ativar uma pesquisa.
	 * @param codigo
	 */
	public void ativaPesquisa(String codigo) {
		if(pesquisas.containsKey(codigo)) {
			if(pesquisas.get(codigo).isAtivado() == false) {
				pesquisas.get(codigo).setAtivado(true);
			} else {
				throw new IllegalArgumentException("Pesquisa ja ativada.");			
			} 
		}else {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
	}
	
	/**
	 * Metodo responsavel por exibir uma pesquisa.
	 * @param codigo
	 * @return
	 */
	public String exibePesquisa(String codigo) {
		if(pesquisas.containsKey(codigo)) {
			return pesquisas.get(codigo).toString();
		}
		throw new IllegalArgumentException("Pesquisa nao encontrada.");
	}
	
	/**
	 * Metodo responsavel por verificar o status de uma pesquisa.
	 * @param codigo
	 * @return
	 */
	public boolean pesquisaEhAtiva(String codigo) {
		excecoes.verificaString(codigo, "Codigo nao pode ser nulo ou vazio.");
		if(pesquisas.containsKey(codigo)) {
			return pesquisas.get(codigo).isAtivado();
		}
		throw new IllegalArgumentException("Pesquisa nao encontrada.");
	}

	/**
	 * Metodo que verifica se entre as pesquisas cadastradas existe o termo 
	 * @param termo
	 * @return List com os resultados
	 */
	@Override
	public List<Busca> busca(String termo) {
		List<Busca> resultados = new ArrayList<Busca>();
		List<Pesquisa> pesquisas = getPesquisas();
		Collections.sort(pesquisas);
		for (Pesquisa pesquisa : pesquisas) {
			if (pesquisa.getDescricao().toLowerCase().contains(termo.toLowerCase())
				&& pesquisa.getCampoDeInteresse().toLowerCase().contains(termo.toLowerCase())) {
				
				Busca busca1 = new Busca(pesquisa.getCodigo(), pesquisa.getCampoDeInteresse());
				resultados.add(busca1);
				
				Busca busca2 = new Busca(pesquisa.getCodigo(), pesquisa.getDescricao());
				resultados.add(busca2);
				
			}else if(pesquisa.getDescricao().toLowerCase().contains(termo.toLowerCase())){
				Busca busca = new Busca(pesquisa.getCodigo(), pesquisa.getDescricao());
				resultados.add(busca);
			}else if (pesquisa.getCampoDeInteresse().toLowerCase().contains(termo.toLowerCase())) {
				Busca busca = new Busca(pesquisa.getCodigo(), pesquisa.getCampoDeInteresse());
				resultados.add(busca);
			}

		}

		return resultados;
	}
	/**
	 * Metodo responsavel por retornar numa lista as pesquisas cadastradas
	 * @return Lista de pesquisas
	 */
	private List<Pesquisa> getPesquisas() {
		List<Pesquisa> pesquisas = new ArrayList<Pesquisa>();
		for (Pesquisa pesquisa : this.pesquisas.values()) {
			pesquisas.add(pesquisa);
		}
		return pesquisas;
	}

	/**
	 * Associa um problema a uma pesquisa, passando os
	 * respectivos ids e o controller de problema para
	 * ter acesso a um determinado problema.
	 * @param idPesquisa
	 * @param idProblema
	 * @param controleProblema
	 * @return se a associacao foi bem sucedida
	 */
	public boolean associaProblema(String idPesquisa, String idProblema, ControllerProblema controleProblema) {
		excecoes.verificaString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		excecoes.verificaString(idProblema, "Campo idProblema nao pode ser nulo ou vazio.");
		if (pesquisas.containsKey(idPesquisa)) {
			if (controleProblema.problemaJaCadastrado(idProblema)) {
				Pesquisa pesq = pesquisas.get(idPesquisa);
				Problema prob = controleProblema.pegaProblema(idProblema);
				return pesq.associaProblema(prob);
			} else {
				throw new IllegalArgumentException("");
			}
		} else {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
	}
	/**
	 * Desassocia um problema em uma pesquisa a partir
	 * do id de pesquisa e do controller de Problema.
	 * @param idPesquisa
	 * @param controleProblema
	 * @return se a desassociacao foi bem sucedida.
	 */
	public boolean desassociaProblema(String idPesquisa, ControllerProblema controleProblema) {
		excecoes.verificaString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		if (pesquisas.containsKey(idPesquisa)) {
			Pesquisa pesq = pesquisas.get(idPesquisa);
			return pesq.desassociaProblema();
		} else {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
	}
	
	/**
	 * Associa um objetivo a uma pesquisa a partir dos seus
	 * respectivos ids e do controller de objetivo, utilizado para
	 * acessar o objetivo em questao
	 * @param idPesquisa
	 * @param idObjetivo
	 * @param controleObjetivo
	 * @return se a associacao foi bem sucedida
	 */
	public boolean associaObjetivo(String idPesquisa, String idObjetivo, ControllerObjetivo controleObjetivo) {
		excecoes.verificaString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		excecoes.verificaString(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		if (pesquisas.containsKey(idPesquisa)) {
			Pesquisa p = pesquisas.get(idPesquisa);
			Objetivo o = controleObjetivo.pegaObjetivo(idObjetivo);
			return p.associaObjetivo(idObjetivo, o);
		} else {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
	}
	
	/**
	 * Desassocia um objetivo a uma pesquisa a partir dos seus
	 * respectivos ids e do controller de objetivo, utilizado para
	 * acessar o objetivo em questao
	 * @param idPesquisa
	 * @param idObjetivo
	 * @param controleObjetivo
	 * @return
	 */
	public boolean desassociaObjetivo(String idPesquisa, String idObjetivo, ControllerObjetivo controleObjetivo) {
		excecoes.verificaString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		excecoes.verificaString(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		if (pesquisas.containsKey(idPesquisa)) {
			Pesquisa p = pesquisas.get(idPesquisa);
			Objetivo o = controleObjetivo.pegaObjetivo(idObjetivo);
			return p.desassociaObjetivo(o);
		} else {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
	}
	/**
	 * Gera uma String ordenada de pesquisas de acordo com estar
	 * associadas a problemas ou nao
	 * @param pesquisas
	 * @return as pesquisas ordenadas
	 */
	public String listaPesquisasProblema(List<Pesquisa> pesquisas) {
		List<Pesquisa> associadas = new ArrayList<Pesquisa>();
		List<Pesquisa> naoAssociadas = new ArrayList<Pesquisa>();
		String listaAssociadas = "";
		String listaNaoAssociadas = "";
		for (Pesquisa p : pesquisas) {
			if (p.isAssociada()) {
				associadas.add(p);
			} else {
				naoAssociadas.add(p);
			}
		}
		for (Pesquisa a : associadas) {
			if (listaAssociadas.isEmpty()) {
				listaAssociadas += a.toString();
			} else {
				listaAssociadas += " | " + a.toString();
			}
		}
		for (Pesquisa n : naoAssociadas) {
			listaNaoAssociadas += " | " + n.toString();
		}
		return listaAssociadas + listaNaoAssociadas;
	}
	/**
	 * Gera uma String ordenada de pesquisas a partir da
	 * quantidade de objetivos	
	 * @param pesquisas
	 * @return as pesquisas ordenadas
	 */
	public String listaPesquisasObj(List<Pesquisa> pesquisas) {
		List<Pesquisa> listaPesquisasO = pesquisas;

		Collections.sort(listaPesquisasO, new ComparadorPesquisasQtdObjetivos());
		Collections.reverse(listaPesquisasO);
		String lista = "";
		for (Pesquisa p : listaPesquisasO) {
			if (lista.isEmpty()) {
				lista += p.toString();
			} else {
				lista += " | " + p.toString();
			}
		}
		return lista;
	}
	/**
	 * Gera uma String ordenada de pesquisas a partir dos seus
	 * respectivos IDs
	 * @param pesquisas
	 * @return as pesquisas ordenadas
	 */
	public String listaPesquisasPesquisa(List<Pesquisa> pesquisas) {
		String lista = "";
		for (Pesquisa p : pesquisas) {
			if (lista.isEmpty()) {
				lista += p.toString();
			} else {
				lista += " | " + p.toString();
			}
		}
		return lista;
	}
	/**
	 * Gera uma String ordenada de pesquisas de acordo com
	 * a ordem passada como parametro
	 * @param ordem
	 * @return as pesquisas ordenadas
	 */
	public String listaPesquisas(String ordem) {
		excecoes.verificaString(ordem, "Valor invalido da ordem");
		List<Pesquisa> pesquisas = getPesquisas();
		Collections.sort(pesquisas);
		Collections.reverse(pesquisas);
		if (ordem.equalsIgnoreCase("problema")) {
			return listaPesquisasProblema(pesquisas);
		} else if (ordem.equalsIgnoreCase("objetivos")) {
			return listaPesquisasObj(pesquisas);
		} else if (ordem.equalsIgnoreCase("pesquisa")) {
			return listaPesquisasPesquisa(pesquisas);
		} else {
			throw new IllegalArgumentException("Valor invalido da ordem");
		}
	}
}