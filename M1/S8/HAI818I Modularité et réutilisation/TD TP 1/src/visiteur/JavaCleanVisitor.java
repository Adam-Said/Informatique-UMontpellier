package visiteur;

public class JavaCleanVisitor extends Visitor{

	@Override
	public void visitFile(File f) {
		if (f.getName().endsWith(".class")) {
			f.getParent().remove(f);
		}
	}

	@Override
	public void visitDirectory(Directory d) {
		// TODO Auto-generated method stub
		
	}

}
