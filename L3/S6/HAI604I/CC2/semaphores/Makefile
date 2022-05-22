
BIN=bin/semInit  bin/bus bin/visiteur bin/semSuppr

SRCS0=semInit.c
SRCS1=bus.c
SRCS2=visiteur.c
SRCS3=semSuppr.c

default: $(BIN)


obj/%.o: %.c
	gcc -Wall -Iinclude -c $< -o $@

bin/semInit: $(SRCS0:%.c=obj/%.o)
	gcc -o $@ $+

bin/bus: $(SRCS1:%.c=obj/%.o) obj/visite.o
	gcc -o $@ $+

bin/visiteur: $(SRCS2:%.c=obj/%.o)
	gcc -o $@ $+

bin/semSuppr: $(SRCS3:%.c=obj/%.o)
	gcc -o $@ $+


clean:
	rm -f $(BIN) *~
