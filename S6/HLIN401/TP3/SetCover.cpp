#include <cstdlib>
#include <iostream>
#include <fstream>
#include <string>

#include "SetCover.h"

using namespace std;

coord* maisonsAleatoires(int n, int l, int h)
{
  coord* maisons = new coord[n];
  for (int i = 0; i < n; i++)
  {
    maisons[i].x = rand() % (l-19) + 10;
    maisons[i].y = rand() % (h-19) + 10;
  }
  
  return maisons;
}

bool couvre(int i, int j, coord* maisons, int dcouv)
{
  return (((maisons[i].x-maisons[j].x)*(maisons[i].x-maisons[j].x)+(maisons[i].y-maisons[j].y)*(maisons[i].y-maisons[j].y)) < dcouv*dcouv);
}

bool** graphe(int n, coord* maisons, int dcouv)
{
  bool** G = new bool*[n];
  for (int i=0; i < n; i++) {
    G[i] = new bool[n];
    for (int j = 0; j < n; j++)
    {
      G[i][j] = couvre(i,j, maisons, dcouv);
    }
  }
  return G;
}

int prochaineMaison(int n, bool** G, bool* couvertes)
{
  int maisonM = 0;
  int voisinM = 0;
  int vois = 0;
  for (int i = 0; i < n; i++)
  {
    if(!couvertes[i]) {
      vois = 0;
      for (int j = 0; j < n; j++)
      {
        if (G[i][j] && !couvertes[j]) vois++;        
      }
      if (vois > voisinM)
      {
        maisonM = i;
        voisinM = vois;
      }
      
    }
  }
  return maisonM;
}

int placementGlouton(int n, bool** G, bool* emetteurs)
{
  int count = 0;
  bool* couvertes = new bool[n];
  for (int i = 0; i < n; i++)
  {
    couvertes[i] = false;
    emetteurs[i] = false;
  }
  bool tout_couvert = false;
  while (count < n && !tout_couvert)
  {
    count++;
    int prochainEmetteur = prochaineMaison(n, G, couvertes);
    emetteurs[prochainEmetteur] = true;

    for (int i = 0; i < n; i++) {
      if (emetteurs[i]) {
        for (int j=0; j<n; j++) {
          if (G[i][j]) couvertes[j] = true; 
        }
      }
    }
    tout_couvert = true;
    for (int i = 0; i < n; i++)
    {
      if (couvertes[i] == false)
      {
        tout_couvert=false;
      }
    }
  }  
  return count;
}

int placementOptimal(int n, bool** G, bool* emetteurs)
{
  return 0; // À modifier !
}
