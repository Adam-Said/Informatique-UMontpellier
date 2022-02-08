#ifndef __JDV_H
#define __JDV_H

#include "population-vivante-v2.h"
#include "tabOptions.h"

#define HELP_ID          1
#define VERSION_ID       2
#define DIMENSION_ID    10
#define PROBABILITY_ID  11
#define CONFIG_ID       20


class JeuDeLaVie {
 private:
  PopulationVivante POP;
  TabOptions opts;
  
  void nettoie(std::string &s);
  bool findCleVal(std::string &s, std::string &s1,std::string &s2);
  void TraiteOption(const std::string &cle, const std::string &valeur, size_t num_ligne);
  
 public:
  
  JeuDeLaVie();
  
  void loadConfig(std::string file);
  void run(size_t);
  void parseOptions(int argc, char** argv);
  
};






#endif
