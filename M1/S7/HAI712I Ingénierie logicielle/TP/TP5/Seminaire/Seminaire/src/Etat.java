public abstract class Etat {
    abstract int enter(String commande) throws EtatException;
	Seminaire seminaire;
	
	Etat(Seminaire sem){
		seminaire = sem;
	}
}
