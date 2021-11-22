package objetPostal;

public class Lettre extends ObjetPostal {
	
	private boolean urgente;
	private static double tarifBase = 0.5; // valeur globale à toutes les lettres

	public Lettre() {}

	public Lettre(String origine, String destination, String codePostal, double poids, 
			double volume, Tauxrecom tauxRec, boolean urgente) {
		// ici on ne va pas initialiser tarifBase car ce n'est pas propre à une lettre
		super(origine, destination, codePostal, poids, volume, tauxRec);
		this.setUrgente(urgente);
	}
	
	public boolean isUrgente() {
		return urgente;
	}

	public void setUrgente(boolean urgente) {
		this.urgente = urgente;
	}

	public static void setTarifBase(double tarifBase) {
		Lettre.tarifBase = tarifBase; // pas this.tarifBase !
	}

	@Override 
	// elle n'est pas static : je veux de la liaison dynamique
	public double getTarifBase() {
		return Lettre.tarifBase;
	}
	
	@Override
	public double tarifAff() {
		double res = super.tarifAff();
		if (this.isUrgente())
			res += 0.3;
		return res;
	}

	@Override
	public double tarifRemb() {
		if (this.getTauxRec()==Tauxrecom.moyen)
			return 1.5;
		if (this.getTauxRec()==Tauxrecom.fort)
			return 15;
		return 0;
	}

	@Override
	public String getTypeObjet() {
		return "Lettre";
	}
	
	@Override // indique que l'on est en train de redéfinir une méthode
	// héritée, et le compilateur va vérifier que la signature est
	// cohérente entre la méthode héritée et la méthode redéfinie
	public String toString() {
		String res = super.toString();
		if (urgente)
			res += "/urgente";
		else res += "/ordinaire";
		return res;
	}
}
