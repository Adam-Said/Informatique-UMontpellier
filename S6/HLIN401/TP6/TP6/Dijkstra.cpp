/* Désolé j'ai oubléi de submit le fichier avant de partir 
de chez moi pour le week-end */
#include <iostream>
#include <cmath>
#include "Dijkstra.h"

using namespace std;

coord* sommetsAleatoires(int n, int l, int h)
{
  coord* sommets = new coord[n];
  for (int i = 0; i < n; i++)
  {
    sommets[i].x = rand() % (l-19) + 10;
    sommets[i].y = rand() % (h-19) + 10;
  }
  
  return sommets;
}
float distance(coord* sommets, int i, int j)
{
  return sqrt((sommets[i].x-sommets[j].x)*(sommets[i].x-sommets[j].x)+(sommets[i].y-sommets[j].y)*(sommets[i].y-sommets[j].y));
}

listeAdj* graphe(int n, coord* sommets, float dmax)
{
  listeAdj* G = new listeAdj[n];
  for (int i = 0; i < n; i++)
  {
    G[i] = NULL;
  }
  
  for (int i=0; i < n; i++) {
    for (int j = 0; j < n; j++)
    {
      float poids = distance(sommets, i, j);
      if (j != i && poids < dmax)
      {
        G[i] = new Voisin(j, poids, G[i]);
      }
      
    }
    
  }
  return G; 
}

void dijkstra(int n, listeAdj* G, int s, float*& D, int*& P)
{
  File* F = new File(n);
  D = new float[n];
  P = new int[n];
  for (int i = 0; i < n; i++)
  {
    D[i] = INFINITY;
    P[i] = -1;
  };
  for (int i = 0; i < n; i++)
  {
    F->changer_priorite(s, 0);
    D[s] = 0;
  }
  while (!(F->est_vide()))
  {
    int u = F->extraire_min();
    Voisin* v = G[u];
    while (v != NULL)
    {
      if ((D[u] + v->poids) < D[v->sommet])
      {
        D[v->sommet] = D[u] + v->poids;
        P[v->sommet] = u;
        F->changer_priorite(v->sommet, D[v->sommet]);
      }
      v = v->suivant;
    }
  }
  return ; 
}

listeAdj* arbre(int n, listeAdj* G, int* P, int s)
{
  listeAdj* T = new listeAdj[n];
  for (int i=0; i<n; i++) {T[i]=NULL;}
  for (int i = 0; i < n; i++)
  {
    if (i != s)
    {
      T[P[i]] = new Voisin(i, 0, T[P[i]]);
      T[i] = new Voisin(P[i], 0, T[i]);
    }
  }
  return T;
}

listeAdj chemin(int n, listeAdj* G, int* P, int v, int s)
{
  listeAdj C = new Voisin(s, 0, NULL);
  while (v != s)
  {
    s = P[s];
    C = new Voisin(s, 0, C);
  }
  return C;
}
  
void a_etoile(int n, listeAdj* G, coord* sommets, int s, int t, float*& D, int*& P)
{
  return; // à modifier
}
