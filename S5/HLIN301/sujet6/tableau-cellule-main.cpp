#include <iostream>
#include <cstdlib>
#include "cellule.h"
#include "tableau-cellule.h"
#include "recherche-dicho.h"

#define N 5
int main(){

  TableauCellule tab(25);
  
  std::srand(time(NULL)); 
  for (size_t i = 0; i < tab.size(); i++) {
    tab.at(i).setX(rand() % N);
    tab.at(i).setY(rand() % N);
  }

  Cellule cell(true,rand() % N,rand() % N);

  std::cout<<"La Cellule ";
  cell.print();
  std::cout<<" est à la position "<<rechercheDichotomique(tab,cell)<<" dans le tableau suivant :"<<std::endl;
  tab.write(std::cout);
  
  return 0;
}
