package tp2;

public class TestEtudiant { 
	public static void main(String[] args) {

		Module moduleMath1 = new Module("Mathématiques 1");
		Module moduleMath2 = new Module("Mathématiques 2");
		Module moduleAnglais = new Module("Anglais");

		Note[] notes = new Note[3];
		notes[0] = new Note(10, moduleMath1);
		notes[1] = new Note(15, moduleMath2);
		notes[2] = new Note(20, moduleAnglais);

		Etudiant etudiant = new Etudiant("Jean", "FR", 17, "01 mars 2000", notes);
		//System.out.println(etudiant.moyenne());
		//System.out.println("Résultat par défaut de toString() sur un objet");
		//System.out.println(etudiant.toString());

		//System.out.println("Résultat de toString() sur un objet Etudiant avec redéfinition");
		//System.out.println(etudiant);

		//System.out.println("Résultat de toString() sur un objet de type Module");
		//System.out.println(moduleMath1);

		//System.out.println("Résultat de toString() sur un objet de type Note");
		//System.out.println(notes[0]);

		Note[] notesJulien = new Note[3];
		notesJulien[0] = new Note(0, moduleMath1);
		notesJulien[1] = new Note(12, moduleMath2);
		notesJulien[2] = new Note(11, moduleAnglais);
		Etudiant etudiantJulien = new Etudiant("Julien", "FR", "03 mai 2000", notesJulien);
    System.out.println("Date de naissance : "+ etudiantJulien.getDateDeNaissance());
		System.out.println("Age : " + etudiantJulien.getAge());
		System.out.println("Ligne résultat : "+etudiantJulien.ligneResultats());

    etudiantJulien.setDateDeNaissance("01 octobre 1996");
		System.out.println("Date de naissance : "+etudiantJulien.getDateDeNaissance());
		System.out.println("Age : " + etudiantJulien.getAge());
    }

}
