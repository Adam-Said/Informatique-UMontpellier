#include <iostream>
#include <fstream>
#include <string>

using namespace std;

int main(int argc, char** argv) {

  if (argc < 2) {
    cerr << "usage: " << argv[0] << " <Fichier> [<Fichier> ...]" << endl;
    return 1;
  }
  ofstream fich(argv[1]);

  if (!fich.is_open()) {
    cerr << "Le fichier " << argv[1] << " n'a pas pu être ouvert." << endl;
    return 1;
  }

  while (!cin.eof()) {
    std::string  s;
    getline(cin,s);    
    if (!cin.eof() || !s.empty()) {
      fich << s <<endl;
      cout << s <<endl;
    }
  }

  fich.close();

  for (int i = 2; i < argc; i++) {
    ifstream fich1(argv[1]); 
    ofstream fich2(argv[i]);
    if (!fich1.is_open()) {
      cerr << "Le fichier " << argv[1] << " n'a pas pu être ouvert." << endl;
      return 2;
    }
    if (!fich2.is_open()) {
      cerr << "Le fichier " << argv[i] << " n'a pas pu être ouvert." << endl;
      return 2;
    }
    
    while (!fich1.eof()) {
      std::string  s;
      getline(fich1,s);    
      if (!fich1.eof() || !s.empty()) {
	fich2<< s;
      }
    }
    fich1.close();
    fich2.close();
  }

  return 0;
}

