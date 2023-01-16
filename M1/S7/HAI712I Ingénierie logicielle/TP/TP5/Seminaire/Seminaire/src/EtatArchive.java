
public class EtatArchive extends Etat {
    
    public EtatArchive(Seminaire sem) {
        super(sem);
    }

    @Override
    int enter(String commande) throws EtatException {
        System.err.println("Le séminaire est terminé, aucune action n'est réalisable");
        return 4;
    }
}
