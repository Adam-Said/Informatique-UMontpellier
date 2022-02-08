#ifndef __OPTION_H
#define __OPTION_H

#include <string>

class Option {
 public:
  enum Type {
    AUCUN,
    BOOLEEN,
    ENTIER,
    REEL,
    CHAR,
    CHAINE
  };
  
 private:
  int id;
  std::string nom, raccourci, description;
  Type type;
  
 public:
  Option();
  Option(int id, const std::string &nom, const std::string &raccourci,const std::string &desc, Type type);

  // Accesseurs en lecture
  int getId() const;
  std::string getName() const;
  std::string getShortcut() const;
  std::string getDescription() const;
  Type getType() const;

  // Accesseurs en écriture
  void setId(int id);
  void setName(const std::string &name);
  void setShortcut(const std::string &shortcut);
  void setDescription(const std::string &desc);
  void setType(Type t);

  // Affichage
  void print() const;
};

std::string Type2String(Option::Type t);

#endif
