#include <iostream>
using namespace std;
#include "Itv.h"

int main(int argc, char** argv) {
  Itv I1, I2(3, 1);

  cout << "I1.getBorneInf() : " << I1.getBorneInf() << endl;
  cout << "I1.getBorneSup() : " << I1.getBorneSup() << endl;
  cout << "Appel de I1.setBorneInf(3)" << endl; I1.setBorneInf(3);
  cout << "Appel de I1.setBorneSup(2)" << endl; I1.setBorneSup(2);
  cout << "I1.Afficher() : "; I1. Afficher(); cout << endl; 
  cout << "I2.Afficher() : "; I2. Afficher(); cout << endl;
  cout << "I1.Longueur() = " << I1.Longueur() << endl;
  cout << "I2.Longueur() = " << I2.Longueur() << endl;
  cout << "I1.Appartient(2.5) = " << I1.Appartient(2.5) << endl; 
  cout << "I2.Appartient(2.5) = " << I2.Appartient(2.5) << endl; 
  I2.setBorneSup(4);
  I2.setBorneInf(3);
  cout << "I2.Afficher() : "; I2. Afficher(); cout << endl;
  cout << "I2.Appartient(2.5) = " << I2.Appartient(2.5) << endl; 

  // Test des nouveautés 
  cout << "I1.Translate(1) = "; I1.Translate(1); I1.Afficher(); cout << endl;
  cout << "I1.estEgal(I2) = " << I1.estEgal(I2) << endl;
  cout << "I1.estInclusStrictement(I2) = " << I1.estInclusStrictement(I2) << endl;
  cout << "I1.estAccole(I2) = " << I1.estAccole(I2) << endl;
  cout << "Translate(I2, 1) = "; Translate(I2, 1); I2.Afficher(); cout << endl;
  cout << "I1.estDisjoint(I2) = " << I1.estDisjoint(I2) << endl;
  I1.setBorneSup(4.5); cout << "Maintenant I1 = "; I1.Afficher(); cout << endl;
  cout << "I1.estImbrique(I2) = " << I1.estImbrique(I2) << endl;
  return 0;
}
