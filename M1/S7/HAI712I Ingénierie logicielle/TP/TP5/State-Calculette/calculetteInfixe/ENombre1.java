package calculetteInfixe;

public class ENombre1 extends EtatCalculette{

	ENombre1(Calculette c){super(c);}
	
	public int enter(String s) throws CalculetteException {
		try{calc.setAcc(Float.parseFloat(s));}
		catch (NumberFormatException e)
			{throw new CalculetteException("Entrez un nombre svp!");}
		
		return(3);}
}
