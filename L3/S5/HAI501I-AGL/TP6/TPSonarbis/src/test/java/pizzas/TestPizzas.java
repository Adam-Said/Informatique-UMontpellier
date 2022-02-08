package pizzas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestPizzas {

	private BasePizzas base=new BasePizzas();
	
	@BeforeEach
	public void init() {
		base.addPizzaToMenu(base.createSurpriseWhitePizza());
	}
	
	@Test
	void testAjoutPizza() {
		Pizza p=new Pizza("fromages", 10);
		p.ajoutIngredient(new Ingredient("Mozzarelle", true));
		p.ajoutIngredient(new Ingredient("Talegio", true));
		assertEquals("fromages", p.getNom());
		assertEquals(10, p.getPrix());
		base.addPizzaToMenu(p);
	}
	
	@Test
	 void testAjoutIng1() {
		Pizza p=base.getPizzaFromMenu("Surprise blanche");
		System.out.println(p.formattedIngredients());
		var oldSize=p.ingredients().length;
		p.ajoutIngredient(new Ingredient("brocolis", true));
		assertEquals(oldSize+1, p.ingredients().length);
	}
	
	@Test
	void testPizzaEquals() {
		Pizza pi = new Pizza("marga", 13);
		Pizza pi2 = new Pizza("marga", 13);
		assertEquals(pi, pi2);
	}
	
	@Test
	void testPizzaNotEquals() {
		Pizza pi = new Pizza("marga", 13);
		Pizza pi2 = new Pizza("marga", 22);
		assertNotEquals(pi, pi2);
	}


}
