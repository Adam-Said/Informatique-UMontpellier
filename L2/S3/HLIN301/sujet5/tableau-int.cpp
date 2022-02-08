#include "tableau-int.h"
#include <iostream>


TableauInt::TableauInt(size_t n) : _data(new int[n]), _n(n) {}
TableauInt::TableauInt(const TableauInt& T) : _data(new int[T._n]), _n(T._n) {
  for(size_t i=0;i<_n;i++)
    _data[i]=T._data[i];
}
TableauInt& TableauInt::operator=(const TableauInt& T){
  if (this!= &T){
    delete[] _data;
    _data=new int[T._n];
    _n=T._n;
    for(size_t i=0;i<_n;i++)
      _data[i]=T._data[i];    
  }
  return *this;
}
TableauInt::~TableauInt(){delete[] _data;}

size_t TableauInt::size()const {return _n;}

int& TableauInt::at(size_t i)           {return _data[i];}
const int& TableauInt::at(size_t i)const {return _data[i];}

void write(std::ostream& os, const TableauInt& T){
  for (size_t i=0;i<T.size();i++)
    os<<T.at(i)<<" ";
  os<<std::endl;
}











//////////// Tableau Int Extensible //////////////////

void TableauIntExtensible::extend() {
  if (_n<_alloc) return;
  _alloc=2*_alloc;
  int* tmp=new int[_alloc];
  for(size_t i=0;i<_n;i++){tmp[i]=_data[i];}
  delete[] _data;
  _data=tmp;
}
TableauIntExtensible::TableauIntExtensible() : _data(new int[1]), _n(0), _alloc(1) {}

TableauIntExtensible::TableauIntExtensible(size_t n) : _data(new int[n]), _n(n), _alloc(n) {}

TableauIntExtensible::TableauIntExtensible(const TableauIntExtensible& T): _data(new int[T._n]), _n(T._n), _alloc(_n) {
  for(size_t i=0;i<_n;i++)
    _data[i]=T._data[i];
}

TableauIntExtensible& TableauIntExtensible::operator=(const TableauIntExtensible& T){
  if (this!= &T){
    delete[] _data;
    _data=new int[T._n];
    _n=T._n;
    _alloc=_n;
    for(size_t i=0;i<_n;i++)
      _data[i]=T._data[i];    
  }
  return *this;
}
TableauIntExtensible::~TableauIntExtensible(){delete[] _data;}

size_t TableauIntExtensible::size()const {return _n;}

int& TableauIntExtensible::at(size_t i) {return _data[i];}

const int& TableauIntExtensible::at(size_t i)const {return _data[i];}

void TableauIntExtensible::push_back(int x) {
  extend();
  _data[_n]=x;
  _n++;
}


void write(std::ostream& os, const TableauIntExtensible& T){
  for (size_t i=0;i<T.size();i++)
    os<<T.at(i)<<" ";
  os<<std::endl;
}
