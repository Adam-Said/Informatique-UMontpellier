#include<iostream>
#include<fstream>
#include<sstream>
#include<cmath>
#include "Structures.h"
#include "Affichage.h"
#include "Dijkstra.h"

using namespace std;

void TEMPS(clock_t t1, clock_t t2) 
{
  double t = (double)(t2-t1)/CLOCKS_PER_SEC;
  if (t > 1) cout << t << " s" << endl;
  else if (t > 0.001) cout << t*1000 << " ms" << endl;
  else cout << t*1000000 << " µs" << endl;
}

int dim(int n)
{
  int k = 1;
  while (k*k < n) k++;
  return 25*k;
}

void coins(int n, coord* S, int& i, int& j)
{
  i = 0; j = 0;
  int di = S[i].x*S[i].x + S[i].x*S[i].y;
  int dj = di;
  for (int t = 1; t < n; t++)
  {
    int d = S[t].x*S[t].x + S[t].y*S[t].y;
    if (d < di) { i = t; di = d; }
    else if (d > dj) { j = t; dj = d; }
  }
}

void centre(int n, coord* S, int l, int h, int& i, int& j)
{
  coord c = {l/2, h/2};
  i = 0; 
  int di = (S[i].x-c.x)*(S[i].x-c.x) + (S[i].y-c.y)*(S[i].y-c.y);
  for (int t=1; t < n; t++)
  {
    int dt = (S[t].x-c.x)*(S[t].x-c.x) + (S[t].y-c.y)*(S[t].y-c.y);
    if (dt < di) { i = t; di = dt; }
  }
  j = rand() % n;
}

template<typename E>
void afficher(int n, E* T)
{
  int m = (n > 15)?10:n;
  cout << "[";
  for(int i=0;i<m-1;i++) cout << T[i] << ",";
  if (m) cout << T[m-1];
  if (m < n) cout << ",... (+ " << n-m << " éléments)";
  cout << "]" << endl;
}

int main()
{
  srand(time(NULL));
  int q, n, l, h, s, t, nobs;
  int x1, y1, x2, y2;
  listeAdj* G;
  listeAdj C;
  listeAdj* A;
  coord* O;
  float dmax;
  coord* S;
  float* D;
  int* P;
  string c;
  clock_t t1, t2;
  do
  {
    cout << "Question à tester (0 pour sortir) : ";
    cin >> q;

    G = NULL; S = NULL; A = NULL; C = NULL; O = NULL; nobs = 0; 

    switch(q)
    {
      case 0: return 0;
      case 1:
        cout << "Entrer le nombre de sommets : ";
        cin >> n;
        l = dim(n); h = dim(n);
        S = sommetsAleatoires(n, l, h);
        affichageSommets(n, S, "graphe", l, h);
        cout << "Sommets dessinés dans graphe.svg" << endl;
        break;

      case 2:
        cout << "Entrer l'abscisse et l'ordonnée du premier sommet : ";
        cin >> x1 >> y1;
        cout << "Entrer l'abscisse et l'ordonnée du second sommet : ";
        cin >> x2 >> y2;
        S = new coord[2];
        S[0] = {x1,y1}; S[1] = {x2,y2};
        cout << "Les sommets de coordonnées (" << x1 << "," << y1 << ") et (" 
             << x2 << "," << y2 << ") sont à distance " << distance(S, 0, 1) << endl;
        break;

      case 3:
        cout << "Entrer le nombre de sommets : ";
        cin >> n;
        l = dim(n); h = dim(n);
        cout << "Entrer la distance maximale (recommandé : env. " << 1.5*l/sqrt(n) << ") : ";
        cin >> dmax;
        S = sommetsAleatoires(n, l, h);
        G = graphe(n, S, dmax);
        affichageGraphe(n, S, G, "graphe", l, h);
        cout << "Graphe dessiné dans graphe.svg" << endl;
        break;
    
      default:
        cout << "Choix du graphe :" << endl
             << "    1. Graphe n°1 (5 sommets)" << endl
             << "    2. Graphe n°2 (10 sommets)" << endl
             << "    3. Graphe aléatoire avec obstacles" << endl
             << "    4. Grille aléatoire" << endl
             << "    n. Graphe aléatoire à n sommets (question 3)" << endl
             << "Choix (entier) : ";
        cin >> c;
        if (c == "1") 
        {
          lecture("Graphe5.txt", G, S, n, l, h);
          s = 0; t = 4; nobs = 0;
        }
        else if (c == "2")
        {
          lecture("Graphe10.txt", G, S, n, l, h);
          s = 0; t = 9; nobs = 0;
        }
        else if (c == "3")
        {
          cout << "Nombre de sommets : ";
          cin >> n;
          l = dim(n);
          h = dim(n);
          dmax = 3*l/sqrt(n);
          nobs = l/50; 
          int lobs = int(2*dmax);
          grapheObstacles(n, l, h, nobs, lobs, dmax, S, G, O);
        }
        else if (c == "4")
        {
          cout << "Dimensions de la grille (deux entiers) : ";
          cin >> l >> h;
          cout << "Proportion d'arêtes (0 ≤ p ≤ 1) : ";
          float p; cin >> p;
          n = l*h;
          grille(l, h, p, S, G);
          l = 20*l+20; h = 20*h+20;
        }
        else
        {
          if (c == "n")
          {
            cout << "Nombre de sommets : ";
            cin >> n;
          }
          else n = stoi(c);
          l = dim(n); h = dim(n); dmax = 1.5*l/sqrt(n); 
          S = sommetsAleatoires(n, l, h);
          G = graphe(n, S, dmax);
        }

        if (c != "1" and c != "2")
        {
          cout << "Choix des sommets de départ et d'arrivée :" << endl
               << "  1. Sommets aléatoires" << endl
               << "  2. Départ au centre, arrivée aléatoire" << endl
               << "  3. Sommets dans deux coins opposés" << endl
               << "Choix (entier) : ";
          cin >> s;
          switch(s)
          {
            case 2: centre(n, S, l, h, s, t); break;
            case 3: coins(n, S, s, t); break;
            default: s = rand() % n; t = rand() % n; break;
          }
        }
        
        affichageGraphe(n, S, G, "graphe", l, h, NULL, s, t, NULL, nobs, O);
        cout << "Graphe dessiné dans graphe.svg" << endl << endl;

        if (q == 7) 
        {
          cout << "Appliquer l'algorithme de Dijkstra (question 4) ? [o/n] ";
          cin >> c;
        }

        if (q != 7 or c[0] == 'o' or c[0] == 'O' or c[0] == '4')
        {
          t1 = clock();
          dijkstra(n, G, s, D, P);
          t2 = clock();
          cout << "  Algorithme de Dijkstra appliqué en temps : "; TEMPS(t1,t2);
          cout << "  Distances : "; afficher<float>(n, D);
          cout << "  Prédecesseurs : "; afficher<int>(n, P);
          cout << "  Longueur du plus court chemin entre les sommets " << s << " et " << t << " : " << D[t] << endl;
          cout << endl;

          if (q != 5)
          {
            cout << "Reconstruire le chemin (question 5) ? [o/n] ";
            cin >> c;
          }
          if (q == 5 or c[0] == 'o' or c[0] == 'O' or c[0] == '5')
          {
            C = chemin(n, G, P, s, t);
            if (C) 
            {
              cout << "  Chemin : ";
              listeAdj C2 = C;
              while (C2->suivant)
              {
                cout << C2->sommet << "→";
                C2 = C2->suivant;
              }
              cout << C2->sommet << endl;

              affichageGraphe(n, S, G, "graphe", l, h, A, s, t, C, nobs, O);
              cout << "  Chemin représenté dans graphe.svg." << endl;
            }
            else cout << "  Aucun chemin (distance infinie)" << endl;
            cout << endl;
          }
          
          if (q != 6)
          {
            cout << "Construire l'arbre des plus courts chemins (question 6) ? [o/n] ";
            cin >> c;
          }
          if (q == 6 or c[0] == 'o' or c[0] == 'O' or c[0] == '6')
          {
            A = arbre(n, G, P, s);
            affichageGraphe(n, S, G, "graphe", l, h, A, s, t, C, nobs, O);
            cout << "  Arbre représenté dans graphe.svg." << endl;
            cout << endl;
          }
        }

        if (q != 7)
        {
          cout << "Appliquer l'algorithme A* (question 7) ? [o/n] "; 
          cin >> c;
        }
        if (q == 7 or c[0] == 'o' or c[0] == 'O' or c[0] == '7')
        {
            t1 = clock();
            a_etoile(n, G, S, s, t, D, P);
            t2 = clock();
            cout << "  Algorithme A* appliqué en temps : "; TEMPS(t1,t2);
            cout << "  Longueur du plus court chemin entre les sommets " << s << " et " << t << " : " << D[t] << endl;

            C = chemin(n, G, P, s, t);
            A = arbre(n, G, P, s);
            affichageGraphe(n, S, G, "graphe", l, h, A, s, t, C, nobs, O);
            cout << "  Chemin et arbre représentés dans graphe.svg" << endl;
        }
    }

    cout << endl;

    if (G) delete[] G;
    if (S) delete[] S;
    if (A) delete[] A;
    if (C) delete[] C;
    if (O) delete[] O;

  } while (c != "X");
  return 0;
}
