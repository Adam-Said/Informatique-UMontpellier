tests: tests.o Affichage.o Dijkstra.o Structures.o 
	g++ -Wall $^ -o $@

%.o: %.cpp
	g++ -Wall -c $<

clean:
	rm -f tests.o Affichage.o Dijkstra.o Structures.o
