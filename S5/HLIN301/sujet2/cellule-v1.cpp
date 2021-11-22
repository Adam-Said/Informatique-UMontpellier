#include "cellule-v1.h"
#include <iostream>

using namespace std;

Cellule::Cellule(bool etat, unsigned int _x, unsigned int _y):
  vivante(etat), x(_x), y(_y)
{}

bool Cellule::estVoisine(const Cellule &c) const {
  return c.vivante &&
    ((x - c.x) * (x - c.x) + (y - c.y) * (y - c.y) <= 2);
}


