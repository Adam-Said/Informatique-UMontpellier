// Fichier dans lequel vous écrirez les expressions à évaluer
#include "definitionsFonctions.cpp"
#define EVAL(exp) cout << "Valeur de " << (#exp) <<" : "<< (exp) << endl


int main()
{
  srand(time(NULL));
  boolalpha(cout);

  cout << "\n=================================================================\n ";

  // Expressions à évaluer
  cout << "\n==== Prise en main =======\n";
  // commande pour évaluer l'expression 3+4
  // A vous :
  EVAL(moyenne(2.3 , 8)); 
  EVAL(max3(3,9 , 2.5));
  EVAL(triangleequi(5,5,5));
  EVAL(triangle(3,1,2));
  EVAL(trianglerect(5,4,5));
  EVAL(triangleiso(1,1,3));
  EVAL(ouexcl(true,false));
  cout << "\n=================================================================\n ";
  return 0;
}

