package tdExceptionsAssertions;

import java.util.ArrayList;

public class PileBornee<T> {
  private int max;
  private ArrayList<T> elements;

  public PileBornee(int max){
    initialiser(max);
    assert this.estVide() : "La pile est vide";
  }

  public void setMax(int maxi) {
    this.max = maxi;
  }

  public void initialiser(int maxi) {
    elements = new ArrayList<T>();
    this.setMax(maxi);
    assert this.taille()==0 : "Liste vide !";
  }
  
  public void empiler(T t) throws PileVideException {
    if(this.taille() +1 > this.max)
      throw new PileVideException("La pile est pleine !");
    int tailleInit = this.taille();
		elements.add(t);
		assert this.sommet()==t : "dernier empile ="+this.sommet();
    assert this.taille()==(tailleInit+1) : "Nombre d'éléments à augmenté de 1";
	}

  public T depiler() throws PileVideException{
    int tailleInit = this.taille();
		if (this.estVide())
			throw new PileVideException("La pile est vide !");
		T sommet = elements.get(elements.size()-1);
		elements.remove(sommet);
    assert this.taille()==(tailleInit-1) : "Nombre d'éléments à diminué de 1";
		return sommet;
	}

  public T sommet() throws PileVideException{
		if (this.estVide())
			throw new PileVideException("en dépilant");
		return elements.get(elements.size()-1);
	}

  public boolean estVide() {return elements.isEmpty();}

  public int taille() {return elements.size();}

	public String toString(){return "Pile = "+ elements;}
}