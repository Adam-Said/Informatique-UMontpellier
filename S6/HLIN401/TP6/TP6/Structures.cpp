#include <iostream>
#include <fstream>
#include <sstream>
#include <cmath>
#include "Structures.h"

using namespace std;

// FILE

File::File(int n)
{
  taille = n;
  nb_elt = n;
  Element = new int[n];
  Priorite = new float[n];
  Indice = new int[n];
  for (int i=0; i < n; i++)
  {
    Element[i] = i;
    Priorite[i] = INFINITY;
    Indice[i] = i;
  }
}

File::~File()
{
  delete[] Element;
  delete[] Priorite;
  delete[] Indice;
}

template<typename E>
void swap(E* T, int i, int j)
{
  E t = T[i];
  T[i] = T[j];
  T[j] = t;
}

void File::remonter(int i)
{
  while (i > 0 and Priorite[(i-1)/2] > Priorite[i])
  {
    swap(Element, i, (i-1)/2);
    swap(Priorite, i, (i-1)/2);
    Indice[Element[i]] = i;
    Indice[Element[(i-1)/2]] = (i-1)/2;
    i = (i-1)/2;
  }
}

void File::entasser(int i)
{
  while (2*i+1 < nb_elt)
  {
    int m = i;
    if (Priorite[2*i+1] < Priorite[m]) m = 2*i+1;
    if (2*i+2 < nb_elt and Priorite[2*i+2] < Priorite[m]) m = 2*i+2;
    if (m == i) return;

    swap(Element, i, m);
    swap(Priorite, i, m);
    Indice[Element[i]] = i;
    Indice[Element[m]] = m;
    i = m;
  }
}

int File::extraire_min()
{
  int e = Element[0];
  Indice[e] = -1;
  nb_elt--;
  Element[0] = Element[nb_elt];
  Priorite[0] = Priorite[nb_elt];
  Indice[Element[0]] = 0;
  entasser(0);
  return e;
}

void File::changer_priorite(int x, float p)
{
  int i = Indice[x];
  float q = Priorite[i];
  Priorite[i] = p;
  if (p < q) remonter(i);
  else entasser(i);
}

bool File::est_vide()
{
  return nb_elt == 0;
}

void File::afficher()
{
  if (est_vide())
  {
    std::cout << "[]";
    return;
  }
  std::cout << '[';
  int i;
  for (i=0; i < nb_elt-1; i++)
    std::cout << "(" << Element[i] << "," << Priorite[i] << "),";
  std::cout << "(" << Element[i] << "," << Priorite[i] << ")]" << std::flush;
}

// LISTE

Voisin::Voisin(int u, float p, Voisin* s)
{
  sommet = u;
  poids = p;
  suivant = s;
}

// GRAPHES

void lecture(string nom, listeAdj*& G, coord*& S, int& n, int& l, int& h)
{
  string ligne;
  ifstream fichier(nom);
  int i, j, x, y;
  float p;

  getline(fichier, ligne);
  istringstream pligne(ligne);

  pligne >> n >> l >> h;
  G = new listeAdj[n];
  for (int i=0; i<n; i++) G[i] = NULL;
  S = new coord[n];
  while(getline(fichier, ligne))
  {
    istringstream sligne(ligne);
    string type;
    sligne >> type;
    if (type == "C")
    {
        sligne >> i >> x >> y;
        S[i] = {x,y};
    } 
    else if (type == "A")
    {
        sligne >> i >> j >> p;
        G[i] = new Voisin(j, p, G[i]);
        G[j] = new Voisin(i, p, G[j]);
    }
  }

}

bool intersect(coord A, coord B, coord C, coord D)
{
  if (C.x == D.x)
  {
    if ((A.x-C.x)*(B.x-C.x) > 0) return false;
    if (A.x == B.x) return false;
    float y = A.y+(C.x-A.x)*(float)(A.y-B.y)/(A.x-B.x);
    if ((C.y < y and y < D.y) or (C.y > y and y > D.y)) return true;
    return false;
  }
  else
  {
    if ((A.y-C.y)*(B.y-C.y) > 0) return false;
    if (A.y == B.y) return false;
    float x = A.x+(C.y-A.y)*(float)(A.x-B.x)/(A.y-B.y);
    if ((C.x < x and x < D.x) or (C.x > x and x > D.x)) return true;
    return false;
  }
}

void grapheObstacles(int n, int l, int h, int nobs, int lobs, float dmax, coord*& S, listeAdj*& G, coord*& O)
{
  S = new coord[n];
  for(int i=0;i<n;i++)
  {
    S[i].x = 10 + rand() % (l-19);
    S[i].y = 10 + rand() % (h-19);
  }

  O = new coord[2*nobs];
  for (int o=0; o < nobs; o++)
  {
    O[2*o].x = 10 + rand() % (l-19);
    O[2*o].y = 10 + rand() % (h-19);
    if (rand()%2)
    {
      O[2*o+1].x = O[2*o].x+lobs;
      O[2*o+1].y = O[2*o].y;
    }
    else
    {
      O[2*o+1].x = O[2*o].x;
      O[2*o+1].y = O[2*o].y+lobs;
    }
  }

  G = new listeAdj[n];
  for (int i=0; i < n; i++) G[i] = NULL;
  for (int i=0; i < n; i++)
    for (int j=i+1; j < n; j++)
    {
      int dx = S[i].x-S[j].x;
      int dy = S[i].y-S[j].y;
      float d = sqrt(dx*dx+dy*dy);
      if (d <= dmax)
      {
        bool b = true;
        for (int o = 0; b and o < nobs; o++)
          if (intersect(S[i],S[j],O[2*o],O[2*o+1]))
            b = false;
        if (b)
        {
          G[i] = new Voisin(j, d, G[i]);
          G[j] = new Voisin(i, d, G[j]);
        }
      }  
    }
}

void grille(int l, int h, float p, coord*& S, listeAdj*& G)
{
  int n = l*h;
  S = new coord[n];
  G = new listeAdj[n];
  for (int i=0; i < n; i++) G[i] = NULL;
  for (int i=0; i < l; i++)
    for (int j=0; j < h; j++)
    {
      int ind = i*h+j;
      S[ind] = {20*i+20, 20*j+20};
      if (i+1 < l and rand()<p*RAND_MAX)
      {
        G[ind] = new Voisin(ind+h, 1, G[ind]);
        G[ind+h] = new Voisin(ind, 1, G[ind+h]);
      }
      if (j+1 < h and rand()<p*RAND_MAX)
      {
        G[ind] = new Voisin(ind+1, 1, G[ind]);
        G[ind+1] = new Voisin(ind, 1, G[ind+1]);
      }
    }
}
