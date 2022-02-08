#include <iostream>

class Itv {
 private:
  // Attributs
  double bi, bs;

 public:
  // Constructeurs
  Itv();
  Itv(double bi, double bs);

  // Accesseurs en lecture
  double getBorneInf() const;
  double getBorneSup() const;

  // Accesseurs en écriture
  void setBorneInf(double v);
  void setBorneSup(double v);

  // Autres Méthodes
  double operator()() const;
  bool operator|(double v) const;
  bool operator==(const Itv &I) const;
  bool operator!=(const Itv &I) const;
  bool operator<(const Itv &I) const;
  bool operator>(const Itv &I) const;
  bool operator<=(const Itv &I) const;
  bool operator>=(const Itv &I) const;

  Itv &operator+=(double v);
  Itv &operator-=(double v);
  Itv operator+(double v) const;
  Itv operator-(double v) const;
  Itv& operator++();
  const Itv operator++(int);
  Itv &operator--();
  const Itv operator--(int);
};

/* Itv &operator+=(Itv &I, double v); */
std::ostream &operator<<(std::ostream &os, const Itv &I);
std::istream &operator>>(std::istream &is, Itv &I);
