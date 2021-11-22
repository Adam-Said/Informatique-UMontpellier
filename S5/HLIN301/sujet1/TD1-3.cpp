#include <iostream>
#include <cstdlib>

// Version itérative
int PuissanceIter(int x, unsigned int n) {
  int x_n = 1;
  while (n>0) {
    x_n *= x;
    n--;
  }
  return x_n;
}

// Version récursive
int PuissanceRec(int x, unsigned int n) {
  if (n>0) {
    return x * PuissanceRec(x,n-1);
  } else {
    return 1;
  }
}





int PuissanceRecFast(int x, unsigned int n) {
  if (n>0) {
    if (n%2 == 0){
      int r=PuissanceRecFast(x,n/2);
      return r*r;
    }else {
      int r=PuissanceRecFast(x,(n-1)/2);
      return r*r*x;
    }    
  } else {
    return 1;
  }
}


// Version optimisée
int Puissance(int x, unsigned int n) {
  int aux = x;
  int res = 1;
  while (n>0) {
    if (n%2 == 1) { /* n impair */
      res *= aux;
    }
    aux *= aux;
    n /= 2;
  }
  return res;
}

int main(int argc, char** argv) {
  int x, y, a, b, c;
  srand(0);
  clock_t t1, t2, t3, t;
  t1 = t2 = t3 = 0;
  for (size_t i = 0; i < 1000; i++) {
    x = rand()%1000;
    y = rand()%1000;
    t = clock();
    a = PuissanceIter(x, y);
    t1 += clock() - t;
    t = clock();
    b = PuissanceRec(x, y);
    t2 += clock() - t;
    t = clock();
    c = Puissance(x, y);
    t3 += clock() - t;
    if ((a != b) && (a != c)) {
      std::cout << "BUG" << std::endl;
    }
  }
  std::cout << "t1=" << t1 << std::endl;
  std::cout << "t2=" << t2 << std::endl;
  std::cout << "t3=" << t3 << std::endl;  
  return 0;
}
