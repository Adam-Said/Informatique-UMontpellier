package mvc2;
import java.util.Collection;

public class Model {
	//Implante MVC incluant le schéma Observateur
  //Un modèle connait indirectement ses vues 
	//Il est un observé, voir https://www.oodesign.com/observer-pattern
	
	//quand un modèle change, ses vues sont prévenues
  
	//A ecrire la méthode notify du schéma Observateur
	//également nommée changed dans le schéma MVC
  
	public void changed(Object how) {
		Collection<View> views = MV_Association.getViews(this);
		for (View view : views) {
			view.update(how);
		}
  }
}
