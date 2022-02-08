package enseignement;

public interface ICreneauHoraire {
  public static String Separateur = ":";
  public abstract int getHeure();
  public abstract int getMin();
  public abstract int getDuration();

  public default String getDebString() {
    String hour = Integer.toString(getHeure());
    String min = Integer.toString(getMin());

    if (hour.length() == 1) hour = "0" + hour;
    
    if (min.length() == 1) min = "0" + min;

    return hour + Separateur + min;
  }


  public default boolean chevauche(CreneauHoraire CH) {
    int deb = getHeure() * 60 + getMin();
    int fin = deb + getDuration();

    int debCH = CH.getHeure() * 60 + CH.getMin();
    int finCH = debCH + CH.getDuration();

    return (fin > debCH) && (finCH > deb);
  }

}