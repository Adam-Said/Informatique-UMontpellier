#include <cstdlib>
#include <iostream>
#include <fstream>
#include <string>

#include "Dijkstra.h"
#include "Affichage.h"
#include "Structures.h"

using namespace std;

#define DEBUT(w,h) \
  ofstream output; \
  output.open(nom + ".svg",ios::out); \
  output << "<?xml version=\"1.0\" encoding=\"utf-8\"?>" << endl; \
  output << "<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" " \
         << "width=\"" << (w) << "\" height=\"" << (h) << "\">" << endl; \
  output << "<rect width=\"" << (w) << "\" height=\"" << (h) << "\" fill=\"white\" x=\"0\" y=\"0\"/>" << endl; \
  output << endl;  

#define FIN \
  if (sommets and n <= 20) \
    for (int i=0; i<n;i++) \
      output << "<text x=\"" << sommets[i].x+5 << "\" y=\"" << sommets[i].y+5 << "\">" << i << "</text>" << endl; \
  output << endl; \
  output << "</svg>" << endl; \
  output.close();

#define ARETES(G, color, texte) \
  for (int i=0; i<n;i++) \
  { \
    listeAdj L = G[i]; \
    while (L) { \
      if (L->sommet > i) \
      { \
        int v = L->sommet; \
        output << "<line x1=\"" << sommets[i].x << "\" y1=\"" << sommets[i].y << "\" " \
               << "x2=\"" << sommets[v].x << "\" y2=\"" << sommets[v].y << "\" " \
               << "stroke=\"" << color << "\"/>" << endl; \
        if (texte) \
          output << "<text x=\"" << (sommets[i].x+sommets[v].x)/2. << "\" y=\"" << (sommets[i].y+sommets[v].y)/2. \
                 << "\" fill=\"darkgray\">" << L->poids << "</text>" << endl; \
      } \
      L = L->suivant; \
    } \
  }

#define CHEMIN \
  if (ch) \
  { \
    int i = ch->sommet; \
    output << "<circle cx=\"" << sommets[i].x << "\" cy=\"" << sommets[i].y << "\" " \
           << "r=\"4\" fill=\"red\" />" << endl; \
    ch = ch->suivant; \
    while (ch) \
    { \
      int v = ch->sommet; \
      output << "<line x1=\"" << sommets[i].x << "\" y1=\"" << sommets[i].y << "\" " \
             << "x2=\"" << sommets[v].x << "\" y2=\"" << sommets[v].y << "\" " \
             << "stroke=\"red\" stroke-width=\"2\"/>" << endl; \
      i = v; \
      ch = ch->suivant; \
    } \
    output << "<circle cx=\"" << sommets[i].x << "\" cy=\"" << sommets[i].y << "\" " \
           << "r=\"4\" fill=\"red\" />" << endl; \
  }

void affichageSommets(int n, coord* sommets, string nom, int w, int h)
{
  DEBUT(w, h)
  if (sommets)
    for (int i=0;i<n;i++)
      output << "<circle cx=\"" << sommets[i].x << "\" cy=\"" << sommets[i].y << "\" "
             << "r=\"4\" fill=\"black\" />" << endl;
  FIN
}

void affichageGraphe(int n, coord* sommets, listeAdj* G, string nom, int w, int h, listeAdj* A, int o, int a, listeAdj ch, int nobs, coord* obstacles)
{
  DEBUT(w, h)
  string couleur = (A)?"lightgray":"darkgray";
  if (G) { ARETES(G, couleur, n <= 20) }
  if (A) { ARETES(A, "green", false) }
  CHEMIN

  for (int i=0; i < nobs; i++)
  {
    coord A = obstacles[2*i];
    coord B = obstacles[2*i+1];
    output << "<line x1=\"" << A.x << "\" y1=\"" << A.y << "\" " \
           << "x2=\"" << B.x << "\" y2=\"" << B.y << "\" " \
           << "stroke=\"black\" stroke-width=\"3\"/>" << endl; \
  }

  if (sommets and n <= 100000)
    for (int i=0;i<n;i++)
      output << "<circle cx=\"" << sommets[i].x << "\" cy=\"" << sommets[i].y << "\" "
             << "r=\"1\" fill=\"black\" />" << endl;

  if (o > -1)
    output << "<circle cx=\"" << sommets[o].x << "\" cy=\"" << sommets[o].y << "\" "
           << "r=\"4\" fill=\"green\" />" << endl;

  if (a > -1)
    output << "<circle cx=\"" << sommets[a].x << "\" cy=\"" << sommets[a].y << "\" "
           << "r=\"4\" fill=\"red\" />" << endl;

  FIN
}

#undef FIN
#undef DEBUT
#undef ARETES
#undef CHEMIN
