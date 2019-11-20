package Psquiza;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

/**
 * Classe responsavel pela verificacao de formatos e excessoes
 * @author Thiago Lira, Kleberson Maria, Ricardo Felix e Iele Passos
 *
 */
public class Excecoes implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2715061879036174552L;
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
	
	/**
	 * Verifica se o Campo de Interesse de uma pesquisa esta num formato valido
	 * @param campoDeInteresse
	 * @param msg
	 */
	public void verificaCampo(String campoDeInteresse, String msg) {
		String[] separados = campoDeInteresse.split(",");
		if(campoDeInteresse.length() > 255) {
			throw new IllegalArgumentException(msg);
		}else{
			if(separados.length > 4) {
				throw new IllegalArgumentException(msg);
			}else {
				for(int i= 0;i < separados.length;i++) {
					if(separados[i].length() < 3 || separados[i].equals("")) {
						throw new IllegalArgumentException(msg);
					}
				}
			}
		}
	}
	/**
	 * Verifica se o numero passado como parametro eh positivo
	 * @param numeroDoResultado
	 * @param msg
	 */
	public void verificaNumeroDoResultado(int numeroDoResultado, String msg) {
		if (numeroDoResultado<0) {
			throw new IllegalArgumentException(msg);
		}
	}
	public void verificaItemDuracao(int num,String msg) {
		if(num == 0 || num<0) {
			throw new IllegalArgumentException(msg);
			
		}
	}
	/**
	 * Verifica se o numero passado como parametro eh positivo
	 * @param novoValor
	 * @param msg
	 */
	public void verificaNumero(int novoValor, String msg) {
		if (novoValor<=0) {
			throw new IllegalArgumentException(msg);
		}
	}
	
	public void verficaDataValida(String valor, String msg) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		try {
			formatter.parse(valor);
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException(msg);
		}
	}

	
	public void verificaIEA(double valor, String msg) {
		
		
		if (valor < 0 || valor >10) {
			throw new IllegalArgumentException(msg);
		}
	}
	
}
