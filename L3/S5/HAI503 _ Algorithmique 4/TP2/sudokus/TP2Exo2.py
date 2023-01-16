#!/usr/bin/env python3
# -*- coding: utf-8 -*-
from datetime import datetime

print("\n---- Question 1.1 ----\n")


def lecture_sudoku(nom) :
    L = []
    L1 = []
    with open( nom ) as f:
        for ligne in f:
            s= ligne.split()
            L1 = [int(x) for x in s]
            for x in L1 :
                L.append(x)
    x = L[0]
    del L[0]
    return (L,x)

print("Affichage sudoku S1 :\n")
print(lecture_sudoku("sudoku-3-facile-1.txt"))

print("\n---- Question 1.2 ----\n")

def ecriture_sudoku(G,n,nom) :
    with open( nom , 'w' ) as f:
        f.write(str(n)+"\n")
        for i in range(0, n**2) :
            for j in range(0,n**2) :
                if j != 0 :
                    f.write(" ")
                f.write(str(G[i*(n**2) + j]))
            f.write("\n")
            
G = lecture_sudoku("sudoku-3-moyen-7.txt")[0]
ecriture_sudoku(G,3,"philippe")

print("\n---- Question 1.3 ----\n")

def affichage_sudoku(G,n):
    for i in range(n*n):
        for j in range(n*n):
            if j != 0:
                print(" ", end='')
            if G[i*(n*n)+j] == 0:
                print("_", end='')
            else:
                print(G[i*(n*n)+j], end='')
        print("")
        
affichage_sudoku(G, 3)

print("\n---- Question 2.1 ----\n")

def zone(n, u):
    L = []
    i = int(u / (n**2))
    j = int(u % (n**2))
    zi = int(i / n)*n
    zj = int(j / n)*n
    for k in range(n):
        for l in range(n):
            L.append((zi+k)*(n**2)+(zj+l))
    return L

for i in range(0, 81, 12):
    print("zone(3,",i,") :", zone(3, i))
    
print("\n---- Question 2.2 ----\n")

def valide(G,n,u,x):
    G[u] = 0
    for i in zone(n, u):
        if x == G[i]:
            return False
    j = u % (n**2)
    i = int(u / (n**2))
    for k in range(n**2):
        if (k != j and x == G[i*(n**2)+k]) or (k != i and x == G[k*(n**2)+j]):
            return False
    return True

for i in range(9):
    print(i+1,"en 5 : ",valide(G, 3, 5, i+1))

print("\n---- Question 2.3 ----\n")

def BT_sudoku(G, n, u):

    while (u < n**4) and (G[u] != 0):
        u = u + 1
    if u == (n**4):
        return G
    for x in range (n**2):
        if valide(G, n, u, x+1):
            G[u] = x+1
            S = BT_sudoku(G, n, u+1)
            if isinstance(S, list):
                return S
    G[u] = 0
    return False


def sudoku(nom):
    G = lecture_sudoku(nom)
    print(nom, ":")
    affichage_sudoku(G[0], G[1])
    print("résolu : ")
    temps1 = datetime.now()
    affichage_sudoku(BT_sudoku(G[0], G[1], 0), G[1])
    print("temps mis =",datetime.now()-temps1,"\n") 

sudoku("sudoku-3-moyen-7.txt")



















