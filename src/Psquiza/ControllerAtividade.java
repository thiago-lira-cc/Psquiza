package Psquiza;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Controller responsavel por gerenciar operacoes
 * sobre atividades.
 * @author 
 *
 */
public class ControllerAtividade implements Services{
	private Map <String, Atividade> atividades;
	private Excecoes excecoes;
	private int contCodigo;
	
	/**
	 * Constroi um controle de atividades.
	 */
	public ControllerAtividade() {
		this.atividades = new HashMap<String, Atividade>();
		this.excecoes = new Excecoes();
	}
	
	/**
	 * Gera codigo de identificacao de uma atividade.
	 * @return retorna o codigo
	 */
	public String geraCodigo() {
		this.contCodigo++ ;
		return "A" + contCodigo;
	}
	
	public Atividade pegaCodigo(String codigo) {
		return atividades.get(codigo);
	}
	/**
	 * Verifica se uma atividade existe ou nao no sistema.
	 * @param codigo o codigo a ser verificado
	 * @return retorna se existe ou nao.
	 */
	public boolean ativExiste(String codigo) {
		if(atividades.containsKey(codigo)) {
			return true;
		}
		return false;	
	}
	/**
	 * Cadastra uma atividade no sistema.
	 * @param descricao
	 * @param nivelRisco
	 * @param descricaoRisco
	 * @return retorna o codigo da atividade.
	 */
	public String cadastraAtividade(String descricao, String nivelRisco, String descricaoRisco) {
		excecoes.verificaString(descricao, "Campo Descricao nao pode ser nulo ou vazio.");
		excecoes.verificaString(nivelRisco, "Campo nivelRisco nao pode ser nulo ou vazio.");
		excecoes.verificaString(descricaoRisco, "Campo descricaoRisco nao pode ser nulo ou vazio.");
		excecoes.verificaNivelRisco(nivelRisco, "Valor invalido do nivel do risco.");
		String codigo = geraCodigo();
		if (!atividades.containsKey(codigo)) {
			Atividade atividade = new Atividade (descricao, nivelRisco, descricaoRisco,codigo);
			atividades.put(codigo, atividade);
			
		}
		return codigo;
	}

	/**
	 * Apaga uma atividade do sistema
	 * @param codigo codigo a ser apagado
	 */
	public void apagaAtividade(String codigo) {
		excecoes.verificaString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		if (atividades.containsKey(codigo)) {
			atividades.remove(codigo);
		} else {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
	}
	/**
	 * Cadastra um item no sistema
	 * @param codigo
	 * @param item
	 */
	public void cadastraItem(String codigo, String item) {
		excecoes.verificaString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		excecoes.verificaString(item, "Item nao pode ser nulo ou vazio.");
		if (atividades.containsKey(codigo)) {
			atividades.get(codigo).cadastraItem(item);
		} else {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
		
	}
	
	/**
	 * Exibe uma atividade existente no sistema.
	 * @param codigo
	 * @return
	 */
	public String exibeAtividade(String codigo) {
		excecoes.verificaString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		if (atividades.containsKey(codigo)) {
			 return atividades.get(codigo).exibeAtividade();
		} else {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
		
	}
	
	/**
	 * Metodo que verifica se entre as atividades cadastradas existe o termo 
	 * @param termo
	 * @return List com os resultados
	 */
	@Override
	public List<Busca> busca(String termo) {
		List<Busca> resultados = new ArrayList<Busca>();
		List<Atividade> atividades = getAtividades();
		Collections.sort(atividades);
		for (Atividade atividade : atividades) {
			if (atividade.getDescricao().toLowerCase().contains(termo.toLowerCase())
				&& atividade.getDescricaoRisco().toLowerCase().contains(termo.toLowerCase())) {
				
				Busca busca1 = new Busca(atividade.getCodigo(), atividade.getDescricaoRisco());
				resultados.add(busca1);
				
				Busca busca2 = new Busca(atividade.getCodigo(), atividade.getDescricao());
				resultados.add(busca2);
				
			}else if(atividade.getDescricao().toLowerCase().contains(termo.toLowerCase())){
				Busca busca = new Busca(atividade.getCodigo(), atividade.getDescricao());
				resultados.add(busca);
			}else if (atividade.getDescricaoRisco().toLowerCase().contains(termo.toLowerCase())) {
				Busca busca = new Busca(atividade.getCodigo(), atividade.getDescricaoRisco());
				resultados.add(busca);
			}

		}

		return resultados;
	}

	private List<Atividade> getAtividades() {
		List<Atividade> atividades = new ArrayList<Atividade>();
		for (Atividade atividade : this.atividades.values()) {
			atividades.add(atividade);
		}
		return atividades;
	}

	/**
	 * Metodo que conta os itens pendentes no sistema.
	 * @param codigo
	 * @return
	 */
	public int contaItensPendentes(String codigo) {
		excecoes.verificaString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		if(atividades.containsKey(codigo)) {
			 return atividades.get(codigo).contaItensPendentes();
		} else {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
	}

	/**
	 * Metodo que conta os itens ja realizados no sistema.
	 * @param codigo
	 * @return
	 */
	public int contaItensRealizados(String codigo) {
		excecoes.verificaString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		if(atividades.containsKey(codigo)) {
			 return atividades.get(codigo).contaItensRealizados();
		} else {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
	}

	/**
	 * Metodo que executa uma atividade
	 * @param codigoAtividade
	 * @param item
	 * @param duracao
	 */
	public void executaAtividade(String codigoAtividade, int item, int duracao) {
		excecoes.verificaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		excecoes.verificaItemDuracao(item, "Item nao pode ser nulo ou negativo.");
		excecoes.verificaItemDuracao(duracao, "Duracao nao pode ser nula ou negativa.");
		if(atividades.get(codigoAtividade).isAtivAssociada()==false) {
			throw new IllegalArgumentException("Atividade sem associacoes com pesquisas.");
		}
		this.atividades.get(codigoAtividade).executaItem(item,duracao);
		
	}
	/**
	 * Metodo que cadastra o resultado de uma atividade
	 * @param codigoAtividade
	 * @param resultado
	 * @return 
	 */
	public int cadastraResultado(String codigoAtividade,String resultado) {
		excecoes.verificaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		excecoes.verificaString(resultado, "Resultado nao pode ser nulo ou vazio.");
		return this.atividades.get(codigoAtividade).cadastraResultado(resultado);
	}
	/**
	 * Metodo que remove um resulltado ja cadastrado.
	 * @param codigoAtividade
	 * @param numeroDoResultado
	 * @return
	 */
	public boolean removeResultado(String codigoAtividade, int numeroDoResultado) {
		excecoes.verificaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		excecoes.verificaItemDuracao(numeroDoResultado, "numeroResultado nao pode ser nulo ou negativo.");
		if(atividades.containsKey(codigoAtividade)) {
			return this.atividades.get(codigoAtividade).removeResultado(numeroDoResultado);
		}else {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
		
	}
	/**
	 * Metodo que lista os resultados cadastrados
	 * @param codigoAtividade
	 * @return
	 */
	public String listaResultados(String codigoAtividade) {
		excecoes.verificaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		if(atividades.containsKey(codigoAtividade)) {
			return this.atividades.get(codigoAtividade).listaResultados();
		}
		throw new IllegalArgumentException("Atividade nao encontrada");
	}
	public int getDuracao(String codigoAtividade) {
		excecoes.verificaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		if(atividades.containsKey(codigoAtividade)) {
			return this.atividades.get(codigoAtividade).getDuracao();
		}else {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}		
	}
	/**
	 * Metodo que defini uma proxima atividade a ser realizada
	 * @param idPrecedente
	 * @param idSubsquente
	 */
	public void defineProximaAtividade(String idPrecedente, String idSubsquente) {
		excecoes.verificaString(idPrecedente, "Atividade nao pode ser nulo ou vazio.");
		excecoes.verificaString(idSubsquente, "Atividade nao pode ser nulo ou vazio.");
		if (this.atividades.containsKey(idPrecedente)&&this.atividades.containsKey(idSubsquente)) {
			if (this.atividades.get(idPrecedente).getProxima()==null) {	
				if (this.atividades.get(idSubsquente).getProxima()!=null) {
					if (this.atividades.get(idSubsquente).getProxima().equals(this.atividades.get(idPrecedente))) {
						throw new IllegalArgumentException("Criacao de loops negada.");
					}
				}
				this.atividades.get(idPrecedente).setProxima(this.atividades.get(idSubsquente));
			}else {
				throw new IllegalArgumentException("Atividade ja possui uma subsequente.");
			}
		}else {
			throw new IllegalArgumentException("Atividade nao encontrada.");
		}
	}
	/**
	 * remove a proxima atividade
	 * @param idPrecedente
	 */
	public void tiraProximaAtividade(String idPrecedente) {
		excecoes.verificaString(idPrecedente, "Atividade nao pode ser nulo ou vazio.");
		if (atividades.containsKey(idPrecedente)) {
			atividades.get(idPrecedente).setProxima(null);
		}else {
			throw new IllegalArgumentException("Atividade nao encontrada.");
		}
	}
	/**
	 * Conta quantas atividades tem depois de uma especifica
	 * @param idPrecedente
	 * @return
	 */
	public int contaProximos(String idPrecedente) {
		excecoes.verificaString(idPrecedente, "Atividade nao pode ser nulo ou vazio.");
		if(atividades.containsKey(idPrecedente)) {
			if (atividades.get(idPrecedente).getProxima()==null) {
				return 0;
			}else {
				return 1 + contaProximos(atividades.get(idPrecedente).getProxima().getCodigo());
			}
		}
		throw new IllegalArgumentException("Atividade inexistente.");
	}
}
