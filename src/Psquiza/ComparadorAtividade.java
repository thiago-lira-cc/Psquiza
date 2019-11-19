package Psquiza;

import java.util.Comparator;

public class ComparadorAtividade implements Comparator<Atividade> {

	@Override
	public int compare(Atividade a1, Atividade a2) {
		int c1 = Integer.parseInt(a1.getCodigo().substring(1, a1.getCodigo().length()));
		int c2 = Integer.parseInt(a2.getCodigo().substring(1, a2.getCodigo().length()));
		if (c1>c2) {
			return 1;
		} else if (c1<c2) {
			return -1;
		} else {
			return a1.compareTo(a2);
		}
	}

}
