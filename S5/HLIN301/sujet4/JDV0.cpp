#include "JDV0.h"
#include <cstdlib>
#include <iostream>
#include <fstream>
#include <exception>
using namespace std;


void JeuDeLaVie::nettoie(std::string &s){
  size_t pos=s.find_first_of("#");
  s=s.substr(0,pos);  
  int beg=0,end=s.size()-1;
  while(beg<end+1 && (s[beg]==' ' || s[beg]=='\t' )) beg++;
  while(end>beg-1 && (s[end]==' ' || s[end]=='\t' )) end--;
  s=s.substr(beg,end-beg+1);
}


bool JeuDeLaVie::findCleVal(std::string &s, std::string &s1,std::string &s2){
  nettoie(s);
  if (s==string("")) return false;
  size_t pos=s.find_first_of(":");
  if (pos==string::npos) {
    cerr << "Le fichier est mal formé" << endl;
    terminate();
  }
  s1=s.substr(0,pos);
  s2=s.substr(pos+1);
  nettoie(s1);
  nettoie(s2);
  //cerr<<"Found cle/val -> "<< s1<< " and "<<s2<<endl;
  return true;
}

void JeuDeLaVie::TraiteOption(const string &cle, const string &valeur, size_t num_ligne){
  if (cle == "Dimension") {
    POP.setDimension(atoi(valeur.c_str()));
    //POP.reset();
  }
  if (cle == "Probability") {
    POP.setProbability(atof(valeur.c_str()));
    //POP.reset();
  }
  if (cle == "Cell") { 
    size_t x, y;
    size_t pos = valeur.find_first_of("x, ");
    if (pos == string::npos || valeur[pos] == '\0') {
      cerr << "Le fichier est mal formé. Vérifiez la syntaxe de la ligne "<< num_ligne << endl;
    }
    else {
      x = atoi(valeur.substr(0, pos).c_str());
      y = atoi(valeur.substr(pos).c_str());
      POP.birth(x,y);    
    }
  }
}
  
JeuDeLaVie::JeuDeLaVie() : POP(8,0.25) {}
  
void JeuDeLaVie::loadConfig(std::string file){
  ifstream input(file.c_str());
  string cle, valeur;
  size_t num_ligne=0;

  if (!input.is_open()) {
    cerr << "Le fichier " << file << " n'a pas pu être ouvert." << endl;
    terminate();
  }
  string line;
  while (!input.eof()) {
    getline(input,line);
    if (!input.eof()) {
      if (findCleVal(line,cle,valeur))
	TraiteOption(cle,valeur,num_ligne);
    }
    num_ligne++;
  } 
  input.close();
}

#include <iostream>
#include <chrono>
#include <thread>

void JeuDeLaVie::run(size_t n) {
  POP.print();
  for(size_t i=0;i<n;i++){
    system("clear");
    POP=POP.next();
    POP.print();
    std::this_thread::sleep_for(std::chrono::seconds(1));

  }
}
