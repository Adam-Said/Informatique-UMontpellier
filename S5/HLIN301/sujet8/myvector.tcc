#include "generic-sort.h"

template<typename T>
MyVector<T>::MyVector() : _data(NULL), _n(0), _alloc(0), _sorted(true) {}

template<typename T>
MyVector<T>::MyVector(size_t n) : _data(new T[n]), _n(n), _alloc(n),
				  _sorted((n < 2) || (_data[0] <= _data[1])) {}

template<typename T>
MyVector<T>::MyVector(const MyVector& A) :
  _data(new T[A._n]), _n(A._n), _alloc(_n), _sorted(A._sorted)
{
  for(size_t i=0;i<_n;i++){
    _data[i]=A._data[i];
  }
}

template<typename T>
MyVector<T>::~MyVector(){ delete[] _data;}

template<typename T>
MyVector<T>& MyVector<T>::operator=(const MyVector<T>& A)
{
  if (this!=&A){
    delete[] _data;
    _data=new T[A._n];
    _n=A._n;
    _alloc=_n;
    _sorted = A._sorted;
    for(size_t i=0;i<_n;i++){
      _data[i]=A._data[i];
    }    
  }
  return *this;
}

template<typename T>
T& MyVector<T>::operator[](size_t i)      {return _data[i];}

template<typename T>
const T& MyVector<T>::operator[](size_t i) const  {return _data[i];}

template<typename T>
size_t MyVector<T>::size()const {return _n;}


template<typename T>
void MyVector<T>::extend(){
  if (_n<_alloc) return;
  _alloc=_n ? 2*_n : 1;
  T* tmp=new T[_alloc];
  for(size_t i=0;i<_n;i++){tmp[i]=_data[i];}
  delete[] _data;
  _data=tmp;
}

template<typename T>
void MyVector<T>::push_back(const T& C){
  extend();
  _data[_n]=C;
  if (_n++ && _sorted) {
    _sorted = (C >= _data[_n - 2]);
  }
}

template<typename T>
void MyVector<T>::erase(int idx){
  if (idx>=0 && idx<(int)_n){
    for(size_t i=idx;i<_n-1;i++)
      _data[i]=_data[i+1];
    if (!--_n) {
      _sorted = true;
    }
  }  
}

template<typename T>
void MyVector<T>::clear() {
  delete[] _data;
  _data = NULL;
  _n = _alloc = 0;
  _sorted = true;
}

template<typename T>
int MyVector<T>::find(const T& C) const{
  if (_sorted) {
    return rechercheDichotomique(*this, C);
  } else {
    for (size_t i=0;i<_n;i++)    
      if (C == _data[i]) return i;
    return -1;
  }
}

template<typename T>
void MyVector<T>::sort() {
  if (!_sorted) {
    triParTas(*this);
    _sorted = true;
  }
}


template<typename T>
std::istream &operator>>(std::istream &is, MyVector<T> &V){
  for(size_t i=0;i<V.size();i++)
    {is>>V[i];}  	     
  return is;
}

template<typename T>
std::ostream &operator<<(std::ostream &os, const MyVector<T> &V) {
  for(size_t i=0;i<V.size();i++)
    {os << (i ? ", " : "") << V[i];}
  os << std::endl;
  return os;
} 	     				     
