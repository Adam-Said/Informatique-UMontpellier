all:
	flex markdownHTML.l
	gcc -o markdownHTML lex.yy.c -lfl
	rm lex.yy.c
