import java.util.Scanner;

public class EtatPlanification extends Etat {

    public EtatPlanification(Seminaire sem) {
        super(sem);
    }

    @Override
    int enter(String commande) throws EtatException {
        if(commande.equals("planifier")) {
            Scanner scanner = new Scanner(System.in);
            String titre = scanner.nextLine();
            String resume = scanner.nextLine();
            int capa = scanner.nextInt();
            seminaire.setTitre(titre);
            seminaire.setResume(resume);
            seminaire.setCapacite(capa);
            scanner.close();
            System.out.println("Ouverture de réservations !\n");
        }
        else{
            throw new EtatException("Error: merci de commencer par la fonction planifier pour débuter");
        }
        return 1;
    }
}
