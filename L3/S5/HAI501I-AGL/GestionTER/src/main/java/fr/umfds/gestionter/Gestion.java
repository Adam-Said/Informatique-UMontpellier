package fr.umfds.gestionter;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Gestion {

	public static void main(String[] args) {
		System.out.println("\n------ GestionTER v1.0.0 ------\n");

		// Création d'un scanner pour les inputs
		Scanner userInput = new Scanner(System.in);
		int choice = 0;

		try {      
			while (choice < 7) {
				// Lancement du menu
				System.out.println("\n Que voulez-vous faire ?\n"+
						"1. Création des enseignants (à partir d'exemples)\n"+
						"2. Création des étudiants (à partir d'un fichier)\n"+
						"3. Création des groupes\n"+
						"4. Récupération des sujets\n"+
						"5. Affectations Sujets aux groupes\n"+
						"6. Générer une soutenance\n"+
						"7. Quitter\n");
				choice = userInput.nextInt();

				// Création du mapper pour retreive les objets du json
				ObjectMapper objMapp = new ObjectMapper();

				switch(choice) {
				case 1:
					System.out.println("\n Création d'enseignants exemples :\n");
					// Création d'enseignants type
					Enseignant Baert = new Enseignant(1, "AE", "BAERT");
					Enseignant Poncelet = new Enseignant(2, "PASCAL", "PONCELET");
					// Mise en place de la HashMap pour les id des enseignants
					HashMap<Integer, Enseignant> listeEnseignant = new HashMap<Integer, Enseignant>();

					listeEnseignant.put(Baert.getId(), Baert);
					listeEnseignant.put(Poncelet.getId(), Poncelet);
					// Ecriture dans le fichier
					objMapp.writeValue(new File("data/enseignant.json"), listeEnseignant);
					System.out.println(listeEnseignant);
					break;
				case 2:
					System.out.println("\n Création d'étudiants à partir d'un fichier :\n");

					// Récupération des objets en liste
					Etudiant[] listeEtudiants = objMapp.readValue(Paths.get("data/etudiants.json").toFile(), Etudiant[].class);

					HashMap<Integer, Etudiant> dicoEtudiants = new HashMap<Integer, Etudiant>();

					// Affichage de la liste + assoc hashmap
					System.out.println("\nListe des etudiants : \n");
					for(int i =0 ; i < listeEtudiants.length; i++) {
						dicoEtudiants.put(listeEtudiants[i].getId(), listeEtudiants[i]);
						System.out.println(listeEtudiants[i]);
					}
					break;
				case 3:
					System.out.println("\n Création des groupes :\n");

					// Récupération des objets en liste
					Etudiant[] listeEtudiants2 = objMapp.readValue(Paths.get("data/etudiants.json").toFile(), Etudiant[].class);

					// Ajout des étudiants à la liste pour 1 groupe
					ArrayList<Etudiant> Gr1 = new ArrayList<Etudiant>();
					Gr1.add(listeEtudiants2[0]);
					Gr1.add(listeEtudiants2[1]);

					ArrayList<Etudiant> Gr2 = new ArrayList<Etudiant>();
					Gr2.add(listeEtudiants2[2]);
					Gr2.add(listeEtudiants2[3]);

					// Création des groupes
					Groupe Groupe1 = new Groupe(1, "Groupe des travailleurs", Gr1);
					Groupe Groupe2 = new Groupe(2, "Grp2", Gr2);

					// Affichage des groupes
					System.out.println(Groupe1.toString());
					System.out.println(Groupe2.toString());


					break;
				case 4:
					System.out.println("\n Récupération des sujets :\n");
					// Récupération des sujets à partir du fichier
					Sujet[] listeSujets = new Sujet[0];
					try {
						listeSujets = objMapp.readValue(Paths.get("data/sujets.json").toFile(), Sujet[].class);
					}
					catch(Exception e) {
						e.printStackTrace();
					}

					// Affichage des sujets
					for (int i = 0; i < listeSujets.length; i++) {
						System.out.println("\n Sujet n°"+ i +" : "+ listeSujets[i]);
					}
					break;
				case 5:
					System.out.println("\n Affectation des Sujets :\n");
					// Créations des groupes et sujets à partir du JSON
					Sujet[] Sujets = new Sujet[0];
					Groupe[] Groupes = new Groupe[0];
					try {
						Sujets = objMapp.readValue(Paths.get("data/sujets.json").toFile(), Sujet[].class);
						Groupes = objMapp.readValue(Paths.get("data/groupes.json").toFile(), Groupe[].class);
					}
					catch(Exception e) {
						e.printStackTrace();
					}
					System.out.println("\n Récupération des sujets :\n");
					// Affichage groupes
					System.out.println("\n Affichage des groupes :\n");
					for (Groupe G : Groupes) {
						System.out.println(G.toString());
					}
					// Affichage sujets
					System.out.println("\n Affichage des Sujets :\n");
					for (Sujet S : Sujets) {
						System.out.println(S.toString());
					}
					int i = 0;
					HashMap<String,String> GroupeSujet = new HashMap<String,String>();
					if(Sujets.length >= Groupes.length) {
						for (Groupe G : Groupes) {
							GroupeSujet.put(G.getNomGroupe(), Sujets[i].getTitreSujet());
							i++;
						}
						// Ecriture dans un nouveau fichier
						objMapp.writeValue(new File("data/groupesujet.json"), GroupeSujet);
					}
					else {
						System.out.println("\n nombre de sujets insuffisant\n");
					}
					break;
				case 6 :
					Sujet SujetTest = new Sujet(3, "Les singes", "Observation des singes");
					ArrayList<Etudiant> GroupeT = new ArrayList<Etudiant>();
					Groupe GroupeTest = new Groupe(1, "Groupe des travailleurs", GroupeT);
					Enseignant Baer = new Enseignant(1, "AE", "BAERT");

					Soutenance Sout = new Soutenance(GroupeTest, SujetTest, Baer);
					System.out.println(Sout);
					System.out.println();
					for(int i1 = 0; i1<50; i1++) {
						System.out.println(Sout.setHorraire());
					}
					Sout.getPlanning();
					break;
				}
			}
		}	
		catch(Exception e) {
			e.printStackTrace();
		}
		// Fermeture du Scanner
		userInput.close();
	}
}
