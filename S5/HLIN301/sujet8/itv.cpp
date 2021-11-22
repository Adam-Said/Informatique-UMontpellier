#include "itv.h"

using namespace std;

Itv::Itv():bi(0), bs(0) { }
Itv::Itv(double bi, double bs):bi(bi), bs(bs) {
  if (bi > bs) {
    cerr << "Avertissement: les valeurs fournies (bi=" << bi << ", bs = " << bs
	 << ") pour la création de l'intervalle ont été inversées." << endl;
    this->bi = bs;
    this->bs = bi;
  }
}

double  Itv::getBorneInf() const { return bi; }
double  Itv::getBorneSup() const { return bs; }

void  Itv::setBorneInf(double v) {
  if (v > bs) {
    cerr << "Avertissement: la valeur de la borne supérieure (bs = " << bs
	 << ") a été mise à jour automatiquement suite à la mise à jour de la "
	 << "borne inférieure (bi passant de " << bi << " a " << v << ")."
	 << endl;
    bs = v;
  }
  bi = v;
}
void  Itv::setBorneSup(double v) {
  if (bi > v) {
    cerr << "Avertissement: la valeur de la borne inférieure (bi = " << bi
	 << ") a été mise à jour automatiquement suite à la mise à jour de la "
	 << "borne supérieure (bs passant de " << bs << " a " << v << ")."
	 << endl;
    bi = v;
  }
  bs = v;
}

double  Itv::operator()() const { return bs - bi; }

bool  Itv::operator|(double v) const { return (v >= bi) && (v <= bs); }

bool Itv::operator==(const Itv &I) const {
  return (bi == I.bi) && (bs == I.bs);
}

bool Itv::operator!=(const Itv &I) const {
  return !operator==(I);
}

bool Itv::operator<(const Itv &I) const {
  return ((bi > I.bi) && (bs <= I.bs)) || ((bi == I.bi) && (bs < I.bs));
}

bool Itv::operator>(const Itv &I) const {
  return (bi > I.bs) || (bs < I.bi);
}

bool Itv::operator<=(const Itv &I) const {
  return (bi == I.bs) || (bs == I.bi);
}

bool Itv::operator>=(const Itv &I) const {
  return (operator!=(I) && !operator<(I)
	  && !operator>(I) && !operator<=(I));
}

Itv& Itv::operator+=(double v) {
  bi += v;
  bs += v;
  return *this;
}

Itv &Itv::operator-=(double v) {
  bi -= v;
  bs -= v;
  return *this;
}

/*Itv &operator+=(Itv &I, double v){
    return I.operator+=(v);
  }*/

Itv Itv::operator+(double v) const {
  Itv I(*this);
  return I += v;
}

Itv Itv::operator-(double v) const {
  return operator+(-v);
}

Itv &Itv::operator++() {
  return operator+=(1);
}

const Itv Itv::operator++(int) {
  Itv tmp(*this);
  operator+=(1);
  return tmp;
}

Itv &Itv::operator--() {
  return operator-=(1);
}

const Itv Itv::operator--(int) {
  Itv tmp(*this);
  operator-=(1);
  return tmp;
}

ostream &operator<<(ostream &os, const Itv &I) {
  os << "[" << I.getBorneInf() << ", " << I.getBorneSup() << "]";
  return os;
}

istream &operator>>(istream &is, Itv &I) {
  double a, b;
  is >> a >> b;
  I.setBorneSup(b);
  I.setBorneInf(a);
  return is;
}

int main(int argc, char** argv) {
  Itv I1, I2(3, 1);

  cout << "I1.getBorneInf() : " << I1.getBorneInf() << endl;
  cout << "I1.getBorneSup() : " << I1.getBorneSup() << endl;
  cout << "Appel de I1.setBorneInf(3)" << endl; I1.setBorneInf(3);
  cout << "Appel de I1.setBorneSup(2)" << endl; I1.setBorneSup(2);
  cout << "Affichage I1 : " << I1 << endl; 
  cout << "Affichage I2 : " << I2 << endl;
  cout << "I1.Longueur() = " << I1() << endl;
  cout << "I2.Longueur() = " << I2() << endl;
  cout << "Appartenance (I1 | 2.5) = " << (I1 | 2.5) << endl; 
  cout << "Appartenance (I2 | 2.5) = " << (I2 | 2.5) << endl; 
  // cin >> I2;
  I2.setBorneSup(4);
  I2.setBorneInf(3);
  cout << "Affichage I2 : " << I2 << endl;
  cout << "Appartenance (I2 | 2.5) = " << (I2 | 2.5) << endl; 
  cout << "Translation (I1 += 1) = " << (I1 += 1) << endl;
  cout << "Translation (I1 -= 1) = " << (I1 -= 1) << endl;
  cout << "Pré-incrémentation (++I1) = " << ++I1 << endl;
  cout << "Post-incrémentation (I1++) = " << I1++ << endl;
  cout << "          et maintenant I1 = " << I1 << endl;
  cout << "Pré-décrémentation (--I1) = " << --I1 << endl;
  cout << "Post-décrémentation (I1--) = " << I1-- << endl;
  cout << "          et maintenant I1 = " << I1 << endl;
  cout << "Addition (I1 = I1 + 2) = " << (I1 = I1 + 2) << endl;
  cout << "Soustraction (I1 = I1 - 1) = " << (I1 = I1 - 1) << endl;
  cout << "Égalité (I1 == I2) = " << (I1 == I2) << endl;
  cout << "Différence (I1 != I2) = " << (I1 != I2) << endl;
  cout << "Inclusion stricte (I1 < I2) = " << (I1 < I2) << endl;
  cout << "Accolement (I1 <= I2) = " << (I1 <= I2) << endl;
  cout << "Translate(I2, 1) = " << (I2 += 1) << endl;
  cout << "Disjonction (I1 > I2) = " << (I1 > I2) << endl;
  I1.setBorneSup(4.5); cout << "Maintenant I1 = " << I1 << endl;
  cout << "Imbrication (I1 >= I2) = " << (I1 >= I2) << endl;
  return 0;
}
