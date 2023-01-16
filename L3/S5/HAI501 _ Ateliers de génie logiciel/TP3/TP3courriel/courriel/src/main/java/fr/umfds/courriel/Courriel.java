package fr.umfds.courriel;
import java.util.ArrayList;

public class Courriel {
	private String destinataire ="";
	private String titre= "";
	private String corps ="";
	private ArrayList<String> piecesJ = new ArrayList<String>();
	
	public Courriel() {
		destinataire ="NULL@NULL.NULL";
		titre = "NULL";
		corps = "NULL";
	}
	public Courriel(String dest,String titre,String corps,ArrayList<String> ListePJ) {
		this.destinataire = dest;
		this.titre = titre;
		this.corps = corps;
		this.piecesJ = ListePJ;
		
	}
	
	public Courriel(String dest,String titre,String corps) {
		this.destinataire = dest;
		this.titre = titre;
		this.corps = corps;
		this.piecesJ = new ArrayList<String>();
		
	}
	
	public Boolean envoi() throws DestInvalide,TitreNul,PresencePJ {
		if (!this.destinataire.matches("[a-zA-Z]+@[a-zA-Z]+\\.[a-zA-Z]+") ) { throw new DestInvalide("L'adresse du destinataire est invalide");};
		if (this.titre == "" || this.titre == " " || this.titre == null) { throw new TitreNul("Le courrier doit contenir un titre");};
		if ((this.corps.contains("PJ") || this.corps.contains("joint") || this.corps.contains("jointe")) && this.piecesJ.isEmpty() ) {
			throw new PresencePJ("Êtes vous sur d'avoir mis toutes vos pièces jointes ?");
		}
		return true;
	}
}
