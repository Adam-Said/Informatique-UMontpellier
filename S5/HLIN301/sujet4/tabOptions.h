#ifndef __TABOPTIONS_H__
#define __TABOPTIONS_H__

#include <string>
#include "option.h"

#define NMAX_OPTS 100
class TabOptions {
 private:
  Option opts[NMAX_OPTS];
  size_t nb_opts;
  int getOptionIndex(const std::string &opt) const;
 public:
  TabOptions();  
  void addOption(const Option &o);
  void print() const;
  int getOptionId(const std::string &opt) const;
  bool optionHasArgument(const std::string &opt) const;
  Option::Type optionArgumentType(const std::string &opt) const;
};

#endif
