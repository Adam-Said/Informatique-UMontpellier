#include <iostream>
#include <cstdlib>
#include "cellule.h"
#include "tableau-cellule.h"
#include "recherche-dicho.h"
#include "triBulle.h"
#include "triRapide.h"
#include "triTas.h"



using namespace std;

#define N 20
int main(int argc, char** argv) {

  TableauCellule tab(15);
  TableauCellule orig_tab;

  //  srand(383);
  srand(time(NULL)); 
  for (size_t i = 0; i < tab.size(); i++) {
    tab.at(i).setX(rand() % N);
    tab.at(i).setY(rand() % N);
  }
  orig_tab = tab;
  cout << "tab = ";
  tab.write(cout);
  cout << endl << "triBulle..." << endl;
  triBulle(tab);
  cout << "tab = ";  
  tab.write(cout);
  cout << endl << endl;

  tab = orig_tab;
  cout << "tab = ";
  tab.write(cout);
  cout << endl << "triRapide..." << endl;
  triRapide(tab);
  cout << "tab = ";  
  tab.write(cout);
  cout << endl << endl;
  
  tab = orig_tab;
  cout << "tab = ";
  tab.write(cout);
  cout << endl << "triParTas..." << endl;
  triParTas(tab);
  cout << "tab = ";  
  tab.write(cout);
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
