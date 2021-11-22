#include "cellule-v2.h"
#include <iostream>
using namespace std;

Cellule::Cellule(): vivante(false), x(0), y(0) {
}
Cellule::Cellule(bool etat, unsigned int x, unsigned int y):
  vivante(etat), x(x), y(y) {
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
// Accesseurs en écriture
void Cellule::setVivante(bool etat) {
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
