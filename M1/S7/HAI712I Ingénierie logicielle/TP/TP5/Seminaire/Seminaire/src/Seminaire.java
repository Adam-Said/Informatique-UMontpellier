import java.util.HashMap;

public class Seminaire {
    
    private String titre;
    private String resume;
    private int capacite;
    private Etat etatCourant;
    private Etat[] etats;
    private HashMap<String, String> inscrits;


    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public String getResume() {
        return resume;
    }
    public void setResume(String resume) {
        this.resume = resume;
    }
    public int getCapacite() {
        return capacite;
    }
    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }
    public Etat getEtatCourant() {
        return etatCourant;
    }
    public void setEtatCourant(Etat etatCourant) {
        this.etatCourant = etatCourant;
    }
    public Etat[] getEtats() {
        return etats;
    }
    public void setEtats(Etat[] etats) {
        this.etats = etats;
    }
    public HashMap<String, String> getInscrits() {
        return inscrits;
    }
    public void setInscrits(HashMap<String, String> inscrits) {
        this.inscrits = inscrits;
    }
    public Seminaire(String titre, String resume, int capacite) {
        this.titre = titre;
        this.resume = resume;
        this.capacite = capacite;
        this.etats = new Etat[5];
        etats[0] = new EtatPlanification(this);
        etats[1] = new EtatReservation(this);
        etats[2] = new EtatEnCours(this);
        etats[3] = new EtatFin(this);
        etats[4] = new EtatArchive(this);
        this.etatCourant = etats[0];
        this.inscrits = new HashMap<>();
    }

    public Seminaire() {
        this.titre = "null";
        this.resume = "null";
        this.capacite = 0;
        this.etats = new Etat[5];
        etats[0] = new EtatPlanification(this);
        etats[1] = new EtatReservation(this);
        etats[2] = new EtatEnCours(this);
        etats[3] = new EtatFin(this);
        etats[4] = new EtatArchive(this);
        this.etatCourant = etats[0];
        this.inscrits = new HashMap<>();
    }

    public void enter(String commande) throws EtatException {
        int etatSuivant = this.etatCourant.enter(commande);
        this.etatCourant = etats[etatSuivant];
    }

    
}
