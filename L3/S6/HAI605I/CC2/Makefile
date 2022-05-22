# Makefile pour un unique ex�cutable

# liste des variables � renseigner
#   CIBLE : nom du programme ( $(CIBLE).c doit contenir main() )
#   SRCS : ensemble des fichiers sources 
#   LIBS : liste des biblioth�ques utiles � l'�dition des liens 
#          (format : -lnom1 -lnom2 ...) 
#   PREFIX : chemin de la hi�rarchie 
#
# NE PAS OUBLIER D'AJOUTER LA LISTE DES DEPENDANCES A LA FIN DU FICHIER

CIBLE = tp
SRCS =  src/Camera.cpp tp.cpp src/Triangle.cpp src/Mesh.cpp src/Vertex.cpp src/Shader.cpp
LIBS =  -lglut -lGLU -lGL -lm -lGLEW -lpthread

#########################################################"

INCDIR = .
LIBDIR = .
BINDIR = .

# nom du compilateur
CC = g++
CPP = g++

# options du compilateur          
CFLAGS = -Wall -O3 
CXXFLAGS = -Wall -O3 

# option du preprocesseur
CPPFLAGS =  -I$(INCDIR) 

# options du linker et liste des biblioth�ques � charger
LDFLAGS = -L/usr/X11R6/lib              
LDLIBS = -L$(LIBDIR) $(LIBS)  

# construire la liste des fichiers objets une nouvelle chaine � partir
# de SRCS en substituant les occurences de ".c" par ".o" 
OBJS = $(SRCS:.cpp=.o)   

# cible par d�faut
$(CIBLE): $(OBJS)

install:  $(CIBLE)
	cp $(CIBLE) $(BINDIR)/

installdirs:
	test -d $(INCDIR) || mkdir $(INCDIR)
	test -d $(LIBDIR) || mkdir $(LIBDIR)
	test -d $(BINDIR) || mkdir $(BINDIR)

clean:
	rm -f  *~  $(CIBLE) $(OBJS)

veryclean: clean
	rm -f $(BINDIR)/$(CIBLE)

dep:
	gcc $(CPPFLAGS) -MM $(SRCS)

# liste des d�pendances g�n�r�e par 'make dep'
Camera.o: src/Camera.cpp src/Camera.h src/Vec3D.h src/Edge.h src/Triangle.cpp src/Triangle.h src/Vertex.cpp src/Vertex.h src/Mesh.cpp src/Mesh.h src/Shader.cpp src/Shader.h 
tp.o: tp.cpp src/Vec3D.h src/Camera.cpp src/Camera.h src/Vec3D.h src/Edge.h src/Triangle.cpp src/Triangle.h src/Vertex.cpp src/Vertex.h src/Mesh.cpp src/Mesh.h src/Shader.cpp src/Shader.h



