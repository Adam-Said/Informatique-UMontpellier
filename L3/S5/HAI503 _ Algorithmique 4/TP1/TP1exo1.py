#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu Sep 30 10:05:07 2021

@author: adam.said@etu.umontpellier.fr
"""

from random import *
from matplotlib import pyplot as plt

def entiersAleatoires(n,a,b) :
    l=[]
    for i in range(n) :
        l.append(randint(a,b))
    return l


def entiersAleatoires2(n,a,b) :
    l=[]
    for i in range(n) :
        l.append(randrange(a,b))
    return l
    
    
L1 = entiersAleatoires(1000,1,100)
L2 = entiersAleatoires2(1000,1,100) 
#print(L1)
#print(L2)

#plt.hist(L1,bins=100)
#plt.show

#plt.hist(L2,bins=100)
#plt.show

def flottantsAleatoires(n) :
    l=[]
    for i in range(n) :
        l.append(random())
    return l

def flottantsAleatoires2(n,a,b) :
    l=[]
    for i in range(n) :
        l.append(uniform(a,b))
    return l

#L1 = flottantsAleatoires(1000)
#L2 = flottantsAleatoires2(1000,-3,10) 
#print(L1)
#print(L2)

#plt.plot(L1)
#plt.show

#plt.plot(L2)
#plt.show

def pointsDisque(n) :
    L = []
    for i in range(n) : 
        x = uniform(-1,1)
        y = uniform(-1,1)
        while(x*x + y*y > 1) :
            x = uniform(-1,1)
            y = uniform(-1,1)
        L.append([x,y])
    return L

#print(pointsDisque(3))
        
def pointsDisque2(n) :
    L = []
    for i in range(n) :    
        x = uniform(-1,1)
        y = uniform(-1,1)
        while(x*x + y*y > 1) :
            y = uniform(-1,1)
        L.append((x,y))
    return L     
    
#print(pointsDisque2(3))

def affichagePoints(L):
    X = [x for x,y in L] # on récupère les abscisses...
    Y = [y for x,y in L] # ... et les ordonnées
    plt.scatter(X, Y, s = 3) # taille des points minimale
    plt.axis( 'square' ) # même échelle en abscisse et ordonnée
    plt.show()

#affichagePoints(pointsDisque(1000))
#affichagePoints(pointsDisque2(4))

def aleatoireModulo(n) :
    return getrandbits(n.bit_length()) % n

#print(aleatoireModulo(5))

def aleatoireRejet(n) :
    x = getrandbits(n.bit_length())
    while (x >= n) :
        x = getrandbits(n.bit_length())
    return x

#print(aleatoireRejet(5))










