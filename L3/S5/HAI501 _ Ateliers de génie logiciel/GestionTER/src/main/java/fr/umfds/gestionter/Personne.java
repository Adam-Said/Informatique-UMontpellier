package fr.umfds.gestionter;
import java.lang.String;

public class Personne {
  private int id;
  private String prenom;
  private String nom;

  public void setId(int id) {
    this.id = id;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public int getId() {
    return id;
  }

  public String getPrenom() {
    return prenom;
  }

  public String getNom() {
    return nom;
  }

  public Personne() throws ObjetInvalide {
    this.id = 0;
    this.prenom = "";
    this.nom = "";
  }

  public Personne(int id, String prenom, String nom) throws ObjetInvalide {
    if (id < 0) {
      throw new ObjetInvalide("L'id ne peut pas être négative ou inférieure à 0 !");
    }
    this.id = id;
    this.prenom = prenom;
    this.nom = nom;
  }

  public String toString() {
    return "\n N° " + id + " | Nom : " + nom + " Prenom : " + prenom;
  }
}