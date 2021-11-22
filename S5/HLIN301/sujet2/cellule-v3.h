#ifndef __CELLULE_V3_H
#define __CELLULE_V3_H

#include <string>

class Cellule {
 public:

  enum Couleur {
    NOIR,
    BLEU,
    VERT,
    ROUGE,
    NB_COULEURS
  };

 private:
  bool vivante;
  unsigned int x, y;
  Couleur couleur;

 public:

  // Constructeurs
  Cellule();
  Cellule(bool etat, unsigned int x, unsigned int y);

  // Accesseurs en lecture
  bool getVivante() const;
  unsigned int getX() const;
  unsigned int getY() const;
  Couleur getCouleur() const;

  // Accesseurs en écriture
  void setX(unsigned int x);
  void setY(unsigned int y);
  void setVivante(bool etat);

  // Autre méthode
  bool estVoisine(const Cellule &c) const;
};

// Renvoie vrai si la cellule est de la couleur passée en paramètre, faux sinon.
bool CelluleEstDeLaCouleur(const Cellule &cellule, Cellule::Couleur couleur);

// Retourne la chaîne correspondant à la couleur passée en paramètre 
std::string Couleur2String(Cellule::Couleur c);

#endif
