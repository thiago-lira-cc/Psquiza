package Psquiza;

public interface Pesquisador {
	
	
	String getFuncao();
	
	void setNome(String novoValor);

	void setFuncao(String novoValor);

	void setBiografia(String novoValor);

	void setFoto(String novoValor);

	void setEmail(String novoValor);

	boolean isStatus();

	void setStatus(boolean b);

	String getBiografia();

	String getEmail();

	default void cadastraEspecialidadeProfessor(String formacao, String unidade, String data) {
		if (this.getFuncao().equals("professor")) {
			
		}
	}

	
	
}
