package visiteur;

public class Main {

	public static void main(String[] args) {
	       	Directory d1 = new Directory("racine");

	        Directory d2 = new Directory("dossier1");



	        File f1 = new File("Fichier1");
	        f1.setContents("contenue1");

	        File f2 = new File("Fichier2");
	        f2.setContents("contenue2");


	        File f3 = new File("Fichier3");
	        f3.setContents("contenue3");


	        d1.add(f3);


	        d2.add(f1);
	        d2.add(f2);
	        
	        d1.add(d2);
	        System.out.println(f1.size());
	        System.out.println(f2.size());
	        System.out.println(f3.size());


	        RazVisitor r = new RazVisitor();
	        //CountVisitor r = new CountVisitor();
	        d1.accept(r);
	        
	        System.out.println(f1.size());
	        System.out.println(f2.size());
	        System.out.println(f3.size());
	        
	        //System.out.println(r.getCount());
	        
	        
	        
	        System.out.println("D1 : ");
	        d1.ls();
	        System.out.println("D2 : ");
	        d2.ls();
	        
	        JavaCleanVisitor visi = new JavaCleanVisitor();
	        d1.accept(visi);
	        
	        System.out.println("D1 : (apres)");
	        d1.ls();
	        System.out.println("D2 : (apres)");
	        d2.ls();
		
	}

}
