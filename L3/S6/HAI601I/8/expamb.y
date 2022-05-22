%{
#include <stdio.h>
#include <stdlib.h>
#include "arbin.h"
void yyerror(char*);
int yylex();
%}
%union { 
    int c;
    Arbin a;
}
%token<c> SYMBOLE
%type<a> expr

%left '|'
%left CONCAT SYMBOLE '('
%left '*'
%%
liste:                          { /* epsilon, fin */ }
 |     liste ligne              {}
 ;

ligne: '\n'                     { /* Filtrage */ } 
 |     expr '\n'                { ab_afficher($1); }
 ;

expr : '(' expr ')'             { $$ = $2; }
 |      expr expr %prec CONCAT  { $$ = ab_construire('.', $1, $2); }
 |      expr '|' expr           { $$ = ab_construire('|', $1, $3); }
 |      expr '*'                { $$ = ab_construire('*', $1, ab_creer()); }
 |      SYMBOLE                 { $$ = ab_construire($1, ab_creer(), ab_creer()); }
 ;

%%
int yylex() {
    int c = getchar();
    if ((c >= 'a' && c <= 'z') || c == '@' || c == '0') {
        yylval.c = c;
        return SYMBOLE;
    }
    return c;
}
void yyerror(char *s) {
    fprintf(stderr,"ERREUR : %s\n",s);
}
int main(){
    yydebug = 0;
    return yyparse();
}