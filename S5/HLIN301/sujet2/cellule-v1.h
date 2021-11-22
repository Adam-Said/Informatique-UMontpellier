#ifndef __CELLULE_V1_H
#define __CELLULE_V1_H

class Cellule {
 public:
  // Attributs
  bool vivante;
  const unsigned int x, y;

  // Constructeur
  Cellule(bool etat, unsigned int x, unsigned int y);

  // Méthode
  bool estVoisine(const Cellule &c) const;
};

#endif
