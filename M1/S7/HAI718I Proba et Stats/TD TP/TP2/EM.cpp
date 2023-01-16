#include <iostream>
#include "GaussianMixtureModel.h"
#include "image_ppm.h"


int main(int argc, char* argv[]) {

	if (argc < 2)
	{
		printf("Usage: ImageIn.pgm [NbGaussienne] [Precision]\nImageIn.pgm : Image d'entree au format pgm\nNbGaussienne : nombre de gaussiennes a optimiser, valeur par defaut = 3\nPrecision : precision a laquelle arreter l'optimisation, valeur par defaut = 0.0001\n");
		exit (1) ;
	}

	// Lecture de l'image
    char tabcImageName[250];
	sscanf(argv[1],"%s",tabcImageName);

	int iHeight = 0;
	int iWidth = 0;
	lire_nb_lignes_colonnes_image_pgm(tabcImageName, &iHeight, &iWidth);

	long lImageSize = iHeight * iWidth;
	if(lImageSize <= 0)
	{
		printf("Error: Unable to read the input image\n");
		exit (2);
	}

	OCTET * tabImage = new OCTET[lImageSize];
	lire_image_pgm(tabcImageName, tabImage, lImageSize);

	// Lecture du nombre de gaussiennes Ã  optimiser
	long lNbGaussienne = 0;
	if(argc > 2)
		sscanf(argv[2],"%ld",&lNbGaussienne);
	if(lNbGaussienne <= 0 || lNbGaussienne >= 256)
		lNbGaussienne = 3; // valeur par dÃ©faut

	// Lecture de la prÃ©cision Ã  laquelle arrÃªter l'optimisation
	double dAccuracy = 0;
	if(argc > 3)
		sscanf(argv[3],"%lf",&dAccuracy);
	if(dAccuracy <= 0)
		dAccuracy = 0.0001; // valeur par dÃ©faut

	// Construction de l'histogramme
	long tablHistogram[256] = {0};
	for (int i = 0; i < iHeight; i++)
		for (int j = 0; j < iWidth; j++)
			tablHistogram[tabImage[i * iWidth + j]]++;

	// Initialisation des gaussiennes
	double * tabdProp = new double[lNbGaussienne]; // proportions (gÃ©nÃ©ralement Ã©gales au dÃ©part)
	double * tabdMu = new double[lNbGaussienne]; // moyennes (doivent Ãªtre diffÃ©rentes au dÃ©part)
	double * tabdSigma = new double[lNbGaussienne]; // Ã©carts types (n'importe quelle valeur)
	for(int i = 0; i < lNbGaussienne; i++)
	{
		tabdProp[i] = 1.0 / lNbGaussienne;
		tabdMu[i] = i;
		tabdSigma[i] = 10.0;
	}

	// Optimisation des gaussiennes
	long lNbIteration = 0; // nombre d'itÃ©rations effectuÃ©es (paramÃ¨tre optionnel)
	ExpectationMaximizationGMM_OnHistogram(tablHistogram, 256, dAccuracy, tabdProp, tabdMu, tabdSigma, lNbGaussienne, &lNbIteration);

	// Affichage des modÃ¨les calculÃ©s
	cout << endl << "Computed Models :" << endl << endl;
	for(int i = 0; i < lNbGaussienne; i++)
	{
		cout << "    Model " << i + 1 << " : Gaussian (" << tabdMu[i] << ", " << tabdSigma[i] << " with proportion of " << tabdProp[i] << endl;
	}
	cout << endl << "Done with " << lNbIteration << " iterations and accuracy of " << dAccuracy << endl;

	// LibÃ©ration mÃ©moire
	delete[] tabImage;
	delete[] tabdProp;
	delete[] tabdMu;
	delete[] tabdSigma;

	return 0;
}

