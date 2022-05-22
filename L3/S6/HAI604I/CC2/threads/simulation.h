#include <unistd.h>

//fonction qui simule la visite et dure environ sec secondes. 
void visite(int sec);

// fonction qui simule l'action de monter dans le bus ou de descendre
void monterOuDescendre();

// affiche une trace, avec : a = 'b' trace du bus, sinon d'un visiteur
void afficher (char a, char * trace, int indice);


// fonction à ne pas utiliser dans vos threads. 
void initDefault(int s);
