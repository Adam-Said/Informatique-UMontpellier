#ifndef __CELLULE_H
#define __CELLULE_H

#include <string>

class Cellule {
 public:

  enum Couleur {
    NOIR,
    BLEU,
    VERT,
    ROUGE,
    JAUNE,
    NB_COULEURS
  };

 private:
  size_t age;
  unsigned int x, y;
  Couleur couleur;

 public:

  // Constructeurs
  Cellule(); // morte par défaut
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

  // renvoie vrai si la cellule courante est vivante et est voisine de c
  bool estVoisine(const Cellule &c) const;
  // affiche la cellule
  void print() const;

  // spécifie qu'une cellule doit mourir au prochain tour du jeu (-> changement de couleur)
  void doitMourir();
};

// Renvoie vrai si la cellule est de la couleur passée en paramètre, faux sinon.
bool CelluleEstDeLaCouleur(const Cellule &cellule, Cellule::Couleur couleur);

// Retourne la chaîne correspondant à la couleur passée en paramètre 
std::string Couleur2String(Cellule::Couleur c);

#endif
