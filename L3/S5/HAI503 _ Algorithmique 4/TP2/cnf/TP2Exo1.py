#!/usr/bin/env python3
# -*- coding: utf-8 -*-


print("\n---- Question 1.1 ----\n")

def clause(s) :
    L = s.rstrip()
    L = s.split(" ")
    L = [int(x) for x in L]
    del L[-1]
    return L

print(clause("1 2 -3 0\n"))

print("\n---- Question 1.2 ----\n")

def parseur(nom) :
    L = []
    F = []
    nbVar = 0
    with open(nom) as file :
        for ligne in file :
            if (ligne != ''):
                L.append(ligne)
    for i in range(0,len(L)) :
        if ((L[i] != "\n") and (L[i][0] != "c")) :
            if (L[i][0] == "p") :
                nbVar = int(L[i].split()[-2])
            else :
                F.append(clause(L[i]))
    return (F,nbVar)

print(parseur("simple_v3_c2.cnf"))

print("\n---- Question 2.1 ----\n")

def est_valide(F,A) :
    for line in F :
        res = False
        for elt in line :
            if ((elt * A[abs(elt) - 1]) > 0) :
                res = True
        if (not res) : 
            return False
    return True

F = parseur("simple_v3_c2.cnf")[0]
A1 = [1,1,-1]
A2 = [-1,-1,-1]
print("Formule F = ",F,'\n')
print("Affectation A1 = ",A1," \n")
print("Affectation A2 = ",A2," \n")

print("Est ce que A1 satisfait F ? ",est_valide(F,A1),"\n")
print("Est ce que A1 satisfait F ? ",est_valide(F,A2),"\n")

print("\n---- Question 2.2 ----\n")

def aff_suivante(A) :
    i = 0
    while (i < len(A) and A[i] == 1) :
        A[i] = -1
        i = i + 1
    if (i == len(A)) :
        return "None"
    A[i] = 1
    return A

#print(aff_suivante([1,1,-1]))

A0 = [-1,-1,-1,-1]
print("Affichage de toutes les affectations à 4 variables : \n")
print("A0 = ",A0," \n")


while(A0 != "None") :
    A0 = aff_suivante(A0)
    print("Affectation suivante = ",A0,"\n")

print("\n---- Question 2.3 ----\n")

def sat_exhau(F,n) :
    Aff = []
    for i in range(n) :
        Aff.append(-1)
    while (est_valide(F,Aff) != True) :
        if (Aff == "None") :
            break
        Aff = aff_suivante(Aff)
    return Aff

print("Recherche affectation valide pour la formule \n F = ",F,"\n")
n = parseur("simple_v3_c2.cnf")[1]
print("Affectation satisfaisante : ",sat_exhau(F,n),"\n")

Ffaux = parseur("random-5-sat.cnf")[0]
nfaux = parseur("random-5-sat.cnf")[1]
print("Recherche affectation valide pour la formule \n F = ",Ffaux,"\n")
print("Affectation satisfaisante : ",sat_exhau(Ffaux,nfaux),"\n")

print("\n---- Question 3.1 ----\n")

def elimination(F,n,b) :
    formule = []
    for line in F:
        clause = []
        sat = False
        for elt in line :
            if abs(elt) == n and elt * b > 0 :
                sat = True
            elif abs(elt) != n :
                clause.append(elt)
        if not(sat) :
            formule.append(clause)
    return formule


Felim = parseur("random-5-sat.cnf")
print("F random5 = ",Felim[0],"\n")
print("Elimination de F random5 : ",elimination(Felim[0], Felim[1], -1),"\n")

print("\n---- Question 3.2 ----\n")

def sat_backtrack(F,n) :
    if not F :
        A = []
        for i in range(n) :
            A.append(1)
        return A
    if [] in F :
        return "Insatisfiable"
    for b in range (-1,2,2):
        formule = elimination(F,n,b)
        A = sat_backtrack(formule,n-1)
        if A != "Insatisfiable":
            return A+[b]
    return "Insatisfiable"

print("Calcul backtrack de l'affectation pour F =\n")
F5 = parseur("simple_v3_c2.cnf")
print(F5[0],"\n")
print(sat_backtrack(F5[0],F5[1]))
    









