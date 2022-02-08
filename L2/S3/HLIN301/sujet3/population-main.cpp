#include "population.h"

int main(int argc, char** argv){
  Population JDV;
  JDV.init(8);  
  JDV.print();
  JDV=JDV.next();
  JDV.print();
  
  return 0;
}
