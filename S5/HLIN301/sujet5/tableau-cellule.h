#ifndef TABLEAU_CELLULE_H
#define TABLEAU_CELLULE_H

#include "cellule.h"
#include <iostream>

class TableauCellule {
 private:
  Cellule *_data;
  size_t   _n;
  size_t   _alloc;

  void extend();
 public:

  TableauCellule();
  TableauCellule(size_t);
  TableauCellule(const TableauCellule&);
  ~TableauCellule();
  
  TableauCellule& operator=(const TableauCellule&);
  
  Cellule& at(size_t);
  const Cellule&  at(size_t) const;

  size_t size()const;

  void read(std::istream&);
  void write(std::ostream&)const;

  void push_back(const Cellule&); // add the cell at the end (possibly extend array)
  
  void erase(int);          // remove the element at the given position
  int  find(const Cellule&) const; // return the position of the cell or -1
  
};

void read_cell(std::istream&, Cellule&);
void write_cell(std::ostream&, const Cellule&);
bool identique(const Cellule&,const Cellule&);

#endif
