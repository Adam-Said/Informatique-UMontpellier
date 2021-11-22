#include <cstdlib>
#include <iostream>
#include <fstream>
#include <string>

#include "SetCover.h"

using namespace std;

void affichageMaisons(int, coord*, string, int, int);
void affichageGraphe(int, coord*, bool**, string, int, int);
void affichageEmetteurs(int, coord*, bool**, bool*, string, int, int, int = 100);

