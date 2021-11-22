import os
import sys

#Affiche les fichiers disponibles pour l'archivage
print("Spécifier des fichiers ou dossier à archiver")
os.system("ls -a")
#Récupère les fichiers à archiver
fichier=input()
print("Nom de l'archive :")
#Récupère le nom de l'archive
nom=input()
#Archivage des fichiers sélectionnés
os.system("tar -cvf " + nom + ".tar " + fichier)
print("Archivage terminé !")