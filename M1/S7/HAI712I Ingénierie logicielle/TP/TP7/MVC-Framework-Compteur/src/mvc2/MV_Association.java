package mvc2;

import java.util.Collection;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Vector;

public class MV_Association {
	// modèle-vue association
	// stocker les couples modèle-vues dans un dictionnaire
	// le cas échéant, ne pas oublier les "remove" pour que le GarbageCollector fonctionne

	protected static Dictionary<Model, Collection<View>> MVDictionary = new Hashtable<>();

	// associe un modèle à une vue
	public static void add(Model m, View v) {
		Collection<View> views = MVDictionary.get(m);
		if (views == null) {
			views = new Vector<View>();
			MVDictionary.put(m, views); }
		views.add(v); }
	
	// rend la collection de vues associées à un modèle
	public static Collection<View> getViews(Model m) {
		return MVDictionary.get(m); }
	
	public static void remove(Model m) {
		MVDictionary.put(m, null); }
	
	public static void remove (Model m, View v) {
		Collection<View> views = MVDictionary.get(m);
		views.remove(v); 	}

}
