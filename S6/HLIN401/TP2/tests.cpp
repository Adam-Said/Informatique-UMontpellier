#include <iostream>
#include <fstream>
#include "Tas.h"

#define XSCALE 12
#define YSCALE 25 
#define RADIUS 10
#define LINE(x1, y1, x2, y2) \
  svg << "<line x1=\"" << (x1)*XSCALE << "\" y1=\"" << (y1)*YSCALE << "\" " \
      << "x2=\"" << (x2)*XSCALE << "\" y2=\"" << (y2)*YSCALE \
      << "\" stroke=\"black\"/>" << endl
#define NOEUD(x,y,v) \
    svg << "<circle cx=\"" << (x)*XSCALE << "\" cy=\"" << (y)*YSCALE << "\" r=\"" << RADIUS << "\" " \
        << "fill=\"#90a5c3\" stroke=\"#204a87\"/>" << endl \
        << "<text text-anchor=\"middle\" dy=\"" << YSCALE/5 << "\" x=\"" << (x)*XSCALE \
        << "\" y=\"" << (y)*YSCALE << "\">" << (v) << "</text>" << endl
  
using namespace std;

void dessiner(int n, int* T, string nom)
{
  int h = 0, v = n;
  for(;v;v>>=1) h++;

  int l = -(1<<(h-1)), w = 1<<h;

  ofstream svg;
  svg.open(nom + ".svg", ios::out);
  svg << "<?xml version=\"1.0\" encoding=\"utf-8\"?>" << endl
      << "<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" viewBox=\"" 
      << XSCALE*l << " -" << YSCALE/2 << "  " << XSCALE*w << " " << YSCALE*h << "\">" << endl
      << "<rect width=\"100%\" height=\"100%\" fill=\"white\" x=\"" 
      << XSCALE*l << "\" y=\"-" << YSCALE/2 << "\"/>" << endl;

  int x=0, y=0;
  for (int i = 0; i < n; i++)
  {
    if (2*i+1 < n) LINE(x,y,x-(1<<(h-2)),y+1); // vers filsG 
    if (2*i+2 < n) LINE(x,y,x+(1<<(h-2)),y+1); // vers filsD
    NOEUD(x,y,T[i]);

    if (!((i+1)&(i+2))) // passage à la ligne (i+2 est une puissance de 2)
    {
      y++; h--;
      x = -x-(1<<(h-1));
    }
    else x += (1<<h);
  }

  
  svg << "</svg>" << endl;
  svg.close();
}

void affichage(int n, int* T)
{
  dessiner(n, T, "arbre");
  cout << "arbre.svg mis à jour" << endl
       << "Tableau T : ";
  afficher(n, T);
}

void question1(int n, int* T)
{
  cout << "Tableau T : ";
  afficher(n, T);
}
  
void question2(int n, int* T)
{
  cout << "Tableau T est tas max : " << boolalpha << estTasMax(n, T) << endl;
  cout << "Tableau T est tas min : " << boolalpha << estTasMin(n, T) << endl;
}

void question3(int n, int* T)
{
  tableauManuel(n, T);
  affichage(n, T);
}

void question4(int n, int* T)
{
  int m, M;
  cout << "Valeur minimale : ";
  cin >> m;
  cout << "Valeur maximale : ";
  cin >> M;
  tableauAleatoire(n, T, m, M);
  affichage(n, T);
}

void question5(int n, int* T)
{
  int i;
  cout << "Choisir une case à entasser : ";
  cin >> i;
  entasser(n, T, i);
  affichage(n, T);
}

void question6(int n, int* T)
{
  tas(n, T);
  affichage(n, T);
}

void question7(int n, int* T)
{
  int *Ttrie = trier(n, T);
  dessiner(n, Ttrie, "arbreTrie");
  cout << "Résultat dessiné dans arbreTrie.svg" << endl
       << "Tableau Ttrie : ";
  afficher(n, Ttrie);
}

void question8(int n, int* T)
{
  trierSurPlace(n, T);
  affichage(n, T);
}


void question(int q, int n, int* T)
{
  switch(q)
  {
    case 1: question1(n, T); break;
    case 2: question2(n, T); break;
    case 3: question3(n, T); break;
    case 4: question4(n, T); break;
    case 5: question5(n, T); break;
    case 6: question6(n, T); break;
    case 7: question7(n, T); break;
    case 8: question8(n, T); break;
  }
}

int main()
{

  srand(time(NULL));
  int* T1 = new int[10]{7,12,4,8,10,2,1,1,3,9};
  int* T2 = new int[10]{9,7,8,6,4,0,2,3,5,1};

  int c,q;

  cout << "Choisir un type de tableau :" << endl
       << "    1. tableau test fixé" << endl
       << "    2. tas test fixé" << endl
       << "    n. Tableau de taille n rempli de 0 (n > 2)" << endl
       << "Entrer la valeur choisie (0 pour sortir) : ";
  cin >> c;

  int* T;
  int n;

  switch (c)
  {
    case 0: return 0;
    case 1: T = T1; n = 10; break;
    case 2: T = T2; n = 10; break;
    default: 
      n = c;
      T = new int[n];
      for (int i=0; i<n; i++) T[i] = 0;
      break;
  }

  dessiner(n, T, "arbre");
  cout << endl << "Tableau de taille " << n << " dessiné comme arbre semi-complet dans arbre.svg" << endl << endl;

  cout << "Liste des questions :" << endl
       << "    1. Afficher le tableau" << endl
       << "    2. Tester si le tableau est un tas" << endl
       << "    3. Remplir le tableau à la main" << endl
       << "    4. Remplir le tableau aléatoirement" << endl
       << "    5. Entasser un élément" << endl
       << "    6. Transformer le tableau en tas" << endl
       << "    7. Trier le tableau" << endl
       << "    8. Trier le tableau sur place" << endl;

  while (1)
  {
    cout << endl << "Choisir une question (≤ 0 ou > 8 pour sortir) : ";
    cin >> q;
    
    if (q <= 0 or q > 8) return 0;

    question(q, n, T);
  }
}

#undef XSCALE
#undef YSCALE
#undef RADIUS
#undef LINE
#undef NOEUD
