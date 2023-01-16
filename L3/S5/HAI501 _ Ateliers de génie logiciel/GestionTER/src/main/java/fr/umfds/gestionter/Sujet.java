package fr.umfds.gestionter;
import java.lang.String;

public class Sujet {
  private int idSujet;
  private String titreSujet;
  private String resumeSujet;
  private Enseignant encadrantSujet;
  private Groupe groupe;

  public void setIdSujet(int id) {
    this.idSujet = id;
  }

  public void setTitreSujet(String titre) {
    this.titreSujet = titre;
  }

  public void setResumeSujet(String resume) {
    this.resumeSujet = resume;
  }

  public void setEncadrant(Enseignant encadrant) {
    this.encadrantSujet = encadrant;
  }
  
  public void setGroupe(Groupe g) {
	    this.groupe = g;
	  }

  public int getIdSujet() {
    return this.idSujet;
  }

  public String getTitreSujet() {
    return this.titreSujet;
  }

  public String getResumeSujet() {
    return this.resumeSujet;
  }

  public Enseignant getEncadrant() {
    return this.encadrantSujet;
  }
  
  public Groupe getGroupe() {
	    return this.groupe;
	  }

  public String toString() {
	  String string = "\n Id : " + getIdSujet() + " Titre : " + getTitreSujet(); 
	  string+= "\n " + this.getResumeSujet();
	  return  string;
	  }

  public Sujet() throws ObjetInvalide {
    idSujet = 0;
    titreSujet = "";
    resumeSujet = "";
    encadrantSujet = null;
    groupe = null;
  }

  public Sujet(int id, String titre, String resume, Enseignant encadrant) throws ObjetInvalide {
    if (titre == "" || titre == " ") {
      throw new ObjetInvalide("Le titre ne peut pas être nul !");
    }
    this.idSujet = id;
    this.titreSujet = titre;
    this.resumeSujet = resume;
    this.encadrantSujet = encadrant;
    this.groupe = null;
  }

    public Sujet(int id, String titre, String resume) throws ObjetInvalide {
    if (titre == "" || titre == " ") {
      throw new ObjetInvalide("Le titre ne peut pas être nul !");
    }
    this.idSujet = id;
    this.titreSujet = titre;
    this.resumeSujet = resume;
    this.encadrantSujet = null;
    this.groupe = null;
  }

}