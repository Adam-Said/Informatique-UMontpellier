#include <cstdlib>
#include <iostream>
#include <string>

#include "SetCover.h"
#include "Affichage.h"


coord ex5[] = {{176,113},{94,11},{174,42},{97,156},{48,138}};
coord ex10[] = {{104,34},{11,183},{169,214},{101,52},{137,141},{53,239},{260,158},{201,126},{189,110},{156,225}};
coord ex15[] = {{210,14},{40,267},{159,148},{130,92},{40,60},{247,137},{262,67},{93,218},{205,210},{27,130},{118,10},{219,258},{269,102},{195,198},{249,137}};
coord ex20[] = {{89,55},{160,231},{51,298},{120,337},{110,99},{101,261},{327,205},{304,85},{58,80},{268,329},{137,190},{185,67},{187,194},{27,240},{248,280},{75,327},{325,157},{150,36},{115,260},{295,216}};
coord ex30[] = {{88,338},{403,36},{161,132},{248,371},{57,168},{404,106},{113,51},{270,20},{121,69},{127,204},{256,273},{201,86},{22,11},{128,279},{197,297},{405,275},{177,398},{253,280},{72,91},{242,120},{201,188},{168,256},{229,380},{266,340},{39,335},{87,237},{151,278},{314,115},{279,384},{384,18}};
coord ex50[] = {{322,372},{140,194},{184,511},{445,352},{333,417},{198,145},{330,235},{51,408},{186,387},{24,367},{301,98},{402,538},{229,377},{183,269},{461,192},{338,465},{247,468},{110,113},{122,545},{455,445},{104,335},{273,424},{20,314},{282,196},{383,528},{245,135},{309,98},{123,528},{157,528},{247,300},{403,267},{216,100},{186,548},{203,298},{235,340},{425,329},{125,380},{436,367},{376,400},{245,210},{379,480},{27,138},{28,372},{348,408},{350,45},{158,435},{303,56},{525,171},{54,410},{151,279}};

int dim(int n)
{
  int k = 1;
  while (k*k < n) k++;
  return 70*k;
}

int question1(coord*& maisons)
{
  int n;
  cout << "Entrer le nombre de maisons : ";
  cin >> n;
  int l = dim(n), h = dim(n);
  maisons = maisonsAleatoires(n, l, h);
  affichageMaisons(n, maisons, "Maisons", l, h);
  cout << "Fichier Maisons.svg mis à jour" << endl;
  return n;
}

void question2(coord* maisons, int dcouv)
{
  cout << "Entrer le numéro de la première maison : ";
  int i, j;
  cin >> i;
  cout << "Entrer le numéro de la première maison : ";
  cin >> j;
  cout << "Les deux maisons " << i << " et " << j << " sont à distance " << ((couvre(i,j,maisons,dcouv))?"inférieure ou égale":"strictement supérieure") << " à " << dcouv << endl;

}

bool** question3(int n, coord* maisons)
{
  bool** G = graphe(n, maisons);
  affichageGraphe(n, maisons, G, "Maisons", dim(n), dim(n));
  cout << "Graphe construit, fichier Maisons.svg mis à jour" << endl;
  return G;
}

void question4(int n, coord* maisons, bool** G)
{
  bool* emetteurs = new bool[n];
  bool* couvertes = new bool[n];
  for (int i=0; i < n; i++) emetteurs[i] = false;
  for (int i=0; i < n; i++) couvertes[i] = false;
  
  int i = prochaineMaison(n, G, couvertes);
  cout << "Premier émetteur sur la maison " << i << endl;

}

void question5(int n, coord* maisons, bool** G)
{
  bool* emetteurs = new bool[n];
  clock_t t1, t2; 
  t1 = clock();
  int nbEmetteurs = placementGlouton(n, G, emetteurs);
  t2 = clock();
  affichageEmetteurs(n, maisons, G, emetteurs, "Emetteurs", dim(n), dim(n));
  cout << "Nombre d'émetteurs placés : "<< nbEmetteurs;
  cout << "; fichier Emetteurs.svg mis à jour" << endl;
  cout << "Temps : " << (double)(t2-t1) * 1000 / CLOCKS_PER_SEC << " ms" << endl;
}

void question6(int n, coord* maisons, bool** G)
{
  bool* emetteursOpt = new bool[n];
  clock_t t1, t2;
  t1 = clock();
  int nbEmetteursOpt = placementOptimal(n, G, emetteursOpt);
  t2 = clock();
  affichageEmetteurs(n, maisons, G, emetteursOpt, "EmetteursOpt",dim(n),dim(n));
  cout << "Nombre d'émetteurs optimal : " << nbEmetteursOpt;
  cout << "; fichier EmetteursOpt.svg mis à jour" << endl;
  cout << "Temps : " << (double)(t2-t1) * 1000 / CLOCKS_PER_SEC << " ms" << endl;

}

int main(int argc, char** argv)
{
  time_t graine = time(NULL);
  srand(time(NULL));

  coord* maisons;
  bool** G;

  int n;
  string c;
  cout << "Choix des maisons :" << endl
       << "    0. Exemple test fixé" << endl
       << "    1. Réutiliser une graîne" << endl
       << "    n. Choix aléatoire (question 1) avec n maisons" << endl
       << "Choix (entier) : ";
  cin >> c;
  if (c == "n")
  {
    cout << "Entrer le nombre de maisons : ";
    cin >> n;
  }
  else n = stoi(c);
  
  if (n == 0) 
  { 
    do
    {
      cout << "Choisir le nombre de maisons parmi 5, 10, 15, 20, 30 ou 50 : ";
      cin >> n;
      switch (n)
      {
        case 5: maisons = ex5; break;
        case 10: maisons = ex10; break;
        case 15: maisons = ex15; break;
        case 20: maisons = ex20; break;
        case 30: maisons = ex30; break;
        case 50: maisons = ex5; break;
        default: n = 0;
      }
    } while (n == 0);
  }
  else 
  {
    if (n == 1)
    {
      cout << "Entrer la graîne : ";
      cin >> graine;
      cout << "Entrer le nombre de maisons : ";
      cin >> n;
      srand(graine);
    }
    maisons = maisonsAleatoires(n, dim(n), dim(n));
    cout << "Graîne utilisée : " << graine << endl;
  }

  int l = dim(n), h = dim(n);
  affichageMaisons(n, maisons, "Maisons", l, h);
  cout << "Fichier Maisons.svg mis à jour" << endl;

  cout << endl
       << "Liste des questions :" << endl
       << "    1. Tirer aléatoirement des nouvelles maisons" << endl
       << "    2. Test de la fonction couvre" << endl
       << "    3. Construction du graphe" << endl
       << "    4. Prochaine maison" << endl
       << "    5. Placement d'émetteurs" << endl
       << "    6. Placement optimal (attention au nombre de sommets !)" << endl;

  do
  {
    cout << endl << "Choisir une question (0 pour sortir) : ";
    int v = 0;
    cin >> v;

    switch(v)
    {
      case 0: return 0;
      case 1: n = question1(maisons); break;
      case 2: question2(maisons, 100); break;
      case 3: G = question3(n, maisons); break;
      case 4: question4(n, maisons, G); break;
      case 5: question5(n, maisons, G); break;
      case 6: question6(n, maisons, G); break;
      default: break;
    }
  } while(true);

  return 0;
}
