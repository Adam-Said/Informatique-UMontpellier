%{
#include <stdio.h>
#include <math.h>
#include "arbin.h"
struct YYData {
    char *name;
    int val;
};
struct YYData yydata;

#define yymkdata(value) \
    yydata.val = value; yydata.name = #value; return value;

enum Token {
    BASE = 300,
};

char yylval;
%}
%%
[a-z0@]  { yylval = yytext[0]; return BASE; }
(.|\n)   { return yytext[0]; }
%%

#define AVANCER { jeton = yylex(); numcar++; }
#define TEST_AVANCE(prevu) { if (jeton == (prevu)) AVANCER else ERREUR_SYNTAXE }
#define ERREUR_SYNTAXE { printf("\nMot non reconnu : %s au caractère %d\n", yytext, numcar); exit(1); } 


Arbin S(); Arbin X(); Arbin E(); Arbin R(); Arbin T(); Arbin Y(); Arbin F();

int jeton;                                  /* caractère courant du flot d'entrée */
int numcar = 0;                             /* numero du caractère courant (jeton) */

Arbin S() {                                   /* regle : S->EX */    
    Arbin e = E();
    Arbin x = X();
    if (ab_vide(x)) {
        return e;
    }
    Arbin op = ab_construire(ab_racine(x), e, ab_sag(x));
    if (ab_vide(ab_sad(x))) {
        return op;
    }
    return ab_construire(ab_racine(ab_sad(x)), op, ab_sag(ab_sad(x))); 
}

Arbin X() {                           /* regle : X->'|'EX|epsilon */
    Arbin res = ab_creer();
    if (jeton == '|') {                     
        AVANCER
        Arbin e = E();
        Arbin x = X();
        res = ab_construire('|', e, x);
    }
    return res;
}

Arbin E() {                                   /* regle : E->TR */
    return R(T());
}

Arbin R(Arbin g) {                           /* regle : R->TR|epsilon */
    if(jeton == BASE || jeton == '(') {
        return R(ab_construire('.',g,T()));
    }
    else {
        return g;
    }
}

Arbin T() {                                   /* regle : T->FY */
    return Y(F());
}

Arbin Y(Arbin g) {                                   /* regle : Y->*Y|epsilon */
    Arbin res = ab_creer();
    if (jeton == '*') {                     
        AVANCER
        return Y(ab_construire('*', g, ab_creer()));
    }
    else return g;
}

Arbin F() {                                   /* regle : F->'('S')' | BASE */
    if (jeton == '(') {                     
        AVANCER
        Arbin res = S();
        TEST_AVANCE(')')
        return res;
    }
    else {
        if (jeton == BASE) {
            Arbin a = ab_construire(yylval, NULL, NULL);
            AVANCER
            return a;
        }
        else {
            ERREUR_SYNTAXE
        }
    }
}

int main(int argc, char **argv) {
    printf("Entrer un calcul à effectuer : ");  
    AVANCER
    Arbin result = S();                    /* axiome */
    if (jeton == '\n') {                    /* expression reconnue et rien après */
        printf("Mot reconnu. \n");
        ab_afficher(result);
    }
    else {
        ERREUR_SYNTAXE                      /* expression reconnue mais il reste des car */
    }
    return 0;
}