#include <iostream>
#include "option.h"

using namespace std;

Option::Option(): id(-1), nom(), raccourci(), description(), type(AUCUN) {}
Option::Option(int id, const string &nom, const string &raccourci,
	       const string &desc, Option::Type type):
  id(id), nom(nom), raccourci(raccourci), description(desc), type(type) {}

int Option::getId() const { return id; }
string Option::getName() const { return nom; }
string Option::getShortcut() const { return raccourci; }
string Option::getDescription() const { return description; }
Option::Type Option::getType() const { return type; }

void Option::setId(int id) { this->id = id; }
void Option::setName(const string &name) { nom = name; }
void Option::setShortcut(const string &shortcut) { raccourci = shortcut; }
void Option::setDescription(const string &desc) { description = desc; }
void Option::setType(Option::Type t) { type = t; }

Option::operator int () const {
  return id;
}

ostream &operator<<(ostream &os, const Option &o) {
  os << "  " << o.getName() << "\t| " << o.getShortcut()
     << " " << Type2String(o.getType())
     << "\t" << o.getDescription() << endl;
  return os;
}

string Type2String(Option::Type t) {
  string tmp;
  switch (t) {
  case Option::AUCUN:    tmp = "\t"; break;
  case Option::BOOLEEN:  tmp = "<booléen>"; break;
  case Option::ENTIER:   tmp = "<entier>"; break;
  case Option::REEL:     tmp = "<réel>"; break;
  case Option::CHAR:     tmp = "<caractère>"; break;
  case Option::CHAINE:   tmp = "<chaîne>"; break;
  case Option::NB_TYPES: tmp = "<invalide>"; break;
  }
  return tmp;
}
