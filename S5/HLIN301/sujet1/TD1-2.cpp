#ifdef __BAD_CODE__
#include <iostream>

int min1(int a, int b) {
  return (a < b ? a : b);
}

int min2(int &a, int &b) {
  return (a < b ? a : b);
}

int &min3(int &a, int &b) {
  return (a < b ? a : b);
}

#define PrintVal(v)                    \
  std::cout << "l" << __LINE__ << ": " \
            << #v << " = " << v        \
            << std::endl
  
int main(int argc, char** argv) {
  int x = 2, y = -5;
  PrintVal(x); PrintVal(y);

  PrintVal(min1(x, y));
  PrintVal(min2(x, y));
  PrintVal(min3(x, y));

  PrintVal(min1(2, 3));
  PrintVal(min2(2, 3));
  PrintVal(min3(2, 3));

  PrintVal(min1(0, min1(x, y)));
  PrintVal(min2(0, min2(x, y)));
  PrintVal(min3(0, min3(x, y)));

  min1(x, y) = 10;
  min2(x, y) = 10;
  min3(x, y) = 10;

  PrintVal(x); PrintVal(y);
  return 0;
}
#else
#include <iostream>

int min1(int a, int b) {
  return (a < b ? a : b);
}

int min2(int &a, int &b) {
  return (a < b ? a : b);
}

int &min3(int &a, int &b) {
  return (a < b ? a : b);
}

#define PrintVal(v)                    \
  std::cout << "l" << __LINE__ << ": " \
            << #v << " = " << v        \
            << std::endl
  
int main(int argc, char** argv) {
  int x = 2, y = -5;
  PrintVal(x); PrintVal(y);

  PrintVal(min1(x, y));
  PrintVal(min2(x, y));
  PrintVal(min3(x, y));

  PrintVal(min1(2, 3));
  //PrintVal(min2(2, 3));
  //PrintVal(min3(2, 3));

  PrintVal(min1(0, min1(x, y)));
  //PrintVal(min2(0, min2(x, y)));
  //PrintVal(min3(0, min3(x, y)));

  //min1(x, y) = 10;
  //min2(x, y) = 10;
  min3(x, y) = 10;

  PrintVal(x); PrintVal(y);
  return 0;
}
#endif
