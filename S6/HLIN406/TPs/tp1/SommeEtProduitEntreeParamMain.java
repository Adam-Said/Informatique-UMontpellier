package tp1;

public class SommeEtProduitEntreeParamMain {
  public static void main(String[] a) throws java.io.IOException
  {
      double x = Double.valueOf(a[0]);
      double y = Double.valueOf(a[1]);
      double somme = x+y;
      double produit = x*y;
      System.out.println("somme = "+somme+" produit = "+produit);
   }

}
