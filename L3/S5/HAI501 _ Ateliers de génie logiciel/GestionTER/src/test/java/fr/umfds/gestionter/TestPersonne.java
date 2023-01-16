package fr.umfds.gestionter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestPersonne {
	
	
    @BeforeAll
    static void initAll() {
    	System.out.println("---- Debut des Tests ----");
    }
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    
    @DisplayName("Test getter et setter d'un Groupe")
    @Test
    public void TestSetterGetterPersonne() throws Exception {
    	Personne P1 = new Personne();
    	
    	assertEquals(P1.getId(), 0);
    	assertEquals(P1.getNom(), "");
    	assertEquals(P1.getPrenom(), "");
    	
    	P1.setId(1);
    	P1.setNom("Durand");
    	P1.setPrenom("Bruno");
    	
    	assertEquals(P1.getId(), 1);
    	assertEquals(P1.getNom(), "Durand");
    	assertEquals(P1.getPrenom(), "Bruno");  	
    }
    
    @DisplayName("Test toString d'un Groupe")
    @Test
    public void testToString() throws Exception{
    	Personne P1 = new Personne();
    	assertEquals(P1.toString(), "\n N° 0 | Nom :  Prenom : ");
    }
    
    @AfterAll
    static void endAll() {
    	System.out.println("---- Fin des Tests ----");
    }
}