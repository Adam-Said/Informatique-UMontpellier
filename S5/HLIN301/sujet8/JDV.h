#ifndef __JDV_H__
#define __JDV_H__

#include <string>
#include "population.h"
#include "option.h"
#include "tabOptions.h"


class JDV {
 private:
  size_t cur_cycle, max_cycles, delay;
  std::string logfile_pattern;
  Population population;
  TabOptions opts;

  size_t chercheDelim(const std::string &txt, size_t deb, size_t fin, char c) const;
  std::string nettoieChaine(const std::string &txt, size_t deb, size_t fin) const;
  void traiteOption(const std::string &cle, const std::string &valeur);
  void readConfig(const char* fichier);
  void initOptions();
  void parseOptions(int argc, char** argv);
  void log() const;
  
 public:
  JDV(int argc, char** argv);
  size_t getDelay() const;
  size_t getCurrentCycle() const;
  size_t getMaxCycles() const;
  std::string getLogFilePattern() const;
  std::string getCurrentLogFile() const;
  void setDelay(size_t ms);
  void setMaxCycles(size_t nb);
  void setLogFilePattern(const std::string &logfile);
  void run();
};

#endif
