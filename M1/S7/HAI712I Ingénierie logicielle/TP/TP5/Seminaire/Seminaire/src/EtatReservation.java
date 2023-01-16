import java.util.Scanner;

public class EtatReservation extends Etat {
    
    public EtatReservation(Seminaire sem) {
        super(sem);
    }

    @Override
    int enter(String commande) throws EtatException {
        Scanner scanner = new Scanner(System.in);
        if (commande.equals("inscription")) {
            if(this.seminaire.getInscrits().size() < this.seminaire.getCapacite()) {
                System.out.println("Nom participant: ");
                String nom = scanner.nextLine();
                System.out.println("Prénom participant: ");
                String prenom = scanner.nextLine();
                seminaire.getInscrits().put(nom, prenom);
                System.out.println("Utilisateur inscrit avec succès !");
            }
            else {
                System.err.println("Plus de places disponibles !");
            }
            scanner.close();
            return 1;
        }
        else if (commande.equals("desistement")) {
            System.out.println("Nom participant: ");
            String nom = scanner.nextLine();
            System.out.println("Prénom participant: ");
            String prenom = scanner.nextLine();
            seminaire.getInscrits().remove(nom, prenom);
            System.out.println("Utilisateur retiré avec succés");
            scanner.close();
            return 1;
        }
        else if (commande.equals("start")) {
            System.out.println("Début du séminaire");
            scanner.close();
            return 2;
        }
        else {
            scanner.close();
            throw new EtatException("Error: invalid command !");
        }
    }

}
