package fr.umfds.gestionter;

public class Enseignant extends Personne {

	public Enseignant() throws ObjetInvalide {
		super(0, "", "");
	}

	public Enseignant(int id, String prenom, String nom) throws ObjetInvalide {
		super(id, prenom, nom);
		if (id <= 0) {
			throw new ObjetInvalide("L'id doit être supérieure à 0 !");
		}
	}
}