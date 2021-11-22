#include <cstdlib>
#include <iostream>
#include <fstream>
#include <unistd.h>
#include <string>
#include "ArbreBinaire.h"

#pragma once

using namespace std;

class ABR: public ArbreBinaire {
  public:
    noeud* inserer(int);
    void parcoursInfixe(noeud*);
    noeud* minimum(noeud*);
    noeud* rechercher(int);
    noeud* successeur(noeud*);
    void supprimer(noeud*);

    void rotationGauche(noeud*);
    void rotationDroite(noeud*);

    void parcoursInfixe() { parcoursInfixe(racine); }
    noeud* minimum() { return minimum(racine); }

    using ArbreBinaire::ArbreBinaire;
};

