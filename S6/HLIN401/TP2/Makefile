SVG=$(wildcard *.svg)

tests: tests.cpp Tas.cpp
	g++ -Wpedantic $^ -o $@

clean:
	rm -f $(SVG)
