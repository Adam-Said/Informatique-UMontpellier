########################################
#~ définitions
########################################

BIN=bin/Pinit  bin/Pprod bin/Pcons bin/suppr

# liste des fichiers sources
SRCS0=Pinit.cc
SRCS1=Pcons.cc
SRCS3=Pprod.cc
SRCS4=supprimeFile.cc

default: $(BIN)

########################################
#~ regles pour l'executable
########################################

obj/%.o: %.cc
	g++ -Wall -Iinclude -c $< -o $@

bin/Pinit: $(SRCS0:%.c=obj/%.o)
	g++ -o $@ $+

bin/Pcons: $(SRCS1:%.c=obj/%.o)
	g++ -o $@ $+

bin/Pprod: $(SRCS3:%.c=obj/%.o)
	g++ -o $@ $+

bin/suppr: $(SRCS4:%.c=obj/%.o)
	g++ -o $@ $+



clean:
	rm -f $(BIN) obj/*.o *~
