package fr.umfds.gestionter;

public class Etudiant extends Personne {

  public Etudiant() throws ObjetInvalide {
    super(0, "", "");
  }

  public Etudiant(int id, String prenom, String nom) throws ObjetInvalide {
    super(id, prenom, nom);
    if (id <= 0) {
      throw new ObjetInvalide("L'id ne peut pas être négative ou inférieure à 0 !");
    }
  }
}