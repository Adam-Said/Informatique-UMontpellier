package mvc2;

public abstract class View extends javax.swing.JPanel{
	protected Controller c;
	protected Model m;

	public View(Model m, Controller c){
	  this.m = m;
	  this.c = c;
	  MV_Association.add(m, this);  }
	
	public abstract void update (Object how);
	
}
