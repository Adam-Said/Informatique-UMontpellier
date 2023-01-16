import java.util.Scanner;

public class EtatEnCours extends Etat {
    public EtatEnCours(Seminaire sem) {
        super(sem);
    }

    @Override
    int enter(String commande) throws EtatException {
		Scanner scanner = new Scanner(System.in);
		if (commande.equals("desistement")) {
			System.out.println("Nom participant: ");
            String nom = scanner.nextLine();
            System.out.println("Prénom participant: ");
            String prenom = scanner.nextLine();
            seminaire.getInscrits().remove(nom, prenom);
            System.out.println("Utilisateur retiré avec succès");
            scanner.close();
		}
		else if (commande.equals("fin")) {
	
			scanner.close();
			return 3;
		}
		else {
			scanner.close();
			throw new EtatException("Error: invalid command !");
		}
		if(seminaire.getInscrits().size() < 2) {
			System.err.println("Le nombre de participants est inférieur à 2 !\n Abandon du séminaire...");
			return 3;
		}
		else {
			return 2;
		}
    }
}
