import math
import random
from dessins import *
from Ex1 import *

print("------- Ex2 --------")
print("2.1.1")


def poidsMin(Arete):
  min = 0
  for i in range(len(Arete)):
    if Arete[i][2] < Arete[min][2]:
      min = i
  return min




def arbreCouvrant(nbPoints,Aretes):
  Arbre = []
  nbComposantes = nbPoints
  tabComposantes = [for i in range(n)]
  for i in range(len(Aretes)):
    min = poidsMin(Arete)
    u = Arete[min][0]
    v = Arete[min][1]
    if (tabComposantes[arete[0] != arete[1]]):
      Arbre.append((u,v))
      