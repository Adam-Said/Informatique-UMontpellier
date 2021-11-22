#ifndef __POPULATION_VIVANTE_H
#define __POPULATION_VIVANTE_H
#include "cellule.h"
#include "tableau-cellule.h"
#include <fstream>

class PopulationVivante {
  
 private:
  TableauCellule T;
  size_t         N;
  float probability;
  
  // retourne le nbr de voisins vivants de la cellule à (i,j)
  size_t nb_voisins_vivants(size_t, size_t) const;
  
  //retourne le nbr de cellule d'une couleur donnée
  size_t nb_cellules(Cellule::Couleur) const;  

  // Mise à jour des couleurs des cellules mourantes
  void updateColors();

 public:
  // construction d'une n-population avec une probabilité fixée.
  PopulationVivante(size_t n, float prob);
  // constructeur d'une population à partir d'un fichier de configuration
  PopulationVivante(std::ifstream&);

    // accesseurs en interrogation
  size_t nb_vivants()    const;
  size_t nb_deces()     const;
  size_t nb_morts()      const; 
  size_t nb_naissances() const;
  size_t getDimension() const;
  float getProbability() const;
  

  // accesseurs en lecture d'une cellule
  Cellule getCelluleCopie(size_t i, size_t j) const;

  // accesseurs en modification
  void kill(size_t i, size_t j);    
  void birth(size_t i, size_t j);
  void setDimension(size_t n);
  void setProbability(float p);

  // affichage d'une cellule
  void printCell(size_t i, size_t j) const;

  // affichage de la population
  void print() const;
  void print(std::ofstream&)const;
  
  // Re-création de la population
  void reset();  
  
  // calcul de la population suivante
  PopulationVivante next() const;  
};


#endif 
