#include <iostream>
#include <cstdlib>
#include <exception>
#include "population-vivante-v4.h"
#include "myvector-cellule.h"
#include "generic-sort.h"

using namespace std;

#define CHECK_BOUND(i,j)						\
  if (i>=N || j>=N){							\
    cout << "Accessing a Cell at (" << i << ", " << j << ")"	\
	 << " out of range ... aborting" << endl;		\
    terminate();						\
  }

size_t PopulationVivante::nb_voisins_vivants(size_t ci, size_t cj) const {
  size_t cpt=0;
  size_t imin,imax,jmin,jmax;
  imin = ci==0?ci:ci-1;
  imax = ci==(N-1)?ci:ci+1;
  jmin = cj==0?cj:cj-1;
  jmax = cj==(N-1)?cj:cj+1;
  
  for (size_t i = imin ; i <= imax ; i++) {
    for (size_t j = jmin ; j <= jmax ; j++) {
      if (rechercheDichotomique(T,Cellule(true,i,j))!=size_t(-1)) { 
      cpt++;
      }
    }
  }
  return cpt - (rechercheDichotomique(T,Cellule(true,ci,cj))!=size_t(-1) ? 1 : 0);
}

void PopulationVivante::updateColors() {
  //calcule les cellules vivantes qui vont mourir  
  for (size_t i = 0; i < T.size(); i++) {
    size_t voisin=nb_voisins_vivants(T.at(i).getX(), T.at(i).getY());
    if ((voisin != 2) && (voisin != 3)) T.at(i).doitMourir();  
  }
}


size_t PopulationVivante::nb_cellules(Cellule::Couleur c) const {
  size_t cpt=0;
  for(size_t i=0;i<T.size();i++)
    if (CelluleEstDeLaCouleur(T.at(i),c)) {
      cpt++;
    }    
  return cpt;
}

size_t PopulationVivante::getDimension() const { return N; }
void PopulationVivante::setDimension(size_t n) { N = n; }
float PopulationVivante::getProbability() const { return probability; }
void PopulationVivante::setProbability(float p) { probability = p; }

PopulationVivante::PopulationVivante(size_t n, float prob):  T(), N(n), probability(prob) {
  reset();
}

	   
PopulationVivante::PopulationVivante(std::ifstream& is) {
  size_t nb;
  is>>N>>nb;;  
  std::vector<Cellule> tmp(nb);
  read(is, tmp);
  T=tmp;
  updateColors();
}

size_t PopulationVivante::nb_vivants()    const { return N*N-nb_morts();}
size_t PopulationVivante::nb_deces()     const { return nb_cellules(Cellule::ROUGE)+nb_cellules(Cellule::JAUNE);}
size_t PopulationVivante::nb_morts()      const { return nb_cellules(Cellule::NOIR);}
size_t PopulationVivante::nb_naissances() const { return nb_cellules(Cellule::BLEU)+nb_cellules(Cellule::JAUNE);}

Cellule PopulationVivante::getCelluleCopie(size_t i, size_t j) const {
  CHECK_BOUND(i,j);
  size_t idx = rechercheDichotomique(T,Cellule(true,i,j));
  if (idx==size_t(-1)) {
    return Cellule(false,i,j);
  } else {
    return T.at(idx);
  }
}



void PopulationVivante::printCell(size_t i, size_t j) const {
  CHECK_BOUND(i,j);
  getCelluleCopie(i,j).print();
}   


void PopulationVivante::kill(size_t i, size_t j) {
  size_t idx = rechercheDichotomique(T,Cellule(true,i,j)); 
  if (idx!=size_t(-1))
    T.erase(T.begin()+idx);
}
    
void PopulationVivante::birth(size_t i, size_t j) {
  size_t idx = rechercheDichotomique(T,Cellule(true,i,j));
  if (idx==size_t(-1))
    T.push_back(Cellule(true,i,j));
  else
    T.at(idx).setVivante(true);
}



void PopulationVivante::print() const {
  for (size_t i = 0 ; i < N + 2 ; i++) {
    cout<<"X";
  }
  cout<<endl;
  for (size_t i = 0 ; i < N ; i++) {
    cout<<"X";
    for (size_t j = 0 ; j < N ; j++) {
      size_t idx = rechercheDichotomique(T,Cellule(true,i,j));
      if (idx!=size_t(-1))
	cout<<Couleur2String(T.at(idx).getCouleur());
      else
	cout<<Couleur2String(Cellule::NOIR);
    }
    cout<<"X"<<endl;
  }
  for (size_t i = 0 ; i < N + 2 ; i++) {
    cout<<"X";
  }
  cout<<endl;
}

void PopulationVivante::print(std::ofstream& os) const {
  os<<N<<" "<<T.size()<<std::endl;
  write(os,T);  
}

void PopulationVivante::reset() {
  srand(time(NULL));
  T = std::vector<Cellule>();
  if (probability < 0.5) {
    for (size_t x = 0 ; x < N * N * probability ; x++) {
      size_t i,j;
      Cellule c(true, i, j);
      do {
        i = rand() % N;
        j = rand() % N;
      } while (rechercheDichotomique(T,c) != size_t(-1));
      T.push_back(c);
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
  updateColors();
}

PopulationVivante PopulationVivante::next() const {
  PopulationVivante POP(*this);
  for (size_t i = 0 ; i < N ; i++) {
    for (size_t j = 0 ; j < N ; j++) {
      size_t voisin=nb_voisins_vivants(i,j);
      if ((voisin == 3) || ((voisin == 2) && (rechercheDichotomique(T,Cellule(true,i,j)) != size_t(-1)))) {
	POP.birth(i,j);
      } else {
	POP.kill(i,j);
      }
    }
  }
  POP.updateColors();
  return POP;
}
