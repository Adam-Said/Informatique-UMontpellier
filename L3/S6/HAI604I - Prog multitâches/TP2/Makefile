########################################
#~ définitions
########################################

# Executables à générer
BIN=bin/clientTCP  bin/serveurTCP

# liste des fichiers sources 
SRCS0=clientTCP.c
SRCS1=serveurTCP.c

default: $(BIN)

########################################
#~ regles pour l'executable
########################################

obj/%.o: %.c
	gcc -Wall -Iinclude -c $< -o $@

bin/clientTCP: $(SRCS0:%.c=obj/%.o)
	gcc -o $@ $+

bin/serveurTCP: $(SRCS1:%.c=obj/%.o)
	gcc -o $@ $+

clean:
	rm -f $(BIN) obj/*.o *~