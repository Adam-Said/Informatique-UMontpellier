package fr.umfds.poste;

import java.time.LocalDate;


public class ColisExpress extends Colis {
	private LocalDate dateEnvoi;
	private static int nbColisExpress=0;
	private int numeroSuivi;
	private boolean emballagePoste;
	private static float affranchissementColisExpress=30f;
	private static float tarifEmballage=3f;
	private static float poidsMax=30;

	public ColisExpress(String o, String d, String cP, float pds, float v, Recommandation t, String dC, float vD,
			boolean emballagePoste) throws ColisExpressInvalide {
		super(o, d, cP, pds, v, t, dC, vD); // appel du constructeur de la super classe Colis
		if (pds>=poidsMax) {throw new ColisExpressInvalide("poids incohérent, votre colis ne pourra pas être acheminé."); } // Exception si le poids est supérieur à la capacité max
		this.emballagePoste = emballagePoste;
		numeroSuivi=nbColisExpress;
		nbColisExpress++;
		dateEnvoi=LocalDate.now();// date du jour
	}

	// redéfinition en masquant le comportement hérité de la super-classe
	public float tarifAffranchissement() {
		float result=ColisExpress.affranchissementColisExpress;
		if (emballagePoste) result+=ColisExpress.tarifEmballage;
		return result;
	}

	public String typeObjetPostal() {return "Colis express";}
	public String toString() {
		return super.toString()+"/"+getPoids()+"/"+numeroSuivi;
	}

}
