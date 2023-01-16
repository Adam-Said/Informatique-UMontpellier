package calculetteInfixe;

public class Calculette {
	public static String version = "Infixe";
	protected EtatCalculette etatCourant;
	protected EtatCalculette[] etats = new EtatCalculette[3];
	double acc;
	String operateur;
	
	public Calculette(){
		etats[0] = new EOperateur(this);
		etats[1] = new ENombre1(this);
		etats[2] = new ENombre2(this);
		etatCourant = etats[0];
		acc = 0;
	}
	
	double getAcc(){return acc;}
	void setAcc(double v){acc = v;}
	String getOp(){return operateur;}
	void setOp(String v){operateur = v;}
	
	public void enter(String s) throws CalculetteException{
		
		//déléguer le traitement de la requète à l'état courant
		//qui de plus décide de qui est l'état suivant
		int etatSuivant = etatCourant.enter(s);
		//changer l'état courant (le -1 car en Java les tableau débutent à l'indice 0)
		etatCourant = etats[etatSuivant - 1];
	}
	
	public double getResult(){
		return acc;
	}
}
