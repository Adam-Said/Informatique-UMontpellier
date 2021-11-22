template<typename VECT, typename C>
size_t rechercheDichotomique(const VECT &T, const C& c) {
  size_t deb = 0, fin = T.size();
  size_t res = (size_t) -1;

  while ((res == (size_t) -1) && (deb < fin)) {
    size_t mid = (deb + fin) / 2;
    if (c == T[mid]) {
      res = mid;
    } else {
      if (c < T[mid]) {
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
      if (T[i+1] < T[i]) {
	echanger(T[i], T[i+1]);
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
    while ((d < pivot) && (T[d] <= T[pivot])) {
      d++;
    }
    while ((pivot < f) && (T[f] >= T[pivot])) {
      f--;
    }
    echanger(T[d], T[f]);
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

template<typename VECT>
void entasser(VECT &T, size_t i, size_t n) {
  size_t max_indice = i,
    fg = 2 * i + 1,
    fd = 2 * i + 2;
  if ((fg < n) && (T[max_indice] < T[fg])) {
    max_indice = fg;
  }
  if ((fd < n) && (T[max_indice] < T[fd])) {
    max_indice = fd;
  }
  if (i != max_indice) {
    echanger(T[i], T[max_indice]);
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
    echanger(T[0], T[i]);
    entasser(T, 0, i);
  }
}
