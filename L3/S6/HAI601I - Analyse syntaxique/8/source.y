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
%type<a> S E T F

%left '*' '|'
%%
ligne: error '\n'      {yyerrok; exit(0);}
      | S '\n' {ab_afficher($1); exit(0);}
      ;
S: S '|' E     { $$ = ab_construire('|', $1, $3);}
 | E           { $$ = $1;}
 ;

E: E T         { $$ = ab_construire('.', $1, $2);}
 | T           { $$ = $1;}
 ;

T: T '*'       { $$ = ab_construire('*', $1, ab_creer());}
 | F           { $$ = $1;}
 ;

F: '(' S ')'   { $$ = $2;}
 | SYMBOLE     { $$ = ab_construire($1, ab_creer(), ab_creer());}
 ;
%%
int yylex(){
    int i=getchar();
    if ((i>='a' && i<='z')||i=='@'||i=='0'){
        yylval.c=i;
        return SYMBOLE;
    }
    else return i;
}
void yyerror(char *s) {
    fprintf(stderr,"ERREUR : %s\n",s);
}
int main(){
    return yyparse();
}