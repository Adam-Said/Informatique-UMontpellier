
#include <stdio.h>
#include <math.h>
#include "image_ppm.h"
using namespace std;

int main(int argc, char* argv[])
{
  char cNomImgLue[250];
  int nH, nW, nTaille;

  if (argc != 2)
     {
       printf("Usage: ImageIn.pgm\n");
       exit (1) ;
     }

   sscanf (argv[1],"%s",cNomImgLue) ;

   OCTET *ImgIn;

   lire_nb_lignes_colonnes_image_pgm(cNomImgLue, &nH, &nW);
   nTaille = nH * nW;

   allocation_tableau(ImgIn, OCTET, nTaille);
   lire_image_pgm(cNomImgLue, ImgIn, nH * nW);

   //   for (int i=0; i < nTaille; i++)
   // {
   //  if ( ImgIn[i] < S) TxtOut[i]=0; else TxtOut[i]=255;
   //  }

    int TxtOut[256] = {0};

 for (int i=0; i < nH; i++)
   for (int j=0; j < nW; j++)
     {
        TxtOut[ImgIn[i*nW+j]]++;
     }
    int moy = 0;
   int var = 0;
    for (int i = 0; i < 256; i++) {
        moy = moy + TxtOut[i] * i ;
    }
    moy = moy / nTaille;
    for (int i = 0; i < 256; i++) {
        var = var + (TxtOut[i] - moy)*(TxtOut[i] - moy);
    }
    var = var / nTaille;
    int ect = sqrt(var);
    printf("Moyenne: %i, Variance: %i, Ecart-type: %i", moy, var, ect);
   free(ImgIn);

   return 1;
}

