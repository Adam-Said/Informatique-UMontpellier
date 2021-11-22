package tp2;

public class Module {
	private String nom;

	public Module(String nom) {
		super();
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String toString() {
		return "Nom module : " + this.getNom();
	}
}
