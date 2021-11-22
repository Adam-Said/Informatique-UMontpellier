#include <iostream>
using namespace std;

#include "myvector.h"
#include "myvector-cellule.h"
#include "cellule.h"

int main(int argc,char** argv){
  MyVector<int>     T1;
  MyVector<double>  T2;
  MyVector<Cellule> T3;
  for (size_t i=1;i<10;i++){
    T1.push_back(i);
    T2.push_back(1./i);
    T3.push_back(Cellule(true,i,i+1));
  }
  write(cout, T1);
  write(cout, T2);
  write(cout, T3);
  
  return 0;
}
