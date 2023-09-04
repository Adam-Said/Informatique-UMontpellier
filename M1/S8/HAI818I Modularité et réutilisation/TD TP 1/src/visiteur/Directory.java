package visiteur;
import java.util.ArrayList;
import java.util.Collection;


public class Directory extends ElementStockage
//classe reprsentant un dossier
{
	// pour la coh屍ence interne du dossier, il est fondamental que la collection
	// de ses 四士ents soit inaccessible aux clients.
	protected Collection<ElementStockage> elements ;
	
	@Override
	public void accept(Visitor v) {
		for(ElementStockage e : elements) {
			e.accept(v);
		}
		v.visitDirectory(this);
	}
	
	
	
	public Directory(String nom)
	{
		super(nom,4) ;
		elements = new ArrayList<ElementStockage>() ;
	}
	
	public int size(){
	//somme des tailles des diffrents lments prsents dans ce dossier.
		int somme=basicSize ;
		for (ElementStockage s : elements){
			somme=somme+s.size() ;}	
		return somme ;
	}
		
	public void ls()
	//affiche  l'cran la liste des lments qu'il contient
	{
		for (ElementStockage s : elements)
		{
			System.out.println(s.name) ;
		}
	}
		
	public int nbElm()
	//retourne le nombre d'lments du dossier
	{
		return elements.size() ;
	}
	
	public int getCount(){
		int count = 0;
		for (ElementStockage s : elements){
			count = count + 1 + s.getCount();
		}
		return count;
	}
		
	public boolean add(ElementStockage e)
	//ajoute l'lment e dans le dossier
	{
		e.nouveauParent(this) ; // le rpertoire courant devient le pre de l'lment e
		return elements.add(e) ; //ajout de l'lment e  la collection
	}
		
	public boolean remove(ElementStockage e)
	//supprime et retourne vrai si la suppression de l'element e s'est bien passe, faux sinon (e non prsent ou erreur)
	{
		for (ElementStockage s : elements)
		{
			if (s.name==e.name)
			return elements.remove(s) ; // suppression de l'lment e dans la collection
		}
			
		return false ;
	}
	
	public boolean includes(ElementStockage e)
	{
		return elements.contains(e) ;
	}
	
	public ElementStockage findElement(String nom)
	//rend l'element de nom "nom" si il existe (sinon retourne null)
	{
		for (ElementStockage s : elements)
		{
			if (s.name.equals(nom))
			return s ;
		}
		
		return null ;
	}
		
	public ArrayList<String> find(String nom)
	//rend la collection des adresses absolues de nom "nom" que le repertoire contient
	{
		ArrayList<String> collection = new ArrayList<String>() ;
			
		for (ElementStockage s : elements)
		{
			if (s.name.equals(nom))
			collection.add(s.absoluteAdress()) ;
		}
			
		return collection ;
	}
		
	public ArrayList<String> findR(String nom){
	// rend la collection des adresses absolues de nom "nom" que 
	// le rpertoire contient directement ou par transitivit
	
		ArrayList<String> collection = new ArrayList<String>() ;
		ArrayList<String> temporaire ; // collection temporaire servant  stocker les adresses absolues obtenues par transitivit
			
		for (ElementStockage s : elements){
			if (s.name==nom)
			collection.add(s.absoluteAdress()) ;
			
			if (s instanceof Directory) // si l'lment de stockag est un dossier, lance la recherche sur s (appel rcursif)
			{
				temporaire = ((Directory) s).findR(nom) ;
			
				for (String es : temporaire)
				collection.add(es) ;
			}
		}
		return collection ;
	}

	
	public void afficheCollection(ArrayList<String> liste)
	//affiche le contenu de la collection liste
	{
		for (String s : liste)
		System.out.println(s) ;
	}
}