package calculetteInfixe;

public class ENombre2 extends EtatCalculette {
	ENombre2(Calculette c){super(c);}
	
	int enter(String s) throws CalculetteException {
		float temp = 0;
		try {temp = Float.parseFloat(s);}
		catch (NumberFormatException e) {
			throw new CalculetteException("Entrez un nombre svp!");
		}

		switch (operations.valueOf(calc.getOp())) {
		case plus:
			calc.setAcc(calc.getAcc() + temp);
			break;
		case mult:
			calc.setAcc(calc.getAcc() * temp);
			break;
		case div:
			calc.setAcc(calc.getAcc() / temp);
			break;
		case moins:
			calc.setAcc(calc.getAcc() - temp);
			break;
		default:
			throw new CalculetteException("Operateur inconnu " + calc.getOp());
		}
		return (1);
	}
}
