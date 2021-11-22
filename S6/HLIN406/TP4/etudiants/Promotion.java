package tp2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class Promotion{

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
  
  /*public void affichePromo() {
    for (int i=0; i<ListePromo.length; i++) {
      System.out.println("étudiant " + i + " : " + ListePromo[1].nom);
    }
  }*/
}