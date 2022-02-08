package fr.umfds.biblio;

import java.util.ArrayList;

public interface GlobalBiblioAccessInterface {

	NoticeBibliographique getNoticeFromIsbn(String isbn) throws IncorrectIsbnException;

	ArrayList<NoticeBibliographique> noticesDuMemeAuteurQue(NoticeBibliographique ref);

	ArrayList<NoticeBibliographique> autresEditions(NoticeBibliographique ref);

}