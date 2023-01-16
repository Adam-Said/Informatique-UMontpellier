package fr.umfds.gestionter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestGroupe {
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
    public void testEnseignant() throws Exception {
    	ArrayList<Etudiant> listeEtu = new ArrayList<Etudiant>();
    	Etudiant Etu1 = new Etudiant(1, "Alban", "A");
    	Etudiant Etu2 = new Etudiant(2, "Bertrand", "B");
    	listeEtu.add(Etu1);
    	listeEtu.add(Etu2);
    	
    	Groupe G1 = new Groupe();
    	try {
    		G1.setEtudiant(listeEtu);
    		G1.setId(5);
    		G1.setNom("Un super groupe");
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
      assertEquals(5, G1.getId());
      assertEquals("Un super groupe", G1.getNomGroupe());
      assertEquals(listeEtu, G1.getEtudiants());
    }
    
    @DisplayName("Test creation d'un Groupe")
    @Test
    public void TestCreationGroupe() throws Exception {
    	ArrayList<Etudiant> listeEtu = new ArrayList<Etudiant>(); // Création d'une liste d'étudiant pour un groupe
    	Etudiant Etu1 = new Etudiant(1, "Alban", "A");
    	Etudiant Etu2 = new Etudiant(2, "Bertrand", "B");
    	listeEtu.add(Etu1);
    	listeEtu.add(Etu2);
    	
    	Groupe G1 = new Groupe(1,"Groupe 1", listeEtu);
    	Groupe G2 = new Groupe(1,"Groupe 1");
    	Groupe G3 = new Groupe();
    	assertTrue(G1.getId()==1);
    	assertTrue(G2.getEtudiants() == null);
    	assertTrue(G3.getNomGroupe() == "");
    	
    }
    
    @DisplayName("Test toString")
    @Test
    public void TestToString() throws Exception {
    	Groupe G3 = new Groupe();
    	assertEquals(G3.toString(), "\nN° Groupe : 0 - ");
    }
    
    @AfterAll
    static void endAll() {
    	System.out.println("---- Fin des Tests ----");
    }
}
