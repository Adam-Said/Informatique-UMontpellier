#include "population-vivante.h"

int main(int argc, char** argv){
  PopulationVivante JDV(20);
  JDV.init(8);
  JDV.print();
  JDV=JDV.next();
  JDV.print();
  
  return 0;
}
 
