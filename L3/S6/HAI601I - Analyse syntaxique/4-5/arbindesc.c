/** 
 * @file analdesc.c        
 * @author Michel Meynard
 * @brief Analyse descendante récursive d'expression arithmétique
 *
 * Ce fichier contient un reconnaisseur d'expressions arithmétiques composée de 
 * littéraux entiers sur un car, des opérateurs +, * et du parenthésage ().
 * Remarque : soit rediriger en entrée un fichier, soit terminer par deux 
 * caractères EOF (Ctrl-D), un pour lancer la lecture, l'autre comme "vrai" EOF.
 */
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include "arbin.h"

                             /* les macros sont des blocs : pas de ';' apres */
#define AVANCER { jeton = getchar(); numcar++; }
#define TEST_AVANCE(prevu) { if (jeton == (prevu)) AVANCER else ERREUR_SYNTAXE }
#define ERREUR_SYNTAXE { printf("\nMot non reconnu : erreur de syntaxe \
au caractère numéro %d \n",numcar); exit(1); } 

Arbin E(); Arbin R(); Arbin T(); Arbin S(); Arbin F(); 

int jeton;                                  /* caractère courant du flot d'entrée */
int numcar = 0;                             /* numero du caractère courant (jeton) */

Arbin E() {                                   /* regle : E->TR */    
    Arbin t = T();
    Arbin r = R();
    if (ab_vide(r)) {
        return t;
    }
    Arbin op = ab_construire(ab_racine(r), t, ab_sag(r));
    if (ab_vide(ab_sad(r))) {
        return op;
    }
    return ab_construire(ab_racine(ab_sad(r)), op, ab_sag(ab_sad(r))); 
}

Arbin R() {                           /* regle : R->+TR|epsilon */
    Arbin res = ab_creer();
    if (jeton == '+') {                     
        AVANCER
        Arbin t = T();
        Arbin r = R();
        res = ab_construire('+', t, r);
    }
    return res;
}

Arbin T() {                                  /* regle : T->FS */
    Arbin f = F();
    Arbin s = S();
    if (ab_vide(s)) {
        return f;
    }
    Arbin op = ab_construire(ab_racine(s), f, ab_sag(s));
    if (ab_vide(ab_sad(s))) {
        return op;
    }
    return ab_construire(ab_racine(ab_sad(s)), op, ab_sag(ab_sad(s))); 
}

Arbin S() {                           /* regle : S->*FS|epsilon */
    Arbin res = ab_creer();
    if (jeton == '*') {                     
        AVANCER
        Arbin s = S();
        Arbin f = F();
        res = ab_construire('*', s, f);
    }
    return res;
}

Arbin F() {                                   /* regle : F->(E)|0|1|...|9 */
    if (jeton == '(') {                     
        AVANCER
        Arbin e = E();
        TEST_AVANCE(')')
        return e;
    }
    else {
        if (isdigit(jeton)) {
            int value = jeton;
            AVANCER
            return ab_construire(value, ab_creer(), ab_creer());
        }
        else {
            ERREUR_SYNTAXE
        }
    }
}

int main() {                           
    AVANCER			                        /* initialiser jeton sur le premier car */
    Arbin e = E();                                    /* axiome */
    if (jeton == '\n') {                    /* expression reconnue et rien après */
        printf("Mot reconnu.\n"); 
        ab_afficher(e);
    }
    else {
        ERREUR_SYNTAXE                      /* expression reconnue mais il reste des car */
    }
    ab_vide(&e);
    return 0;
}