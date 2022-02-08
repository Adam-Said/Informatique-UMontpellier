package fr.umfds.poste;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Colis extends ObjetPostal
{
	private String declareContenu;
	private float valeurDeclaree;

	private static float tarifBase=2;
	private static String typeObjetPostal="Colis";
	
	public Colis()
	{// implicitement : super();
		declareContenu="vide"; valeurDeclaree=0;}



	public Colis(String origine, String destination, String codePostal, float poids, float volume,
			Recommandation tauxRecommandation, String declareContenu, float valeurDeclaree) {
		super(origine, destination, codePostal, poids, volume, tauxRecommandation); // appel au constructeur de la super classe
		this.declareContenu = declareContenu;
		this.valeurDeclaree = valeurDeclaree;
	}



	//accesseurs (en lecture)
	public String getDeclareContenu() {return declareContenu;}
	//public void setDeclareContenu(String dc) {declareContenu=dc;}

	public float getValeurDeclaree() {return valeurDeclaree;}
	//public void  setValeurDeclaree(float vd) {valeurDeclaree=vd;}

	//autres methodes

	public float getTarifBase(){return tarifBase;}

	public float tarifRemboursement()
	{if (getTauxRecommandation()==Recommandation.un)
		return getValeurDeclaree()*0.1f;
	else if (getTauxRecommandation()==Recommandation.deux)
		return getValeurDeclaree()*0.5f;
	else return 0;}

	public float tarifAffranchissement()
	{
		float t=super.tarifAffranchissement();
		if (getVolume() > 1f/8f) {t = t+3;}
		return t;
	}

	public String toString()
	{
		NumberFormat formatter = null;
		formatter=java.text.NumberFormat.getInstance(java.util.Locale.FRENCH);
		/**
		 * Arrondir au centime
		 */
		formatter = new DecimalFormat("#0.00");

		return super.toString()+"/"+(float)Math.round(getVolume()*100)/100+"/"+getValeurDeclaree();}

	public String typeObjetPostal() {return typeObjetPostal;}

}
