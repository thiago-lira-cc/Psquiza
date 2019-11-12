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
		for (Pesquisa pesquisa : pesquisas.values()) {
			if (pesquisa.getDescricao().toLowerCase().contains(termo.toLowerCase())) {
				Busca busca = new Busca(pesquisa.getCodigo(), pesquisa.getDescricao());
				resultados.add(busca);
			}
			if (pesquisa.getCampoDeInteresse().toLowerCase().contains(termo.toLowerCase())) {
				Busca busca = new Busca(pesquisa.getCodigo(), pesquisa.getCampoDeInteresse());
				resultados.add(busca);
			}
		}
		Collections.sort(resultados);
		return resultados;
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
}