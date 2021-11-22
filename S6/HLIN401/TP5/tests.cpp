#include <iostream>
#include <fstream>
#include <string>
#include "edition.h"

string lecture(string nom, string dossier="covid19/")
{
  ifstream fichier;
  fichier.open(dossier+nom);
  string ligne, sortie;

  while (getline(fichier, ligne))
    sortie.append(ligne);

  fichier.close();
  return sortie;
}

int main()
{

  int** E;
  string s1, s2, aligne;
  int q, m, n, d;

  do
  {
    cout << "Entrer le numéro de question à tester (0 pour quitter) : ";
    cin >> q;

    switch (q)
    {
      case 1:
        cout << "Entrer la première chaîne de caractère : "; cin >> s1;
        cout << "Entrer la seconde chaîne de caractère : "; cin >> s2;

        m = s1.size(); n = s2.size();
        E = matriceDistances(s1, s2);
        cout << "Matrice des distances : " << endl;
        for (int i=0; i<=m; i++)
        {
          for (int j=0; j<=n; j++) cout << E[i][j] << " ";
          cout << endl;
        }
        cout << endl;
        for (int i=0; i<=m; i++) delete[] E[i];
        delete[] E;
        break;

      case 2:
        cout << "Entrer la première chaîne de caractère : "; cin >> s1;
        cout << "Entrer la seconde chaîne de caractère : "; cin >> s2;

        d = alignement(s1, s2);
        cout << "Distance entre les deux chaînes : " << d << endl;
        cout << "Alignement : " << s1 << endl
             << "             " << s2 << endl << endl;
        break;

      case 3:
        cout << "Entrer la première chaîne de caractère : "; cin >> s1;
        cout << "Entrer la seconde chaîne de caractère : "; cin >> s2;

        d = distanceEdition(s1, s2);
        cout << "Distance entre les deux chaînes : " << d << endl << endl;
        break;

      case 4:
        string noms[9] = {"","2019-nCoV_WH01.fa", "2019-nCoV_WH03.fa", "2019-nCoV_WH04.fa", 
                             "bat-SL-CoVZC45.fa", "bat-SL-CoVZXC21.fa", "bat-CoV-RaTG13.fa", 
                             "MERS-CoV.fa", "SARS-CoV.fa"};
        cout << "Liste des fichiers :" << endl;
        for (int f=1; f < 9; f++)
          cout << "  " << f << ". " << noms[f] << endl;
        int f1, f2;
        cout << "Premier choix : "; cin >> f1;
        cout << "Second choix : "; cin >> f2;

        s1 = lecture(noms[f1]);
        s2 = lecture(noms[f2]);
        m = s1.size(); n = s2.size();

        cout << "La séquence " << noms[f1] << " est de longueur " << m << "." << endl;
        cout << "La séquence " << noms[f2] << " est de longueur " << n << "." << endl;

        cout << "Calcul en cours... " << flush;

        clock_t t1, t2;
        t1 = clock();
        d = distanceEdition(s1, s2);
        t2 = clock();
        
        cout << "terminé en ";
        double t = (double)(t2-t1)/CLOCKS_PER_SEC;
        if (t > 1) cout << t << " s" << endl;
        else if (t > 0.001) cout << t*1000 << " ms" << endl;
        else cout << t*1000000 << " µs" << endl;
        
        cout << "Distance entre les deux génomes : " << d << "." << endl << endl;
    }

  } while(q > 0);
  return 0;
}

