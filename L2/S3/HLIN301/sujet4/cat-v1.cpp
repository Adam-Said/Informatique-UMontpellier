#include <iostream>
#include <fstream>
#include <string>
using namespace std;

int main(int argc, char** argv) {

  if (argc != 2) {
    cerr << "usage: " << argv[0] << " <Fichier>" << endl;
    return 1;
  }
  ifstream fich(argv[1]);
  if (!fich.is_open()) {
    cerr << "Le fichier " << argv[1] << " n'a pas pu être ouvert." << endl;
    return 1;
  }
  while (!fich.eof()) {
    std::string  s;
    getline(fich,s);
    if (!fich.eof() || !s.empty()){
      cout<<s<<endl;
    }
  } 
  fich.close();
  return 0;
}





