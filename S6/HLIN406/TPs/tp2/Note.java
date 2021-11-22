package tp2;

public class Note {
	/**
	 * Valeur de la note obtenue
	 */
	private float valeur;
	/**
	 * Module
	 */
	private Module module;

	public Note(float valeur, Module module) {
		super();
		this.valeur = valeur;
		this.module = module;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public float getValeur() {
		return valeur;
	}

	public void setValeur(float valeur) {
		this.valeur = valeur;
	}

	public String toString() {
		return "Note : " + getValeur() + " " + module.toString();
	}
}
