package biblio;

import java.util.ArrayList;

public class Abonne extends Personne {
  private int numero;
  private int age;
  private ArrayList<Exemplaire> emprunts;

  public Abonne(int numero, String nom, String prenom, int age) {
    super(nom, prenom);
    this.numero = numero;
    this.age = age;
  }

  public boolean estMineur() {return (age<18);}
  public int getNumeroAbonne() {return numero;}
  public int getAge() {return age;}

  public void emprunte(Exemplaire ex) {
    if (emprunts.size() < 5) {
      emprunts.add(ex);
      ex.setDisponible(false);
    }
    else {
      System.out.println("Cet abonné a déjà emprunté 5 livres.");
    }
  }

  public void rendre(Exemplaire ex) {
    if (emprunts.contains(ex)) {
      emprunts.remove(ex);
      ex.setDisponible(true);
    }
    else {
      System.out.println("Cet abonné ne possède pas ce livre.");
    }
  }

  public String toString() {
		return "Numéro aboné :"+this.getNumeroAbonne(); c
	}


}