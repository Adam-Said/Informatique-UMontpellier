#include "recherche-dicho.h"


size_t rechercheDichotomique(const TableauCellule &T, const Cellule& c) {
  size_t deb = 0, fin = T.size();
  size_t res = (size_t) -1;

  while ((res == (size_t) -1) && (deb < fin)) {
    size_t mid = (deb + fin) / 2;
    if (c.estEquivalente(T.at(mid))) {
      res = mid;
    } else {
      if (c.estAvant(T.at(mid))) {
	fin = mid;
      } else {
	deb = mid + 1;
      }
    }
  }
  return res;
}
