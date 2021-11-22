#include <cstdlib>
#include <iostream>
#include <fstream>
#include <string>

#pragma once

struct coord
{
  int x;
  int y;
};

coord* maisonsAleatoires(int, int, int);
bool couvre(int, int, coord*, int = 100);
bool** graphe(int, coord*, int = 100);
int prochaineMaison(int, bool**, bool*);
int placementGlouton(int, bool**, bool*);
int placementOptimal(int, bool**, bool*);
