package fr.umfds.poste;

public enum Recommandation {
	
	zero(0), un(1), deux(2);
	private int value;
	private Recommandation(int value) {
		this.value=value;
	}
	
	public String toString() {
		return String.valueOf(value);
	}
}
