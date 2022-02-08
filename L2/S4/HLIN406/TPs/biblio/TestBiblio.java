package biblio;
import java.util.ArrayList;

public class TestBiblio {

  public static void main(String[] args) {
    Catalogue catalogue = new Catalogue();

    NoticeBibliographique n1 = new NoticeBibliographique(
      "xxxx00000xxxx",
      "XXX",
      new ArrayList<Contribution>(
        new Contribution(
          new Contributeur("XX, XX")
        )
      )
    );
    Exemplaire exemplaire1 = new Exemplaire(n1);
    System.out.println(exemplaire1);




  }
}