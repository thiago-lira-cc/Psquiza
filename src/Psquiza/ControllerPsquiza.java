package Psquiza;

public class ControllerPsquiza {
	private Excecoes excecoes;
	private ControllerPesquisa controlePesquisa;
	private ControllerPesquisador controlePesquisador;
	private ControllerProblema controleProblema;
	private ControllerObjetivo controleObjetivo;
	private ControllerAtividade controleAtividade;
	
	public ControllerPsquiza(ControllerPesquisa controlePesquisa, ControllerPesquisador controlePesquisador, ControllerObjetivo controleObjetivo, ControllerProblema controleProblema, ControllerAtividade controleAtividade) {
		this.excecoes = new Excecoes();
		this.controlePesquisa = new ControllerPesquisa();
		this.controlePesquisador = new ControllerPesquisador();
		this.controleProblema = new ControllerProblema();
		this.controleObjetivo = new ControllerObjetivo();
		this.controleAtividade =  new ControllerAtividade();
	}

}
