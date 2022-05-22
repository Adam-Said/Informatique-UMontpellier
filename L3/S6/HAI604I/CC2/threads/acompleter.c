
/*
 Programme ronde avec un thread bus et des threads visiteurs. 
 Les zones � compl�ter sont indiqu�es en commentaires.
 
 Les traces fournies sont suffisantes.
 
 Vous avez � votre disposition test/zoo qui est un ex�cutable vous illustrant le comportement attendu.
 
*/

#include <sys/types.h>
#include <pthread.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include "simulation.h"

struct varPartagees {
  // structure regroupant toutes le varables partag�es entre threads
  
  int nbPlaces; // nombre total de places dans le bus
  int placesLibres;
  int statutVisite;
  pthread_mutex_t* lock;
  pthread_cond_t* cond; 
  pthread_cond_t* condVisite;
};


struct params {

  // structure pour regrouper les param�tres d'un thread. 
  int idThread; // un identifiant de visiteur, de 1 � N (N le nombre total de visiteurs)
  struct varPartagees * varP;
};

// pour le thread bus
void * bus (void * p) {

  struct params * args = (struct params *) p;
  struct varPartagees * varP  = args -> varP;
  
  while(1){
  
   afficher('b', "embarquement immediat, soyez les bienvenus!", 0);
   
   // ... zone � compl�ter pour lancer l'embarquement
   //pthread_mutex_unlock(varP->lock);
    while (varP->placesLibres != 0) {
      afficher('b', "attente que le bus soit complet", 0);
      //pthread_cond_wait(varP->condVisite, varP->lock);
    }

    pthread_mutex_lock(varP->lock);
   afficher('b',"bus complet, la visite commence !", 0); 
    varP->statutVisite =1;

   visite(4); // vous pouvez changer la valeur du param�tre (voir .h)
   
   afficher('b',"visite terminee, tout le monde descend !", 0);
   varP->statutVisite = 2;
   pthread_mutex_unlock(varP->lock);
   pthread_cond_broadcast(varP->condVisite);
   
   // ... zone � compl�ter pour ordonner la descente du bus
    pthread_mutex_lock(varP->lock);
    while (varP->statutVisite != 3) {
      afficher('b', "attente que tout le monde descende", 0);
      pthread_cond_wait(varP->condVisite, varP->lock);
    }
   /* ... zone a compl�ter pour attendre que le bus soit vide
    cette zone doit inclure la ligne :
  */

    pthread_mutex_lock(varP->lock);
    varP->statutVisite = 0;
    pthread_mutex_unlock(varP->lock);
    pthread_cond_broadcast(varP->condVisite);
  }

  pthread_exit(NULL); 
}


// pour le thread visiteur
void * visiteur (void * p) {

  struct params * args = (struct params *) p;
  struct  varPartagees * varP = args -> varP;
  
   afficher('v', "je demande a monter", args -> idThread);
   
   pthread_mutex_lock(varP->lock);
   if (varP->placesLibres ==0) {
     afficher('v', "pas de place, j'attends", args -> idThread);
     pthread_cond_wait(varP->cond, varP->lock);
   }
   pthread_mutex_unlock(varP->lock);
   
   // simulation mont�e du visiteur
   pthread_mutex_lock(varP->lock);
   afficher('v', "je monte ...", args -> idThread);
   // .. zone qui pourrait �ventuellement vous �tre utile
   monterOuDescendre();
   // .. zone qui pourrait �ventuellement vous �tre utile
   afficher('v', "je suis a bord et bien installe !", args -> idThread);
   varP->placesLibres--;

   if (varP->placesLibres == 0) {
     varP->statutVisite = 1;
    pthread_cond_broadcast(varP->condVisite);
   }
   pthread_mutex_unlock(varP->lock);

  // ... zone qui peut, en fonction de votre solution, �tre utile pour compl�ter la mise en place de la mont�e.
   
   // ici se produit automatiquement la visite qui est g�r�e par le bus
   while (varP->statutVisite != 2) {
     afficher('v', "j'attends la fin de la visite", args -> idThread);
     pthread_cond_wait(varP->condVisite, varP->lock);
   }
   
   /*
   ... zone � compl�ter pour la mise en place de la descente du passager.
   Elle doit inclure la ligne :
   */
  pthread_mutex_lock(varP->lock);
   afficher('v', "visite terminee, je descends ...", args -> idThread);
   varP->placesLibres++;
   // .. zone qui pourrait �ventuellement vous �tre utile
   monterOuDescendre();
   // .. zone qui pourrait �ventuellement vous �tre utile
   afficher('v', "je suis descendu !", args -> idThread);
   if (varP->placesLibres == varP->nbPlaces) {
     varP->statutVisite = 0;
     pthread_cond_broadcast(varP->condVisite);
   }
   pthread_mutex_unlock(varP->lock);
  // ... zone qui peut, en fonction de votre solution, �tre utile pour compl�ter la mise en place de la descente.
   
  pthread_exit(NULL); 
}


int main(int argc, char * argv[]){
  
  if (argc!=3) {
    printf(" argument requis \n %s nombres_places_bus nombre_visiteurs\n", argv[0]);
    exit(1);
  }

 initDefault(atoi(argv[2])); // ne pas modifier cet appel ni le d�placer.
 
 
  // initialisations 
  pthread_t threads[atoi(argv[2])+1];
  struct params tabParams[atoi(argv[2])+1];
 
  struct varPartagees  varP;
  
  varP.nbPlaces = atoi(argv[1]);
  varP.placesLibres = atoi(argv[1]);
  pthread_mutex_t lock;
  pthread_cond_t cond;
  pthread_mutex_init(&lock, NULL);
  pthread_cond_init(&cond, NULL);
  varP.cond = &cond;
  varP.lock = &lock;
 
  // cr�ation des threads
  tabParams[0].idThread = 0;
  tabParams[0].varP = &varP; 
  if (pthread_create(&(threads[0]), NULL, bus, &(tabParams[0])) != 0){
      perror("erreur creation thread bus");
      exit(1);
    }  
  for (int i = 1; i < atoi(argv[2])+1; i++){
    tabParams[i].idThread = i;
    tabParams[i].varP = &varP; 
    if (pthread_create(&threads[i], NULL, visiteur, &(tabParams[i])) != 0){
      perror("erreur creation thread visiteur");
      exit(1);
    }
  }
  

  // attente de la fin des  threads. 
  for (int i = 0; i < atoi(argv[2])+1; i++)
    if (pthread_join(threads[i], NULL) != 0){
      perror("erreur attente thread");
      exit(1);
    }
    
  // ... zone � compl�ter pour terminer proprement votre programme.
  pthread_mutex_destroy(&lock);
  pthread_cond_destroy(&cond);
  return 0;
}
 
