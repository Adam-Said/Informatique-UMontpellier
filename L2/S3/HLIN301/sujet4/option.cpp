#include <iostream>
#include "option.h"
#include <string>

using namespace std;

///////////////////
// Classe Option //
///////////////////

Option::Option(): id(-1), nom(), raccourci(), description(), type(AUCUN) {}

Option::Option(int _id, const string &_nom, const string &_raccourci,
	       const string &_desc, Option::Type _type):
  id(_id), nom(_nom), raccourci(_raccourci), description(_desc), type(_type) {}

int Option::getId() const { return id; }
string Option::getName() const { return nom; }
string Option::getShortcut() const { return raccourci; }
string Option::getDescription() const { return description; }
Option::Type Option::getType() const { return type; }

void Option::setId(int _id) { this->id = _id; }
void Option::setName(const string &name) { nom = name; }
void Option::setShortcut(const string &shortcut) { raccourci = shortcut; }
void Option::setDescription(const string &desc) { description = desc; }
void Option::setType(Option::Type t) { type = t; }

void Option::print() const {
  cout << nom << " (" << raccourci << ") " << Type2String(type)
       << "\t" << description << endl;
}

string Type2String(Option::Type t) {
  string tmp;
  switch (t) {
  case Option::AUCUN:    tmp = ""; break;
  case Option::BOOLEEN:  tmp = "<booléen>"; break;
  case Option::ENTIER:   tmp = "<entier>"; break;
  case Option::REEL:     tmp = "<réel>"; break;
  case Option::CHAR:     tmp = "<caractère>"; break;
  case Option::CHAINE:   tmp = "<chaîne>"; break;
  }
  return tmp;
}
