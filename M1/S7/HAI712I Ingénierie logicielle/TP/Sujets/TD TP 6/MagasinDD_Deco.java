package DDandDecoratorv1;

//Location de produit avec Double-Dispatch et Decorateur sur les comptes

public class MagasinDD_Deco{
	
	public static void main(String[] args){
		Produit lgv = new Produit("La grande vadrouille", 10.0);
		
		Client cl = new Client("Dupont");

		//un compte normal pour le client Dupont
		Compte cmt = new CompteBasique(cl);
		System.out.println("basique lgv : " + cmt.prixLocation(lgv));
		
		//Dupont achete un forfait rÃ©duction.
		cmt = new ForfaitReduction (cmt);
		System.out.println("rÃ©duction lgv : " + cmt.prixLocation(lgv));
				
		//Dupont achete en plus un forfait seuil, le seuil est Ã  2
		cmt = new ForfaitSeuil (cmt);
		System.out.println("Seuil1+Reduction lgv: " + cmt.prixLocation(lgv));
		System.out.println("Seuil2+Reduction lgv: " + cmt.prixLocation(lgv));
		System.out.println("Seuil3+Reduction lgv: " + cmt.prixLocation(lgv));
		
		//Dupont avec ses 2 forfaits loue un produit soldÃ©
		Produit r4 = new ProduitSoldÃ©("RockyIV", 10.0);
		System.out.println("Seuil1+Reduction+Solde rocky: " + cmt.prixLocation(r4));
		System.out.println("Seuil2+Reduction+Solde rocky: " + cmt.prixLocation(r4));
		System.out.println("Seuil3+Reduction+Solde rocky: " + cmt.prixLocation(r4));
		}
}

//--------------------------------------------------
class Produit {
	double prixBase;
	double TVA = 19.6;
	double marge = 1.10;
	String titre;
	
	Produit(String titre, double pb) {
		this.titre = titre;
		this.prixBase = pb;}
	
	protected double prixHT(){return prixBase * marge;}

	double prixVente(){
		return (this.prixHT() * (1 + TVA));}
	
	double prixLocation(){
		//5% du prix de vente
		return this.prixVente() * 5 /100;
	}
}

//--------------------------------------------------
class ProduitSoldÃ© extends Produit{
	ProduitSoldÃ©(String titre, double pb) {
		super(titre, pb);}
	
	double prixVente(){return (super.prixVente() / 2);}
}

//--------------------------------------------------
//--------------------------------------------------

class Client{
	protected String nom;
	protected Compte compte;
	public void setCompte(Compte c){compte = c;}
	public Compte getCompte(){return compte;}
	
	public Client(String nom){
		//si c'est un client alors il a un compte
		this(nom, null);
		compte = new CompteBasique(this);}
	
	public Client(String nom, Compte c){
		this.nom = nom;
		this.compte = c;}
}

//--------------------------------------------------
//--------------------------------------------------
abstract class Compte {
	//conseil : pour ne pas alourdir les dÃ©corations, on dÃ©localise l'attribut
	//client titulaire du compte sur la classe CompteBasique.
	//Si une dÃ©coration a besoin d'une information relative au client, elle
	//utilisera la mÃ©thode getTitulaire()
	public abstract Client getTitulaire();
	public abstract double prixLocation(Produit p);
}

//--------------------------------------------------
class CompteBasique extends Compte{
	static int nbComptes = 0;
	int numero;
	Client titulaire;
		
	public CompteBasique (Client cl){
		titulaire = cl;
		this.numero = ++nbComptes;
	}
	
	public Client getTitulaire() {
		return titulaire;
	}
	
	//devrait-on la factoriser sur Compte ?
	public double prixLocation(Produit p){
		return p.prixLocation();}
}

//--------------------------------------------------
//--------------------------------------------------
abstract class Forfait extends Compte{
	Compte decore; //lire dÃ©corÃ©
	
	public Forfait(Compte c){
		decore = c;	}
	
  //par dÃ©faut toutes les mÃ©thodes redirigent le message
	//reÃ§u vers le dÃ©corÃ©
	
	public Client getTitulaire() {
		return decore.getTitulaire();
	}
	
	public double prixLocation(Produit p){
		return decore.prixLocation(p);}
}

//--------------------------------------------------
class ForfaitReduction extends Forfait{
	//donne une reduction de 10% sur chaque location
	double reduction = 0.10;
	
	public ForfaitReduction(Compte c){ super(c); }
	
	public double prixLocation(Produit p){
		return(super.prixLocation(p) * (1-reduction));}
}

//--------------------------------------------------
class ForfaitSeuil extends Forfait{
	//donne une location gratuite apres $compteur payantes
	static int init = 2;
	int compteur = init;
	
	public ForfaitSeuil(Compte c){super(c);}
	
	public double prixLocation(Produit p){
		if (compteur-- == 0) 
			{compteur = init; return 0.0;}
		else return super.prixLocation(p);
	}
}