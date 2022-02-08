// Définition des types Liste et Tableaux
// NE PAS MODIFIER CE FICHIER

#include <iostream>
#include <array>
#include <list>
#include <vector>
#include <cmath>
using namespace std;

#include<cassert>


template<typename T> list<T> liVide()
{
    list<T> l;
    return l;
}

template<typename T> bool estVide(list<T> l)
{
    return l.empty();
}

template<typename T> T tete(list<T> l)
{
    assert( !estVide(l));
    return *(l.begin());
}

template<typename T> list<T> queue(list<T> l)
{
    assert( !estVide(l)) ;
    l.pop_front();
    return l;
}

template<typename T> list<T> cons(T v,list<T> l)
{
    l.push_front(v);
    return l;
}

template<typename T> ostream& operator << (ostream& flux, list<T> l)
{
  flux << "( ";
  for (auto it=l.begin(); it !=l.end(); ++it )
    {
      flux<<*it<<" ";
    }
  flux << ")";
  return flux;
}

list<int> liste(list<int> li)
{
    return li;
}

template<typename T> int taille(vector<T> ta)
{
    return ta.size();
}

vector<int> tableau(vector<int> tab)
{
    return tab;
}


template<typename T> ostream& operator << (ostream& flux, vector<T> l)
{
    flux << "[ ";
    for (auto it=l.begin(); it !=l.end(); ++it )
    {
        flux<<*it<<" ";
    }
    flux << "]";
    return flux;
}

void ecrPoly(vector<int> po)
{
    bool prem;
    prem=true;
    for (size_t i=po.size()-1; i>0; i--)
    {
        if (po[i]!=0)
        {
            if (prem)
            {
                prem = false;
                if (po[i]!=1)
                {
                    if (po[i]!=-1) cout << po[i];
                    else cout << "-";
                }
            }
            else
            {
                if (po[i]>0) cout << " + ";
                else cout << " - ";
                if (abs(po[i]) !=1) cout << abs(po[i]);
            }
            cout << " X";
            if (i>1) cout<<"^"<<i<<" ";
        }
    }
    if (prem) cout <<po[0];
    else
    {
        if (po[0]!=0)
        {
            if (po[0]>0) cout << " + ";
            else cout << " - ";
            cout << abs(po[0]);

        }
    }
    cout<< endl;
    return ;
}



