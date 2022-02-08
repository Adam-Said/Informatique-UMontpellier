#include <cstdlib>
#include <iostream>
#include <unistd.h>
#include <string>
#include <fstream>
#include "ArbreBinaire.h"

#define UNIT 25
#define RADIUS 10

// Constructeur de nœud de valeur k
noeud::noeud(int k) {
  pere = NULL;
  filsG = NULL;
  filsD = NULL;
  val = k;
}

// Constructeur d'ArbreBinaire vide
ArbreBinaire::ArbreBinaire() {
  racine = NULL;
}

// Remplacement du nœud x par le nœud z dans l'ArbreBinaire
void ArbreBinaire::remplacer(noeud* x, noeud* z)
{
    noeud* p = x->pere;
    x->pere = NULL;
    if (!p) racine = z;
    else if (x == p->filsG) p->filsG = z;
    else p->filsD = z;
    if (z) z->pere = p;
}

// Calcul des coordonnées du nœud pour dessiner un arbre
void noeud::coord(float*& G, float*& D, int& h)
{
  float *fgG, *fgD, *fdG, *fdD;
  int hG = 0, hD = 0;
  
  if (filsG) filsG->coord(fgG, fgD, hG);
  if (filsD) filsD->coord(fdG, fdD, hD);

  x = 0.; y = 0.;

  float distance = 2.;
  if (filsG && filsD)
    for (int i=0; i < hG and i < hD; i++)
      if (distance < 2 + fgD[i] - fdG[i]) 
        distance = 2 + fgD[i] - fdG[i];
  
  if (filsG) filsG->majcoord(-distance/2.);
  if (filsD) filsD->majcoord(distance/2.);
  
  h = 1 + ((hG>hD)?hG:hD);
  G = new float[h];
  D = new float[h];
  G[0] = x;
  D[0] = y;

  for (int i=1; i < h; i++)
  {
    if (i <= hG) G[i] = fgG[i-1] - distance/2;
    else G[i] = fdG[i-1] + distance/2; 

    if (i <= hD) D[i] = fdD[i-1] + distance/2;
    else D[i] = fgD[i-1] - distance/2;
  }

  if (filsG)
  {
    delete [] fgG;
    delete [] fgD;
  }
  if (filsD)
  {
    delete [] fdG;
    delete [] fdD;
  }
  
}

// Utilitaire de mise à jour des coordonnées
void noeud::majcoord(float d)
{
  x += d;
  y += 1;
  if (filsG) filsG->majcoord(d);
  if (filsD) filsD->majcoord(d);
}

// Affichage du sous-arbre enraciné en le noeud, en mettant en valeur highlight
void noeud::affichage(ofstream& output, noeud* highlight)
{
  if (filsG) 
    output << "<line x1=\"" << x*UNIT << "\" y1=\"" << y*UNIT << "\" "
           << "x2=\"" << UNIT*filsG->x << "\" y2=\"" << UNIT*filsG->y
           << "\" stroke=\"black\"/>" << std::endl;
  if (filsD)
    output << "<line x1=\"" << x*UNIT << "\" y1=\"" << y*UNIT << "\" "
           << "x2=\"" << UNIT*filsD->x << "\" y2=\"" << UNIT*filsD->y
           << "\" stroke=\"black\"/>" << std::endl;

  output << "<circle cx=\"" << x*UNIT << "\" cy=\"" << y*UNIT << "\" r=\"" << RADIUS << "\" ";
  if (highlight==this) output << "fill=\"#a7cd83\" stroke=\"#4e9a06\"/>";
  else                 output << "fill=\"#90a5c3\" stroke=\"#204a87\"/>";
  output << std::endl; 
  output << "<text text-anchor=\"middle\" dy=\"" << UNIT/5 << "\" x=\"" << x*UNIT 
         << "\" y=\"" << y*UNIT << "\">" << val << "</text>" << std::endl;

  if (filsG) filsG->affichage(output, highlight);
  if (filsD) filsD->affichage(output, highlight);
}

// Affichage de l'ArbreBinaire, en mettant en valeur highlight
void ArbreBinaire::affichage(string nom, noeud* highlight)
{

  float g = -1. ,d = 1.;
  int h=0;
  if (racine) 
  {
    float *G, *D;
    racine->coord(G, D, h);
    for (int i=0; i < h; i++)
    {
      if (G[i] < g) g = G[i];
      if (D[i] > d) d = D[i];
    }

    delete [] G;
    delete [] D;
  }
  float w = UNIT*(d-g+2);
  h = UNIT*(h+1);
  
  ofstream output;
  output.open(nom + ".svg", ios::out);
  output << "<?xml version=\"1.0\" encoding=\"utf-8\"?>" << endl;
  output << "<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" viewBox=\"" 
          << UNIT*(g-1) << " -" << UNIT << "  " << w << " " << h << "\">" << endl;
  output << "<rect width=\"100%\" height=\"100%\" fill=\"white\" x=\"" << UNIT*(g-1) << "\" y=\"-" << UNIT << "\"/>" << endl;
  output << endl;  

  if (racine) racine->affichage(output, highlight);
  output << "</svg>" << endl;
  output.close();
}

#undef RADIUS
#undef UNIT
