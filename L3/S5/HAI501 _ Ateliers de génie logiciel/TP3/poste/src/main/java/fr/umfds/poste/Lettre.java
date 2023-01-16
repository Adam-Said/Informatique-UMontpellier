package fr.umfds.poste;

public class Lettre extends ObjetPostal
{
	private boolean urgence;
	private static float tarifBase=0.5f;
	private static String typeObjetPostal="Lettre";
	
	public Lettre()
	{ 
		// il est possible mais inutile d'ecrire super();
		urgence=false;
	}  



	public Lettre(String origine, String destination, String codePostal, float poids, float volume,
			Recommandation tauxRecommandation, boolean urgence) {
		super(origine, destination, codePostal, poids, volume, tauxRecommandation); // appel au constructeur de la super classe
		this.urgence = urgence;
	}



	//accesseurs

	public boolean isUrgence() {return urgence;}  
	//public void setUrgence(boolean u) {urgence=u;}

	//autres methodes

	public float getTarifBase(){return tarifBase;}

	public float tarifRemboursement()
	{if (getTauxRecommandation()==Recommandation.un) return 1.5f;
	else if (getTauxRecommandation()==Recommandation.deux) return 15;
	else return 0;
	}

	// redéfinition : spécialisation de la méthode tarifAffranchissement de la super classe
	public float tarifAffranchissement()
	{
		float t=super.tarifAffranchissement();
		if (isUrgence())
			t = t+0.3f;
		return t;
	}


	public String toString()
	{String S = super.toString()+"/";
	if (isUrgence()) S += "urgence";
	else S += "ordinaire";
	return S;
	}

	public String typeObjetPostal() {return typeObjetPostal;}


}
