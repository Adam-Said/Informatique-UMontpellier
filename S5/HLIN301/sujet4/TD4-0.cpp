#include <iostream>

using namespace std;

int main(int argc, char** argv) {

  cout << "Le programme a été exécuté avec argc = " << argc << " et :" << endl;
  for (int i = 0; i < argc; i++) {
     cout << "- argv[" << i << "] = '" << argv[i] << "'" << endl;
   }
   cout << "Au revoir." << endl;
  return 0;

}
