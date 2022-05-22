########################################


BIN=bin/acompleter

# liste des fichiers sources
SRCS1=acompleter.c

default: $(BIN)

########################################
#~ regles pour l'executable
########################################

obj/%.o: %.c
	gcc -Wall -Iinclude -c $< -o $@

bin/acompleter: $(SRCS1:%.c=obj/%.o) obj/simulation.o
	g++ -o $@ $+ -lpthread


clean:
	rm -f $(BIN) *~
