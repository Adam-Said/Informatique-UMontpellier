#ifndef __ITV_H
#define __ITV_H

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
  void Afficher() const;
  double Longueur() const;
  bool Appartient(double v) const;

  // Nouvelles Méthodes
  bool estEgal(const Itv &I) const;
  bool estInclusStrictement(const Itv &I) const;
  bool estDisjoint(const Itv &I) const;
  bool estAccole(const Itv &I) const;
  bool estImbrique(const Itv &I) const;

  void Translate(double v);
};

void Translate(Itv &I, double v);
#endif
