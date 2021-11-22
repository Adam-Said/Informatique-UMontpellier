package tdExceptionsAssertions;

public class TestPile {

  public static void main(String[] args) {
    PileBornee pile1 = new PileBornee(10);
    boolean PilePasPleine = pile1.estVide();
    System.out.println("La pile est vide ? : "+PilePasPleine);
  
    try {
      pile1.empiler(1);
    }
    catch (Exception e) {
      System.out.println("Exception " + e.getMessage());
    }



  
  }
}

