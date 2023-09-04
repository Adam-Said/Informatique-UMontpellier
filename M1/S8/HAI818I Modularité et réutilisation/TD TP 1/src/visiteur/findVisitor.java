package visiteur;

import java.util.ArrayList;
import java.util.List;

public class findVisitor extends Visitor{

	private List<String> path;
	private String list;	
	public findVisitor(String list) {
		super();
		this.list = list;
		this.path = new ArrayList<>();
	}
	public List<String> getPath() {
		return path;
	}

	@Override
	public void visitFile(File f) {
		if (f.getName().equalsIgnoreCase(list)) {
			path.add(f.absoluteAdress()) ;
		}
	}

	@Override
	public void visitDirectory(Directory d) {
		// TODO Auto-generated method stub
		
	}
	
	

}
