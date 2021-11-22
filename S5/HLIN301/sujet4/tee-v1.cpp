#include <iostream>
#include <string>
#include <fstream>

using namespace std;

int main(int argc, char** argv) {

  if (argc != 2) {
    cerr << "usage: " << argv[0] << " <Fichier>" << endl;
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
    if (!cin.eof()|| !s.empty()) {
      fich << s << endl;
      cout << s << endl;
    }
  }

  fich.close();
  return 0;
}
