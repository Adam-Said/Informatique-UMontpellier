#include <iostream>
using namespace std;

#include "JDV0.h"

int main(int argc, char** argv){

  JeuDeLaVie JDV;
  if (argc != 2) {std::cerr << "usage " << argv[0] << " config_file" << endl; return 1;}

  JDV.loadConfig(argv[1]);
  JDV.run(8);

  return 0;
}

