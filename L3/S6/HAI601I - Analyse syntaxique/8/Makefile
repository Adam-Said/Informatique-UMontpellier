CC = gcc
CFLAGS = -Wall -Iinclude -ansi

YACC=bison
YACCFLAGS=-ydtv

BIN=bin/source bin/expamb
DIRG=@mkdir -p $(@D)

all: $(BIN)

clean:
	rm y.output y.tab.*
	rm -rf bin

bin/expamb: expamb.y arbin.c
	$(DIRG)
	$(YACC) $(YACCFLAGS) expamb.y
	$(CC) $(CFLAGS) -o $@ y.tab.c arbin.c

bin/source: source.y arbin.c
	$(DIRG)
	$(YACC) $(YACCFLAGS) source.y
	$(CC) $(CFLAGS) -o $@ y.tab.c arbin.c