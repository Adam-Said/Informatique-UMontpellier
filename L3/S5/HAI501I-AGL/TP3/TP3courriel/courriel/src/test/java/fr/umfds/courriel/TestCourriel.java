package fr.umfds.courriel;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;


public class TestCourriel  {

	public ArrayList<String> creationPJ(String c1, String c2) {
		ArrayList<String> liste = new ArrayList<String>();
		liste.add(c1);
		liste.add(c2);
		return liste;
	};
	// Création d'objets de test
	ArrayList<String> listePJ = creationPJ("cc1.pdf", "cc2.pdf");
    Courriel courrierNull = new Courriel();
    Courriel courrier1 = new Courriel("daude@daude.com", "CC 2", "CC PJ annulé", listePJ);
    Courriel courrierEmail = new Courriel("@daude.com", "CC 2", "CC annulé", listePJ);
    Courriel courrierCorps = new Courriel("daude@daude.com", "CC 2", "CC avec PJ annulé");
    Courriel courrierTitre = new Courriel("daude@daude.com", "", "CC annulé");
    

    
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    
    @Test
    public void TestDestinataire() throws DestInvalide, TitreNul, PresencePJ {
    	assertThrows(DestInvalide.class,() -> { 
    		courrierEmail.envoi(); 
    		});
    	assertTrue(courrier1.envoi());
    }
    
    @Test
    public void TestTitre() throws DestInvalide, TitreNul, PresencePJ {
    	assertThrows(TitreNul.class,() -> {
    		courrierTitre.envoi();
    	});
    	assertTrue(courrier1.envoi());
    }
    
    @Test
    public void TestPJ() throws DestInvalide, TitreNul, PresencePJ {
    	assertThrows(PresencePJ.class,() -> {
    		courrierCorps.envoi();
    	});
    	assertTrue(courrier1.envoi());
    }
    
    
    // Ajout d'un test paramétré pour tester une adresse invalide
    // Avec la source de valeur en paramètre
    @ParameterizedTest(name="Addresse invalide")
    @ValueSource(strings = {"!zef@zfù!.co", "aze!aze.z"}) 
    public void TestAdressParam(String strings) throws DestInvalide {
      Courriel CourrierAdresse = new Courriel(strings, "Un objet", "Le corps"); // Création d'un objet avec la ValueSource
      assertThrows(DestInvalide.class, () -> {
        CourrierAdresse.envoi(); // test si la méthode envoi lève une exception 
      });
    }
    
    // Autre test paramétré avec les valeurs envoyées par une méthode AdressProvider
    @ParameterizedTest(name="Test {index} {0} n'est pas une adresse valide")
    @MethodSource("adressProvider")
    public void TestAdressGenParam(String input) throws DestInvalide {
      Courriel CourrierAdresse = new Courriel(input, "Un objet", "Le corps");
      assertThrows(DestInvalide.class, () -> {
        CourrierAdresse.envoi();
      });
    }
    
    // Création de la méthode pour fournir les adresses invalides pour le test
    private static Stream<Arguments> adressProvider() {
    	return Stream.of(
    			Arguments.of("jean/valide.fr "),
    			Arguments.of("::az.fr"),
    			Arguments.of("jean@az"));
    }
}
