SVG=$(wildcard *.svg)

all: tests

%: %.cpp ABR.cpp ArbreBinaire.cpp
	g++ -Wpedantic $^ -o $@

clean:
	rm -f $(SVG)
