package Psquiza;
/**
 * Classe responsavel pela verificacao de formatos e excessoes
 * @author Thiago Lira, Kleberson Maria, Ricardo Felix e Iele Passos
 *
 */
public class Excecoes {
	/**
	 * Verifica se uma string é vazia ou nula
	 * @param atributo
	 * @param msg
	 */
	public void verificaString(String atributo, String msg) {
		if (atributo==null||atributo.equals("")) {
			throw new IllegalArgumentException(msg);
		}
	}
	/**
	 * Verifica se o formato de email eh valido
	 * Um formato valido deve ter no minimo uma letra ou um numero antes e depois do arroba
	 * @param email
	 * @param msg
	 */
	public void verificaEmail(String email, String msg) {
		String[] emailSeparado = email.split("@");
		
		if (emailSeparado.length!=2 |emailSeparado[0].equals("")) {
			throw new IllegalArgumentException(msg);
		}

	    if (!emailSeparado[0].matches("[a-zA-Z0-9]+") && !emailSeparado[1].matches("[a-zA-Z0-9]+")) {
	        throw new IllegalArgumentException(msg);
	    }
	    
	    
	}
	/**
	 * Verifica se a fotoURL eh valida
	 * Um formato valido deve comecar com http:// ou https://
	 * @param foto
	 * @param msg
	 */
	public void verificaURL(String foto, String msg) {
		String http = "http://";
		String https = "https://";
		
		if (!foto.startsWith(http) && !foto.startsWith(https)) {
			throw new IllegalArgumentException(msg);
		}
		
	}
	
	/**
	 * Verifica se a viabilidade eh valida
	 * Um valor deve ser maior igual a 1 e menor igual
	 * a 5
	 * @param viabilidade
	 * @param msg
	 */
	public void verificaValor(int viabilidade, String msg) {
		if (!(viabilidade>=1 && viabilidade<=5)) {
			throw new IllegalArgumentException(msg);
		}
	}
	
	
	/**
	 * Verifica se o tipo de um objetivo eh valido
	 * @param tipo
	 * @param msg
	 */
	public void verificaTipo(String tipo, String msg) {
		if (!(tipo.equalsIgnoreCase("geral") || tipo.equalsIgnoreCase("especifico"))) {
			throw new IllegalArgumentException(msg);
		}
	}
	
	
	/**
	 * Verifica se o nivel de risco eh valido
	 * Os niveis validos sao BAIXO, MEDIO ou ALTO.
	 * @param valor
	 * @param msg
	 */
	public void verificaNivelRisco(String valor, String msg) {
		if (!(valor.equals("BAIXO") || valor.equals("MEDIO") || valor.equals("ALTO"))) {
			throw new IllegalArgumentException(msg);
		}
	}
	
}
