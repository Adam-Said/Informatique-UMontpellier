#include "voiture.h"
#include <iostream>
#include <string>
using namespace std;

voiture(): _Marque("Aixam"), _Modele("1"), _Etat(neuf), _Anne(0), _NbrChevaux(30), _Type(monospace) {
}; 

voiture(Etat t, string m, string mo, int a, int nbrChev, Type ty) {
	_Marque(m), _Modele(mo), _Etat(t), _Anne(a), _NbrChevaux(nbrChev), _Type(ty); 
}

string getModele() const {
	return _Modele;
}

void setEtat(Etat const &etat) {
	this->_Etat = etat;
}



void display(ostream &output) {
	output << "Marque : " << _Marque  << "\n Modele : " << _Modele <<
	 "\n Etat : " << _Etat << "\n Année de fabrication : " << _Anne <<
	 "\n Nombre de chevaux : " << _NbrChevaux << "\n Type : " << _Type << endl;
}

ostream &operator<<(ostream &output, const voiture &v) {
	v.display(output);
	return output;
} 