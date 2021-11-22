#include <cstdlib>
#include <iostream>
#include <fstream>
#include <unistd.h>
#include <string>

#pragma once

using namespace std;

class noeud {
public:
  int val;
  noeud* pere;
  noeud* filsG;
  noeud* filsD;

  noeud (int); // constructeur
  void affichage(ofstream&, noeud* = NULL); // dessin d'ABR
  void coord(float*&, float*&, int&); // calcul des coordonnées pour le dessin

private:
  float x; // abscisse du nœud dans le dessin
  float y; // ordonnée du nœud dans le dessin
  void majcoord(float); // fonction utilitaire
};

class ArbreBinaire {
  public:
    noeud* racine;
    ArbreBinaire();
    void remplacer(noeud*, noeud*);
    void affichage(string, noeud* = NULL);
};
