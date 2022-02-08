#include "cellule-v1.h"
#include <iostream>
using namespace std;

void test_cell(const Cellule &c) {
  cout << "La cellule (à l'adresse mémoire " << &c << ") = {"
       << (c.vivante ? "vivante" : "morte")
       << ", " << c.x << "x" << c.y << "}"
       << endl;
}
#define PrintCell(c) \
  cout << "L'objet " #c " est à l'adresse mémoire " \
  << &c << endl

#define PrintVoisines(c1, c2)				\
  cout << "La cellule " #c1 " "				\
       << (c1.estVoisine(c2) ? "est" : "n'est pas")	\
       << " voisine de " #c2 "." << endl

int main(int argc, char** argv) {
  Cellule c1(true, 1, 2), c2(false, 1, 3);  
  PrintCell(c1); PrintCell(c2);
  test_cell(c1); test_cell(c2);
  PrintVoisines(c1, c2); PrintVoisines(c2, c1);
  return 0;
}
