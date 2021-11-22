#ifndef __POPULATION_H
#define __POPULATION_H
#include "cellule.h"
#include "myvector.h"
#include <iostream>

class Population {
 private:
  MyVector<Cellule> T;
  size_t N;
  float probability;

  // retourne le nbr de voisins vivants de la cellule à (i,j)
  size_t nb_voisins_vivants(size_t, size_t) const;
  // Mise à jour des couleurs des cellules mourantes
  void updateColors();

 public:
  // construction d'une n-population avec une probabilité fixée.
  Population(size_t n, float prob);

  // accesseurs en interrogation
  size_t nb_vivants()    const;
  size_t nb_deces()     const;
  size_t nb_morts()      const; 
  size_t nb_naissances() const;
  size_t getDimension() const;
  float getProbability() const;
  
  // accesseurs en lecture d'une cellule
  Cellule operator()(size_t i, size_t j) const;

  bool operator==(const Population &p) const;

  // accesseurs en modification
  void kill(size_t i, size_t j);    
  void birth(size_t i, size_t j);
  void setDimension(size_t n);
  void setProbability(float p);

  // Recréation d'une population en fonction de la probabilité.
  void reset();

  // calcul de la population suivante.
  Population &operator++();  
  Population operator++(int);
};

std::ostream &operator<<(std::ostream &os, const Population &p);
#endif
