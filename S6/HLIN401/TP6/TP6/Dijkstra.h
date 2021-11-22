#include<iostream>
#include "Structures.h"

#pragma once

using namespace std;

coord* sommetsAleatoires(int, int, int);
float distance(coord*, int, int);
listeAdj* graphe(int, coord*, float);
void dijkstra(int, listeAdj*, int, float*&, int*&);
listeAdj* arbre(int, listeAdj*, int*, int);
listeAdj chemin(int, listeAdj*, int*, int, int);
void a_etoile(int, listeAdj*, coord*, int, int, float*&, int*&);
