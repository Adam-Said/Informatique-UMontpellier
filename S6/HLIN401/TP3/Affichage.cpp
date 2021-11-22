#include <cstdlib>
#include <iostream>
#include <fstream>
#include <string>

#include "SetCover.h"
#include "Affichage.h"

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
  for (int i=0; i<n;i++) \
    output << "<text x=\"" << maisons[i].x+5 << "\" y=\"" << maisons[i].y+5 << "\">" << i << "</text>" << endl; \
  output << endl; \
  output << "</svg>" << endl; \
  output.close();

#define ARETES \
  for (int i=0; i<n;i++) \
    for (int j=i+1; j<n; j++) \
      if (G[i][j]) \
        output << "<line x1=\"" << maisons[i].x << "\" y1=\"" << maisons[i].y << "\" " \
               << "x2=\"" << maisons[j].x << "\" y2=\"" << maisons[j].y << "\" " \
               << "stroke=\"darkgray\"/>" << endl;

void affichageMaisons(int n, coord* maisons, string nom, int w, int h)
// Cree le fichier Maisons.svg qui affiche les maisons uniquement.
{
  DEBUT(w, h)
  for (int i=0;i<n;i++)
    output << "<circle cx=\"" << maisons[i].x << "\" cy=\"" << maisons[i].y << "\" "
           << "r=\"4\" fill=\"black\" />" << endl;
  FIN
}

void affichageGraphe(int n, coord* maisons, bool** G, string nom, int w, int h)
{
  DEBUT(w, h)
  ARETES
  for (int i=0;i<n;i++)
    output << "<circle cx=\"" << maisons[i].x << "\" cy=\"" << maisons[i].y << "\" "
           << "r=\"4\" fill=\"black\" />" << endl;
  FIN
}

void affichageEmetteurs(int n, coord* maisons, bool** G, bool* emetteurs, string nom, int w, int h, int d)
// Cree le fichier Emetteurs.svg qui affiche les maisons et les emetteurs (en rouge) avec leur couverture.
{                        
  DEBUT(w, h)
  // Disques de couverture
  for (int i=0; i<n;i++)
    if (emetteurs[i])
      output << "<circle cx=\"" << maisons[i].x << "\" cy=\"" << maisons[i].y << "\" "
           << "r=\"" << d << "\" fill=\"tan\"/>" << endl;
  ARETES
  // Frontières des disques
  for (int i=0; i<n;i++)
    if (emetteurs[i])
      output << "<circle cx=\"" << maisons[i].x << "\" cy=\"" << maisons[i].y << "\" "
           << "r=\"" << d << "\" fill=\"none\" stroke=\"black\" stroke-dasharray=\"10,10\"/>" << endl;
  // Maisons
  for (int i=0;i<n;i++)
      output << "<circle cx=\"" << maisons[i].x << "\" cy=\"" << maisons[i].y << "\" "
           << "r=\"4\" fill=\"" << (emetteurs[i]?"red":"black") << "\" />" << endl;
  FIN
}

#undef FIN
#undef DEBUT
