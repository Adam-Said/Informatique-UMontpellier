package objetPostal;

import java.time.LocalDate;

public class ColisExpress extends Colis {
	
	private final LocalDate dateEnvoi; 
	// différence par rapport au modèle UML on précise
	// que la date d'envoi ne changera pas
	private final int numeroSuivi;
	private static int numeroCourant = 0;
	private static double tarifBase = 30;
	private boolean emballage;
	private static double tarifEmballage = 3;

	public ColisExpress() {
		ColisExpress.numeroCourant++;
		this.numeroSuivi=numeroCourant;
		this.dateEnvoi = LocalDate.now();
	}

	public ColisExpress(String origine, String destination, String codePostal, double poids, double volume,
			Tauxrecom tauxRec, String declContenu, double valeurDecl,
			boolean emballage) {
// écart par rapport au modèle : on ne passe pas la date
// en paramètre car on prend la date du jour
		super(origine, destination, codePostal, poids, volume, tauxRec, declContenu, valeurDecl);
		ColisExpress.numeroCourant++;
		this.numeroSuivi=numeroCourant;
		this.dateEnvoi = LocalDate.now();
		this.setEmballage(emballage);
	}

	public LocalDate getDateEnvoi() {
		return this.dateEnvoi;
	}

	public static int getNumeroCourant() {
		return ColisExpress.numeroCourant;
	}

	// a éviter car on le gère en interne
	// ce sont les constructeurs qui font avancer ce nombre
	/*
	public static void setNumeroCourant(int numeroCourant) {
		ColisExpress.numeroCourant = numeroCourant;
	}*/

	@Override
	public double getTarifBase() {
		return ColisExpress.tarifBase;
	}

	public static void setTarifBase(double tarifBase) {
		ColisExpress.tarifBase = tarifBase;
	}

	public boolean isEmballage() {
		return this.emballage;
	}

	public void setEmballage(boolean emballage) {
		this.emballage = emballage;
	}

	public static double getTarifEmballage() {
		return ColisExpress.tarifEmballage;
	}

	public static void setTarifEmballage(double tarifEmballage) {
		ColisExpress.tarifEmballage = tarifEmballage;
	}

	public int getNumeroSuivi() {
		return numeroSuivi;
	}
	
	@Override // masquage de la méthode héritée
	public double tarifAff() {
		double res = this.getTarifBase();
		if (this.isEmballage()) // ==true
			res += getTarifEmballage();
		return res;
	}
	
	@Override
	public String getTypeObjet() {
		return "ColisExpress";
	}
	
	@Override
	public String toString() {
		return super.toString()+" Poids : "+this.getPoids()
			+"\n Numéro suivi : "+this.getNumeroSuivi();
	}

	@Override
	public void setPoids(double poids) {
		if (poids > 30)
			System.out.println("poids supérieur à 30");
		else 
			super.setPoids(poids);
	}
}
