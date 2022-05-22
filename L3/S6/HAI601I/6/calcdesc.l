%{
#include <stdio.h>
#include <math.h>
struct YYData {
    char *name;
    int val;
};
struct YYData yydata;

#define yymkdata(value) \
    yydata.val = value; yydata.name = #value; return value;

enum Token {
    LITFLOT = 300,
};

double yylval;
%}
%%
(0|[1-9][0-9]*|[0-9]+\.[0-9]*|\.[0-9]+)([eEdD][\+-]?[0-9]+)?f?  { yylval = atof(yytext); yymkdata(LITFLOT) }
(.|\n)                                                          { return yytext[0]; }
%%

#define AVANCER { jeton = yylex(); numcar++; }
#define TEST_AVANCE(prevu) { if (jeton == (prevu)) AVANCER else ERREUR_SYNTAXE }
#define ERREUR_SYNTAXE { printf("\nMot non reconnu : %s\n", yytext); exit(1); } 

double E(); double R(double gauche); double T(); double S(double left); double F(); double G(); double H(double left);
double jeton;                                  /* caractère courant du flot d'entrée */
double numcar = 0;                             /* numero du caractère courant (jeton) */

double E() {                                   /* regle : E->TR */    
    return R(T());
}

double R(double left) {                           /* regle : R->+TR|epsilon */
    if (jeton == '+') {                     
        AVANCER
        return R(left + T());
    }
    else if (jeton == '-') {                     
        AVANCER
        return R(left - T());
    }
    return left;
}

double T() {                                   /* regle : T->FS */
    return S(F());
}

double S(double left) {                           /* regle : S->*FS|epsilon */
    if (jeton == '*') {                     
        AVANCER
        return S(left * F());
    }
    else if (jeton == '/') {                     
        AVANCER
        return S(left / F());
    }
    return left;
}

double F() {
    return H(G());
}

double H(double left) {                         /* regle : ^GH|epsilon */
    if (jeton == '^') {
        AVANCER 
        double v = pow(left, H(G()));
        return v;
    }
    return left;
}

double G() {                                   /* regle : G->(E)|0|1|...|9 */
    if (jeton == '(') {                     
        AVANCER
        double res = E();
        TEST_AVANCE(')')
        return res;
    }
    else {
        if (jeton == LITFLOT) {
            double val = yylval;
            AVANCER
            return val;
        }
        else {
            ERREUR_SYNTAXE
        }
    }
}

int main(int argc, char **argv) {
    printf("Entrer un calcul à effectuer : ");  
    AVANCER
    double result = E();                    /* axiome */
    if (jeton == '\n') {                    /* expression reconnue et rien après */
        printf("Mot reconnu. Résultat: %f\n", result); 
    }
    else {
        ERREUR_SYNTAXE                      /* expression reconnue mais il reste des car */
    }
    return 0;
}