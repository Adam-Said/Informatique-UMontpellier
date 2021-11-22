#include <iostream>
#include <cstdlib>
#include "cellule.h"
#include "myvector.h"
#include "myvector-cellule.h"
#include "generic-sort.h"

using namespace std;

#define N 20
int main(int argc, char** argv) {

  MyVector<Cellule> tab(15);
  MyVector<Cellule> orig_tab;

  srand(383); // Exemple qui fonctionne bien ;D sur ma machine
  for (size_t i = 0; i < tab.size(); i++) {
    tab.at(i).setX(rand() % N);
    tab.at(i).setY(rand() % N);
  }
  orig_tab = tab;
  cout << "tab = ";
  write(cout,tab);
  cout << endl << "triBulle..." << endl;
  triBulle(tab);
  cout << "tab = ";  
  write(cout,tab);
  cout << endl << endl;

  tab = orig_tab;
  cout << "tab = ";
  write(cout,tab);
  cout << endl << "triRapide..." << endl;
  triRapide(tab);
  cout << "tab = ";  
  write(cout,tab);
  cout << endl << endl;  
  tab = orig_tab;
  cout << "tab = ";
  write(cout,tab);
  cout << endl << "triParTas..." << endl;
  triParTas(tab);
  cout << "tab = ";  
  write(cout,tab);
  cout << endl << endl;

  size_t nb = 5;
  do {
    Cellule target(true, rand() % N, rand() % N);
    cout << "Recherche de la cellule ";
    write_cell(cout, target);
    cout << endl;
    size_t i = rechercheDichotomique(tab, target);
    if (i != (size_t) -1) {
      cout << "Cellule trouvée à l'indice " << i << "=> ";
      write_cell(cout, tab.at(i));
      cout << endl;
    } else {
      cout << "Cellule non trouvée." << endl;
    }
  } while (--nb);
  
  return 0;
}
