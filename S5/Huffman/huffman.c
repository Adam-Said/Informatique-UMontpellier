#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>
#include <unistd.h>


typedef struct node {int nbOccur, parent, childL, childR;} node;
struct node tree[511];
char*  strcodes[256] =  {NULL}; // Représentation sous forme de chaines de caractères du code binaire de chaque caractère

long int findSize(char* file) // Calcul la taille du fichier
{ 
  FILE* fd = fopen(file,"r");
  
  fseek(fd, 0L, SEEK_END);
  long int size = ftell(fd);
  fclose(fd); 
  
  return size; 
}

int* comptages(char* file) {   // Compte la fréquence de chaque caractère

       
    int* TabOcc = (int*)malloc(256*sizeof(int));  // Crée un tableau initialisé à 0 
    for (int i = 0; i < 256; i++) TabOcc[i] = 0;
    

    FILE* fd = fopen(file,"r"); // Ouvrir le fichier
    unsigned char ascii;
    if (fd) {
      while ((ascii = fgetc(fd)) != 255) // Lecture des caractères et implémentation dans le tableau
      {
        TabOcc[ascii]++;
      }}
    fclose(fd);
    return TabOcc;
}

int treeMaker(int* TabOcc) {  // Création de l'arbre
    for (int i = 0; i < 511; i++) // Initialisation des Parents et Enfants
    {
        tree[i].parent =-1;
        tree[i].childL =-1;
        tree[i].childR =-1;
        tree[i].nbOccur = 0;
    }
    for (int i = 0; i < 256; i++) tree[i].nbOccur = TabOcc[i];
    
    int lastNode = 255;
    int imin1, imin2, nbOccmin1, nbOccmin2;

    do // Appariemment des noeuds deux à deux avec nbOccur > 0, sans père et de nbOccur min
    {
        
        imin1 = imin2 = -1;
        nbOccmin1 = nbOccmin2 = INT_MAX;
        
        for (int i = 0; i <= lastNode; i++) // Parcours des feuilles pour trouver 1 noeud à appareiller
        {
            if ((tree[i].nbOccur > 0) && (tree[i].parent == -1) && (tree[i].nbOccur < nbOccmin1))
            {
                imin1 = i;
                nbOccmin1 = tree[i].nbOccur;
            }
        }
        
        for (int i = 0; i <= lastNode; i++) // Parcours des feuilles pour trouver le 2nd noeud à appareiller
        {
            if ((i != imin1) && (tree[i].nbOccur > 0) && (tree[i].parent == -1) && (tree[i].nbOccur < nbOccmin2))
            {
                imin2 = i;
                nbOccmin2 = tree[i].nbOccur;
            }
        }

        if (imin1 != -1 && imin2 != -1)
        {   
            lastNode++;
            tree[lastNode].childL = imin1;
            tree[lastNode].childR = imin2;
            tree[lastNode].nbOccur = tree[imin1].nbOccur + tree[imin2].nbOccur;
            tree[imin1].parent = lastNode;
            tree[imin2].parent = lastNode;
        }
        else {
          lastNode++;
          tree[lastNode].childL = imin1;
          tree[lastNode].nbOccur = tree[imin1].nbOccur;
          tree[imin1].parent = lastNode;
        }

    } while (imin1 != -1 && imin2 != -1);  // Tant qu'il reste des noeud à apparier
    return lastNode;
}

void treeLookUp(int node, int ident, char* code) { // Parcours de l'arbre en profondeur + coloration
    
    for (int i = 0; i <= 2*ident; i++) printf("   "); // Identation des lignes

    printf("=> %i (%i) %s\n", node, tree[node].nbOccur, code);

    char ncode[strlen(code)+2];
    strcpy(ncode, code);
    if (tree[node].childL != -1)
    {   
        ncode[strlen(code)] = '0';
        ncode[strlen(code)+1] = '\0';
        treeLookUp(tree[node].childL, ident+1, ncode);
        ncode[strlen(code)] = '1';
        treeLookUp(tree[node].childR, ident +1, ncode);
    }
    else // Sur une feuille
    {
        strcodes[node] = (char*)malloc(strlen(ncode)*sizeof(int));
        strcpy(strcodes[node], ncode);
    }
    
}

void treeEncoder(FILE* compressedFile, int node){
    if (tree[node].childL != -1 && tree[node].childR != -1){
        fprintf(compressedFile,"%s","0");
        treeEncoder(compressedFile, tree[node].childL);
        treeEncoder(compressedFile, tree[node].childR);
    }
    else {
        char bin[9]="00000000";
        int i=0;
        while(node>0){
            int r = node%2;

            if (r==1){
                bin[7-i]='1';
            }
            node /= 2;
            i++;
            }
        fprintf(compressedFile,"%s","1");
        fprintf(compressedFile,"%s",bin);
        }
    }

void compression(char* file) { // Compression
    char compressedFile[strlen(file)+4];
    strcpy(compressedFile, file);
    strcat(compressedFile, ".huf");
    FILE* fd = fopen(file,"r");
    FILE* fr = fopen(compressedFile, "w");
    unsigned char c;
    char byte = 0;
    int nbBits = 0;
    int sizeC = 1;
    printf("\nCompression en cours...\n");
    if (fd)
    {
        while ((c=fgetc(fd)) != 255)
        {
            int i = 0;
            while (strcodes[c][i])
            {
                int bit = strcodes[c][i] - '0';
                byte = byte | bit;
                nbBits++;
                if (nbBits == 8)
                {
                    fputc(byte, fr);
                    byte = 0;
                    nbBits = 0;
                    sizeC++;
                }
                else byte = byte << i;
                i++;
            }
            
        }
        for (int i = 0; i < 8-nbBits; i++)
        {
            byte = byte << 1;
        }
        fputc(byte,fr);
    }
    printf("\nCompression effectuée avec succès !\n\n");
    printf("=================================\n");
    printf("\nTaille originale : %ld octets\n", findSize(file));
    printf("\nTaille compréssée : %i octets\n", sizeC); 
    int TauxComp = 100-(sizeC*100)/(findSize(file));
    printf("\nTaux de compression : %i%%\n", TauxComp);
}

int checkContent(char* file) {
    FILE* fd = fopen(file, "r");
    const char* fName = file;
    if (fd == NULL || fgetc(fd) == EOF || access(fName, F_OK))
    {
        printf("Votre fichier est vide ou inexistant\n");
        return 0;
    }
    else
    {
        return 1;
    }
    
}


int main(int argc, char** argv)
{
        if (checkContent(argv[1])) // vérifie si le fichier existe et s'il contient un élément
        {
            int* TabOccur = comptages(argv[1]);  // Comptage des fréquences
            for (int i = 0; i < 256; i++)
            {
            
                if (TabOccur[i] > 0 )
                {
                    printf("%i %i %c\n", i, TabOccur[i], i);
                }
            }


            int numLastNode = treeMaker(TabOccur);  // Construction de l'arbre
            for (int i = 0; i < 511; i++)
            {
                if (tree[i].nbOccur > 0)
                {
                    printf("%i : parent=%i ChildL=%i ChildR=%i nbOccur=%i\n", i, tree[i].parent, tree[i].childL, tree[i].childR, tree[i].nbOccur);
                }
            }
            
            printf("\n ---Arbre : ---\n\n");
            treeLookUp(numLastNode, 0, ""); // Parcours de l'arbre
            printf("\n ---Table de codage--- \n\n");
            for (int i = 0; i < 256; i++) if (*(strcodes+i) != NULL) printf("Code de %i %c : %s\n", i, i, strcodes[i]);
            
            //treeEncoder((FILE*)argv[1], numLastNode);

            compression(argv[1]); // Exécution
        }

    return 0;
}
