#include <iostream>
#include <vector>
#include "myvector.h"
#include "print-vector.h"
using namespace std;
 
int main(int argc,char** argv){
  MyVector<int>     T1;
  MyVector<double>  T2;
  MyVector<Cellule> T3;
  
  for (size_t i=1;i<10;i++){
    T1.push_back(i);
    T3.push_back(Cellule(true,i,i));
    T2.push_back(1./i);
  }
  write(cout,T1);
  write(cout,T2);
  write(cout,T3);
  return 0;
}
