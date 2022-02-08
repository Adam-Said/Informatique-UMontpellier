#ifndef __POPULATION_H
#define __POPULATION_H
#include "cellule.h"

#define N 20 

class Population {
  
 private:
  Cellule T[N][N];
  
  // retourne le nbr de voisins vivants de la cellule à (i,j)
  size_t nb_voisins_vivants(size_t, size_t) const;
  
  //retourne le nbr de cellule d'une couleur donnée
  size_t nb_cellules(Cellule::Couleur) const;  

  // Mise à jour des couleurs des cellules mourantes
  void updateColors();

 public:
  
  // constructeur d'une population vide (sur une grille NxN)
  Population();

  // création de n cellules vivantes aléatoires (uniquement si population vide) 
  void init(size_t);
  
  // accesseurs en interrogation
  size_t nb_vivants()    const;
  size_t nb_deces()     const;
  size_t nb_morts()      const; 
  size_t nb_naissances() const;

  // accesseurs en lecture d'une cellule
  Cellule getCelluleCopie(size_t i, size_t j) const;
  const Cellule& getCellule(size_t i, size_t j) const;

  // accesseurs en modification
  void kill(size_t i, size_t j);    
  void birth(size_t i, size_t j);

  // affichage d'une cellule
  void printCell(size_t i, size_t j) const;
  // affichage de la population
  void print() const;
  
  // calcul de la population suivante
  Population next() const;
    
};

#endif 
