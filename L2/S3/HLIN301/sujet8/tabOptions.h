#ifndef __TABOPTIONS_H__
#define __TABOPTIONS_H__

#include <string>
#include <iostream>
#include "option.h"
#include "myvector.h"

class TabOptions {
 private:
  MyVector<Option> opts;
  size_t operator()(const std::string &opt, const bool warn) const;
 public:
  TabOptions();

  void print(std::ostream &os) const;
  TabOptions &operator+=(const Option &o);
  int getOptionId(const std::string &opt) const;
  bool optionHasArgument(const std::string &opt) const;
  Option::Type optionArgumentType(const std::string &opt) const;
};

std::ostream &operator<<(std::ostream &os, const TabOptions &t);

#endif
