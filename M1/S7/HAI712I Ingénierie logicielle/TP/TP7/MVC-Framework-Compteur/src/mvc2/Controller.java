package mvc2;

import java.awt.event.ActionListener;

public abstract class Controller implements ActionListener{
	//Implantation de Contrôleur (MVC) dans une vision réduite awt.event.ActionListener
	//Un contrôleur réagit lorsqu'il reçoit un évènement ActionEvent.
	
	//dans cette implantation un controlleur connait le modèle qu'il contrôle.
	//il pourrait également connaître la vue ... à ajouter si souhaité

  Model m;

  Controller() {};

  public Controller(Model m){
    this.m = m;
  }
}