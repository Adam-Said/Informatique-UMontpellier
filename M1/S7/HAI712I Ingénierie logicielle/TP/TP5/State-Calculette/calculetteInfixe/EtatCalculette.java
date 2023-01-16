package calculetteInfixe;

abstract class EtatCalculette {
	static protected enum operations {plus, moins, mult, div};
	abstract int enter(String s) throws CalculetteException;
	Calculette calc;
	
	EtatCalculette(Calculette c){
		calc = c;
	}
}
