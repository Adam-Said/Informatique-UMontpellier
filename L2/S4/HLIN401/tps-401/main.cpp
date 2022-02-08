#include <iostream>

int coefbin(int n, int k) {
  if(k == 0 || k == n) return 1;
  else return coefbin(n-1, k) + coefbin(n-1, k-1);
}

int main() {
  std::cout << coefbin(5,2);
}