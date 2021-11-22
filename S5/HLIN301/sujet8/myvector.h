#ifndef MYVECTOR_H
#define MYVECTOR_H

#include <iostream>

template<typename T>
class MyVector {
 private:
  T *_data;
  size_t   _n;
  size_t   _alloc;
  bool _sorted;

  void extend();
 public:

  MyVector();
  MyVector(size_t);
  MyVector(const MyVector&);
  ~MyVector();
  
  MyVector& operator=(const MyVector&);
  
  T&        operator[](size_t);
  const T&  operator[](size_t) const;

  void sort(); // sort the array (it needs operators : <, >, <=, >=, ==, !=)
  size_t size()const;  
  void push_back(const T&); // add an element at the end (possibly extend array)  
  void erase(int);          // remove the element at the given position
  void clear();  // remove all elements
  int  find(const T&) const; // return the position of the element or -1
};

template<typename T>
std::istream &operator>>(std::istream &is, MyVector<T> &V);

template<typename T>
std::ostream &operator<<(std::ostream& os, const MyVector<T> &V);

#include "myvector.tcc"

#endif
