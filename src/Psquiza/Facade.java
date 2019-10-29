package Psquiza;

public class Facade {
	private ControllerPesquisador controlePesquisador;
	private ControllerProblema controleProblema;
	private ControllerObjetivo controleObjetivo;

	public Facade() {
		this.controlePesquisador = new ControllerPesquisador();
		this.controleProblema = new ControllerProblema();
		this.controleObjetivo = new ControllerObjetivo();
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
