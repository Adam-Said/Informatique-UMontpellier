import math
import random
from dessins import *

print("------- Ex1 --------")
print("1.1")

def distance(A,B):
  return math.sqrt(pow(A[0]-B[0],2)+pow(A[1]-B[1],2))

A = [121,77]
B = [48,70]

print("Distance entre A et B = ", distance(A,B))

print("1.2")

def aretes(P):
  listeArete= []
  for i in range(len(P)):
    for j in range(i+1,len(P)):
      listeArete.append([i,j,distance(P[i],P[j])])
  return listeArete

P = [(6,20),(67,18),(96,4),(32,45)]
print("liste des aretes de P :", aretes(P))

print("1.3.1")

def pointsAleatoires(n,xmax,ymax):
  listePoints = []
  for i in range(n):
    listePoints.append([random.uniform(0,xmax),random.uniform(0,ymax)])
  return listePoints

print("Une liste de points aléatoires :", pointsAleatoires(3,10,20))


print("1.3.2")

#dessinPoints(pointsAleatoires(3,10,20))

print("1.4.1")

def listesAdjacence(n,A):
  DicoAdja = {}
  for i in range(n):
    DicoAdja[i] = []
    listTmp = []
    for arete in A:
      if (i in arete):
        if(i == arete[0]):
          listTmp.append(arete[1])
        else:
          listTmp.append(arete[0])
        DicoAdja[i] = listTmp
  return DicoAdja

A = [(0,1),(0,2),(0,3),(1,2),(1,3),(2,3)]
print("Une liste d'adjacence des points :", listesAdjacence(4,A))

print("1.4.2")

#dessinGraphe(pointsAleatoires(4,10,20),listesAdjacence(4,A))

