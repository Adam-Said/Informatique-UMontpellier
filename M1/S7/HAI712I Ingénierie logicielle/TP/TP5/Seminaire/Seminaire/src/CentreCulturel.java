public class CentreCulturel {
    
    public Seminaire creationCycleSeminaire(String titre, String resume, int capacite) {
        return new Seminaire(titre, resume, capacite);
    }

    public static void main(String args[]) {
        Seminaire seminaire = new Seminaire();
        try {
            seminaire.enter("planifier");
            seminaire.enter("inscription");
            seminaire.enter("inscription");
            seminaire.enter("inscription");
            seminaire.enter("start");
            seminaire.enter("desistement");
            seminaire.enter("fin");
            seminaire.enter("archivage");

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
