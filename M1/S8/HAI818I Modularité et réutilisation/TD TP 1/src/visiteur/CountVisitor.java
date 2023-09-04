package visiteur;

public class CountVisitor extends Visitor {

	int count = 0;
	
	
	
	public CountVisitor() {
		super();
		this.count = 0;
	}
	
	public int getCount() {
		return count ;
	}
	@Override
	public void visitFile(File f) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitDirectory(Directory d) {
		count++;
	}

}
