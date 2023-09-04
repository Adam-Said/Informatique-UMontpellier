package visiteur;

public class File extends ElementStockage
//classe reprsentant un fichier
{
	protected String contenu ; // possde un contenu
	
	@Override
	public void accept(Visitor v) {
		v.visitFile(this);
	}

	
	public File(String nom){
		super(nom,0) ;
		this.contenu="" ;
	}
	
	public File(String nom,String contenu){
		super(nom,0) ;
		this.contenu=contenu ;
	}
	
	public int size(){
		return (contenu.length());
	}
	
	public void cat(){
		System.out.println(contenu) ;
	}
	
	public boolean contains(String sub){
		return (contenu.indexOf(sub) != -1);
	}
	
	public void setContents(String c)
	//change le contenu du fichier. Le contenu devient c
	{
		contenu = c ;
	}
}