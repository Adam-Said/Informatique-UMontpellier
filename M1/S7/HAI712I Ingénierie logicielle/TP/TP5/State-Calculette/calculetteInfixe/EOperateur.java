package calculetteInfixe;

public class EOperateur extends EtatCalculette{
	EOperateur(Calculette c){ super(c); }
	
	public int enter(String s) throws CalculetteException {
		calc.setOp(s);
		return(2);}
}
