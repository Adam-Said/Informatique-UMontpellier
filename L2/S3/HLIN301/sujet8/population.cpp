#include <cstdlib>
#include <exception>
#include "population.h"
#include "generic-sort.h"
using namespace std;

#define CHECK_BOUND(i,j)					\
  if (i>=N || j>=N){						\
    cout << "Accessing a Cell at (" << i << ", " << j << ")"	\
	 << " out of range ... aborting" << endl;		\
    terminate();						\
  }

size_t Population::nb_voisins_vivants(size_t ci, size_t cj) const {
  size_t cpt=0;
  size_t imin,imax,jmin,jmax;
  imin = ci==0?ci:ci-1;
  imax = ci==(N-1)?ci:ci+1;
  jmin = cj==0?cj:cj-1;
  jmax = cj==(N-1)?cj:cj+1;
  
  for (size_t i = imin ; i <= imax ; i++) {
    for (size_t j = jmin ; j <= jmax ; j++) {
      if (T.find(Cellule(true, i,j)) != -1) {
	cpt++;
      }
    }
  }
  return cpt - (T.find(Cellule(true, ci, cj)) != -1);
}

void Population::updateColors() {
  //calcule les cellules vivantes qui vont mourir
  for (size_t i = 0; i < T.size(); i++) {
    size_t voisin=nb_voisins_vivants(T[i].getX(), T[i].getY());
    if ((voisin != 2) && (voisin != 3)) T[i].doitMourir();  
  }
}

Population::Population(size_t n, float prob):
  N(n), probability(prob) {
  reset();
}

size_t Population::getDimension() const { return N; }
void Population::setDimension(size_t n) { N = n; }
float Population::getProbability() const { return probability; }
void Population::setProbability(float p) { probability = p; }

#define NBCOLORS(c1, c2)				\
  size_t cpt = 0;					\
  for (size_t i = 0 ; i < T.size(); i++) {		\
    if ((Cellule::c1 == T[i].getCouleur())		\
       || (Cellule::c2 == T[i].getCouleur())) { cpt++; }\
  }							\
  return cpt

size_t Population::nb_vivants()    const { return T.size();}
size_t Population::nb_deces()     const { NBCOLORS(ROUGE, JAUNE);}
size_t Population::nb_morts()      const { return N*N-nb_vivants();}
size_t Population::nb_naissances() const { NBCOLORS(BLEU, JAUNE);}

Cellule Population::operator()(size_t i, size_t j) const {
  CHECK_BOUND(i,j);
  size_t x = T.find(Cellule(true, i, j));
  return (x == (size_t) -1 ? Cellule(false,i,j) : T[x]);
}

void Population::kill(size_t i, size_t j) {
  CHECK_BOUND(i,j);
  T.erase(T.find(Cellule(true, i, j)));
}
    
void Population::birth(size_t i, size_t j) {
  CHECK_BOUND(i,j);
  size_t k = T.find(Cellule(true, i, j));
  if (k == (size_t) -1) {
    T.push_back(Cellule(true, i, j));
  } else {
    T[k].setVivante(true);
  }
}

ostream &operator<<(ostream &os, const Population &p) {
  for (size_t i = 0; i < p.getDimension() + 2 ; i++) {
    os << "X";
  }
  os << endl;
  for (size_t i = 0 ; i < p.getDimension() ; i++) {
    os << "X";
    for (size_t j = 0 ; j < p.getDimension() ; j++) {
      os << Couleur2String(p(i,j).getCouleur());
    }
    os << "X" << endl;
  }
  for (size_t i = 0 ; i < p.getDimension() + 2 ; i++) {
    os << "X";
  }
  os << endl;
  return os;
}

void Population::reset() {
  srand(time(NULL));
  T.clear();
  if (probability < 0.5) {
    while (T.size() < N * N * probability) {
      size_t i,j;
      do {
        i = rand() % N;
        j = rand() % N;
      } while (T.find(Cellule(true, i, j)) != -1);
      T.push_back(Cellule(true, i, j));
    }
  } else {
    for (size_t i = 0; i < N; i++) {
      for (size_t j = 0; j < N; j++) {
        if ((rand() % 10000) / 10000. <= probability) {
          T.push_back(Cellule(true, i, j));
        }
      }
    }
  }
  triParTas(T);
  updateColors();
}

Population &Population::operator++() {
  Population POP(*this);
  for (size_t i = 0 ; i < N ; i++) {
    for (size_t j = 0 ; j < N ; j++) {
      size_t voisin=nb_voisins_vivants(i,j);
      if ((voisin == 3) || ((voisin == 2) && (T.find(Cellule(true, i,j)) != -1))) {
	POP.birth(i,j);
      } else {
	POP.kill(i,j);
      }
    }
  }
  triParTas(POP.T);
  POP.updateColors();
  return *this = POP;
}

Population Population::operator++(int) {
  Population POP(*this);
  ++*this;
  return POP;
}
