package etudiants;
import java.lang.String; 
import java.time.*;
import java.util.Scanner;
import java.util.Calendar;


enum codePays { fr, et, etfr};

public class Etudiant {

  private int dateNaissance;
  private int age;
  private String codePays;
  private float noteA;
  private float noteB;
  private float noteC;


  public int getAge() {return age;}
  public int getdateNaissance() {return dateNaissance;}
  public String getCodePays() {return codePays;}
  public float getNoteA() {return noteA;}
  public float getNoteB() {return noteB;}
  public float getNoteC() {return noteC;}


  public void setAge(int a) {
    int todayYear = Year.now().getValue();
    this.age = todayYear - dateNaissance;
  }
  public void setDateNaissance(int annee) {
    this.dateNaissance = annee;
  }
  public void setCodePays(String code) {
    
  }
  public void setNoteA(float a) {
    this.noteA = a;
  }
  public void setNoteB(float b) {
    this.noteB = b;
  }
  public void setNoteC(float c) {
    this.noteC = c;
  }


  public float moyenne() {
    return (this.noteA + this.noteB + this.noteC) / 3;
  }
  public String mention() {
    float mention = this.moyenne();
    if (mention < 10) {
      return "nonAdmis";
    }
    else if (mention >= 10 && mention < 11) {
      return "admis";
    }
    else if (mention >= 11 && mention < 12) {
      return "assez bien";
    }
    else if (mention >= 12 && mention < 14) {
      return "bien";
    }
    else if (mention >= 14 && mention < 16) {
      return "tres bien";
    }
    else {
      return "felicitations";
    }
  }
  public String ligneResultat() {
      String info = ("Etudiant agé de " +this.getAge());
      float moy = this.moyenne();
      if (moy < 10) {
        info = (info + "\n Moyenne : "+this.moyenne()+ " Ajournement !");
        if (this.getNoteA() >= 10) {
          info = (info +"\n Vous avez "+this.getNoteA()+" pour l'examen A");}
        if (this.getNoteB() >= 10) {
          info = (info + "\n Vous avez "+this.getNoteB()+" pour l'examen B");}
        if (this.getNoteC() >= 10) {
          info = (info +"\n Vous avez "+this.getNoteC()+" pour l'examen C");}
      }
      else {
        info = (info +"\nExamen A : "+this.getNoteA()+"\nExamen B : "+this.getNoteB()+"\nExamen C : "+this.getNoteC());
      }
      return info;
  }

  public Etudiant() {
    dateNaissance = 2000;
    setAge(dateNaissance);
    codePays = "fr";
    noteA = 10;
    noteB = 10;
    noteC = 10;
  }
  public Etudiant(int year, String code, int A, int B, int C){
    dateNaissance = year;
    setAge(dateNaissance);
    codePays = code;
    noteA = A;
    noteB = B;
    noteC = C;
  }

  public String toString() {
    return "Date de naissance : " + this.dateNaissance +
          " Age : " + this.age +
          " Code pays : " + this.codePays +
          " Notes : " + this.noteA+ " ; " + this.noteB+ " ; " + this.noteC;
  }
}
