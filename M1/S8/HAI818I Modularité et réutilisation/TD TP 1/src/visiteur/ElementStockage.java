package visiteur;

public abstract class ElementStockage 
//classe abstraite correspondant  tous lment de stockage mmoire.
{
	protected String name ; // un nom
	protected int basicSize ; //espace de base qu'il occupe en memoire
	protected Directory parent ; // dossier parent 
	
	public ElementStockage(String nom,int t){
		name=nom ;
		basicSize=t ;
		parent=null ;
	}
	
	public ElementStockage(String nom,int t,Directory d){
		name=nom ;
		basicSize=t ;
		parent=d ;
	}
	public String toString(){
		return this.getClass().getName() + " " + this.name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Directory getParent() {return parent;}

	//	 m�thode abstraite car specifique aux diffrentes param�trisations 
	//(retourne la taille de l'lment de stockage)
	public abstract int size() ; 
	
	public String absoluteAdress()
	//retourne l'adresse absolue de l'element.
	{
		if (parent != null) // s'il a un parent
		return (parent.absoluteAdress()+"/"+name) ;
		
		else return (name) ; // ici on est  la racine, on suppose que son nom est racine 
	}
	
	public void nouveauParent(Directory d)
	//d devient le nouveau parent de l'lmnet de stockage.
	{
		parent=d ;
	}
	
	public int bitSize()
	//retourne la taille en bits de l'lment.
	{
		return this.size()*8 ;
	}
	
	public abstract void accept(Visitor v);
	
	public int getCount() {return 0;}

}
