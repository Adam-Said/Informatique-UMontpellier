#include <iostream>
#include <exception>
#include "tabOptions.h"

using namespace std;

TabOptions::TabOptions(): opts(), nb_opts(0) {}

void TabOptions::addOption(const Option &o) {
  if (nb_opts == NMAX_OPTS) {
   cerr << "Erreur: Impossible d'ajouter une nouvelle option." << endl
	<< "        Nombre maximum d'option atteint"<< " (" << NMAX_OPTS << ")."
	<< endl;
   terminate();   
  }
    
  bool found = (getOptionIndex(o.getName()) !=  -1) || (getOptionIndex(o.getShortcut()) !=  -1);
  
  if (found) {
    cerr << "Avertissement: L'identifiant " << o.getId() << " est déjà utilisé."
	 << endl;
  } else {
    opts[nb_opts] = o;
    nb_opts++;    
  }
}

void TabOptions::print() const {
  cout << "Options :" << endl;
  for (size_t i = 0; i < nb_opts; i++) {
    opts[i].print();
  }
}

int TabOptions::getOptionIndex(const string &opt) const {
  bool found = false;
  size_t i = 0;
  while (!found && (i < nb_opts)) {
    found = ((opts[i].getName() == opt) || (opts[i].getShortcut() == opt));
    i++;
  }
  return found ? i - 1 : -1;
}
// opt doit etre une option valide
int TabOptions::getOptionId(const std::string &opt) const {
  int i = getOptionIndex(opt);
  return (i>=0 ? opts[i].getId():-1);
}
// opt doit etre une option valide
bool TabOptions::optionHasArgument(const std::string &opt) const {
  size_t i = getOptionIndex(opt);
  return (opts[i].getType() != Option::AUCUN);
}
// opt doit etre une option valide
Option::Type TabOptions::optionArgumentType(const std::string &opt) const {
  size_t i = getOptionIndex(opt);
  return opts[i].getType();
}
