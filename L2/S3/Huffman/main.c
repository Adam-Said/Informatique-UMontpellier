#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>
#include <unistd.h>


int main(int argc, char const *argv[])
{
  //Menu d'accueil
    system("clear");
    printf("|#######################################|\n");
    printf("|########|                     |########|\n");
    printf("|########| Compresseur Huffman |########|\n");
    printf("|########|                     |########|\n");
    printf("|########| Par Adam et Maxime  |########|\n");
    printf("|########|_____________________|########|\n");
    printf("|#######################################|\n\n");

    printf("--------  Menu  --------\n");
    printf("- Compresser le fichier (1)\n");
    printf("- Décompresser le fichier (2)\n");
    printf("- Archiver un fichier (3)\n");
    printf("- Quitter (4)\n");


    int choix;
    printf("Que voulez-vous faire ? 1/2/3/4\n");
    scanf("%d", &choix);

    if (choix==1) //Menu de compression
    {   
        printf("\n ---  Compression  ---\nEntrez le nom du fichier à compresser\n");
        char fichier[100];
        scanf("%s", fichier);
        char buffer[100] = "./huf ";
        strcat(buffer, fichier);
        system(buffer);
    }
    else if (choix==2) //Menu de décompression
    {
        printf("\n ---  Décompression  ---\nEntrez le nom du fichier à décompresser\n");
        char fichier[100];
        scanf("%s", fichier);
        char buffer[100] = "./dehuf ";
        strcat(buffer, fichier);
        system(buffer);
    }
    else if (choix==3) //Lance l'archiveur
    {
        system("python3 archiveur.py");
    }
    else //Quitte le programme
    {
        printf("Arrêt du programme\n");
        exit(0);
    }
  printf("\nFermeture du programme\n");
  return 0;
}
