package biblio;


public class Exemplaire {
  private NoticeBibliographique notice;
  private boolean disponible;
  private EtatExemplaire etat;
  
  public Exemplaire(NoticeBibliographique notice, EtatExemplaire etat) {
    this.notice = notice;
    this.disponible = true;
    this.etat = etat;
    notice.addExemplaire(this);
  }

  public Exemplaire(NoticeBibliographique notice) {
    this.notice = notice;
    this.disponible = true;
    this.etat = EtatExemplaire.NEUF;
    this.notice.addExemplaire(this);
  }

  public void setDisponible(boolean disponible) {
    this.disponible = disponible;
  }

  public boolean estDisponible() {
    return disponible;
  }

  public EtatExemplaire getEtat() {
    return etat;
  }

  public NoticeBibliographique getNotice() {
    return notice;
  }
}