package fr.umfds.gestionter;
import java.lang.String;

public class Voeux {
  private int ordre;
  private Sujet sujet;
  private Groupe groupe;
/*
  public int getOrdre() {
    return this.ordre;
  }

  public Sujet getSujet() {
    return this.sujet;
  }

  public Groupe getGroupe() {
    return this.groupe;
  }

  public void setOrdre(int ordre) {
    this.ordre = ordre;
  }

  public void setSujet(Sujet sujet) {
    this.sujet = sujet;
  }

  public void setGroupe(Groupe groupe) {
    this.groupe = groupe;
  }
*/
  public Voeux() throws ObjetInvalide, Exception {
    this.ordre = 0;
    this.sujet = null;
    this.groupe = null;
    }

  public Voeux(int ordre, Sujet sujet, Groupe groupe) throws ObjetInvalide {
    this.ordre = ordre;
    this.sujet = sujet;
    this.groupe = groupe;
  }

  public String toString() {
    return "\n N° " + ordre + " - " + sujet.toString() + " groupe : " + groupe.toString(); 
  }

}
