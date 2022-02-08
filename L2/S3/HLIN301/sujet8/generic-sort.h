#ifndef __GENERIC_SORT_H__
#define __GENERIC_SORT_H__

template<typename VECT, typename C>
  size_t rechercheDichotomique(const VECT &T, const C& c);

template<typename T>
void echanger(T &a, T &b);

template<typename VECT>
void triBulle(VECT &T);

template<typename VECT>
void triRapide(VECT &T);

template<typename VECT>
void triParTas(VECT &T);

#include "generic-sort.tcc"

#endif
