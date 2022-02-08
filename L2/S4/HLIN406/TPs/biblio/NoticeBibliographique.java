package biblio;

import java.util.ArrayList;

public class NoticeBibliographique {
  private String isbn;
  private String titre;
  private String sousTitre;
  private ArrayList<Exemplaire> exemplaires;
  private ArrayList<Contribution> contributions;
  private PublicCible publicCible;

  public NoticeBibliographique(String isbn, String titre, String sousTitre, ArrayList<Contribution> contributions, PublicCible publicCible) {
    this.isbn = isbn;
    this.titre = titre;
    this.sousTitre = sousTitre;
    this.contributions = contributions;
    this.publicCible = publicCible;
  }

  public NoticeBibliographique(String isbn, String titre, ArrayList<Contribution> contributions, PublicCible publicCible) {
    this(isbn, titre, "", contributions, publicCible);
  }

  public NoticeBibliographique() {
    this.isbn = "00000xxxx00000";
    this.titre = "XXX";
    this.sousTitre = "XXXX";
    this.publicCible = PublicCible.adultes;
  }

  public String getisbn() {
    return isbn;
  }

  public String getTitre() {
    return titre;
  }

  public String getSousTitre() {
    return titre;
  }

  public ArrayList<Contribution> getContributions() {
    return contributions;
  }

  public ArrayList<Exemplaire> getExemplaires() {
    return exemplaires;
  }

  public PublicCible getPublicCible() {
    return publicCible;
  }

  public void addExemplaire(Exemplaire exemplaire) {
    this.exemplaires.add(exemplaire);
  }

  public boolean estDisponible() {
    for (Exemplaire exemplaire : exemplaires) {
      if (exemplaire.estDisponible()) {
        return true;
      }
    }

    return false;
  }
}