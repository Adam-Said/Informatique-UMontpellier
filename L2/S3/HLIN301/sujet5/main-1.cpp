#include <iostream>
#include <cstdlib>
#include "tableau-int.h"

using namespace std;

int main(int argc, char** argv){
  if (argc !=2) {cerr<<"Usage: "<<argv[0]<<" [tab dim]"<<endl; return 1;}

  size_t n = atoi(argv[1]);
  TableauInt T(n);
  for(size_t i=0;i<n;i++){
    T.at(i)=i+1;
  }
  write(cout,T);
  
  return 0;
}

void f() {
  size_t n = 0;
  TableauIntExtensible T;
  for(size_t i=0;i<n;i++)
    { T.push_back(i+1);}
}
