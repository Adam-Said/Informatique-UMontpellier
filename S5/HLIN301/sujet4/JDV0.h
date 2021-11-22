
#ifndef __JDV_H
#define __JDV_H

#include "population-vivante-v2.h"

class JeuDeLaVie {
 private:
  PopulationVivante POP;
  
  void nettoie(std::string &s);
  bool findCleVal(std::string &s, std::string &s1,std::string &s2);
  void TraiteOption(const std::string &cle, const std::string &valeur, size_t num_ligne);
  
 public:
  
  JeuDeLaVie();
  
  void loadConfig(std::string file);
  void run(size_t);
};






#endif
