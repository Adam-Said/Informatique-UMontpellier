#include <iostream>
#include <string>
using namespace std;
#include "cellule.h"


// Question 1
void q1() {
  Cellule Pop3[3][3]={ Cellule(false,0,0),
		       Cellule(true ,0,1),
		       Cellule(false,0,2),
		       Cellule(true, 1,0),
		       Cellule(false,1,1),
		       Cellule(true, 1,2),
		       Cellule(false,2,0),
		       Cellule(true, 2,1),
		       Cellule(false,2,2)};   
  Pop3[0][0] = Pop3[0][0];
}


// Question 3
void q3() {
  Cellule Pop3[3][3]={ Cellule(false,0,0),
		       Cellule(true ,0,1),
		       Cellule(false,0,2),
		       Cellule(true, 1,0),
		       Cellule(false,1,1),
		       Cellule(true, 1,2),
		       Cellule(false,2,0),
		       Cellule(true, 2,1),
		       Cellule(false,2,2)} ;  

  size_t i,j;
  std::cout << "Quelle cellule voulez-vous modifier ? ";
  std::cin >> i >> j;
  Pop3[i][j].setVivante(!Pop3[i][j].getVivante());


  for (size_t i = 0 ; i < 3 ; i++) {
    for (size_t j = 0 ;j < 3 ; j++) {
      Pop3[i][j].print();
    }
  }
}

void q4() {
  const Cellule Pop3[3][3]={ Cellule(false,0,0),
			      Cellule(true ,0,1),
			      Cellule(false,0,2),
			      Cellule(true, 1,0),
			      Cellule(false,1,1),
			      Cellule(true, 1,2),
			      Cellule(false,2,0),
			      Cellule(true, 2,1),
			      Cellule(false,2,2)};     
  size_t i,j;
  std::cout << "Quelle cellule voulez-vous modifier ? ";
  std::cin >> i >> j;
  //Pop3[i][j].Vivante(!Pop3[i][j].estVivante());
    
  for (size_t i=0;i<3;i++) {
    for (size_t j=0;j<3;j++) {
      Pop3[i][j].print();
    }
  }
}

int main(int argc, char** argv) {

  q1();
  q3();
  q4();
  
  return 0;
}
