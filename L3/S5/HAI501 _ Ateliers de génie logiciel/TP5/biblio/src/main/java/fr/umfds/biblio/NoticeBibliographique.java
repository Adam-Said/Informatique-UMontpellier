package fr.umfds.biblio;

public class NoticeBibliographique {
	private String titre;
	private String auteur;
	private String isbn;
	
	public NoticeBibliographique() {
		super();
		this.isbn="00000";
		this.titre = "NULL";
		this.auteur = "NULL";
	}

	public NoticeBibliographique(String isbn, String titre, String auteur) {
		super();
		this.isbn=isbn;
		this.titre = titre;
		this.auteur = auteur;
	}
	public String getTitre() {
		return titre;
	}
	public String getAuteur() {
		return auteur;
	}

	public String getIsbn() {
		return isbn;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((auteur == null) ? 0 : auteur.hashCode());
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + ((titre == null) ? 0 : titre.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NoticeBibliographique other = (NoticeBibliographique) obj;
		if (auteur == null) {
			if (other.auteur != null)
				return false;
		} else if (!auteur.equals(other.auteur))
			return false;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		if (titre == null) {
			if (other.titre != null)
				return false;
		} else if (!titre.equals(other.titre))
			return false;
		return true;
	}
}
