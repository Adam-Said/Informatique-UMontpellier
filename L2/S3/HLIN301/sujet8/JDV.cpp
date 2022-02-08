#include <iostream>
#include <fstream>
#include <sstream>
#include <cstdlib>
#include <unistd.h>
#include "JDV.h"

using namespace std;
static size_t num_ligne = 0,
  Dimension_init = 0,
  Probability_init = 0,
  LogFile_init = 0,
  Delay_init = 0,
  MaxCycles_init = 0;

JDV::JDV(int argc, char** argv):
  cur_cycle(0), max_cycles(0), delay(0), logfile_pattern(),
  population(8, 0.25), opts() {
  initOptions();
  parseOptions(argc, argv);
}

size_t JDV::getDelay() const { return delay; }
size_t JDV::getCurrentCycle() const { return cur_cycle; }
size_t JDV::getMaxCycles() const { return max_cycles; }
void JDV::setDelay(size_t ms) { delay = ms; }
void JDV::setMaxCycles(size_t nb) { max_cycles = nb; }

string JDV::getLogFilePattern() const { return logfile_pattern; }
void JDV::setLogFilePattern(const string &logfile) { logfile_pattern = logfile; }
string JDV::getCurrentLogFile() const {
  stringstream curfile;
  size_t n = logfile_pattern.length();
  string mot = "<cpt>";
  if (n >= 5) {
    size_t i = 0;
    bool found = false;
    while (!found && (i < n - 5)) {
      size_t j = 0;
      found = true;
      while (found && (j < 5)) {
	found = logfile_pattern[i+j] == mot[j];
	j++;
      }
      if (!found) {
	curfile << logfile_pattern[i++];
      }
    }
    if (found) {
      curfile << cur_cycle;
      for(i += 5; i < n; i++) {
	curfile << logfile_pattern[i];
      }
    } else {
      curfile.clear();
    }
  }
  if (curfile == "") {
    curfile.clear();
    curfile << logfile_pattern << cur_cycle;
  }
  return curfile.str();
}

void JDV::log() const {
  if (logfile_pattern == "") {
    return;
  }
  string curfile = getCurrentLogFile();
  ofstream log(curfile.c_str());
  if (!log.is_open()) {
    cerr << "Avertissement: Impossible d'ouvrir le fichier '"
	 << curfile << "'" << endl;
    return;
  }
  time_t tps = time(NULL);
  log <<
    "#\n"
    "# Fichier de journal pour le cycle " << cur_cycle <<".\n"
    "# Nom : '" << curfile << "'\n"
    "# Date de création : " << ctime(&tps) << "\n"
    "#\n"
    "\n"
    "# Le mot-clé 'Dimension' reçoit un entier compris entre 3 et Nmax.\n"
    "# Il ne doit pas se situer après le mot-clé 'Cell' dans le fichier.\n"
    "# Dimension : 8\n"
    "Dimension : " << population.getDimension()
      << " # Si ce mot-clé n'est pas spécifié,\n"
    "               # la dimension par défaut sera de 8\n"
    "\n"
    "# Le mot-clé 'Probability' reçoit un réel compris entre 0 et 1.\n"
    "# Il ne doit pas se situer après le mot-clé 'Cell' dans le fichier.\n"
    "# Probability : 0.25\n"
    "Probability : 0 # La probabilité originelle était "
      << population.getProbability() << "\n"
    "                   # (ici celle-ci est mise à 0 pour pouvoir "
    "reprendre la partie en cours)\n"
    "                   # Si ce mot-clé n'est pas spécifié,\n"
    "                   # la probabilité par défaut sera de 25%.\n"
    "\n"
    "# Le mot-clé 'MaxCycles' reçoit un entier positif ou nul.\n"
    "# MaxCycles : 0 (= non défini)\n"
    "MaxCycles : " << (getMaxCycles() - cur_cycle)
      << " # Le nombre de cycles maximum originel était " << getMaxCycles()
      << "\n"
    "               # (ici celui-ci a été adapté pour pouvoir "
    "reprendre la partie en cours)\n"
    "               # Si ce mot-clé n'est pas spécifié ou bien s'il est "
    "positionné à 0,\n"
    "               # le nombre de cycles ne sera pas borné.\n"
    "\n"
    "# Le mot-clé 'Delay' reçoit un entier positif ou nul qui est le nombre\n"
    "# de millisecondes entre 2 cycles.\n"
    "# Delay : 150\n"
    "Delay : " << getDelay() << " # Si ce mot-clé n'est pas spécifié,\n"
    "          # le délai entre 2 cycles sera de 150ms.\n"
    "\n"
    "# Le mot-clé 'LogFile' permet de définir un patron "
    "pour les fichiers de journaux.\n"
    "# Ce patron de fichier peut contenir le motif '<cpt>' "
    "qui sera remplacé par le\n"
    "# numéro du cycle en cours. En l'absence de ce motif, "
    "le numéro de cycle en cours\n"
    "# sera ajouté à la fin du patron de fichier.\n"
    "# LogFile : TraceJV_<cpt>.log\n"
    "LogFile : " << getLogFilePattern()
      << " # Si ce mot-clé n'est pas spécifié,\n"
    "                            # aucun journal ne sera généré\n"
    "\n"
    "# Le mot-clé 'Cell' reçoit un couple d'entiers séparés "
    "par une (ou plusieurs)\n"
    "# espace(s) ' ', une virgule (',') ou la lettre 'x'. "
    "Chaque cellule ainsi déclarée\n"
    "# est crée vivante, les autres sont crées vivantes ou "
    "mortes en fonction de la\n"
    "# probabilité fournie." << endl;
  for (size_t i = 0; i < population.getDimension(); i++) {
    for (size_t j = 0; j < population.getDimension(); j++) {
      if (population(i, j).getVivante()) {
	log << "Cell : " << i << "x" << j << endl;
      }
    }
  }
  log << "\n"
    "# ATTENTION : les mots clés Dimension et Probability "
    "doivent impérativement\n"
    "# précéder la déclaration des cellules, sans quoi "
    "celles-ci seraient détruites.\n"
    "\n"
    "# Fin du fichier de journal." << endl;
  log.close();
}

bool Population::operator==(const Population &p) const {
  bool eq = (T.size() == p.T.size()) && (N == p.N);
  size_t i = 0;
  while (eq && i < T.size()) {
    eq = (T[i].getX() == p.T[i].getX())
      && (T[i].getY() == p.T[i].getY());
    i++;
  }
  return eq;
}

void JDV::run() {
  cur_cycle = 0;
  bool suivant;
  Population tmp(0, 0), last(0, 0);
  do {
    cout << "\033[2J\033[1;1fTour " << ++cur_cycle;
    if (max_cycles) {
      cout << " (max: " << max_cycles << ")";
    }
    cout << "." << endl
	 << population;
    log();
    usleep(delay * 1000);
    suivant = (population.nb_deces() || population.nb_naissances());
    if (suivant) {
      tmp = population++;
      suivant &= !(last == population);
    }
    last = tmp;
  } while ((!max_cycles || (cur_cycle < max_cycles)) && suivant);
  cout << "Ce jeu de la vie "
       << (cur_cycle < max_cycles ? "s'est terminé" : "a été interrompu")
       << " après " << cur_cycle << " cycles." << endl;
}

#define HELP_ID          1
#define VERSION_ID       2
#define DIMENSION_ID    10
#define PROBABILITY_ID  11
#define CONFIG_ID       20
#define LOGFILE_ID      21
#define DELAY_ID        50
#define MAXCYCLES_ID    51

void JDV::initOptions() {
  opts += Option(HELP_ID, "--help", "-h",
		 "Affiche l'aide du programme", Option::AUCUN);
  opts += Option(VERSION_ID, "--version", "-v",
		 "Affiche la version du programme", Option::AUCUN);
  opts += Option(DIMENSION_ID, "--dimension", "-N",
		 "Initialise une matrice carrée de la dimension spécifiée",
		 Option::ENTIER);
  opts += Option(PROBABILITY_ID, "--probability", "-p",
		 "Probabilité d'une cellule d'être en vie au démarrage",
		 Option::REEL);
  opts += Option(CONFIG_ID, "--config", "-f",
		 "Charge la configuration initiale du jeu "
		 "à partir du fichier passé en paramètre",
		 Option::CHAINE);
  opts += Option(DELAY_ID, "--delay", "-D",
		 "Délai entre deux tours en millisecondes",
		 Option::ENTIER);
  opts += Option(MAXCYCLES_ID, "--max-cycles", "-M",
		 "Nombre de cycles maximum à afficher",
		 Option::ENTIER);
  opts += Option(LOGFILE_ID, "--logfile", "-l",
		 "Patron du fichier de journal. "
		 "Ce fichier peut contenir le motif '<cpt>' qui sera remplacé "
		 "par le numéro du cycle en cours. En l'absence de ce motif, "
		 "Le numéro de cycle en cours sera ajouté à la fin du patron de fichier.",
		 Option::CHAINE);
}

#define ParamArg(action)						\
  if (i + 1 < argc) {							\
    action;								\
  } else {								\
    cout << "l'option '" << argv[i] << "' attend un argument de type "	\
	 << Type2String(opts.optionArgumentType(argv[i]));		\
    opt_error = true;							\
  }

void JDV::parseOptions(int argc, char** argv) {
  bool opt_error = false;
  int i = 1;
  while (!opt_error && i < argc) {
    int x = opts.getOptionId(argv[i]);
    switch (x) {
    case HELP_ID:
      cout << "usage " << argv[0] << " [Options]" << endl
	   << opts;
      exit(EXIT_SUCCESS);
      break;
    case VERSION_ID:
      cout << "Programme " << argv[0] << " version 0.0.0" << endl;
      exit(EXIT_SUCCESS);
      break;
    case DIMENSION_ID:
      ParamArg(population.setDimension(atoi(argv[++i]));
	       population.reset());
      break;
    case PROBABILITY_ID:
      ParamArg(population.setProbability(atof(argv[++i]));
	       population.reset());
      break;
    case CONFIG_ID:
      ParamArg(readConfig(argv[++i]));
      break;
    case DELAY_ID:
      ParamArg(delay = atoi(argv[++i]));
      break;
    case MAXCYCLES_ID:
      ParamArg(max_cycles = atoi(argv[++i]));
      break;
    case LOGFILE_ID:
      ParamArg(setLogFilePattern(argv[++i]));
      break;
    default:
      if (opts.optionHasArgument(argv[i])) {
	cout << "L'option " << argv[i] << " a été trouvée.";
	cout << " Elle attend un argument de type "
	     << Type2String(opts.optionArgumentType(argv[i]));
	cout << " => " << (++i < argc ? argv[i] : "Erreur");
      } else {
	cout << "L'option '" << argv[i] << "' n'a pas été reconnue."; 
	opt_error = true;
      }
      cout << endl; 
    }
    i++;
  }
  if (opt_error) {
    cout << "Usage : " << argv[0] << " [Options]" << endl
	 << opts;
    terminate();
  }
}

size_t JDV::chercheDelim(const string &txt, size_t deb, size_t fin, char c) const {
  size_t i = deb;
  while ((i < fin) && (txt[i] != c)) {
    i++;
  }
  return i;
}

string JDV::nettoieChaine(const string &txt, size_t deb, size_t fin) const {
  string res = "";
  while ((deb < fin) && ((txt[deb] == ' ') || (txt[deb] == '\t'))) {
    deb++;
  }
  while ((deb < fin) && ((txt[fin - 1] == ' ') || (txt[fin - 1] == '\t'))) {
    fin--;
  }
  while (deb < fin) {
    res += txt[deb++];
  }
  return res;
}
#define DefCleUnique(mot, action)					\
  if (cle == #mot) {							\
    if (!mot ## _init) {						\
      action;								\
      mot ## _init = num_ligne;						\
    } else {								\
      cerr << "Le mot clé '" #mot "' a déjà été rencontré à la ligne "	\
	   << mot ## _init << ". Il est ignoré à la ligne "		\
	   << num_ligne << "." << endl;					\
    }									\
  } else
void JDV::traiteOption(const string &cle, const string &valeur) {
  DefCleUnique(Dimension,
	       population.setDimension(atoi(valeur.c_str()));
	       population.reset());
  DefCleUnique(Delay, setDelay(atoi(valeur.c_str())));
  DefCleUnique(LogFile, setLogFilePattern(valeur));
  DefCleUnique(MaxCycles, setMaxCycles(atoi(valeur.c_str())));
  DefCleUnique(Probability,
	       population.setProbability(atof(valeur.c_str()));
	       population.reset());
  if (cle == "Cell") {
    size_t x, y, n = valeur.length();
    size_t pos = chercheDelim(valeur, 0, n, 'x');
    if (pos == n) {
      pos = chercheDelim(valeur, 0, n, ',');
      if (pos == n) {
        pos = chercheDelim(valeur, 0, n, ' ');
      }
    }
    if (pos == n) {
      cerr << "Le fichier est mal formé. Vérifiez la syntaxe de la ligne "
	   << num_ligne << endl;
    } else {
      x = atoi(nettoieChaine(valeur, 0, pos).c_str());
      y = atoi(nettoieChaine(valeur, pos + 1, n).c_str());
      Cellule tmp = population(x, y);
      if (!tmp.getVivante()) {
	population.birth(x, y);
      }
    }
  }
}

void JDV::readConfig(const char* fichier) {
  ifstream fich(fichier);

  if (!fich.is_open()) {
    cerr << "Le fichier " << fichier << " n'a pas pu être ouvert." << endl;
    terminate();
  }
  num_ligne = 0;
  population.reset();
  while (!fich.eof()) {
    string ligne;
    bool ok = true;
    getline(fich, ligne);
    num_ligne++;
    // Calcule la position de fin de la ligne (avant commentaire)
    size_t n = chercheDelim(ligne, 0, ligne.length(), '#');
    // Recherche le séparateur clé/valeur
    size_t p = chercheDelim(ligne, 0, n, ':');
    if (p < n) { // Un couple clé : valeur a été trouvé
      // assigne la clé et la valeur.
      string cle = nettoieChaine(ligne, 0, p);
      string val = nettoieChaine(ligne, p+1, n);
      if ((cle != "") && (val != "")) {
        traiteOption(cle, val);
      } else {
        ok = false;
      }
    } else {
      ok = nettoieChaine(ligne, 0, n) == "";
    }
    if (!ok) {
      cerr << "Avertissement: La ligne " << num_ligne
        << " du fichier " << fichier << " est mal formée:" << endl
        << "'" << ligne << "'" << endl;
    }
  }
  fich.close();
}
