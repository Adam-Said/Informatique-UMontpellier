package tdExceptionsAssertions;

import java.util.ArrayList;

public class Pile<T> implements IPile<T>{
	// structure de stockage interne des éléments

	private ArrayList<T> elements;
	// Mise en oeuvre des opérations

	public Pile(){initialiser();
  assert this.estVide() : "Pile vide";
  }
  
	public T depiler() throws PileVideException{
    int tailleInit = this.taille();
		if (this.estVide())
			throw new PileVideException("en dépilant");
		T sommet = elements.get(elements.size()-1);
		elements.remove(sommet);
    assert this.taille()==(tailleInit-1) : "Nombre d'éléments à diminué de 1";
		return sommet;
	}

	public void empiler(T t) throws PileVideException { 
    int tailleInit = this.taille();
		elements.add(t);
		assert this.sommet()==t : "dernier empile ="+this.sommet();
    assert this.taille()==(tailleInit+1) : "Nombre d'éléments à augmenté de 1";
	}

	public boolean estVide() {return elements.isEmpty();}

	public void initialiser() {
    elements = new ArrayList<T>();
    assert this.taille()==0 : "Liste vide !";
    }

	public T sommet() throws PileVideException{
		if (this.estVide())
			throw new PileVideException("en dépilant");
		return elements.get(elements.size()-1);
	}

	public int taille() {return elements.size();}

	public String toString(){return "Pile = "+ elements;}
}
