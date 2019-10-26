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
}
