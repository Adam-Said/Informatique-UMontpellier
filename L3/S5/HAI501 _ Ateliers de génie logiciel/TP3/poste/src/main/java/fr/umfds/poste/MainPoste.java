package fr.umfds.poste;

public class MainPoste
{
	private static float tolerancePrix=0.001f;
	private static float toleranceVolume=0.0000001f;
	public static void main(String[] arguments)
	{
		
		// création des objets sous test
		Lettre lettre1 = new Lettre("Le pere Noel",
				"famille Kirik, igloo 5, banquise nord",
				"7877", 25, 0.00018f, Recommandation.un, false);
		Lettre lettre2 = new Lettre("Le pere Noel",
				"famille Kouk, igloo 2, banquise nord",
				"5854", 18, 0.00018f, Recommandation.deux, true);
		Colis colis1 = new Colis("Le pere Noel", 
				"famille Kaya, igloo 10, terres ouest",
				"7877", 1024, 0.02f, Recommandation.deux, "train electrique", 200);      
		// test toString
		if(colis1.toString().equals("Colis 7877/famille Kaya, igloo 10, terres ouest/2/0.02/200.0")){
			System.out.println("toString Colis 1 OK");
		} else{
			System.out.println("toString Colis 1 NOK");
		}

		if(lettre1.toString().equals("Lettre 7877/famille Kirik, igloo 5, banquise nord/1/ordinaire")){
			System.out.println("toString Lettre 1 OK");
		} else{
			System.out.println("toString Lettre 1 NOK");
		}

		if(lettre2.toString().equals("Lettre 5854/famille Kouk, igloo 2, banquise nord/2/urgence")){
			System.out.println("toString Lettre 2 OK");	
		}else{
			System.out.println("toString Lettre 2 NOK");
		}


		// test affranchissement
		if(Math.abs(lettre1.tarifAffranchissement()-1.0f)<tolerancePrix){
			System.out.println("Affranchissement lettre 1 OK");
		} else{
			System.out.println("Affranchissement lettre 1 NOK");
		}
		if(Math.abs(lettre2.tarifAffranchissement()-2.3f)<tolerancePrix){
			System.out.println("Affranchissement lettre 2 OK");
		} else{
			System.out.println("Affranchissement lettre 2 NOK");
		}
		if(Math.abs(colis1.tarifAffranchissement()-3.5f)<tolerancePrix){
			System.out.println("Affranchissement colis 1 OK");
		} else{
			System.out.println("Affranchissement colis 1 NOK");
		}

		// test de tarif de remboursement
		if(Math.abs(lettre1.tarifRemboursement()-1.5f)<tolerancePrix){
			System.out.println("Remboursement lettre 1 OK");
		} else{
			System.out.println("Remboursement lettre 1 NOK");
		}
		if(Math.abs(lettre2.tarifRemboursement()-15.0f)<tolerancePrix){
			System.out.println("Remboursement lettre 2 OK");
		} else{
			System.out.println("Remboursement lettre 2 NOK");
		}
		if(Math.abs(colis1.tarifRemboursement()-100.0f)<tolerancePrix){
			System.out.println("Remboursement colis 1 OK");
		} else{
			System.out.println("Remboursement colis 1 NOK");
		}

		// TODO test sur les ColisEXpress
		
		// tests sur les sacs postaux
		SacPostal sac1 = new SacPostal();
		sac1.ajoute(lettre1);
		sac1.ajoute(lettre2);
		sac1.ajoute(colis1);

		if(Math.abs(sac1.valeurRemboursement()-116.5f)<tolerancePrix){
			System.out.println("Remboursement sac 1 OK");
		} else{
			System.out.println("Remboursement sac 1 NOK");
		}

		if(Math.abs(sac1.getVolume()-0.025359999558422715f)<toleranceVolume){
			System.out.println("volume sac 1 OK");
		} else{
			System.out.println("volume sac 1 NOK");
		}



		SacPostal sac2 = sac1.extraireV1("7877");

		if(sac2.getVolume()-0.02517999955569394f<toleranceVolume){
			System.out.println("volume sac 2 OK");
		} else{
			System.out.println("volume sac 2 NOK");
		}

}
}
	    
		

