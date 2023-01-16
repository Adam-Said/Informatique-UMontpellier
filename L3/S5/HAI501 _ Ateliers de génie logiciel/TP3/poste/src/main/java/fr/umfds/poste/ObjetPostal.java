package fr.umfds.poste;

public abstract class ObjetPostal
{
	private String origine;
	private String destination;
	private String codePostal; // on choisit ici String pour ne pas se préoccuper des 0 non significatifs
	private float  poids;
	private float volume;
	private Recommandation    tauxRecommandation;

	// constructeurs

	public ObjetPostal()
	{origine="inconnue"; destination="inconnue"; codePostal="0000"; 
	poids=0; volume=0; tauxRecommandation=Recommandation.zero;}


	// comme la classe est abstraite, ne sera pas appelé à la suite d'un new mais dans les constructeurs des sous classes
	public ObjetPostal(String origine, String destination, String codePostal, float poids, float volume,
			Recommandation tauxRecommandation) {
		this.origine = origine;
		this.destination = destination;
		this.codePostal = codePostal;
		this.poids = poids;
		this.volume = volume;
		this.tauxRecommandation = tauxRecommandation;
	}



	//accesseurs, seuls certains accesseurs en lecture sont utiles dans notre exemple

	public String getOrigine()  {return origine;}
	//public void setOrigine(String o) {origine=o;}

	public String getDestination()  {return destination;}
	//public void setDestination(String d) {destination=d;}

	public String getCodePostal()   {return codePostal;}
	//public void setCodePostal(String cp) {codePostal=cp;}

	public float getPoids()   {return poids;}
	//public void setPoids(float p) {poids=p;}

	public float getVolume()   {
		return volume;}
	//public void setVolume(float v) {volume=v;}

	public Recommandation getTauxRecommandation()   {
		return tauxRecommandation;
	}  

	//public void  setTauxRecommandation(Recommandation txr) {   
	//tauxRecommandation=txr;
	//}  

	// tous les objets postaux ont un tarif de base, auquel on peut accéder grâce à la méthode getTarifBase
	public abstract  float getTarifBase();

	// autres méthodes

	abstract public float tarifRemboursement();

	public float tarifAffranchissement()
	{// partie commune à tous les objets postaux pour le calcul du tarif d'affranchissement
		float t=getTarifBase();
		if (getTauxRecommandation()==Recommandation.un)  t=t+0.5f;
		else if (getTauxRecommandation()==Recommandation.deux) t=t+1.5f;
		return t;
	}

	public String toString()
	{// partie commune dans le descriptif
		return typeObjetPostal()+" "+getCodePostal()+"/"+getDestination()
		+"/"+getTauxRecommandation(); // ici on n'est pas obligé de passer par les accesseurs
	}

	abstract public String typeObjetPostal(); // utile pour le toString, voir ci-dessus
	
	public void affiche() {
		System.out.println(toString());
	}
}