package enseignement;

public class TestEnseignement {
  public static void main(String[] args) {
    /*CreneauHoraireFDS c1 = new CreneauHoraireFDS(3, 1);
    System.out.println(c1.getHeureDebut());
    System.out.println(c1.getMinuteDebut());
    System.out.println(c1.getDuree());
    System.out.println(c1.getStringDebut());
    System.out.println(c1.chevauche(c1));*/
    
    CreneauHoraire c2 = new CreneauHoraire(8, 35, 120);
    System.out.println(c2.getHeure());
    System.out.println(c2.getMin());
    System.out.println(c2.getDuration());
    System.out.println(c2.getDebString());
    System.out.println(c2.chevauche(c2));
  }
}