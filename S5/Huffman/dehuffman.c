#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct node {int nbOccur, parent, childL, childR, tag;} node;
struct node tree[511];
int lastNode;


int character(char* bin){
  int decimal=0;
  int c=1;
  for (int i=7; i>=0; i--) {
    if (bin[i]=='1') {
      decimal += c;
      c*=2;
    } 
    else {
      c*=2;
    }
  }
  return decimal;
}

//Reconstitution de l'arbre
int treeMaker(FILE* file, int lastNode) {
  int node = lastNode;
  int check;
  check=fgetc(file);
  if (check==49) {
    char  binary[8];
    int indice = 0;
    fgets(binary, 9, file);
    indice = character(binary);
    tree[lastNode].tag=indice;
  }
  else {
    node++;
    while (tree[node].nbOccur == 1) {
      node++;
    }
    tree[node].childL = node;
    tree[node].parent = lastNode;
    tree[node].nbOccur = 1;
    treeMaker(file, node);
    node++;
    while (tree[node].nbOccur == 1) {
      node++;
    }
    tree[node].childR = node;
    tree[node].parent = lastNode;
    tree[node].nbOccur = 1;
    treeMaker(file, node);
  }
  return node;
}

//Fonction de décompression
void decomp(FILE* compressedFile, FILE* uncompFile, int numLastNode) {
  int node=numLastNode;
  while (tree[node].tag != 254) {
    if(tree[node].childL == -1 && tree[node].childR == -1) {
      int ind = tree[node].tag;
      char c = (char)ind;
      fprintf(uncompFile, "%c", c);
      node=numLastNode;
    }
    else if (fgetc(compressedFile) == 48) {node =tree[node].childL;}
    else {
      node = tree[node].childR;}
  }
  printf("Décompression réussie !\n");
}

//Exécution du décompresseur
int main(int argc, char** argv) {
  FILE* fd = fopen(argv[1], "r");
  if (fd) {
    printf("Préparation en cours...\n");
    for (int i=0; i<511; i++){
        tree[i].childL = -1;
        tree[i].childR = -1;
        tree[i].nbOccur = 0;
        tree[i].tag = 0;
    }
    int numLastNode=255;
    tree[numLastNode].nbOccur=1;
    FILE* file=fopen(argv[1],"r");
    lastNode=treeMaker(file, numLastNode);
    for (int i=0; i<511; i++){
        if (tree[i].nbOccur>0){
            printf("%i : father-%i son_left-%i son_right-%i tag-%i\n", i, tree[i].parent, tree[i].childL, tree[i].childR, tree[i].tag);
        }
    }
    printf("Debut de la décompression\n");
    FILE* newFile=fopen("decomp.txt","w");
    decomp(file, newFile, 255);
    printf("Fin de la décompression\n");


  }
  else {
    printf("Fichier non trouvé ou non reconnu\n");
  }
}