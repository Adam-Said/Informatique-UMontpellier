package tdExceptionsAssertions;

public interface IPile<T> {
	void initialiser();
	void empiler(T t) throws PileVideException;
	T depiler()throws PileVideException;;
	T sommet() throws PileVideException;
	boolean estVide();
	int taille();
}
