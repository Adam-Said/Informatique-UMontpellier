#include "cellule.h"
#include <iostream>
#include <string>
using namespace std;

Cellule::Cellule(): age(0), x(0), y(0), couleur(NOIR) {
}

Cellule::Cellule(bool etat, unsigned int x, unsigned int y):
  age(etat ? 1 : 0), x(x), y(y), couleur(etat ? BLEU : NOIR) {
}

bool Cellule::getVivante() const {
  return age;
}

unsigned int Cellule::getX() const {
  return x;
}

unsigned int Cellule::getY() const {
  return y;
}

Cellule::Couleur Cellule::getCouleur() const {
  return couleur;
}

// Accesseurs en écriture
void Cellule::setX(unsigned int x) {
  this->x = x;
}

void Cellule::setY(unsigned int y) {
  this->y = y;
}

bool Cellule::estVoisine(const Cellule &c) const {
  return age &&
    ((x - c.x) * (x - c.x) + (y - c.y) * (y - c.y) <= 2);
}

void Cellule::print() const {
  std::cout<<"("<<x<<","<<y<<") - > "<<Couleur2String(couleur)<<std::endl;
}

void Cellule::setVivante(bool etat) {
  if (etat) {
    couleur = age++ ? VERT : BLEU;
  } else {
    age = 0;
    couleur = NOIR;
  }
}

void Cellule::doitMourir() {
  if (age) { // La cellule est vivante et va mourir
    couleur = (couleur == BLEU ? JAUNE : ROUGE);
  }
}

bool CelluleEstDeLaCouleur(const Cellule &cellule, Cellule::Couleur couleur) {
  return (cellule.getCouleur() == couleur);
}

string Couleur2String(Cellule::Couleur c) {
  string res;
  switch (c) {
  case Cellule::NOIR:
    res = "\033[1;30mX\033[0m"; //"noire";
    break;
  case Cellule::BLEU:
    res = "\033[1;34mX\033[0m";//"bleue";
    break;
  case Cellule::VERT:
    res = "\033[1;32mX\033[0m";//"verte";
    break;
  case Cellule::ROUGE:
    res = "\033[1;31mX\033[0m";//"rouge";
    break;
  case Cellule::JAUNE:
    res = "\033[1;33mX\033[0m";//"jaune";
    break;
  default:
    res = "non définie.";
    cerr << "Erreur:" << __FILE__ << ":" << __FUNCTION__ << ":" << __LINE__
	 << ":Couleur non définie. Les couleurs possibles sont:" << endl;
    for (Cellule::Couleur i = Cellule::NOIR;
	 i != Cellule::NB_COULEURS;
	 i = (Cellule::Couleur) (((int) i)+1)) {
      cerr << "- " << Couleur2String(i) << endl;
    }
  }
  return res;
}


bool Cellule::estAvant(const Cellule &c) const {
  return (x < c.x) || ((x == c.x) && (y < c.y));
}
bool Cellule::estApres(const Cellule &c) const {
  return c.estAvant(*this);
}
bool Cellule::estEquivalente(const Cellule &c) const {
  return !estAvant(c) && !estApres(c);
}
bool Cellule::estDifferente(const Cellule &c) const {
  return !estEquivalente(c);
}
bool Cellule::estAvantOuEquivalente(const Cellule &c) const {
  return estAvant(c) || estEquivalente(c);
}
bool Cellule::estApresOuEquivalente(const Cellule &c) const {
  return estApres(c) || estEquivalente(c);
}

bool Cellule::operator<(const Cellule &c) const {
  return (x < c.x) || ((x == c.x) && (y < c.y));
}

bool Cellule::operator>(const Cellule &c) const {
  //return c.operator<(*this);
  return c<(*this);
}

bool Cellule::operator==(const Cellule &c) const {
  return !operator<(c) && !operator>(c);
}

bool Cellule::operator!=(const Cellule &c) const {
  return !operator==(c);
}

bool Cellule::operator<=(const Cellule &c) const {
  return operator<(c) || operator==(c);
}
bool Cellule::operator>=(const Cellule &c) const {
  return operator>(c) || operator==(c);
}

 
void echanger(Cellule &a, Cellule &b) {
  Cellule tmp = a;
  a = b;
  b = tmp;
}

  
void read_cell(std::istream& is, Cellule& C){
  unsigned int x,y,b;
  is>>x>>y>>b;
  C.setX(x);
  C.setY(y);
  C.setVivante(b);
}
void write_cell(std::ostream& os, const Cellule& C){
  os << C.getX() << "x" << C.getY();
}

 bool identique(const Cellule& C1,const Cellule& C2){
   return (C1.getX()==C2.getX()) && (C1.getY()==C2.getY()) && (C1.getVivante()==C2.getVivante()); 
 }


ostream &operator<<(ostream& os, const Cellule& C){
  write_cell(os,C);return os;
}

istream &operator>>(istream& is, Cellule &C){
  read_cell(is,C); return is;
}
