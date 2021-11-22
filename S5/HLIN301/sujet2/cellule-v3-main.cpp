#include "cellule-v3.h"
#include <iostream>
#include <string>
using namespace std;

void test_cell(const Cellule &c) {
  cout << "La cellule (à l'adresse mémoire " << &c << ") = {"
       << (c.getVivante() ? "vivante" : "morte")
       << ", " << c.getX() << "x" << c.getY() << "}"
       << endl;
}
#define PrintCell(c)				    \
  cout << "L'objet " #c " est à l'adresse mémoire " \
       << &c << endl

#define PrintVoisines(c1, c2)				\
  cout << "La cellule " #c1 " "				\
       << (c1.estVoisine(c2) ? "est" : "n'est pas")	\
       << " voisine de " #c2 "." << endl

#define TestCouleurs(c)							\
  for (Cellule::Couleur i = Cellule::NOIR;				\
       i != Cellule::NB_COULEURS;					\
       i = (Cellule::Couleur) (((int) i)+1)) {				\
    cout << #c << (CelluleEstDeLaCouleur(c, i) ? " est " : " n'est pas ") \
	 << Couleur2String(i) << "." << endl;				\
  }									\
  
int main(int argc, char** argv) {
  Cellule c1(true, 1, 2), c2;
  c2.setVivante(!c2.getVivante());
  c2.setX(c1.getX());
  c2.setY(c1.getX() + c1.getY());
  PrintCell(c1); PrintCell(c2);
  test_cell(c1); test_cell(c2);
  PrintVoisines(c1, c2); PrintVoisines(c2, c1);
  TestCouleurs(c1); TestCouleurs(c2);
  return 0;
}
