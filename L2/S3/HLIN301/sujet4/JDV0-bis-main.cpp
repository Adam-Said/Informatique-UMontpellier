#include <iostream>
using namespace std;

#include "JDV0-bis.h"

int main(int argc, char** argv){

  JeuDeLaVie JDV;
  JDV.parseOptions(argc,argv);
  JDV.run(4);

  return 0;
}

