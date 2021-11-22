#ifndef __GENERIC_SORT_H
#define __GENERIC_SORT_H

template<typename T>
bool estEgal(const T& a,const T& b)
{ return a==b;}
template<typename T>
bool estAvant(const T& a,const T& b)
{ return a<b;}
template<typename T>
bool estApres(const T& a,const T& b)
{ return a>b;}
template<>
bool estEgal(const Cellule& a,const Cellule& b)
{ return a.estEquivalente(b);}
template<>
bool estAvant (const Cellule& a,const Cellule& b)
{ return a.estAvant(b);}
template<>
bool estApres(const Cellule& a,const Cellule& b)
{ return a.estApres(b);}



template<typename VECT, typename C>
size_t rechercheDichotomique(const VECT &T, const C& c) {
  size_t deb = 0, fin = T.size();
  size_t res = (size_t) -1;

  while ((res == (size_t) -1) && (deb < fin)) {
    size_t mid = (deb + fin) / 2;
    if (estEgal(c,T.at(mid))) {
      res = mid;
    } else {
      if (estAvant(c,T.at(mid))) {
	fin = mid;
      } else {
	deb = mid + 1;
      }
    }
  }
  return res;
}
template<typename T>
void echanger(T &a, T &b) {
  T tmp = a;
  a = b;
  b = tmp;
}

template<typename VECT>
void triBulle(VECT &T) {
  bool modif = true;
  size_t n = T.size();
  while (modif && n--) {
    modif = false;
    for (size_t i = 0; i < n; i++) {
      if (estAvant(T.at(i+1),T.at(i))) {
	echanger(T.at(i), T.at(i+1));
	modif = true;
      }
    }
  }
}

template<typename VECT>
void triRapide_aux(VECT &T, size_t deb, size_t fin) {
  if (deb >= fin) return;
  size_t d = deb, f = fin-1, pivot;
  pivot = (deb + fin) / 2;
  while (d < f) {
    while ((d < pivot) && !estApres(T.at(d),T.at(pivot))) {
      d++;
    }
    while ((pivot < f) && !estAvant(T.at(f),T.at(pivot))) {
      f--;
    }
    echanger(T.at(d), T.at(f));
    if (pivot == d) {
      pivot = f++;
    } else {
      if (pivot == f) {
	pivot = d--;
      }
    }
    d++;
    f--;
  }
  triRapide_aux(T, deb, pivot);
  triRapide_aux(T, pivot + 1, fin);
}

template<typename VECT>
void triRapide(VECT &T) {
  triRapide_aux(T, 0, T.size());
}

#define Pere(i) (i ? (i - 1) / 2 : -1)
#define FilsG(i) (2 * i + 1)
#define FilsD(i) (2 * i + 2)
#define EstRacine(i) (!i)
#define EstFeuille(i, n) (i > (n / 2))

template<typename VECT>
void entasser(VECT &T, size_t i, size_t n) {
  size_t max_indice = i,
    fg = FilsG(i),
    fd = FilsD(i);
  if ((fg < n) && estAvant(T.at(max_indice),T.at(fg))) {
    max_indice = fg;
  }
  if ((fd < n) && estAvant(T.at(max_indice),T.at(fd))) {
    max_indice = fd;
  }
  if (i != max_indice) {
    echanger(T.at(i), T.at(max_indice));
    entasser(T, max_indice, n);
  }
}
template<typename VECT>
void initTas(VECT &T) {
  for (size_t i = T.size() / 2 - 1; i != (size_t) -1; i--) {
    entasser(T, i, T.size());
  }
}
template<typename VECT>
void triParTas(VECT &T) {
  initTas(T);
  for (size_t i = T.size() - 1; i != (size_t) -1; i--) {
    echanger(T.at(0), T.at(i));
    entasser(T, 0, i);
  }
}

#endif
