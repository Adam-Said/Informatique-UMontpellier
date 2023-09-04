package visiteur;

public class CountVisitor2 extends Visitor{
	
	private Compteur cmp;
	private int val;
	
	
	public CountVisitor2(Compteur cmp) {
		super();
		this.cmp = cmp;
		val = 0;
	}
	

	@Override
	public void visitFile(File f) {
		val = val + cmp.compter(f) ;
		
	}

	@Override
	public void visitDirectory(Directory d) {
		val = val + cmp.compter(d) ;		
	}

}
