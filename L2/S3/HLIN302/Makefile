CXX = g++
CXXFLAGS = -Wall

PROG = main
SOURCES = voiture.cpp main.cpp
HEADERS = voiture.h

all: $(PROG)


.cpp.o:
	$(CXX) $(CXXFLAGS) $< -c

$(PROG): $(SOURCES:.cpp=.o)
	$(CXX) $^ -o $@

clean:
	rm -f $(SOURCES:.cpp=.o)
	rm -f "$(PROG)"
	rm -f *~

voiture.o: voiture.cpp voiture.h
main.o: main.cpp voiture.h