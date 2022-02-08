#ifndef __MYVECTOR_CELLULE_H
#define __MYVECTOR_CELLULE_H
#include <vector>
#include <iostream>
#include "myvector.h"
#include "cellule.h"
#include "print-vector.h"

template<>
void write(std::ostream& os, const MyVector<Cellule>& T) {
  os<<"code special: ";
  for(size_t i=0;i<T.size();i++) {
    write_cell(os,T.at(i));os<<(i!=T.size()-1?",":"");
  }
}

template<>
void read(std::istream& is, MyVector<Cellule>& T){
  for(size_t i=0;i<T.size();i++) {
    read_cell(is,T.at(i));
  }
}



template<>
void write(std::ostream& os, const std::vector<Cellule>& T) {
  os<<"code special: ";
  for(size_t i=0;i<T.size();i++) {
    write_cell(os,T.at(i));os<<(i!=T.size()-1?",":"");
  }
}

template<>
void read(std::istream& is, std::vector<Cellule>& T){
  for(size_t i=0;i<T.size();i++) {
    read_cell(is,T.at(i));
  }
}

#endif
