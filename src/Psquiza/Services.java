package Psquiza;

import java.util.List;
/**
 * Interface dos servicos gerais do sistema comum a todas as classes
 * @author Thiago Lira
 *
 */
public interface Services {
	/**
	 * Metodo que faz a busca de um termo na classe
	 * @param termo
	 * @return List dos resultados achados
	 */
	List<Busca> busca(String termo);
	
}
