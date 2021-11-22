#ifndef TABLEAU_INT_H
#define TABLEAU_INT_H
#include <iostream>

class TableauInt{
 private:
  int * _data;
  size_t   _n;
  
 public:
  TableauInt(size_t);
  TableauInt(const TableauInt&);
  TableauInt& operator=(const TableauInt&);
  ~TableauInt();
 size_t size()const;
  int& at(size_t);
  const int& at(size_t)const;  
};

void write(std::ostream&, const TableauInt&);








class TableauIntExtensible{
 private:
  int *   _data;
  size_t     _n;
  size_t _alloc;

  void extend();
 public:
  TableauIntExtensible();
  TableauIntExtensible(size_t);
  TableauIntExtensible(const TableauIntExtensible&);
  TableauIntExtensible& operator=(const TableauIntExtensible&);
  ~TableauIntExtensible();
  size_t size()const;
  int& at(size_t);
  const int& at(size_t)const;
  void push_back(int);
};

void write(std::ostream&, const TableauIntExtensible&);

#endif
