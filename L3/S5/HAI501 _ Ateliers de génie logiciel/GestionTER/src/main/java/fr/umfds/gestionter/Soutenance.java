package fr.umfds.gestionter;

import java.util.Random;

public class Soutenance {
	private Groupe groupe;
	private Sujet sujet;
	private String horraire;
	private Enseignant encadrant;
	int [][] H = new int[10][5];
	
	Soutenance(Groupe g, Sujet s, Enseignant e) throws ObjetInvalide {
		this.groupe = g;
		this.sujet = s;
		this.horraire = null;
		if (e != null) this.encadrant = e;
		else throw new ObjetInvalide("Il faut un encadrant pour demander une soutenance !");
	}

	
	
	public String calcJour(int i) {
		String [] J = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi"};
		return J[i];
	}
	
	public int[] Insertion(int i, int j) throws ObjetInvalide {
		int [] newhorraire = new int[2];
		if (H[i][j] == 0) {
			try {
				H[i][j] = groupe.getId();
			} catch (Exception e) {
				e.printStackTrace();
			}
			newhorraire[0] = i;
			newhorraire[1] = j;
			return newhorraire;
		}
		else {
				for (int ligne = 0; ligne < H.length; ligne++) { //lignes
					for (int colonne = 0; colonne < H[ligne].length; colonne++) { //colonnes
						if (H[ligne][colonne] == 0) {
							try {
								H[ligne][colonne] = groupe.getId();
							} catch (Exception e) {
								e.printStackTrace();
							}
							newhorraire[0] = ligne;
							newhorraire[1] = colonne;
							return newhorraire;
						}
					}
			} 
		}
		throw new ObjetInvalide("Trop de groupes !");
	}
	
	public String setHorraire() {
		int heure;
		int date;
		int matriceL;
		int matriceC;
		String datefinale;
		int heurefinale;
		int [] newhorraire = new int[2];
		
		heure = 8 + (int)(Math.random() * ((17 - 8) + 1)); // renvoi un entier entre 8 et 18
		date = 0 + (int)(Math.random() * ((4 - 0) + 1)); //renvoi un entier entre 0 et 4
		
		matriceL = heure-8;
		matriceC = date;
		try {
			newhorraire = Insertion(matriceL, matriceC);
		} catch (ObjetInvalide e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		datefinale = calcJour(newhorraire[1]);
		heurefinale = newhorraire[0] + 8;
						
		return "La soutenance à été crée : Elle aura lieu le " + datefinale + " à " + heurefinale +" Heure";
	}
	
	public void getPlanning() {
		for (int i = 0; i < H.length; i++) {
			for (int j = 0; j < H[i].length; j++) {
				System.out.printf("%4d", H[i][j]);
			}
			System.out.println();
		}
	}
	
 	
	@Override
	public String toString() {
	    return "\nGroupe : " + groupe + "\nSujet : " + sujet + "\nHorraire : " + horraire + "\nEncadrant : " + encadrant;
	}
	
}
