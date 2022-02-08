package objetPostal;

public abstract class ObjetPostal {
	
	private String origine="origine inconnue", 
			destination = "destination inconnue", 
			codePostal = "code postal inconnu";
	private double poids, volume;
	private Tauxrecom tauxRec = Tauxrecom.faible;

	public ObjetPostal() {}

	public ObjetPostal(String origine, String destination, String codePostal, 
			double poids, double volume,Tauxrecom tauxRec) {
		super();
		this.setOrigine(origine);
		this.setDestination(destination);
		this.setCodePostal(codePostal);
		this.setPoids(poids);
		this.setVolume(volume);
		this.setTauxRec(tauxRec);
	}

	public String getOrigine() {
		return origine;
	}

	public void setOrigine(String origine) {
		this.origine = origine;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public double getPoids() {
		return poids;
	}

	public void setPoids(double poids) {
		if (poids >=0)
			this.poids = poids;
		else System.out.println("poids négatif");
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		if (volume >=0)
			this.volume = volume;
		else System.out.println("volume négatif");
	}

	public Tauxrecom getTauxRec() {
		return tauxRec;
	}

	public void setTauxRec(Tauxrecom tauxRec) {
		this.tauxRec = tauxRec;
	}
	
	
	/*
	 * Tarif d'affranchissement
	 * ce qui est commun à tous les objets postaux
	 * tarif base + une quantité dépendante du tauxRec
	 */
	
	public double tarifAff() {
		double res = this.getTarifBase();
		if (this.tauxRec==Tauxrecom.moyen)
			res+=0.5;
		else if (this.tauxRec==Tauxrecom.fort)
			res+=1.5;
		return res;
	}

	abstract public double getTarifBase();
	
	/*
	 * Tarif de remboursement
	 * ce qui est commun à tous les objets postaux
	 * solution 1 : return 0; 
	 * solution 2 : la rendre abstraite
	 */
	
	abstract public double tarifRemb();
	
	
	/*
	 * toString
	 * ce qui est commun à tous les objets postaux
	 *
	 */	
	
	public String toString() {
		return "Type d'objet : "+this.getTypeObjet()+"\n Destination : "+this.getCodePostal()+" - " +this.getDestination()+ "\n Infos : "+this.getTauxRec();
	}
	
	abstract public String getTypeObjet();
	
	/*
	 * affiche
	 * ce qui est commun à tous les objets postaux
	 *
	 */	
	
	public void affiche() {
		System.out.println(this.toString());
	}
}
