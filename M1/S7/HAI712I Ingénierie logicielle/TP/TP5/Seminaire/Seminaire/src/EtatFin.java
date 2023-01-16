
public class EtatFin extends Etat {
    
    public EtatFin(Seminaire sem) {
        super(sem);
    }

    @Override
    int enter(String commande) throws EtatException {
        if(commande.equals("archive")) {
            System.out.println("Seminaire terminé");
            return 4;
        }
        else {
            throw new EtatException("Error: invalid command !");
        }
    }
}
