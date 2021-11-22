package objetPostal;

public class Colis extends ObjetPostal {
	
	private String declContenu = "contenu inconnu";
	private double valeurDecl;
	private static double tarifBase = 2; // global pour tous les colis

	public Colis() {} // ou équivalent {super();}

	public Colis(String origine, String destination, String codePostal, 
			double poids, double volume,
			Tauxrecom tauxRec, String declContenu, double valeurDecl
			) 
	// on ne passe pas en paramètre tarifBase car c'est une valeur
	// globale donc qui ne concerne pas en propre un colis
	{
		super(origine, destination, codePostal, poids, volume, tauxRec);
		this.setDeclContenu(declContenu);
		this.setValeurDecl(valeurDecl);
	}

	public String getDeclContenu() {
		return this.declContenu; //this. est optionnel
		// on l'ajoute pour plus de clarté et voir directement
		// qu'il s'agira d'un attribut
	}

	public void setDeclContenu(String declContenu) {
		this.declContenu = declContenu;
	}

	public double getValeurDecl() {
		return this.valeurDecl;
	}

	public void setValeurDecl(double valeurDecl) {
		this.valeurDecl = valeurDecl;
	}

	public static void setTarifBase(double tarifBase) {
		Colis.tarifBase = tarifBase;
	}


	public double getTarifBase() { // non static comme prévu
		// puisqu'on s'en sert dans la superclasse
		// dans un schéma de programmation par généralisation
		// et qu'on a besoin que la liaison dynamique s'applique
		return Colis.tarifBase;
	}
	
  
	public double tarifAff() {
		double res = super.tarifAff();
		if (this.getVolume() > 1.0/8)
			res += 3;
		return res;
	}


	public double tarifRemb() {
		if (this.getTauxRec()==Tauxrecom.moyen)
			return 0.1*this.getValeurDecl();
		if (this.getTauxRec()==Tauxrecom.fort)
			return this.getValeurDecl()/2;
		return 0;
	}


	public String getTypeObjet() {
		return "Colis";
	}


	public String toString() {
		return super.toString()+" volume : "+this.getVolume() 
		// éviter super.getVolume() car cette méthode n'est pas
		// redéfinie dans Colis et donc elle sera héritée naturellement
			+" - "+this.getValeurDecl();
	}
}


 

