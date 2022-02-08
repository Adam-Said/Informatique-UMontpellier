package fr.umfds.gestionter;
import java.lang.String;
import java.util.ArrayList;


public class Groupe {
  private int idGroupe;
  private String nomGroupe;
  private ArrayList<Etudiant> etudiants;
  private Sujet sujet;
  private int tailleGroupe = 6;

  public void setId(int id) throws Exception {
    this.idGroupe = id;
  }

  public void setNom(String nom) throws Exception  {
    this.nomGroupe = nom;
  }
  
  public void setSujet(Sujet s) throws Exception  {
	    this.sujet = s;
	  }

  public void setEtudiant(ArrayList<Etudiant> etudiants) throws Exception  {
    this.etudiants = etudiants;
  }

  public int getId() throws Exception  {
    return idGroupe;
  }

  public String getNomGroupe() throws Exception  {
    return nomGroupe;
  }
  
  public Sujet getSujet() throws Exception  {
	    return sujet;
	  }

  public ArrayList<Etudiant> getEtudiants() throws Exception  {
    return etudiants;
  }

  public Groupe() {
    this.idGroupe = 0;
    this.nomGroupe = "";
    this.etudiants = new ArrayList<Etudiant>();
    this.sujet = null;
  }

  public Groupe(int idGroupe, String nom, ArrayList<Etudiant> etudiants) throws ObjetInvalide {
    this.idGroupe = idGroupe;
    this.nomGroupe = nom;
    this.etudiants = etudiants;
    this.sujet = null;
  }
  
  public Groupe(int idGroupe, String nom) throws ObjetInvalide {
	    this.idGroupe = idGroupe;
	    this.nomGroupe = nom;
	    this.etudiants = null;
	    this.sujet = null;
	  }
 
@Override
  public String toString() {
    return "\nN° Groupe : " + idGroupe + " - " + nomGroupe ;
  }
}
