package fr.umfds.poste;

import java.util.*;


public class SacPostal
{
	private ArrayList<ObjetPostal> contenu;
	private double capacite;

	public SacPostal()
	{contenu = new ArrayList<ObjetPostal>(); capacite = 0.5;}

	public SacPostal(double c)
	{contenu = new ArrayList<ObjetPostal>(); capacite = c;}  

	//accesseurs

	public double getCapacite()
	{ return capacite;}

	//accesseur de consultation pour contenu possible

	//autres methodes
	public boolean ajoute(ObjetPostal o)
	{if (o.getVolume() + getVolume() <= capacite)
	{contenu.add(o); return true;}
	else return false;
	}

	public double getVolume()
	{double volume=0.005;
	for (int i=0; i < contenu.size(); i++)
		volume += (contenu.get(i)).getVolume();
	return volume;
	}

	public double valeurRemboursement()
	{double valeur=0;
	for (ObjetPostal o:contenu)
	{
		valeur += o.tarifRemboursement();
	}
	return valeur;
	}

	public String toString()
	{return "Sac \ncapacite: "+getCapacite()+
			"\nvolume: "+getVolume()+"\n"+contenu+"\n";}

	public SacPostal extraireV1(String code)
	{
		// on calcule la capacite necessaire
		double capaciteNecessaire=0;
		for (ObjetPostal o:contenu)
		{
			if (o.getCodePostal().equals(code))
				capaciteNecessaire += o.getVolume();
		}

		// et on utilise sa partie entiere superieure pour avoir une capacite
		// plausible de sac postal, mais ceil(double) retourne un double
		//ca, c'est juste pour montrer une utilisation de java.lang.Math
		SacPostal sac = new SacPostal(Math.ceil(capaciteNecessaire));
		int i=0;
		while (i<contenu.size())
		{ObjetPostal o;
		o = contenu.get(i);
		if (o.getCodePostal().equals(code))
		{sac.ajoute(o);
		contenu.remove(i);
		}
		else i++;
		}      
		return sac;
	}

	public SacPostal extraireV2(String code)
	{
		// On calcule la capacite necessaire et on place les objets
		// dans une arraylist intermediaire
		double capaciteNecessaire=0;
		ArrayList<ObjetPostal> intermed=new ArrayList<ObjetPostal>();

		for (ObjetPostal o:contenu)
		{
			if (o.getCodePostal().equals(code))
			{
				capaciteNecessaire += o.getVolume();
				intermed.add(o);
			}
		}

		// On cree un nouveau sac, 
		// on y transfere l'arrayList intermediaire
		// on enleve du sac courant les elements transferes
		SacPostal sac = new SacPostal(Math.ceil(capaciteNecessaire));
		sac.contenu.addAll(intermed);
		contenu.removeAll(intermed);
		return sac;
	}



}

