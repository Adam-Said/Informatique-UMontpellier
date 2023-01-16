#include <cstdlib>
#include <cmath>
#include <limits>
#include <ctime>
#include <cstring>

using namespace std;

#define PI2 6.283185307179586476925286766559
#define Sqrt_PI2 2.506628274631000502415765284811

double NormPDF(double in_dx, double in_dMu, double in_dSigma);
void GenerateGaussianModel(double *out_tabdData, long in_lNbData, double in_dMu, double in_dSigma);
void SimulationGMM(double *out_tabdData, long in_lNbData, double *in_tabdProp, double *in_tabdMu, double *in_tabdSigma, long in_lNbModel);
void ExpectationMaximizationGMM_OnData(double *in_tabdData, long in_lNbData, double in_dAccuracy, double *inout_tabdProp, double *inout_tabdMu, double *inout_tabdSigma, long in_lNbModel, long *out_lNbIteration = NULL);
void ExpectationMaximizationGMM_OnHistogram(long *in_tablData, long in_lNbData, double in_dAccuracy, double *inout_tabdProp, double *inout_tabdMu, double *inout_tabdSigma, long in_lNbModel, long *out_lNbIteration = NULL);

double NormPDF(double in_dx, double in_dMu, double in_dSigma) {
	return exp(-0.5 * pow((in_dx - in_dMu) / in_dSigma, 2)) / (Sqrt_PI2 * in_dSigma);
}

void GenerateGaussianModel(double *out_tabdData, long in_lNbData, double in_dMu, double in_dSigma)
{
	if (out_tabdData == NULL || in_lNbData <= 0)
	{
		return;
	}

	srand(time(NULL));
	const double dEpsilon = std::numeric_limits<double>::epsilon();

	bool bGenerate = true;
	double stabdRandom[2] = { 0 };
	double stabdValue[2] = { 0 };

	double dTemp = 0;

	long lIndexData = 0;
	for (lIndexData = 0; lIndexData < in_lNbData; lIndexData++)
	{
		if (bGenerate == true)
		{
			do
			{
				stabdRandom[0] = rand() * (1.0 / RAND_MAX);
				stabdRandom[1] = rand() * (1.0 / RAND_MAX);
			} while (stabdRandom[0] <= dEpsilon);

			dTemp = sqrt(-2.0 * log(stabdRandom[0]));
			stabdValue[0] = dTemp * cos(PI2 * stabdRandom[1]);
			stabdValue[1] = dTemp * sin(PI2 * stabdRandom[1]);

			out_tabdData[lIndexData] = stabdValue[0] * in_dSigma + in_dMu;
		}
		else
		{
			out_tabdData[lIndexData] = stabdValue[1] * in_dSigma + in_dMu;
		}

		bGenerate = !bGenerate;
	}
}

void SimulationGMM(double *out_tabdData, long in_lNbData, double *in_tabdProp, double *in_tabdMu, double *in_tabdSigma, long in_lNbModel)
{
	srand(time(NULL));

	// Init
	double dRand = 0;
	long lIndexModel = 0;
	double *tabdCumulProp = new double[in_lNbModel];
	tabdCumulProp[0] = in_tabdProp[0];
	for (lIndexModel = 1; lIndexModel < in_lNbModel; lIndexModel++)
	{
		tabdCumulProp[lIndexModel] = tabdCumulProp[lIndexModel - 1] + in_tabdProp[lIndexModel];
	}

	// Simulation of each model
	double *tabdSimulation = new double[in_lNbModel * in_lNbData]; // model in line and data in column
	for (lIndexModel = 0; lIndexModel < in_lNbModel; lIndexModel++)
	{
		GenerateGaussianModel(&tabdSimulation[lIndexModel * in_lNbData], in_lNbData, in_tabdMu[lIndexModel], in_tabdSigma[lIndexModel]);
	}

	// Mixture of models
	long lIndexData = 0;
	for(lIndexData = 0; lIndexData < in_lNbData; lIndexData++)
	{
		// Select model by proportion
		dRand = (rand() % 1000) * 0.001;
		lIndexModel = 0;
		while (dRand > tabdCumulProp[lIndexModel] && lIndexModel < in_lNbModel)
		{
			lIndexModel++;
		}

		// Generate random value
		out_tabdData[lIndexData] = tabdSimulation[lIndexModel * in_lNbData + rand() % in_lNbData];
	}

	delete[] tabdCumulProp;
	delete[] tabdSimulation;
}

void ExpectationMaximizationGMM_OnData(double *in_tabdData, long in_lNbData, double in_dAccuracy, double *inout_tabdProp, double *inout_tabdMu, double *inout_tabdSigma, long in_lNbModel, long *out_lNbIteration)
{
	if (in_tabdData == NULL || in_lNbData == 0 || inout_tabdProp == NULL || inout_tabdMu == NULL || inout_tabdSigma == NULL || in_lNbModel == 0)
	{
		return;
	}
	if (out_lNbIteration != NULL)
	{
		(*out_lNbIteration) = 0;
	}
	const double dEpsilon = std::numeric_limits<double>::epsilon();

	// Save previous parameters to compute difference and then accuracy
	double *tabdProp_TEMP = new double[in_lNbModel];
	double *tabdMu_TEMP = new double[in_lNbModel];
	double *tabdSigma_TEMP = new double[in_lNbModel];

	// H matrix
	double *tabdHMatrix = new double[in_lNbModel * in_lNbData]; // model in line and data in column
	double *tabdHMatrix_RowSum = new double[in_lNbModel];
	double *tabdHMatrix_ColSum = new double[in_lNbData];
	long lIndexHMatrix = 0;

	// Squared accuracy to avoid sqrt()
	double dAccuracy = in_dAccuracy * in_dAccuracy;
	double dAccuracy_TEMP = dAccuracy;

	// EM loop
	long lIndexData = 0;
	long lIndexModel = 0;
	while(dAccuracy_TEMP >= dAccuracy)
	{
		// Save previous parameters
		memcpy(tabdProp_TEMP, inout_tabdProp, in_lNbModel * sizeof(double));
		memcpy(tabdMu_TEMP, inout_tabdMu, in_lNbModel * sizeof(double));
		memcpy(tabdSigma_TEMP, inout_tabdSigma, in_lNbModel * sizeof(double));

		// Init sums
		memset(tabdHMatrix_RowSum, 0, in_lNbModel * sizeof(double));
		memset(tabdHMatrix_ColSum, 0, in_lNbData * sizeof(double));

		// Expectation step
		for(lIndexModel = 0; lIndexModel < in_lNbModel; lIndexModel++)
		{
			for(lIndexData = 0; lIndexData < in_lNbData; lIndexData++)
			{
				lIndexHMatrix = lIndexModel * in_lNbData + lIndexData;
				tabdHMatrix[lIndexHMatrix] = inout_tabdProp[lIndexModel] * NormPDF(in_tabdData[lIndexData], inout_tabdMu[lIndexModel], inout_tabdSigma[lIndexModel]) + dEpsilon;
				tabdHMatrix_ColSum[lIndexData] += tabdHMatrix[lIndexHMatrix];
			}
		}
		for(lIndexModel = 0; lIndexModel < in_lNbModel; lIndexModel++)
		{
			for(lIndexData = 0; lIndexData < in_lNbData; lIndexData++)
			{
				lIndexHMatrix = lIndexModel * in_lNbData + lIndexData;
				tabdHMatrix[lIndexHMatrix] /= tabdHMatrix_ColSum[lIndexData];
				tabdHMatrix_RowSum[lIndexModel] += tabdHMatrix[lIndexHMatrix];
			}
		}

		// Maximization step
		for(lIndexModel = 0; lIndexModel < in_lNbModel; lIndexModel++)
		{
			inout_tabdProp[lIndexModel] = tabdHMatrix_RowSum[lIndexModel] / in_lNbData;

			inout_tabdMu[lIndexModel] = 0;
			for(lIndexData = 0; lIndexData < in_lNbData; lIndexData++)
			{
				inout_tabdMu[lIndexModel] += (tabdHMatrix[lIndexModel * in_lNbData + lIndexData] * in_tabdData[lIndexData]);
			}
			inout_tabdMu[lIndexModel] /= tabdHMatrix_RowSum[lIndexModel];

			inout_tabdSigma[lIndexModel] = 0;
			for(lIndexData = 0; lIndexData < in_lNbData; lIndexData++)
			{
				inout_tabdSigma[lIndexModel] += (tabdHMatrix[lIndexModel * in_lNbData + lIndexData] * pow(in_tabdData[lIndexData] - inout_tabdMu[lIndexModel], 2));
			}
			inout_tabdSigma[lIndexModel] = sqrt(inout_tabdSigma[lIndexModel] / tabdHMatrix_RowSum[lIndexModel]);
		}

		// Accuracy
		dAccuracy_TEMP = 0;
		for(lIndexModel = 0; lIndexModel < in_lNbModel; lIndexModel++)
		{
			dAccuracy_TEMP += pow(tabdProp_TEMP[lIndexModel] - inout_tabdProp[lIndexModel], 2);
			dAccuracy_TEMP += pow(tabdMu_TEMP[lIndexModel] - inout_tabdMu[lIndexModel], 2);
			dAccuracy_TEMP += pow(tabdSigma_TEMP[lIndexModel] - inout_tabdSigma[lIndexModel], 2);
		}

		if (out_lNbIteration != NULL)
		{
			(*out_lNbIteration)++;
		}
	}

	// Free memory
	delete[] tabdProp_TEMP;
	delete[] tabdMu_TEMP;
	delete[] tabdSigma_TEMP;
	delete[] tabdHMatrix;
	delete[] tabdHMatrix_RowSum;
	delete[] tabdHMatrix_ColSum;
}

void ExpectationMaximizationGMM_OnHistogram(long *in_tablData, long in_lNbData, double in_dAccuracy, double *inout_tabdProp, double *inout_tabdMu, double *inout_tabdSigma, long in_lNbModel, long *out_lNbIteration)
{
	if (in_tablData == NULL || in_lNbData == 0 || inout_tabdProp == NULL || inout_tabdMu == NULL || inout_tabdSigma == NULL || in_lNbModel == 0)
	{
		return;
	}
	if (out_lNbIteration != NULL)
	{
		(*out_lNbIteration) = 0;
	}

	// Init
	const double dEpsilon = std::numeric_limits<double>::epsilon();
	long lIndexData = 0;
	long lIndexModel = 0;

	// Retrieve total number of data measures
	long lNbData = 0;
	for(lIndexData = 0; lIndexData < in_lNbData; lIndexData++)
	{
		lNbData += in_tablData[lIndexData];
	}

	// Save previous parameters to compute difference and then accuracy
	double *tabdProp_TEMP = new double[in_lNbModel];
	double *tabdMu_TEMP = new double[in_lNbModel];
	double *tabdSigma_TEMP = new double[in_lNbModel];

	// H matrix
	double *tabdHMatrix = new double[in_lNbModel * in_lNbData]; // model in line and data in column
	double *tabdHMatrix_RowSum = new double[in_lNbModel];
	double *tabdHMatrix_ColSum = new double[in_lNbData];
	long lIndexHMatrix = 0;

	// Squared accuracy to avoid sqrt()
	double dAccuracy = in_dAccuracy * in_dAccuracy;
	double dAccuracy_TEMP = dAccuracy;

	// EM loop
	while(dAccuracy_TEMP >= dAccuracy)
	{
		// Save previous parameters
		memcpy(tabdProp_TEMP, inout_tabdProp, in_lNbModel * sizeof(double));
		memcpy(tabdMu_TEMP, inout_tabdMu, in_lNbModel * sizeof(double));
		memcpy(tabdSigma_TEMP, inout_tabdSigma, in_lNbModel * sizeof(double));

		// Init sums
		memset(tabdHMatrix_RowSum, 0, in_lNbModel * sizeof(double));
		memset(tabdHMatrix_ColSum, 0, in_lNbData * sizeof(double));

		// Expectation step
		for(lIndexModel = 0; lIndexModel < in_lNbModel; lIndexModel++)
		{
			for(lIndexData = 0; lIndexData < in_lNbData; lIndexData++)
			{
				lIndexHMatrix = lIndexModel * in_lNbData + lIndexData;
				tabdHMatrix[lIndexHMatrix] = inout_tabdProp[lIndexModel] * NormPDF(lIndexData, inout_tabdMu[lIndexModel], inout_tabdSigma[lIndexModel]) + dEpsilon;
				tabdHMatrix_ColSum[lIndexData] += tabdHMatrix[lIndexHMatrix];
				tabdHMatrix[lIndexHMatrix] *= in_tablData[lIndexData]; // Histogram multiplication
			}
		}
		for(lIndexModel = 0; lIndexModel < in_lNbModel; lIndexModel++)
		{
			for(lIndexData = 0; lIndexData < in_lNbData; lIndexData++)
			{
				lIndexHMatrix = lIndexModel * in_lNbData + lIndexData;
				tabdHMatrix[lIndexHMatrix] /= tabdHMatrix_ColSum[lIndexData];
				tabdHMatrix_RowSum[lIndexModel] += tabdHMatrix[lIndexHMatrix];
			}
		}

		// Maximization step
		for(lIndexModel = 0; lIndexModel < in_lNbModel; lIndexModel++)
		{
			inout_tabdProp[lIndexModel] = tabdHMatrix_RowSum[lIndexModel] / lNbData; // Real number of data measures

			inout_tabdMu[lIndexModel] = 0;
			for(lIndexData = 0; lIndexData < in_lNbData; lIndexData++)
			{
				inout_tabdMu[lIndexModel] += (tabdHMatrix[lIndexModel * in_lNbData + lIndexData] * lIndexData);
			}
			inout_tabdMu[lIndexModel] /= tabdHMatrix_RowSum[lIndexModel];

			inout_tabdSigma[lIndexModel] = 0;
			for(lIndexData = 0; lIndexData < in_lNbData; lIndexData++)
			{
				inout_tabdSigma[lIndexModel] += (tabdHMatrix[lIndexModel * in_lNbData + lIndexData] * pow(lIndexData - inout_tabdMu[lIndexModel], 2));
			}
			inout_tabdSigma[lIndexModel] = sqrt(inout_tabdSigma[lIndexModel] / tabdHMatrix_RowSum[lIndexModel]);
		}

		// Accuracy
		dAccuracy_TEMP = 0;
		for(lIndexModel = 0; lIndexModel < in_lNbModel; lIndexModel++)
		{
			dAccuracy_TEMP += pow(tabdProp_TEMP[lIndexModel] - inout_tabdProp[lIndexModel], 2);
			dAccuracy_TEMP += pow(tabdMu_TEMP[lIndexModel] - inout_tabdMu[lIndexModel], 2);
			dAccuracy_TEMP += pow(tabdSigma_TEMP[lIndexModel] - inout_tabdSigma[lIndexModel], 2);
		}

		if (out_lNbIteration != NULL)
		{
			(*out_lNbIteration)++;
		}
	}

	// Free memory
	delete[] tabdProp_TEMP;
	delete[] tabdMu_TEMP;
	delete[] tabdSigma_TEMP;
	delete[] tabdHMatrix;
	delete[] tabdHMatrix_RowSum;
	delete[] tabdHMatrix_ColSum;
}
