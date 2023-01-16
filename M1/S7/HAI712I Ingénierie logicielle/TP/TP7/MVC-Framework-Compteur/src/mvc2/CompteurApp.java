package mvc2;

import java.awt.*;
import javax.swing.*;

public class CompteurApp {
	JFrame appWindow;
	Model model;
	Controller controller1;
	View view1, view2, view3;
	
	JButton bIncr;
	JButton bDecr;
	JButton bRAZ ;

	private static CompteurApp instance = null;
	private static CompteurApp instance2 = null;
	
	public CompteurApp() {
		if(instance == null) {
			instance = this;
			this.initMVC();
			this.initGraphic(); 
		}
		else if(instance2 == null) {
			instance2 = this;
			this.initMVC();
			this.initGraphic(); 
		}
	}
		
	public void initMVC() {
		model = new Compteur();
		controller1 = new CompteurController(model);
		view1 = new CompteurView1(model, controller1);
		view2 = new CompteurView2(model, controller1);
}
	
	public void initGraphic() {
		appWindow = new JFrame("CompteurApp");
		//les Jbuttons pour incrémenter le compteur
		bIncr = new JButton("incr");
		bDecr = new JButton("decr");
		bRAZ  = new JButton("raz");
		//les contrôleurs de notre compteur sont des observateurs des boutons
		bIncr.addActionListener(controller1);
		bDecr.addActionListener(controller1);
		bRAZ.addActionListener(controller1);

		//rangement des choses dans la fenêtre de l'application
		//Les vues
		JPanel compteursViews = new JPanel();
		//views.setLayout(new GridLayout(2, 1));		
		compteursViews.setLayout(new FlowLayout(FlowLayout.CENTER));
		compteursViews.add(view1);
		compteursViews.add(view2);
		//Les boutons
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttons.add(bIncr);
		buttons.add(bDecr);
		buttons.add(bRAZ);
		//rangement
		appWindow.getContentPane().setLayout(new BorderLayout());
		appWindow.getContentPane().add(compteursViews, BorderLayout.CENTER);
		appWindow.getContentPane().add(buttons, BorderLayout.SOUTH);
    //ajuster la taille
		appWindow.pack();
	}
	
	public void open() { appWindow.setVisible(true); }

	
	public static void main(String[] args) {
		new CompteurApp().open(); 
		new CompteurApp().open();
		new CompteurApp().open(); // Ne sera pas affiché
	}
}
