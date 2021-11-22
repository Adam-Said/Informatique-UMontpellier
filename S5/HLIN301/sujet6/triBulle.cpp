#include "triBulle.h"
#include "tableau-cellule.h"
#include "cellule.h"

void triBulle(TableauCellule &T) {
  size_t modif=1;
  size_t n = T.size()-1;
  while (modif && n) {
    n--;
    modif=0;
    for (size_t i = 0; i < n; i++) {
      if (T.at(i).estApres(T.at(i+1))) {
	echanger(T.at(i), T.at(i+1));
	modif++;
      }
    }
  }
}
