#include <iostream>
#include "Tas.h"

using namespace std;


// ====================
//  TAS ET TRI PAR TAS
// ====================

void afficher(int n, int* T)
{
  cout << "["<< T[0];

  for (int i = 1; i < n; i++) {
    cout << "," << T[i];}
  cout << "]";

} 

bool estTasMax(int n, int* T)
{
  for (int i = 1; i < n; i++)
  {
    float y = (i-1)/2;
    int c = (int) y;
    if (T[c] < T[i])
    {
      return false;
    }
  }
  return true; 
}

bool estTasMin(int n, int* T)
{
  for (int i = 1; i < n; i++)
  {
    float y = (i-1)/2;
    int c = (int) y;
    if (T[c] > T[i])
    {
      return false;
    }
  }
  return true;
}

void tableauManuel(int n, int* T)
{
  for (int i = 0; i < n; i++)
  {
    cin >> T[i];
  }
}

void tableauAleatoire(int n, int* T, int m, int M)
{
  for (int i = 0; i < n; i++)
  {
    T[i] = rand()%(M - m+1) + m;
  }
}

void entasser(int n, int* T, int i)
{
  while ((i*2 + 1) < n) {
    int m = i;
    int g = (i*2 + 1);
    int d = (i*2 + 2);
    if (T[g] > T[m]) m = g;
    if (d < n && T[d] > T[m]) m = d;
    if (m != i ) {
      int tmp = T[i];
      T[i] = T[m];
      T[m] = tmp;
      i = m;
    }
    else i = n;
  }
}

void tas(int n, int* T)
{
  for (int i = 0; i <= (n/2)-1; i++)
  {
    entasser(n, T, i);
  }
}

int* trier(int n, int* T)
{
  int* S = new int[n];
  tas(n,T);
  for (int i = n-1; i >= 0; i--)
  {
    S[i] = T[0];
    T[0] = T[n-1];
    T[n-1] = S[i];
    n = n-1;
    entasser(n, T, 0);
  }
  return S; 
}

void trierSurPlace(int n, int* T)
{
  tas(n,T);
  for (int i = n-1 ; i >= 0; i--)
  {
    int x = T[0];
    T[0] = T[n-1];
    T[n-1] = x;
    n--;
    entasser(n, T, 0);
  }
}