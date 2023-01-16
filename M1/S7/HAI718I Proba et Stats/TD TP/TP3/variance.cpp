
#include <stdio.h>
#include <math.h>
#include "image_ppm.h"
using namespace std;

int main(int argc, char* argv[])
{
  char cNomImgLue[250], cNomTxtEcrite[250];
  int nH, nW, nTaille;

  if (argc != 3)
     {
       printf("Usage: ImageIn.pgm out.dat\n");
       exit (1) ;
     }

   sscanf (argv[1],"%s",cNomImgLue) ;
   sscanf (argv[2],"%s",cNomTxtEcrite);

   OCTET *ImgIn;

   lire_nb_lignes_colonnes_image_pgm(cNomImgLue, &nH, &nW);
   nTaille = nH * nW;

   allocation_tableau(ImgIn, OCTET, nTaille);
   lire_image_pgm(cNomImgLue, ImgIn, nH * nW);

    float TxtOut[256] = {0};


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

    FILE* file_output;
    if((file_output = fopen(cNomTxtEcrite,"w")) == NULL) {
        exit(EXIT_FAILURE);
    };

    float gauss[256] = {0};
    for(int i = 0; i < 256; i++) {
      TxtOut[i] = 1.0/(sqrt(var)*sqrt(2.0*M_PI)) * exp((-1.0/2.0)*(((i-moy)/sqrt(var))*((i-moy)/sqrt(var))));
      fprintf(file_output, "%d\t%f\n", i, TxtOut[i]);
    }
    fclose(file_output);
    printf("Moyenne: %i, Variance: %i, Ecart-type: %i\n", moy, var, ect);
    free(ImgIn);

   return 1;
}

