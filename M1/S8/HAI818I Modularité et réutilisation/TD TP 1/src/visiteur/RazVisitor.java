package visiteur;

public class RazVisitor extends Visitor{

	@Override
	public void visitFile(File f) {
		f.setContents("");		
	}

	@Override
	public void visitDirectory(Directory d) {
		// TODO Auto-generated method stub
		
	}
	
	

}
