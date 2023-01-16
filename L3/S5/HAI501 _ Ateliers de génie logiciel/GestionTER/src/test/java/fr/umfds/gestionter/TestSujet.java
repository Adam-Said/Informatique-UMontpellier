package fr.umfds.gestionter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestSujet {
	
	
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
    
    @DisplayName("Test getter et setter d'un Sujet")
    @Test
    public void TestSetterGetterSujet() throws Exception {
    	Sujet S1 = new Sujet();
    	Enseignant E1 = new Enseignant();
    	Groupe G1 = new Groupe();
    	
    	assertEquals(0, S1.getIdSujet());
    	assertEquals("", S1.getTitreSujet());
    	assertEquals("", S1.getResumeSujet());
    	assertEquals(null, S1.getEncadrant());
    	assertEquals(null, S1.getGroupe());
    	
    	S1.setIdSujet(1);
    	S1.setTitreSujet("Titre 1");
    	S1.setResumeSujet("Resume 1");
    	S1.setEncadrant(E1);
    	S1.setGroupe(G1);
    	
    	assertEquals(1, S1.getIdSujet());
    	assertEquals("Titre 1", S1.getTitreSujet());
    	assertEquals("Resume 1", S1.getResumeSujet());
    	assertEquals(S1.getEncadrant(), E1);
    	assertEquals(S1.getGroupe(), G1);	
    }
    
    @DisplayName("Test toString d'un Sujet")
    @Test
    public void testToString() throws Exception{
    	Sujet S1 = new Sujet();
    	assertEquals("\n Id : 0 Titre : \n ", S1.toString());
    }
    
    @DisplayName("Test creation d'un Sujet")
    @Test
    public void TestCreationSujet() throws Exception {
    	Enseignant E1 = new Enseignant();
    	
    	Sujet S1 = new Sujet(1,"Titre 1","Resume 1",E1);
    	Sujet S2 = new Sujet(1,"Titre 1","Resume 1");
    	Sujet S3 = new Sujet();
    	assertTrue(S3.getIdSujet()==0);
    	assertTrue(S2.getTitreSujet() == "Titre 1");
    	assertTrue(S1.getEncadrant() == E1);
    	assertThrows(ObjetInvalide.class,() -> new Sujet(1,"","Resume 1"));
    	
    }
    
    
    @AfterAll
    static void endAll() {
    	System.out.println("---- Fin des Tests ----");
    }
}