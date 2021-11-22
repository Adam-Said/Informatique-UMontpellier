#ifndef __CELLULE_V2_H
#define __CELLULE_V2_H

class Cellule {
 private:
  bool vivante;
  unsigned int x, y;

 public:
  // Constructeurs
  Cellule();
  Cellule(bool etat, unsigned int x,
	  unsigned int y);

  // Accesseurs en lecture
  bool getVivante() const;
  unsigned int getX() const;
  unsigned int getY() const;

  // Accesseurs en écriture
  void setVivante(bool etat);
  void setX(unsigned int x);
  void setY(unsigned int y);

  // Autre méthode
  bool estVoisine(const Cellule &c) const;
};

#endif
