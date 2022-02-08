package fr.umfds.biblio;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;

public class BibliUtilities {
	private GlobalBiblioAccessInterface globalAccess;
	private HashMap<String, NoticeBibliographique> notices=new HashMap<>();
	private LocalDate lastInventaire = LocalDate.of(2019, 12, 31);
	private Clock clock;

	public ArrayList<NoticeBibliographique> chercherNoticesConnexes(NoticeBibliographique ref) { // Méthode pour récupérer des suggestions de lectures en récupérant des livres du même auteur
		ArrayList<NoticeBibliographique> listeConnexe = globalAccess.noticesDuMemeAuteurQue(ref);
		// on créé une liste à partir de la méthode renvoyant des livres du même auteur à partir d'un livre
		// On regarde si dans la liste on aurait pas remis le livre de base et si oui on le supprime
		for (NoticeBibliographique elt : listeConnexe ) {
			if (elt.getTitre() == ref.getTitre()) {
				listeConnexe.remove(elt);
			}
		}
		// Si la liste contient plus de 5 elements on supprime les elt en trop (on ne veut que 5 elt)
		if(listeConnexe.size() > 5) {
			for (int i = listeConnexe.size(); i == 5; i--) {
				listeConnexe.remove(i);
			}
		}
		return listeConnexe;
	}
	
	public NoticeStatus ajoutNotice(String isbn) throws AjoutImpossibleException, IncorrectIsbnException {
		// On récupère la notice fournie
			NoticeBibliographique newNotice = new NoticeBibliographique();
		try { // Si la notice n'existe pas on lève une exception
			newNotice = globalAccess.getNoticeFromIsbn(isbn);
		}
		catch(Exception ex) {
			throw new AjoutImpossibleException();
		}
		
		NoticeStatus res=NoticeStatus.nochange;
		
		if(notices.containsKey(isbn) && notices.get(isbn) == newNotice) { // Cas ou la notice est déjà presente
			res = NoticeStatus.nochange;
		}
		else if(notices.containsKey(isbn) && (notices.get(isbn) != newNotice)) { // Cas ou la notice existée mais modifiée
			notices.put(newNotice.getIsbn(), newNotice);
			res = NoticeStatus.updated;
		}
		else { // Sinon on l'ajoute à notre HashMap contenant nos notices
			notices.put(newNotice.getIsbn(), newNotice);
			res=NoticeStatus.newlyAdded;
		}
		return res;
	}
	
	public Boolean prevoirInventaire() { // on cherche si le dernier inventaire date de plus d'un an
	    LocalDate today = LocalDate.now(clock); // on récupère la date actuelle
		return (lastInventaire.plusYears(1)).isBefore(today);
		
	}
}
