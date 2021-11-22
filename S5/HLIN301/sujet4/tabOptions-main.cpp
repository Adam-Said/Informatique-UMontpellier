#include <iostream>
#include "option.h"
#include "tabOptions.h"

using namespace std;

#define HELP_ID          1
#define VERSION_ID       2
#define DIMENSION_ID    10
#define PROBABILITY_ID  11
#define CONFIG_ID       20

int main(int argc, char** argv) {

  TabOptions opts;
  
  opts.addOption( Option (HELP_ID, "--help", "-h",
			"Affiche l'aide du programme", Option::AUCUN));
  opts.addOption(Option(VERSION_ID, "--version", "-v",
			"Affiche la version du programme", Option::AUCUN));
  opts.addOption(Option(DIMENSION_ID, "--dimension", "-N",
			"Initialise une matrice carrée de la dimension spécifiée",Option::ENTIER));
  opts.addOption(Option(PROBABILITY_ID, "--probability", "-p",
			"Probabilité d'une cellule d'être en vie au démarrage",Option::REEL));
  opts.addOption(Option(CONFIG_ID, "--config", "-f",
			"Charge la configuration initiale du jeu à partir du fichier passé en paramètre",Option::CHAINE));



  
  cout << "Options passées en ligne de commande au programme "
       << argv[0] << " :" << endl;

  for (int i = 1; i < argc ; i++) {
    if (opts.getOptionId(argv[i]) != -1) {
      cout << "L'option " << argv[i] << " a été trouvée.";
      if (opts.optionHasArgument(argv[i])) {
	cout << " Elle attend un argument de type "
	     << Type2String(opts.optionArgumentType(argv[i]));
	cout << " => " << (++i < argc ? argv[i] : "Erreur");
      }
      cout << endl; 
    } else {
      cout << "Usage : " << argv[0] << " [Options]" << endl;
      opts.print();
      return 1;
    }
  }
  return 0;
}

