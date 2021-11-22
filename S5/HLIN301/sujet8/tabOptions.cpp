#include "tabOptions.h"

using namespace std;

TabOptions::TabOptions(): opts() {}

TabOptions &TabOptions::operator+=(const Option &o) {
  bool found = (*this)(o.getName(), false) != (size_t) -1;
  if (!found) {
    found = (*this)(o.getShortcut(), false) != (size_t) -1;
  }
  if (found) {
    cerr << "Avertissement: L'identifiant " << (int) o << " est déjà utilisé."
	 << endl
	 << "               Nouvelle option non ajoutée." << endl;
  } else {
    opts.push_back(o);
    opts.sort();
  }
  return *this;
}

void TabOptions::print(ostream &os) const {
  os << "Options :" << endl;
  for (size_t i = 0; i < opts.size(); i++) {
    os << opts[i];
  }
}

bool operator<(const Option &o1, const Option &o2) {
  return o1.getName() < o2.getName();
}

bool operator>(const Option &o1, const Option &o2) {
  return o2 < o1;
}

bool operator<=(const Option &o1, const Option &o2) {
  return !(o2 < o1);
}

bool operator>=(const Option &o1, const Option &o2) {
  return !(o1 < o2);
}

bool operator==(const Option &o1, const Option &o2) {
  return (o1 <= o2) && (o1 >= o2);
}

bool operator!=(const Option &o1, const Option &o2) {
  return !(o1 == o2);
}

size_t TabOptions::operator()(const string &opt, const bool warn) const {
  bool found = false;
  size_t i = 0;
  while (!found && (i < opts.size())) {
    found = (opts[i].getName() == opt) || (opts[i].getShortcut() == opt);
    i++;
  }
  if (warn && !found) {
    cerr << "Avertissement: L'option '" << opt << "' n'a pas été trouvée."
	 << endl;
  }
  return found ? i - 1 : -1;
}

int TabOptions::getOptionId(const std::string &opt) const {
  size_t i = (*this)(opt, true);
  return (i != (size_t) -1 ? opts[i] : -1);
}

bool TabOptions::optionHasArgument(const std::string &opt) const {
  size_t i = (*this)(opt, true);
  return (i != (size_t) -1 
	  ? ((opts[i].getType() != Option::AUCUN)
	     && (opts[i].getType() != Option::NB_TYPES)) : false);
}

Option::Type TabOptions::optionArgumentType(const std::string &opt) const {
  size_t i = (*this)(opt, true);
  return (i != (size_t) -1 ? opts[i].getType() : Option::NB_TYPES);
}

ostream &operator<<(ostream &os, const TabOptions &t) {
  t.print(os);
  return os;
}
