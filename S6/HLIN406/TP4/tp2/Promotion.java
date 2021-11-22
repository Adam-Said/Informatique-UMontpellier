package tp2;

import java.util.ArrayList;
import java.util.Locale;


public class Promotion {

  private ArrayList<Etudiant> ListePromo;
  private int annee;

  public Promotion(){
    this.annee = 0;
    this.ListePromo = new ArrayList();
  }

  public Promotion(int annee, ArrayList<Etudiant> ListePromo){
    this.annee = annee;
    this.ListePromo= ListePromo;
  }

  public int getAnnee() {
    return annee;
  }

  public void setAnne(int annee){
    this.annee = annee;
  }
   
  public ArrayList getListePromo() {
    return ListePromo;
  }

  public void setListePromo(ArrayList<Etudiant> ListePromo) {
    this.ListePromo = ListePromo;
  }
  
  public void affichePromo() {
    for(Etudiant s : ListePromo) {
        System.out.println("étudiant : " + s);
   }
  }

  public int MoyGen() {
    double moy=0.0;
    int cpt=0;
    for(Etudiant etu : ListePromo) {moy+=etu.moyenne(); cpt++;}
    if (cpt>0) {return moy/cpt;} 
    else {return -1;}
  }

  public void inscription(Etudiant e) {
    if (this.etudiant.constains(e)) {System.out.println("Cet étudiant existe déjà");}
    else {
      this.ListePromo.add(e);
      System.out.println("Liste mise à jour : " + this.getListePromo());
    }
  }

  public Etudiant etudiant_i(int i) {
    if (i>=0 && i<this.etudiant.size()) {return this.etudiant.get(i);}
    else {return null;}
  }

  public int nombrePromo() {
    
  }

  
}