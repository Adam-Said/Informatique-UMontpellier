#include "JDV0-bis.h"
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
  while(end>beg-1 && (s[end]==' ' || s[beg]=='\t' )) end--;
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
    //cout<<"reading line ("<<num_ligne<<") -> "<<line<<endl;
    if (!input.eof()) {
      if (findCleVal(line,cle,valeur))
	TraiteOption(cle,valeur,num_ligne);
    }
    num_ligne++;
  }
  input.close();
}  

void JeuDeLaVie::run(size_t n) {
  POP.print();
  for(size_t i=0;i<n;i++){
     POP=POP.next();
     POP.print();
  }
}


JeuDeLaVie::JeuDeLaVie() : POP(8,0.25) {

  opts.addOption(Option(HELP_ID, "--help", "-h",
                        "Affiche l'aide du programme", Option::AUCUN));
  opts.addOption(Option(VERSION_ID, "--version", "-v",
		        "Affiche la version du programme", Option::AUCUN));
  opts.addOption(Option(DIMENSION_ID, "--dimension", "-N",
		        "Initialise une matrice carrée de la dimension spécifiée",
		        Option::ENTIER));
  opts.addOption(Option(PROBABILITY_ID, "--probability", "-p",
		        "Probabilité d'une cellule d'être en vie au démarrage",
		        Option::REEL));
  opts.addOption(Option(CONFIG_ID, "--config", "-f",
		        "Charge la configuration initiale du jeu "
		        "à partir du fichier passé en paramètre",
		        Option::CHAINE));
}


void JeuDeLaVie::parseOptions(int argc, char** argv){
  bool opt_error = false;
  int i = 1;
  while (!opt_error && i < argc) {
    int x = opts.getOptionId(argv[i]);
    switch (x) {
    case HELP_ID:
      cout << "usage " << argv[0] << " [Options]" << endl;
      opts.print(); return;      
    case VERSION_ID:
      cout << "Programme " << argv[0] << " version 0.0.0" << endl;
      return;
    case DIMENSION_ID:
      POP.setDimension(atoi(argv[++i]));
      break;
    case PROBABILITY_ID:
      POP.setProbability(atof(argv[++i]));	 
      break;
    case CONFIG_ID:
      loadConfig(argv[++i]);
      break;
    default:
      if (opts.optionHasArgument(argv[i])) {
	cout << "L'option " << argv[i] << " a été trouvée.";
	cout << " Elle attend un argument de type "
	     << Type2String(opts.optionArgumentType(argv[i]));
	cout << " => " << (++i < argc ? argv[i] : "Erreur");
      } else {
	cout << "Cette option n'a pas été reconnue."; 
	opt_error = true;
      }
      cout << endl; 
    }
    i++;
  }
  if (opt_error) {
    cout << "Usage : " << argv[0] << " [Options]" << endl;
    opts.print();
    terminate();
  }
}
