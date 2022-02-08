package biblio;

public abstract class Personne {
  private String nom;
  private String prenom;

  public Personne() {
    this.nom = "xxx";
    this.prenom = "xxxx";
  }

  public Personne(String n, String p) {
    this.nom = n;
    this.prenom = p;
  }

  public String getNom() {
    return this.nom;
  }
  public String getPrenom() {
    return this.prenom;
  }

  public void setNom(String n) {
    this.nom = n;
  }

  public void setPrenom(String p) {
    this.prenom = p;
  }
}