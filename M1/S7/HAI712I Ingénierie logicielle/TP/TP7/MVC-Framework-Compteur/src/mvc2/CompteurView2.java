package mvc2;

import javax.swing.*;

public class CompteurView2 extends View {

	JProgressBar progressBar = new JProgressBar();
	
	public CompteurView2(Model m, Controller c) {
		super(m, c);
		progressBar.setMinimum(0);
		progressBar.setMaximum(50);
		this.add(progressBar);
		this.update("valeur");
	}

	public void update(Object how) {
		// how n'est pas utilisé dans cet exemple
		int newVal = ((Compteur) m).getValeur();
		progressBar.setValue(newVal);
		//setText déclenche le ré-affichage
	}

}
