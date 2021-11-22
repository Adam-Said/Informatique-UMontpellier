#include "cellule-v3.h"
#include <iostream>
#include <string>

using namespace std;

Cellule::Cellule(): vivante(false), x(0), y(0), couleur(NOIR) {
}

Cellule::Cellule(bool etat, unsigned int x, unsigned int y):
  vivante(etat), x(x), y(y), couleur(etat ? BLEU : NOIR) {
}

bool Cellule::getVivante() const {
  return vivante;
}

unsigned int Cellule::getX() const {
  return x;
}

unsigned int Cellule::getY() const {
  return y;
}

Cellule::Couleur Cellule::getCouleur() const {
  return couleur;
}

// Accesseurs en écriture
void Cellule::setVivante(bool etat) {
  if (etat) {
    couleur = vivante? VERT : BLEU;
  } else {
    couleur = NOIR;
  }
  vivante = etat;
}

void Cellule::setX(unsigned int x) {
  this->x = x;
}

void Cellule::setY(unsigned int y) {
  this->y = y;
}

bool Cellule::estVoisine(const Cellule &c) const {
  return vivante &&
    ((x - c.x) * (x - c.x) + (y - c.y) * (y - c.y) <= 2);
}

bool CelluleEstDeLaCouleur(const Cellule &cellule, Cellule::Couleur couleur) {
  return (cellule.getCouleur() == couleur);
}

string Couleur2String(Cellule::Couleur c) {
  string res;
  switch (c) {
  case Cellule::NOIR:
    res = "noire";
    break;
  case Cellule::BLEU:
    res = "bleue";
    break;
  case Cellule::VERT:
    res = "verte";
    break;
  case Cellule::ROUGE:
    res = "rouge";
    break;
  default:
    res = "non définie.";
    cerr << "Erreur:" << __FILE__ << ":" << __FUNCTION__ << ":" << __LINE__
	 << ":Couleur non définie. Les couleurs possibles sont:" << endl;
    for (Cellule::Couleur i = Cellule::NOIR;
	 i != Cellule::NB_COULEURS;
	 i = (Cellule::Couleur) (((int) i)+1)) {
      cerr << "- " << Couleur2String(i) << endl;
    }
  }
  return res;
}
