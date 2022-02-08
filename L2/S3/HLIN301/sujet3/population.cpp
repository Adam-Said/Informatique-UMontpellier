#include <iostream>
#include <cstdlib>
#include <exception>
#include "population.h"
using namespace std;

#define CHECK_BOUND( i,j) \	
  if (i>=N || j>=N){	\		
    std::cout<<"Accessing a Cell at ("<<i<<","<<j<<") out of range..."	\
	     << " Aborting"<<std::endl;				\
    std::terminate();						\
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
      if (T[i][j].getVivante()) { 
	cpt++;
      }
    }
  }
  return cpt - (T[ci][cj].getVivante() ? 1 : 0);
}

void Population::updateColors() {
  //calcule les cellules vivantes qui vont mourir  
  for (size_t i = 0 ; i < N ; i++) {
    for (size_t j = 0 ; j < N ; j++) {
      size_t voisin=nb_voisins_vivants(i, j);
      if (voisin !=3 && voisin!=2 && T[i][j].getVivante())
	T[i][j].doitMourir();
    }
  }
}
  


Population::Population() {
  for (size_t i = 0 ; i < N ; i++) {
    for (size_t j = 0 ; j < N ; j++) {
      T[i][j].setX(i);
      T[i][j].setY(j);
      T[i][j].setVivante(false);
    }
  }
}

void Population::init(size_t n) {
  srand(time(NULL));
  if (nb_vivants()==0 && n <= N*N){
    size_t i,j;
    for (size_t k = 0 ; k < n ; k++) {
      do {
	i=rand()% N;
	j=rand()% N;
      } while ((T[i][j]).getVivante());
      T[i][j].setVivante(true);
    }
    updateColors();
  }
}


size_t Population::nb_cellules(Cellule::Couleur c) const {
  size_t cpt=0;
  for (size_t i = 0 ; i < N ; i++) {
    for (size_t j = 0 ; j < N ; j++) {
      if (CelluleEstDeLaCouleur(T[i][j],c)) { // if (T[i][j].getCouleur()==c){	
	cpt++;
      }
    }
  }
  return cpt;
}

size_t Population::nb_vivants()    const { return N*N-nb_morts();}
size_t Population::nb_deces()     const { return nb_cellules(Cellule::ROUGE)+nb_cellules(Cellule::JAUNE);}
size_t Population::nb_morts()      const { return nb_cellules(Cellule::NOIR);}
size_t Population::nb_naissances() const { return nb_cellules(Cellule::BLEU);}


Cellule Population::getCelluleCopie(size_t i, size_t j) const {
  CHECK_BOUND(i,j);
  return T[i][j];
}

const Cellule& Population::getCellule(size_t i, size_t j) const{
  CHECK_BOUND(i,j);
  return T[i][j];  
}

void Population::printCell(size_t i, size_t j) const {
  CHECK_BOUND(i,j);
  T[i][j].print();
}

void Population::kill(size_t i, size_t j) {
  CHECK_BOUND(i,j);
  T[i][j].setVivante(false);
}
    
void Population::birth(size_t i, size_t j) {
  CHECK_BOUND(i,j);
  T[i][j].setVivante(true);
}

void Population::print() const {
  for (size_t i = 0 ; i < N + 2 ; i++) {
    cout<<"X";
  }
  cout<<endl;
  for (size_t i = 0 ; i < N ; i++) {
    cout<<"X";
    for (size_t j = 0 ; j < N ; j++) {
      //T[i][j].print();
      cout<<Couleur2String(T[i][j].getCouleur());
    }
    cout<<"X"<<endl;
  }
  for (size_t i = 0 ; i < N + 2 ; i++) {
    cout<<"X";
  }
  cout<<endl;
}

Population Population::next() const {
  Population POP(*this);
  for (size_t i = 0 ; i < N ; i++) {
    for (size_t j = 0 ; j < N ; j++) {
      size_t voisin=nb_voisins_vivants(i,j);
      (POP.T[i][j]).setVivante(voisin ==3 || (voisin==2 && T[i][j].getVivante()));
    }
  }
  POP.updateColors();
  return POP;
}

