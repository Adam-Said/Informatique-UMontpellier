#include <iostream>
#include <string>

using namespace std;

int** matriceDistances(string s1, string s2)
{
  int sM = s1.size();
  int sN = s2.size();
  int** mat = new int* [sM+1];

  for (int i = 0; i < sM+1; i++)
  {
    mat[i] = new int [sN+1];
  }
  for (int i = 0; i < sM+1; i++)
  {
    mat[i][0] = i;
  }
  for (int i = 0; i < sN+1; i++)
  {
    mat[0][i] = i;
  }
  for (int i = 1; i < sM+1; i++)
  {
    for (int j = 1; j < sN+1; j++)
    {
      int tmp = 0;
      if (s1.at(i-1) != s2.at(j-1)) tmp = 1;
      mat[i][j] = min(mat[i-1][j]+1,min(mat[i][j-1]+1,mat[i-1][j-1]+tmp));
    }
  }  
  return mat; 
}

int alignement(string& s1, string& s2)
{
  int i = s1.size();
  int j = s2.size();

  int** mat = matriceDistances(s1, s2);
  while (i > 0 && j > 0)
  {
    if (mat[i][j] == mat[i-1][j-1] && s1.at(i-1) == s2.at(j-1))
    {
      i--;
      j--;
    }
    else if (mat[i][j] == mat[i-1][j-1] +1)
    {
      i--;
      j--;
    }
    else if (mat[i][j] == mat[i-1][j] +1)
    {
      s2.insert(j, "_");
      i--;
    }
    else if (mat[i][j] == mat[i][j-1] +1)
    {
      s1.insert(i, "_");
      j--;
    }
    cout << i << ' ' << j << endl;
  }
  while (j > 0)
  {
    s1.insert(0, "_");
    j--;
  }
  while (i > 0)
  {
    s2.insert(0, "_");
    i--;
  }

  int taille = max(s1.size(), s2.size());
  int dif = 0;
  for (int tmp = 0; tmp < taille; tmp++)
  {
    if (s1.at(tmp) != s2.at(tmp)) dif++;
    
  }

  for (int i = 0; i < s1.size(); ++i) {
    delete[] mat[i];
  }
  delete[] mat;

  return dif; 
}

int distanceEdition(string s1, string s2)
{
  int sM = s1.size();
  int sN = s2.size();
  int* P = new int[sN+1];
  int* C = new int[sN+1];

  for (int j = 0; j < sN+1; j++)
  {
    P[j] = j;
  }

  int e = 0;
  for (int i = 1; i < sM+1; i++)
  {
    C[0] = i;
    for (int j = 1; j < sN+1; j++)
    {
      if (s1.at(i-1) == s2.at(j-1)) e = 0;
      else e = 1;
      C[j] = min(P[j]+1, min(C[j-1]+1, P[j-1]+e));
    }
    for (int j = 0; j < sN+1; j++)
    {
      P[j] = C[j];
    }
  }
  int res = C[sN];

  delete[] P;
  delete[] C;
  return res; 
}