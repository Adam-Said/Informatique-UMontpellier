package enseignement;

import java.time.LocalTime;

public class CreneauHoraire implements ICreneauHoraire {
  private LocalTime Deb;
  private int Heure;
  private int Min;
  private int HeureFin;
  private int MinFin;
  private int MinTime;
  private int duration;

  public CreneauHoraire() {
    Heure=0;
    Min=0;
    HeureFin=0;
    MinFin=0;
    MinTime = 0;
    duration = 0;
    Deb = LocalTime.of(Heure,Min);
  }

  public CreneauHoraire(int heureD,int minD, int dur) {
    Heure=heureD;
    Min=minD;
    duration = dur;
    Deb = LocalTime.of(heureD,minD);
    HeureFin=heureD + (dur / 60);
    MinFin=minD + (dur % 60);
    MinTime = (60*HeureFin + MinFin)-(60*heureD + minD);
  }

  public int getHeure() {
    return Deb.getHour();
  }

  public int getMin() {
    return Deb.getMinute();
  }

  public int getDuration() {
    return duration;
  }

  public boolean chevauche(CreneauHoraire creneau) {
    CreneauHoraire CH = new CreneauHoraire(creneau.getHeure(), creneau.getMin(), creneau.getDuration());

    LocalTime fin = Deb.plusMinutes(duration);
    LocalTime finCH = CH.Deb.plusMinutes(CH.duration);

    return finCH.compareTo(Deb) >= 0 && fin.compareTo(CH.Deb) >= 0;
  }

}