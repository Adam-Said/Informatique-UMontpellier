#ifndef __MYVECTOR_H
#define __MYVECTOR_H
#include <iostream>

template<typename T>
class MyVector {
 private:
  T *_data;
  size_t   _n;
  size_t   _alloc;

  void extend();
 public:

  MyVector();
  MyVector(size_t);
  MyVector(const MyVector&);
  ~MyVector();
  MyVector& operator=(const MyVector&);
  
  T& at(size_t);
  const T&  at(size_t) const;
  
  size_t size()const;  
  void push_back(const T&); // add an element at the end (possibly extend array)  
  void erase(int);          // remove the element at the given position
  int  find(const T&) const; // return the position of the element or -1

};
#include "myvector.tcc"
#endif
