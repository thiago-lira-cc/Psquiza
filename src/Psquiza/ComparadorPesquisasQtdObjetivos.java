package Psquiza;

import java.util.Comparator;

public class ComparadorPesquisasQtdObjetivos implements Comparator<Pesquisa>{
	
	public int compare(Pesquisa p1, Pesquisa p2) {
		if (p1.getQtdObjetivos()>p2.getQtdObjetivos()) {
			return 1;
		} else if (p1.getQtdObjetivos()<p2.getQtdObjetivos()) {
			return -1;
		} else {
			return p1.compareTo(p2);
		}
	}

}
