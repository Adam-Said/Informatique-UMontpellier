#include <iostream>
#include <ctime>
#include <cstdlib>

void ech1(int a, int b) {
  int c;
  c = a; a = b; b = c;
}

void ech2(int *a, int *b) {
  int c;
  c = *a; *a = *b; *b = c;
}

void ech3(int &a, int &b) {
  int c;
  c = a; a = b; b = c;
}

void ech4(size_t u, size_t v, int *t){
  int c;
  c = t[u]; t[u] = t[v]; *(t + v) = c;
}

#define PrintVal(v)                    \
std::cout << "l" << __LINE__ << ": "   \
            << #v << " = " << v        \
            << std::endl

int main(int argc, char** argv) {
  int x[10];
  size_t i, j, k;
  srand(19);
  j = rand() % 10;
  k = rand() % 10;
  for (i = 0; i < 10; i++) {
    x[i] = rand() % 100 + 1;
  }
  PrintVal(j); PrintVal(k); PrintVal(x);
  PrintVal(x[j]); PrintVal(x[k]);
  std::cout << "=====" << std::endl;
  ech1(x[j], x[k]);
  PrintVal(x[j]); PrintVal(x[k]);
  std::cout << "=====" << std::endl;
  ech2(x+j, &x[k]);
  PrintVal(x[j]); PrintVal(x[k]);
  std::cout << "=====" << std::endl;
  ech3(x[j], x[k]);
  PrintVal(x[j]); PrintVal(x[k]);
  std::cout << "=====" << std::endl;
  ech4(j, k, x);
  PrintVal(x[j]); PrintVal(x[k]);
  std::cout << "=====" << std::endl
	    << "Fin du programme"
	    << std::endl;

  return 0;
}
