#pragma once

struct coord
{
  int x;
  int y;
};

class File
{
public:
  File(int);
  ~File();
  int extraire_min();
  void changer_priorite(int, float);
  bool est_vide();
  void afficher();

private:
  int taille;
  int nb_elt;
  int* Element;
  float* Priorite;
  int* Indice;

  void remonter(int);
  void entasser(int);
};

class Voisin
{
public:
  int sommet;
  float poids;
  Voisin* suivant;

  Voisin(int u, float p, Voisin* s);
};

using listeAdj = Voisin*;
void lecture(std::string nom, listeAdj*&, coord*&, int&, int&, int&);
void grapheObstacles(int, int, int, int, int, float, coord*&, listeAdj*&, coord*&);
void grille(int, int, float, coord*&, listeAdj*&);

