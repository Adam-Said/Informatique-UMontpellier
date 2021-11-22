#ifndef __OPTION_H__
#define __OPTION_H__

#include <string>
#include <iostream>

class Option {
 public:
  enum Type {
    AUCUN,
    BOOLEEN,
    ENTIER,
    REEL,
    CHAR,
    CHAINE,
    NB_TYPES
  };
 private:
  int id;
  std::string nom, raccourci, description;
  Type type;
  
 public:
  Option();
  Option(int id, const std::string &nom, const std::string &raccourci,
	 const std::string &desc, Type type);

  // Accesseurs en lecture
  int getId() const;
  std::string getName() const;
  std::string getShortcut() const;
  std::string getDescription() const;
  Type getType() const;

  operator int () const;
  // Accesseurs en écriture
  void setId(int id);
  void setName(const std::string &name);
  void setShortcut(const std::string &shortcut);
  void setDescription(const std::string &desc);
  void setType(Type t);
};

std::string Type2String(Option::Type t);
std::ostream &operator<<(std::ostream &os, const Option &o);

#endif
