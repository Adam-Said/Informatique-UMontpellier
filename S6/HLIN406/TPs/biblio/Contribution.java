package biblio;

import java.util.ArrayList;

public class Contribution {
  private Contributeur contributeur;
  private ArrayList<NatureContribution> roles;
  
  public Contribution(Contributeur contributeur, ArrayList<NatureContribution> roles) {
    this.contributeur = contributeur;
    this.roles = roles;
  }

  public Contributeur getContributeur() {
    return contributeur;
  }

  public ArrayList<NatureContribution> getRoles() {
    return roles;
  }
}