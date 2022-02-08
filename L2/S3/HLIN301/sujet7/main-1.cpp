#include <iostream>
#include <exception>
#include "cellule.h"

template<typename T>
const T& max3(const T& x,const T& y,const T& z){
  if (x>y && x>z) return x;
  if (y>x && y>z) return y;
  return z;
}

template<typename T1,typename T2, typename T3>
const T1& max3(const T1& x,const T2& y,const T3& z){
  std::cerr<<"On ne melange pas les torchons et les serviettes"<<std::endl;
  std::terminate();
  return x; 
}


template<>
const Cellule& max3<Cellule>(const Cellule& x,const Cellule& y,const Cellule& z){
  if (x.estApres(y) && x.estApres(z)) return x;
  if (y.estApres(x) && y.estApres(z)) return y;
  return z;
}



#define MAX3(x,y,z) \
  "max("<<x<<","<<y<<","<<z<<")="<<max3(x,y,z)<<std::endl

int main(int argc, char** argv){
  int a=3,b=1,c=2;
  float d=2.3,e=1.4,f=0.3;

  std::cout<<max3<int>(a,b,d)<<std::endl;
  std::cout<<MAX3(a,b,c);
  std::cout<<MAX3(d,e,f);
  std::cout<<MAX3(a,e,f);

  max3(Cellule(true,3,1),Cellule(false,2,9),Cellule(true,1,2)).print();
  std::cout<<MAX3(a,b,f);
  return 0;
}
  



