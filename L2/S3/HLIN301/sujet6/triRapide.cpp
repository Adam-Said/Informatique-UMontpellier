#include "triRapide.h"
#include "tableau-cellule.h"

void triRapide_aux(TableauCellule &T, int deb, int fin) {
  if (deb >= fin) return;
  int d = deb, f = fin, pivot;
  pivot = (deb + fin) / 2;
  while (d < f) {
    while ((d < pivot) && T.at(d).estAvantOuEquivalente(T.at(pivot))) {
      d++;
    }
    while ((pivot < f) && T.at(f).estApresOuEquivalente(T.at(pivot))) {
      f--;
    }
    echanger(T.at(d), T.at(f));
    
    if (pivot == d) 
      pivot = f;
    else 
      if (pivot == f) 
    	pivot = d;
  }
  triRapide_aux(T, deb, pivot-1);
  triRapide_aux(T, pivot + 1, fin);
}

void triRapide(TableauCellule &T) {
  triRapide_aux(T, 0, T.size()-1);
}
