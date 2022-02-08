package objetPostal;

public class MainObjetPostal {


	public static void main(String[] args) {
		ObjetPostal l1 = new Lettre();
		
		ObjetPostal l2 = new Lettre("Sommières", "Montpellier", "34000", 0.06, 
				0.005, Tauxrecom.faible, false);
		
		l1.affiche();
		l2.affiche();
		
		ObjetPostal l3 = new Colis("Nîmes","Montpellier","34000",1,-0.4,
				Tauxrecom.fort,"chocolat",50);
		l3.affiche();
    l3.affiche(); // pendant affiche, this=l3
		// cela appelle affiche de ObjetPostal
		// qui contient this.toString() avec this=l3
		// donc on cherche la méthode toString à partir
		// de la classe (new) de l3, càd Colis
		
		/*
		 * String origine, String destination, String codePostal, double poids, double volume,
			Tauxrecom tauxRec, String declContenu, double valeurDecl,
			boolean emballage
		 */
		
		ObjetPostal l4 = new ColisExpress("Sommières","Montpellier","34000",1,0.4,
				Tauxrecom.fort,"chocolat",50,true);	
		ObjetPostal l5 = new ColisExpress("Sommières","Montpellier","34000",1,0.4,
				Tauxrecom.fort,"gâteaux secs",70,true);	
		l4.affiche();
		l5.affiche();		
	}
}
