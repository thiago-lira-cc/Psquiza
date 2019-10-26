package Psquiza;

public class Facade {
	private ControllerPesquisador controlePesquisador;

	public Facade() {
		this.controlePesquisador = new ControllerPesquisador();
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
}
