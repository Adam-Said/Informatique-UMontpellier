package fr.umfds.gestionter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class TestGeneraux 
{
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
    @DisplayName("Test de creation d'un étudiant")
    @Test
    public void testCreationEtudiant() throws ObjetInvalide {
    	assertThrows(ObjetInvalide.class, () -> new Etudiant(0, "Jean", "Monke"));
    	assertThrows(ObjetInvalide.class, () -> new Etudiant(-2, "Jean", "Monke"));
    	Etudiant E1 = new Etudiant(1, "Jean", "Monke");
    	assertTrue(E1.getNom() == "Monke");
      try {
        Etudiant E3 = new Etudiant();
      }
      catch(ObjetInvalide e) {
        fail("Etudiant non valide"); 
      }
    }
    
    @DisplayName("Test de creation d'un enseignant")
    @Test
    public void testCreationEnseignant() throws ObjetInvalide {
    	assertThrows(ObjetInvalide.class, () -> new Enseignant(0, "Jean", "Monke"));
    	assertThrows(ObjetInvalide.class, () -> new Enseignant(-2, "Jean", "Monke"));
    	Enseignant E1 = new Enseignant(1, "Jean", "Monke");
    	assertTrue(E1.getNom() == "Monke");
    	try {
    		Enseignant E2 = new Enseignant();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }

    @DisplayName("Test de creation d'un sujet")
    @Test
    public void testCreationSujet() throws ObjetInvalide, Exception {
      Enseignant E1 = new Enseignant(25, "Jean", "Moulin");
      assertThrows(ObjetInvalide.class, () -> new Sujet(1, "", "zeferfergfe", E1));
    }
    
    @DisplayName("Test getter Enseignant")
    @Test
    public void testEnseignant() throws ObjetInvalide {
      Enseignant enseignant = new Enseignant(1, "Jean-Jacques", "Truc");
      assertEquals(1, enseignant.getId());
      assertEquals("Jean-Jacques", enseignant.getPrenom());
      assertEquals("Truc", enseignant.getNom());
    }
    
    @DisplayName("Test getter Etudiant")
    @Test
    public void testEtudiant() throws ObjetInvalide {
      Etudiant etudiant = new Etudiant(1, "Jean-Jacques", "Truc");
      assertEquals(1, etudiant.getId());
      assertEquals("Jean-Jacques", etudiant.getPrenom());
      assertEquals("Truc", etudiant.getNom());
    }


    @AfterAll
    static void endAll() {
    	System.out.println("---- Fin des Tests ----");
    }
    
}