#include <iostream>
#include <fstream>
#include <string>
using namespace std;

int main(int argc, char** argv) {

  if (argc < 2) {
    cerr << "usage: " << argv[0] << " <Fichier> [<Fichier> ...]" << endl;
    return 1;
  }
  for (int i = 1; i < argc; i++) {
    ifstream fich(argv[i]);

    if (!fich.is_open()) {
      cerr << "Le fichier " << argv[i] << " n'a pas pu être ouvert." << endl;
      return 1;
    }
    while (!fich.eof()) {
      std::string  s;
      getline(fich,s);
      if (!fich.eof()|| !s.empty()) {
	cout<<s<<endl;
      }      
    }
    fich.close();
  }
  return 0;
}
