public class Bagage {
  private final String idPassager;
  private Dimension dim;
  private Dimension dimMax = new Dimension(55, 35, 25);
  private float vol;
  private float volMax = 1000;
  private boolean fragile;

  public Bagage(String id, Dimension dim1, float vol1, boolean frag) {
    idPassager = id;
    dim = dim1;
    vol1 = vol;
    fragile = frag;
  }

  public boolean cabine() {
    return dim.inferieurA(dimMax);
  }

  public LogementAvion bagageLoge() {
    if(dim.inferieurA(dimMax) && vol <= volMax) {
      return cabine;
    }
    else if (fragile) {
      return secteurNaviguant;
    }
    else {
      return soute;
    }
  }

  public String toString() {
    return "id passager : " + idPassager + 
    " \n Dimension : " + dim.toString() +
    " \n Logement dans l'avion : " + this.bagageLoge();
  }



}


public class BagageNavigant extends Bagage {
  private final String idNavigant; 

  public Bagage(String id, Dimension dim1, float vol1, boolean frag, String idNav) {
    super(id, dim1, vol1, frag);
    idNavigant = idNav;
  }

  public String toString() {
    return super.toString() + " \n idNavigant : " + idNavigant;
  }

  public LogementAvion bagageLoge() {
    return secteurNavigant;
  }
}

Dimension dim1 = new Dimension(10, 10, 10);
Bagage bag1 = new Bagage("37XEM8", dim1, 850, true);

BagageNavigant bagNav1 = new BagageNavigant("4XKE96", dim1, 750, true, "34XE");
