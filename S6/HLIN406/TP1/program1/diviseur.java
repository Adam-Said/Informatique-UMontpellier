import java.util.Scanner;

public class diviseur {
  public static void main(String[] a) throws java.io.IOException {
    Scanner sc=new Scanner(System.in);

    int e1, e2;
    float f1, f2;

    System.out.println("entrez deux entier ");
      e1 = sc.nextInt();
      e2 = sc.nextInt();

    System.out.println("entrez deux entier ");
      f1 = sc.nextFloat();
      f2 = sc.nextFloat();

    double div_entier = e1/e2;
    double div_float = f1/f2;

    System.out.println("Division des deux entiers :" +e1+ " et " +e2+ " = "+div_entier);
    System.out.println("Division des deux flotants :" +f1+ " et " +f2+ " = "+div_float);

    
  }
}