package tp2;

/**
 * Classe repr�sentant une note.
 * 
 * @author jcufi
 *
 */
public class Note {
	/**
	 * Valeur de la note obtenue
	 */
	private float valeur;
	/**
	 * Module
	 */
	private Module module;

	/**
	 * Constructeur
	 * 
	 * @param valeur
	 *            Valeur de la note
	 * @param module
	 *            Module concern�
	 */
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

	/**
	 * Retourne une chaine repr�sentant la note obtenue pour le module concern�
	 */
	public String toString() {
		return "Note : " + getValeur() + " " + module.toString();
	}
}
