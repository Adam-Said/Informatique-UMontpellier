package mvc2;

import javax.swing.*;

public class CompteurView1 extends View {

	JLabel afficheCompteur = new JLabel();
	
	public CompteurView1(Model m, Controller c) {
		super(m, c);
		this.add(afficheCompteur);
		this.update("valeur");
	}

	public void update(Object how) {
		// how n'est pas utilisé dans cet exemple
		int newVal = ((Compteur) m).getValeur();
		afficheCompteur.setText(String.valueOf(newVal));
		//setText déclenche le ré-affichage
	}

}
