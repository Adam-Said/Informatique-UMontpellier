package pizzas;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BasePizzas {
	private ArrayList<Ingredient> ingredientsDisponibles=new ArrayList<>();
	private HashMap<String, Pizza> menu=new HashMap<>();
	private SecureRandom rand = new SecureRandom();


	public BasePizzas() {
		initIngredients();
		initMenu();
	}

	private void initIngredients() {
		Ingredient i1=new Ingredient("sauce tomate", true);
		Ingredient i2=new Ingredient("creme fraiche", true);
		Ingredient i3=new Ingredient("jambon blanc", false);
		Ingredient i4=new Ingredient("aubergine", true);
		Ingredient i5=new Ingredient("mozzarelle", true);
		Ingredient i6=new Ingredient("pancetta", false);
		Ingredient i7=new Ingredient("miel", true);
		Ingredient i8=new Ingredient("courgette", true);
		Ingredient i9=new Ingredient("taleggio", true);
		Ingredient i10=new Ingredient("chorizo", false);
		ingredientsDisponibles.addAll(List.of(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10));
	}
	
	private void initMenu() {
		this.initIngredients();
		Pizza p1=new Pizza("berk", 10);
		p1.ajoutIngredient(ingredientsDisponibles.get(7));
		p1.ajoutIngredient(ingredientsDisponibles.get(9));
		Pizza p2=new Pizza("vege", 11);
		p2.ajoutIngredient(ingredientsDisponibles.get(0));
		p2.ajoutIngredient(ingredientsDisponibles.get(3));
		p2.ajoutIngredient(ingredientsDisponibles.get(4));
		p2.ajoutIngredient(ingredientsDisponibles.get(6));
		p2.ajoutIngredient(ingredientsDisponibles.get(7));
		Pizza p3=new Pizza("ouch", 15);
		p3.ajoutIngredient(ingredientsDisponibles.get(1));
		p3.ajoutIngredient(ingredientsDisponibles.get(2));
		p3.ajoutIngredient(ingredientsDisponibles.get(3));
		p3.ajoutIngredient(ingredientsDisponibles.get(4));
		p3.ajoutIngredient(ingredientsDisponibles.get(5));
		p3.ajoutIngredient(ingredientsDisponibles.get(8));
		p3.ajoutIngredient(ingredientsDisponibles.get(9));
		menu.put(p1.getNom(), p1);
		menu.put(p2.getNom(), p2);
		menu.put(p3.getNom(), p3);
	}

	public void addPizzaToMenu(Pizza p) {
		menu.put(p.getNom(), p);
	}
	public Pizza getPizzaFromMenu(String nom) {
		return menu.get(nom);
	}

	public Pizza createSurpriseWhitePizza() {
		Pizza p=new Pizza("Surprise blanche", 13);
		int nbIng=5;
		while (nbIng > 0) {
			int ingpos=rand.nextInt(ingredientsDisponibles.size());
			if (!ingredientsDisponibles.get(ingpos).getNom().equals("crème fraiche")) {
				p.ajoutIngredient(ingredientsDisponibles.get(ingpos));
				nbIng--;
			}
		}
		return p;
	}

	public boolean exists(String ingName) {
		for (Ingredient i:ingredientsDisponibles) {
			if (i.getNom().equals(ingName))
				return true;
		}
		return false;
	}

	public 	List<Pizza> pizzasWithMissingIngredient(){
		ArrayList<Pizza> res=new ArrayList<>();
		for (Pizza p:menu.values()) {
			if (!p.getNom().startsWith("surprise")) {
				for (Ingredient i:p.ingredients()) {
					if(!ingredientsDisponibles.contains(i)) {
						res.add(p);
						break;
					}
				}
			}
		}
		return res;
	}
}
