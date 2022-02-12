#ifndef __VOITURE_H__
#define __VOITURE_H__
#include <iostream>
#include <string>
#include <stdlib.h>


class voiture
{

	enum Type
	{
		sport,
		coupe,
		citadine,
		monospace
	};

	enum Etat
	{
		neuf,
		occasion
	};

	public:
		std::string getMarque() const;
		std::string getModele() const;
		int getAnne() const;
		int getNbrChevaux() const;
		std::string getType() const;
		Etat getEtat() const;

		void setMarque(std::string const &marque);
		void setModele(std::string const &modele);
		void setAnne(int const &anne);
		void setNbrChevaux(int const &chevaux);
		void setType(std::string const &type);
		void setEtat(Etat const &etat);

		voiture(voiture const &v);
		voiture(Etat const &t, std::string const &m, std::string const &mo, int const &a, int const &nbrChev, Type const &ty);
		voiture();
		~voiture();

		voiture display(std::ostream &output) const;
	
	private:
		Etat _Etat;
		std::string _Marque;
		std::string _Modele;
		int _Anne;
		int _NbrChevaux;
		Type _Type;

};
std::ostream &operator<<(std::ostream &output, voiture const &v) {
	return v.display(output);
};

#endif
