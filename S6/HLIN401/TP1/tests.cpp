#include <cstdlib>
#include <iostream>
#include <sstream>
#include <unistd.h>
#include <string>
#include "ArbreBinaire.h"
#include "ABR.h"

using namespace std;

//--------------------------------
// Création d'arbres particuliers
//--------------------------------

ABR* arbreTest()
{
  ABR* arbre = new ABR();
  int T[15]={16,6,19,3,1,8,13,5,17,12,14,20,7,23,22};

  for(int i=0;i<15;i++){
    arbre->inserer(T[i]);
  }
  return arbre;
}

ABR* arbreAleatoire(int n)
{
  ABR* arbre = new ABR();
  for(int i=0; i < n; i++)
    arbre->inserer(1+(rand() % (2*n)));
  return arbre;
}

//--------------------------------
// Tests des questions
//--------------------------------

void question1(ABR* arbre) 
{
  int k;
  while(true)
  {
    cout << "Entrer une valeur à insérer dans l'arbre (0 pour quitter) : ";
    cin >> k;
    if (!k) break;
    
    noeud* z = arbre->inserer(k);
    arbre->affichage("arbre", z);
    cout << "Fichier arbre.svg mis à jour en insérant " << k << endl;
  }
}

void question2(ABR* arbre)
{
  cout << "Parcours infixe de l'arbre : ";
  arbre->parcoursInfixe();
  cout << endl;
  noeud* min=arbre->minimum();
  if(min!=NULL)
    cout << "Valeur minimale présente dans l'arbre: "<< min->val << endl;
  else
    cout<< "Arbre vide..." << endl;
  arbre->affichage("arbre", min);
  cout << "Fichier arbre.svg mis à jour en affichant " << min->val << endl;
}

void question3(ABR* arbre)
{
  int k;
  while (true) {
     cout << "Rentrer une valeur à rechercher (0 pour quitter) : ";
     cin >> k;
     if (!k) break;
     noeud* rech=arbre->rechercher(k);
     cout << "Recherche de la valeur " << k << " : ";
     if(rech) cout << "trouvée (vérification : " << (rech->val) << ")" << endl;
     else     cout << "non trouvée" << endl;
     arbre->affichage("arbre", rech);
     if (rech) cout << "Fichier arbre.svg mis à jour en affichant " << k << endl;
  }
}

void question4(ABR* arbre)
{
  int k;
  while (true) {
     cout << "Rentrer la valeur dont on doit trouver le successeur (0 pour quitter) : ";
     cin >> k;
     if (!k) break;
     noeud* rech=arbre->rechercher(k);
     if (rech) {
       cout << "Successeur de la valeur " << k << " : ";
       noeud* s = arbre->successeur(rech);
       if (s) cout << s->val << endl;
       else cout << rech->val << " est la valeur max !"<<endl;
       arbre->affichage("arbre", s);
       if (s) cout << "Fichier arbre.svg mis à jour en affichant " << s->val << endl;
     } else cout << "Valeur "<< k <<" non présente..." << endl;
  }
}

void question5(ABR* arbre)
{
  int k3;
  while (true) {
     cout << "Rentrer la valeur du noeud à supprimer (0 pour quitter) : ";
     cin >> k3;
     if (!k3) break;
     noeud* rech = arbre->rechercher(k3);
     if(rech) {
       arbre->supprimer(rech);
       arbre->affichage("arbre");
       cout << "Fichier arbre.svg mis à jour en supprimant " << k3 << endl;
     } 
     else cout << "Valeur " << k3 << " non présente" << endl;
  }
}

void question6(ABR* arbre)
{
  int k;
  string r;
  while(true)
  {
    cout << "Rentrer la valeur du nœud sur lequel faire une rotation (0 pour quitter) : ";
    cin >> k;
    
    if (!k) break;
    noeud* z = arbre->rechercher(k);
    if (!z) 
    {
      cout << "Valeur " << k << " non trouvée" << endl;
      continue;
    }

    do {
      cout << "Choisir la rotation gauche ou droite [g/d] : ";
      cin >> r;
    } while (r != "g" && r != "d");

    if (r == "g")
    {
      arbre->rotationGauche(z);
      arbre->affichage("arbre");
      cout << "Fichier arbre.svg mis à jour en effectuant une rotation gauche sur " << k << endl;
    } 
    else 
    {
      arbre->rotationDroite(z);
      arbre->affichage("arbre");
      cout << "Fichier arbre.svg mis à jour en effectuant une rotation gauche sur " << k << endl;
    }
  }

}

void question(int q, ABR* arbre)
{
  switch(q)
  {
    case 1: question1(arbre); break;
    case 2: question2(arbre); break;
    case 3: question3(arbre); break;
    case 4: question4(arbre); break;
    case 5: question5(arbre); break;
    case 6: question6(arbre); break;
  }
}

//--------------------------------
// Main
//--------------------------------

int main()
{

  int c,q;

  cout << "Choisir un type d'arbre :" << endl
       << "    0. arbre vide" << endl
       << "    1. arbre test fixé" << endl
       << "    n. arbre aléatoire à n sommets (n > 1)" << endl
       << "Entrer la valeur choisie : ";
  cin >> c;

  ABR* arbre;
  if (c == 0) 
    arbre = new ABR();
  else if (c == 1) 
    arbre = arbreTest();
  else 
    arbre = arbreAleatoire(c);

  arbre->affichage("arbre");
  cout << endl << "Arbre dessiné dans le fichier arbre.svg" << endl << endl;

  cout << "Liste des questions :" << endl
       << "    1. Insertion d'éléments dans l'arbre" << endl
       << "    2. Parcours infixe et recherche de minimum" << endl
       << "    3. Recherche d'une valeur" << endl
       << "    4. Recherche du successeur" << endl
       << "    5. Suppression d'une valeur" << endl
       << "    6. Rotation gauche ou droite d'un nœud." << endl;

   while(1) {
    do {
       cout << endl << "Choisir une question (entre 1 et 6, 0 pour sortir) : ";
       cin >> q;
    } while (q < 0 || q > 6);

    if (q == 0) return 0;

    question(q,arbre);

  }

}
