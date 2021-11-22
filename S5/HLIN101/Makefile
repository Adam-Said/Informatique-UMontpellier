CC = g++
CFLAGS = -Wall -pedantic -std=c++0x -c -Werror

in101 : definitionsFonctions.o programmePrincipal.o listesEtTableaux.o
	${CC} -o in101 programmePrincipal.o

definitionsFonctions.o : definitionsFonctions.cpp
	${CC} ${CFLAGS} definitionsFonctions.cpp

programmePrincipal.o : programmePrincipal.cpp definitionsFonctions.cpp
	${CC} ${CFLAGS} programmePrincipal.cpp

listesEtTableaux.o : listesEtTableaux.cpp
	${CC} ${CFLAGS}  listesEtTableaux.cpp

clean :
	\rm *.o in101
