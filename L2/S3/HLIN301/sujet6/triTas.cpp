#include "triTas.h"
#include "tableau-cellule.h"



//#define Pere(i) (i ? (i - 1) / 2 : -1)
#define FilsG(i) (2 * i + 1)
#define FilsD(i) (2 * i + 2)
//#define EstRacine(i) (!i)
//#define EstFeuille(i, n) (i > (n / 2))

void entasser(TableauCellule &T, size_t i, size_t n) {
  size_t max_indice = i,
    fg = FilsG(i),
    fd = FilsD(i);
  if ((fg < n) && T.at(max_indice).estAvant(T.at(fg))) {
    max_indice = fg;
  }
  if ((fd < n) && (T.at(max_indice).estAvant(T.at(fd)))) {
    max_indice = fd;
  }
  if (i != max_indice) {
    echanger(T.at(i), T.at(max_indice));
    entasser(T, max_indice, n);
  }
}

void entasser(TableauCellule &T, size_t i, size_t n) {
  size_t 
    fg = FilsG(i),
    fd = FilsD(i);
  if ((fg < n) && T.at(i).estAvant(T.at(fg))) {
    echanger(T.at(i), T.at(fg));
    entasser(T, fg, n);
  }
  if ((fd < n) && (T.at(i).estAvant(T.at(fd)))) {
    echanger(T.at(i), T.at(fd));
    entasser(T, fd, n);
  }
}

void initTas(TableauCellule &T) {
  for (size_t i = T.size() / 2 - 1; i != (size_t) -1; i--) {
    entasser(T, i, T.size());
  }
}

void triParTas(TableauCellule &T) {
  initTas(T);
  for (size_t i = T.size() - 1; i != (size_t) -1; i--) {
    echanger(T.at(0), T.at(i));
    entasser(T, 0, i);
  }
}
