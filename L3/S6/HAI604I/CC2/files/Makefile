
BIN=bin/fileInit  bin/bus bin/visiteur bin/fileSuppr

SRCS0=fileInit.c
SRCS1=bus.c
SRCS2=visiteur.c
SRCS3=fileSuppr.c

default: $(BIN)


obj/%.o: %.c
	gcc -Wall -Iinclude -c $< -o $@

bin/fileInit: $(SRCS0:%.c=obj/%.o)
	gcc -o $@ $+

bin/bus: $(SRCS1:%.c=obj/%.o) obj/simulation.o
	gcc -o $@ $+

bin/visiteur: $(SRCS2:%.c=obj/%.o) obj/simulation.o
	gcc -o $@ $+

bin/fileSuppr: $(SRCS3:%.c=obj/%.o)
	gcc -o $@ $+


clean:
	rm -f $(BIN) *~
