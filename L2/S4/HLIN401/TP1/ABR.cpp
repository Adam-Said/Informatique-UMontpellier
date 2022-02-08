#include <cstdlib>
#include <iostream>
#include <unistd.h>
#include <string>
#include "ABR.h"

using namespace std;

//------------------------------------------------------
// Inserer un noeud z dans l'arbre A
//------------------------------------------------------

noeud* ABR::inserer(int k)
{
  noeud* x = racine;
  noeud* p = NULL;
  while (x != NULL) {
    p = x;
    if(k < x->val) {
      x = x->filsG;
    }
    else {
      x = x->filsD;
    }
  }
  noeud* n = new noeud(k);
  n->pere = p;
  if(p == NULL) {
    racine = n;
  }
  else if (k < p->val) {
    p->filsG = n;
  }
  else {
    p->filsD = n;
  }
  return n;
}

//------------------------------------------------------
// Parcours infixe
//------------------------------------------------------

void ABR::parcoursInfixe(noeud* x)
{
  if (x != NULL) {
    parcoursInfixe(x->filsG);
    cout << x->val <<" ; ";
    parcoursInfixe(x->filsD);
  }
}

//------------------------------------------------------
// Noeud de valeur minimale dans l'arbre
//------------------------------------------------------

noeud* ABR::minimum(noeud* x)
{
  noeud* node = x;

  if (x->filsG != NULL) {
    noeud* minG = minimum(x->filsG);
    if(minG->val < node->val) node = minG;
  }
  if (x->filsD != NULL) {
    noeud* minD = minimum(x->filsD);
    if(minD->val < node->val) node = minD;
  }


  return node;
}

//------------------------------------------------------
// Recherche d'un noeud de valeur k
//------------------------------------------------------

noeud* ABR::rechercher(int k)
{
  noeud* rac = racine;
  while (rac != NULL && rac->val != k) {
    if (rac->val > k) rac = rac->filsG;
    else rac = rac->filsD;
  }
  return rac;
}



//------------------------------------------------------
// Recherche du successeur du noeud x
//------------------------------------------------------

noeud* ABR::successeur(noeud *x)
{
  noeud* tmp = NULL;
  int valeur = x->val;
  if (x->filsD == NULL) {
    while (x->pere->val < valeur && x->pere->pere != NULL) {
      x = x->pere;
    }
    tmp = (x->pere);
    if (tmp->val < valeur) {
      tmp = NULL;
    }
  }

  else {
    x = (x->filsD);
    while (x->filsG != NULL) {
      x = x->filsG;
    }
    tmp = x;
  }
  return tmp;
}


//------------------------------------------------------
// Suppression d'un noeud
//------------------------------------------------------

void ABR::supprimer(noeud* z) {
  if (z->filsG == NULL) remplacer(z,z->filsD);
  else if (z->filsD == NULL) remplacer(z,z->filsG);
  else {
    noeud* node = successeur(z);
    remplacer(node,node->filsD);
    node->filsG = z->filsG;
    node->filsD = z->filsD;
    z->filsG = NULL;
    z->filsD = NULL;
    if (node->filsG != NULL) (node->filsG)->pere = node;
    if (node->filsD != NULL) (node->filsD)->pere = node;
    remplacer(z,node);
  }
  delete z;
}


//------------------------------------------------------
// Rotations
//------------------------------------------------------

void ABR::rotationGauche(noeud* z)
{
  // À compléter
}

void ABR::rotationDroite(noeud* z)
{
  // À compléter
}
