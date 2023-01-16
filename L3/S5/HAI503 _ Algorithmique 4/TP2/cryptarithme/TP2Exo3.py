#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu Dec  2 10:52:04 2021

@author: adam.said@etu.umontpellier.fr
"""

print("Ex 3.1")

def suivant(T):
    n = len(T)
    i = 1
    while i < n and T[i - 1] > T[i]:
        i += 1
    if i == n:
        return "Fin"

    j = 0
    for i in range(n - 1):
        if T[i] < T[i + 1]:
            j = i
    l = 0
    for i in range(n):
        if T[j] < T[i]:
            l = i
    tmp = T[j]
    T[j] = T[l]
    T[l] = tmp

    for i in range(1, int((n - j) / 2) + 1):
        tmp = T[j + i]
        T[j + i] = T[n - i]
        T[n - i] = tmp
    return T


T1 = [x for x in range(10)]
print("T1 =", T1)
cpt = 1
for i in range(10):
    cpt += 1
    T1 = suivant(T1)
print("10 * suivant(T1) =", T1)


print("Ex 3.2")

def dico(a,b,c):
    Dico = {}
    cpt = 1
    for mot in (a,b,c):
        for lettre in range(len(mot)):
            if mot[lettre] not in Dico.keys():
                Dico[mot[lettre]] = cpt
                cpt += 1
    return Dico

D1 = dico('OASIS', 'SOLEIL', 'MIRAGE')
print(D1)

print("Ex 3.3")

def valeur(m,D,p):
    val = 0
    for x in m:
        val += 10*val + p[D[x]]
    return val

v1 = valeur('OASIS', D1, T1)
print("OASIS =", v1)


def cryptarithme(a,b,c):
    D = dico(a, b, c)
    P = [x for x in range(10)]
    while (P != "Fin") and (valeur(a, D, P) + valeur(b, D, P) != valeur(
            c, D, P)):
        P = suivant(P)

    if P == "Fin":
        return "Impossible"
    else:
        return {k: P[D[k]] for k in D}

print(cryptarithme('NEUF', 'DEUX', 'ONZE'))




    
    
    
    
    
    
    
    
    
    