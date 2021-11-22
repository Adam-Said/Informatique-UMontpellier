#include "tableau-cellule.h"
#include <iostream>

TableauCellule::TableauCellule() : _data(NULL), _n(0), _alloc(0) {}

TableauCellule::TableauCellule(size_t n) : _data(new Cellule[n]), _n(n), _alloc(n) {}

TableauCellule::TableauCellule(const TableauCellule& T) : _data(new Cellule[T._n]), _n(T._n), _alloc(_n)
{
  for(size_t i=0;i<_n;i++){
    _data[i]=T._data[i];
  }
}
TableauCellule::~TableauCellule(){ delete[] _data;}
  
TableauCellule& TableauCellule::operator=(const TableauCellule& T)
{
  if (this!=&T){
    delete[] _data;
    _data=new Cellule[T._n];
    _n=T._n;
    _alloc=_n;
    for(size_t i=0;i<_n;i++){
      _data[i]=T._data[i];
    }    
  }
  return *this;
}


Cellule& TableauCellule::at(size_t i)      {return _data[i];}
const Cellule& TableauCellule::at(size_t i) const  {return _data[i];}

size_t TableauCellule::size()const {return _n;}



void TableauCellule::extend(){
  if (_n<_alloc) return;
  _alloc=2*_n;
  Cellule* tmp=new Cellule[_alloc];
  for(size_t i=0;i<_n;i++){tmp[i]=_data[i];}
  delete[] _data;
  _data=tmp;
}

void TableauCellule::push_back(const Cellule& C){
  extend();
  _data[_n]=C;
  _n++;
}

void TableauCellule::erase(int idx){
  if (idx>=0 && idx<(int)_n){
    for(size_t i=idx;i<_n-1;i++)
      _data[i]=_data[i+1];
    _n--;
  }  
}

void TableauCellule::read(std::istream& is){
  for(size_t i=0;i<_n;i++) {read_cell(is,_data[i]);}
}
void TableauCellule::write(std::ostream& os)const{
  os << "[";
  for(size_t i=0;i<_n;i++){
    os << (i ? ", " : "");
    write_cell(os,_data[i]);
  }
  os << "]";
} 

int TableauCellule::find(const Cellule& C) const{
  for (size_t i=0;i<_n;i++)    
    if (identique(C,_data[i])) return i;		
  return -1;
}

class f{
  size_t _n; Cellule *_data;
  Cellule* find(const Cellule& C) {
    for (size_t i=0;i<_n;i++)    
      if (identique(C,_data[i])) return _data+i;		
    return NULL;
  }
};
