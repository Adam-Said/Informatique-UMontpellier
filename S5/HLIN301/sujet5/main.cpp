#include <fstream>
#include <iostream>
#include <string>
#include <cstdlib>
#include "population-vivante-v3.h"

int main(int argc, char** argv){

  if (argc!=3){
    std::cerr<<"Usage : "<<argv[0]<<" [filename], [nbr of evolution]"<<std::endl;
    return 1;
  }

  std::ifstream file(argv[1]);
  if (!file.is_open()){
    std::cerr<<"The file "<<argv[1]<<" could not be opened"<<std::endl;
    return 2;
  }

  PopulationVivante Pop(file);
  Pop.print();
  for(int i=0;i<atoi(argv[2]);i++){
    Pop=Pop.next();
    Pop.print();
  }

  std::string outfile(std::string(argv[1])+std::string("-")+std::string(argv[2]));
  std::ofstream out(outfile.c_str());
  if (!out.is_open()){
    std::cerr<<"The file "<<outfile<<" could not be created"<<std::endl;
    return 2;
  }
  
  Pop.print(out);
  
  return 0;
}
