package Psquiza;

public class Item  implements Comparable<Item>{
	private Integer indice;
	private String item;
	private String status;
	
	public Item(String item, Integer indice) {
		this.item = item;
		this.status = "PENDENTE";
		this.indice = indice;
	}

	@Override
	public String toString() {
		return this.status + " - " + this.item;
	}

	@Override
	public int compareTo(Item o) {
		return this.getIndice().compareTo(o.getIndice());
	}

	public Integer getIndice() {
		return indice;
	}
	
	
	

}
