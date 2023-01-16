package fr.umfds.poste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class TestPoste 
{
	private static float tolerancePrix=0.001f;
	private static float toleranceVolume=0.0000001f;
	Lettre lettre1 = new Lettre("Le pere Noel",
			"famille Kirik, igloo 5, banquise nord",
			"7877", 25, 0.00018f, Recommandation.un, false);
	Lettre lettre2 = new Lettre("Le pere Noel",
			"famille Kouk, igloo 2, banquise nord",
			"5854", 18, 0.00018f, Recommandation.deux, true);
	Colis colis1 = new Colis("Le pere Noel", 
			"famille Kaya, igloo 10, terres ouest",
			"7877", 1024, 0.02f, Recommandation.deux, "train electrique", 200);
	ColisExpress colis2;
    /**
     * Rigorous Test :-)
     * @throws ColisExpressInvalide 
     */
	
    @BeforeAll
    static void initAll() {
    	System.out.println("---- Debut des Tests ----");
    }
	
    @DisplayName("Test de base")
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    
    @DisplayName("Test de toString")
    @Test
    public void TestToString() {
	    assertEquals("Colis 7877/famille Kaya, igloo 10, terres ouest/2/0.02/200.0", colis1.toString());
	    assertEquals("Lettre 7877/famille Kirik, igloo 5, banquise nord/1/ordinaire", lettre1.toString());
		assertEquals("Lettre 5854/famille Kouk, igloo 2, banquise nord/2/urgence", lettre2.toString());
	}
    
    @DisplayName("Test de affranchissement")
    @Test
    public void TestAffranchissement() {
		assertTrue(Math.abs(lettre1.tarifAffranchissement()-1.0f)<tolerancePrix);
		assertTrue(Math.abs(lettre2.tarifAffranchissement()-2.3f)<tolerancePrix);
		assertTrue(Math.abs(colis1.tarifAffranchissement()-3.5f)<tolerancePrix);
    }
    
    @DisplayName("Test de remboursement")
    @Test
    public void testTarifRemboursement() {
    	assertTrue(Math.abs(lettre1.tarifRemboursement()-1.5f)<tolerancePrix);
		assertTrue(Math.abs(lettre2.tarifRemboursement()-15.0f)<tolerancePrix);
		assertTrue(Math.abs(colis1.tarifRemboursement()-100.0f)<tolerancePrix);
    }
    
    @DisplayName("Test de Colis express trop lourd") // Test cohérence poids et poids max
    @Test
    public void ColisExpress30kg() throws ColisExpressInvalide {
            assertThrows(ColisExpressInvalide.class, () -> new ColisExpress("bruh", "bruh", "bruh", 33, 1024, Recommandation.un,"bruh", 21, true));
    }
    
    @Test
    public void TestSacPostaux() {
    	SacPostal sac1 = new SacPostal();
		sac1.ajoute(lettre1);
		sac1.ajoute(lettre2);
		sac1.ajoute(colis1);

		assertTrue(Math.abs(sac1.valeurRemboursement()-116.5f)<tolerancePrix);
		assertTrue(Math.abs(sac1.getVolume()-0.025359999558422715f)<toleranceVolume);
		
		SacPostal sac2 = sac1.extraireV1("7877");

		assertTrue(sac2.getVolume()-0.02517999955569394f<toleranceVolume);
    }
    
    
    @AfterAll
    static void endAll() {
    	System.out.println("---- Fin des Tests ----");
    }
}
