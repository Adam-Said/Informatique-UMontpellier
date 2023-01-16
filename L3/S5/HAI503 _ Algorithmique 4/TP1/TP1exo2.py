#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu Sep 30 11:18:35 2021

@author: adam.said@etu.umontpellier.fr
"""
from random import *
from matplotlib import pyplot as plt
from datetime import datetime

def eltMajDet(T) :
    for i in range(len(T)) :
        cpt = 0
        for j in range(i, len(T)) :
            if (T[i] == T[j]) :
                cpt = cpt +1
        if (cpt >= len(T) / 2) :
            return T[i]

T = [1,2,2,2,2,2,3,3,4,5]
#print(eltMajDet(T))

def eltMajProba(T) :
    elt = choice(T)
    for j in range(len(T)) :
        cpt =0
        if (elt == T[j]) :
            cpt = cpt +1
    if (cpt >= len(T) / 2) :
        return elt
    
#print(eltMajProba(T))

def tabAlea(n,a,b,k) :
    T = []
    elt = randint(a,b)
    for i in range(k) :
        T.append(elt)
    for i in range(n-k) :
        T.append(randint(a,b))
    shuffle(T)
    return T

T = tabAlea(10,1, 10,6)
#print(T)
#print(eltMajDet(T))

def tabDeb(n,a,b,k) :
    T = []
    elt = randint(a,b)
    for i in range(k) :
        T.append(elt)
    for i in range(n-k) :
        T.append(randint(a,b))
    return T

def tabFin(n,a,b,k) :
    T = []
    elt = randint(a,b)
    for i in range(k) :
        T.append(elt)
    for i in range(n-k) :
        T.append(randint(a,b))
    T.reverse()
    return T

def contientEltMaj(T,m) :
    start = datetime.now()
    for i in range(m) :
        elt = choice(T)
        for j in range(len(T)) :
            cpt = 0
            if (elt == T[j]) :
                cpt = cpt +1
        if (cpt >= len(T) / 2) :
            print(datetime.now() - start)
            return elt

print(eltMajDet(T))



















