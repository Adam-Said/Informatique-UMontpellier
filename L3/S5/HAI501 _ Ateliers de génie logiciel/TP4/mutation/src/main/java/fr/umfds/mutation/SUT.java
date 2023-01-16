package fr.umfds.mutation;

//import java.util.OptionalInt;

public class SUT {
	private int[] tab;  // On remplace le optional int par un simple tableau d'entiers
	private int taille=0;

	public SUT(int tailleMax) {
		tab=new int[tailleMax];
	}

	public void ajout(int e) throws TableauPleinException{
		if (taille==tab.length) {
			throw new TableauPleinException();
		}
		tab[taille]=e;
		taille++;
	}

	public int[] values() {
		int[] result=new int[taille];
		for (int i=0;i<taille;i++) {
				result[i]=tab[i];
		}
		return result;
	}

	public int retourneEtSupprimePremiereOccurenceMin() throws TableauVideException{
		if (taille==0) {
			throw new TableauVideException();
		}
		int min =tab[0];
		int imin = 0;
		for (int i=1;i<taille;i++) {
			if (tab[i]< min) { // On cherche  le minimum ne remplacant le min actuel par la plus petite valeure trouvée
				// on a un nouveau min
				min=tab[i];
				imin=i;
			}
		}/*   /!\ Suppression de la boucle si après car ne fonctionne pas bien (perte de l'avant dernière valeure) Remplacement par la boucle ci après
		for (int i=taille-1;i>imin;i--) { // parcours du tableau depuis la fin pour décalage à gauche
			tab[i-1]=tab[i]; // suppression et décalage à gauche
		}*/
		
		for(int i = imin; i<taille-1; i++) {
			tab[i] = tab[i+1]; //boucle, on part de notre min, ou la case contient le min, puis on copie ce qui est à sa droite dans sa case (override) et ainsi de suite, puis on supprime le dernier élément.
		}					   //Puis si on a le min à la dernière case, pas de problème il se fera supprimer dans tous les cas.
		
		taille--;
		return min;
	}
}