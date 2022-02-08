package fr.umfds.biblio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class TestBibliUtilities 
{
	NoticeBibliographique N1 = new NoticeBibliographique("12345", "le premier livre", "Jean1");
	NoticeBibliographique N2 = new NoticeBibliographique("12345", "le second livre", "Jean1");
	NoticeBibliographique N3 = new NoticeBibliographique("23456", "le troisieme livre", "Jean1");


	@Mock 
	GlobalBiblioAccessInterface gba2;



	@Mock
	Clock mockedClock;	

	@Mock
	ArrayList<NoticeBibliographique> notices = new ArrayList<>(Arrays.asList(N1,N2,N3));

	@InjectMocks
	BibliUtilities b1 = new BibliUtilities();

	/**
	 * Rigorous Test :-)
	 */
	@Test
	public void shouldAnswerWithTrue()
	{
		assertTrue( true );
	}


	@Test
	public void testRechercheNoticeConnexe() {
		ArrayList<NoticeBibliographique> NoticeBiblio = new ArrayList<NoticeBibliographique>(); // Contient toutes les notices
		ArrayList<NoticeBibliographique> NoticeConnexe = new ArrayList<NoticeBibliographique>(); // Contient les notices connexes
		ArrayList<NoticeBibliographique> NoticeConnexeResultat = new ArrayList<NoticeBibliographique>(); // Contiendra le résultat de la recherche
		NoticeBibliographique N1 = new NoticeBibliographique("12345", "le premier livre", "Jean1");
		NoticeBibliographique N2 = new NoticeBibliographique("12345", "le second livre", "Jean1");
		NoticeBibliographique N3 = new NoticeBibliographique("23456", "le troisieme livre", "Jean1");
		NoticeBibliographique N4 = new NoticeBibliographique("34567", "le quatrieme livre", "Jean1");
		NoticeBibliographique N5 = new NoticeBibliographique("45678", "L'ivre d'un autre auteur", "Louis");
		NoticeBibliographique N6 = new NoticeBibliographique("56789", "Pas le meme auteur", "Louis");
		NoticeBibliographique N7 = new NoticeBibliographique("67890", "Un autre livre", "JeanRandom");
		NoticeConnexe.add(N2);
		NoticeConnexe.add(N3);
		NoticeConnexe.add(N4);
		NoticeBiblio.add(N1);
		NoticeBiblio.add(N2);
		NoticeBiblio.add(N3);
		NoticeBiblio.add(N4);
		NoticeBiblio.add(N5);
		NoticeBiblio.add(N6);
		NoticeBiblio.add(N7);


		when(gba2.noticesDuMemeAuteurQue(N1)).thenReturn(NoticeConnexe);
		NoticeConnexeResultat = b1.chercherNoticesConnexes(N1);
		assertEquals(NoticeConnexeResultat, NoticeConnexe);
	}

	@Test
	public void testAjoutNotice() throws AjoutImpossibleException, IncorrectIsbnException {
		NoticeBibliographique N3bis = new NoticeBibliographique("23456", "le troisieme livre", "Jean1");

		when(gba2.getNoticeFromIsbn("23456")).thenReturn(N3bis);

		assertThrows(AjoutImpossibleException.class, () -> b1.ajoutNotice("92382938")); // l'isbn n'existe pas alors on doit lever l'erreur
		assertEquals(NoticeStatus.newlyAdded, b1.ajoutNotice("23456"));


	}
	@Test
	public void testPrevoirInventaire() {
		LocalDate uneDate = LocalDate.of(2022, 12, 01); // On ajoute à notre bibli une clock avec une fausse date du jour (1/12/2022)
		Clock fixedClock = Clock.fixed(uneDate.atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
		doReturn(fixedClock.instant()).when(mockedClock).instant();
		doReturn(fixedClock.getZone()).when(mockedClock).getZone(); 
		assertTrue(b1.prevoirInventaire()); // Doit renvoyer True car le dernier inventaire date de 2020 et "nous sommes" en 2022
	}
}