package fr.umfds.gestionter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestGroupeSujet {
	
	public void Affectation(Groupe G, Sujet S) {
		try {
			G.setSujet(S);
			S.setGroupe(G);
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	@BeforeAll
    static void initAll() {
    	System.out.println("---- Debut des Tests Groupe et Suejts ----");
    }
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    
    @Test
    public void TestAffectationSujet() throws Exception {
    	Enseignant E1 = new Enseignant(5, "Marc", "Dupond");
    	
    	Sujet s1 = new Sujet(1, "Un super suejt", "Un résumé pour le Sujet 1", E1);
    	

    	
    	Groupe G1 = new Groupe(5, "Un super groupe");
    	// Affectation du sujet
    	
    	Affectation(G1,s1);
    	
    	// Vérification de l'affectation
    	assertEquals(s1, G1.getSujet());
    	assertEquals(G1, s1.getGroupe());
    }
    
    @AfterAll
    static void endAll() {
    	System.out.println("---- Fin des Tests ----");
    }
}
