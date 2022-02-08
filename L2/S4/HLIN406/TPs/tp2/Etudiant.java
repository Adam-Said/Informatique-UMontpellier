package tp2;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


//import java.text.DateFormat;
//import java.text.ParseException;
//import java.util.Date;
//import java.util.GregorianCalendar;
/**
 * Classe representant un Etudiant.
 */
public class Etudiant {

	private String nom;
	private String codePays;
	private int age;
	private LocalDate dateDeNaissance;
	private Note[] notes;

	public Etudiant(String nom, String codePays, String dateDeNaissance,  Note[] notes) {
		this.nom = nom;
		this.codePays = codePays;
		this.dateDeNaissance = creerDate(dateDeNaissance);
		this.age = calculerAge(this.dateDeNaissance);
		this.notes = notes;
	}

	public Etudiant(String nom, String codePays, int age, String dateDeNaissance,  Note[] notes) {
		this.nom = nom;
		this.codePays = codePays;
		this.dateDeNaissance = creerDate(dateDeNaissance);
		this.age = age;
		this.notes = notes;
	}

	public Etudiant() {
		notes = new Note[3];
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public boolean estFrancophone() {
		return getCodePays().equals("FR");
	}

	public String getCodePays() {
		return codePays;
	}

	public void setCodePays(String codePays) {
		this.codePays = codePays;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public LocalDate getDateDeNaissance() {
		return dateDeNaissance;
	}

	public void setDateDeNaissance(String dateDeNaissance) {
		this.dateDeNaissance = creerDate(dateDeNaissance);
    this.age = calculerAge(this.dateDeNaissance);
	}


	public Note[] getNotes() {
		return notes;
	}

	public void setNotes(Note[] notes) {
		this.notes = notes;
	}

	/**
	 * Calcule la moyenne de l'étudiant sur la base des trois notes obtenues
	 * 
	 * @return la moyenne de l'étudiant sur la base des trois notes obtenues
	 */
	public float moyenne() {
		float somme;
		somme = 0;
		for (int i = 0; i < notes.length; i++) {
			somme = somme + notes[i].getValeur();
		}
		return somme / notes.length;
	}

	/**
	 * Calcule la mention obtenue en fonction de la moyenne de l'étudiant
	 * 
	 * @return une chaine de caractères correspondant a la mention obtenue
	 */
	public String calculeMention() {
		String mention;
		float moyenne;
		moyenne = moyenne();
		mention = Mention.AUCUNE.toString();
		if (moyenne > 12.0f && moyenne < 14.0f) {
			mention = Mention.AB.toString();
		} else if (moyenne >= 14.0f && moyenne < 16.0f) {
			mention = Mention.B.toString();
		} else if (moyenne >= 16.0f) {
			mention = Mention.TB.toString();
		}
		return mention;

	}

	/**
	 * Retourne une chaine de caractères représentant la liste des modules
	 * obtenus en fonction de la moyenne
	 * 
	 * @return une chaine de caractères représentant la liste des modules
	 *         obtenus
	 */
	private String getModulesObtenus() {
		String modulesObtenus;
		modulesObtenus = "";
		for (int i = 0; i < notes.length; i++) {
			if (notes[i].getValeur() >= 10.0f) {
				modulesObtenus += ", "+notes[i].getModule().getNom();
			}
		}
		if(modulesObtenus.isEmpty()){
			modulesObtenus = ", Aucun modules obtenus";
		}
		return modulesObtenus;
	}

	/**
	 * 
	 * Méthode qui retourne une chaine d'une ligne précisant le nom, la moyenne
	 * et la mention, et, seulement s'il est ajourné, les modules obtenus.
	 * 
	 * @return une chaine de caractères
	 */
	public String ligneResultats() {
		String resultat;
		resultat = getNom() + ", " + moyenne() + ", " + calculeMention();
		// On fabrique une chaine avec les modules obtenus si l'étudiant est
		// ajourné
		if (moyenne() < 10.0f) {
			resultat += getModulesObtenus();
		}
		return resultat;
	}

	public String toString() {
		return "Nom étudiant :" + this.getNom();
	}

	/**
	 * Calcule l'age de l'étudiant en se basant sur la date de naissance
	 * 
	 * @return l'age de l'étudiant
	 */
	/*public int calculerAge() {
		Long ageEnMillisecondes;
		Long ageEnAnnees;
		ageEnMillisecondes = new GregorianCalendar().getTimeInMillis() - dateDeNaissance.getTime();
		ageEnAnnees = ageEnMillisecondes / (1000L * 60 * 60 * 24 * 365);
		return ageEnAnnees.intValue();
	}

	/**
	 * Méthode retournant un objet de type Date correspondant a la chaine de
	 * caractère en entrée.
	 * 
	 * @param date
	 *            Une chaine de caractère au format jour/mois/année
	 * @return un objet de type Date ou null si le format est incorrect
	 */
	/*private Date creerDate(String date) {
		// On récupère un formatteur de date, qui gère des formats longs
		DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
		Date d;
		d = null;
		try {
			d = df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}*/
	
	// Une chaine de caractère au format jour/mois/année
	private LocalDate creerDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.FRANCE);
	  LocalDate birthDate = LocalDate.parse( date.toLowerCase(), formatter);
	  return birthDate;
	}
	
	public int calculerAge(LocalDate dateDeNaissance) {
		return dateDeNaissance.until(LocalDate.now()).getYears();
	}
	

}
