/*
  Programme bus � compl�ter. Les zones � compl�ter sont indiqu�es et il n'est pas n�cessaire d'ajouter de nouvelles traces d'ex�cution.
  Vous devez expliquer en commentaires : le sens donn� au messages echang�s et aux �tiquettes.
*/

#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <stdlib.h>
#include "simulation.h"

struct sMsg {
    long etiq ; 
    int places;
};

struct access_request {
    long mtype;
    int nproc;
};

int main(int argc, char * argv[]){

  if (argc!=4) {
    printf("Nbre d'args invalide, utilisation :\n");
    printf("%s nombre-places fichier-pour-cle-ipc entier-pour-cle-ipc\n", argv[0]);
    exit(0);
  }


  int cle=ftok(argv[2], atoi(argv[3]));
    if (cle==-1) {
        perror("Erreur ftok : ");
        exit(2);
    }
  int idFile = msgget(cle, IPC_CREAT| IPC_EXCL| 0666);

  // j'utilise msgget de sorte a s'assurer que la file existe.
  if (idFile==-1){
    perror("erreur  msgget");
    exit(-1);
  }

  printf("Bus : Id File msg : %d \n", idFile);
  
  int nbPlaces = atoi(argv[1]);
  
  // ... zone � utiliser si n�cessaire
     
  
  while(1){
     // les traces d'ex�cution sont � garder inchang�es.
 
    printf("Bus : embarquement immediat, soyez les bienvenus! \n");
    
    printf("Bus : attente que le bus soit complet \n");
    for (int i =0; atoi(argv[1]); i++) {
      struct access_request processusRequest;
      int res = msgrcv(idFile, (void *)&processusRequest, sizeof(processusRequest.nproc), 1, 0);
      if (res == -1) {
          perror("Erreur lors de la demande d'accès à la variable partagée ");
          // Destruction de la file et au revoir.
          if (msgctl(idFile, IPC_RMID, NULL) == -1) {
              perror("erreur suppression file de message :");
              exit(-1);
          }
          printf("suppression file et sortie");
          exit(-1);
      }
      printf("Visiteur %i est monté\n",processusRequest.nproc);
    }
   
    printf("Bus : bus complet, la visite commence !\n");
    
    visite(2);  // simulation de la visite. voir .h pour le param�tre
    
    printf("Bus : visite terminee, tout le monde descend !\n"); 
    struct sMsg dataSent;
    dataSent.etiq =2;
    for (int i = 0; i< nbPlaces; i++) {
      int res = msgsnd(idFile, (const void *)&dataSent, sizeof(dataSent.etiq), 0);
        if (res == -1) {
          perror("Erreur lors de l'envoie du message dans la file de messages ");
          if (msgctl(idFile, IPC_RMID, NULL) == -1) {
              perror("erreur suppression file de message :");
              exit(-1);
          }
          perror("suppression file et sortie");
          exit(-1);
      }

      printf("demande sortie envoyée.\n");
    }


    printf("Bus : attendre que le bus soit vide\n");
    for (int i = 0; i < nbPlaces; i++) {
      struct access_request processusRequest;
      ssize_t res = msgrcv(idFile, (void *)&processusRequest, sizeof(processusRequest.nproc), 3, 0);
      if (res == -1) {
          perror("Erreur lors de la demande d'accès à la variable partagée ");
          // Destruction de la file et au revoir.
          if (msgctl(idFile, IPC_RMID, NULL) == -1) {
              perror("erreur suppression file de message :");
              exit(-1);
          }
          printf("suppression file et sortie");
          exit(-1);
      }
      printf("Visiteur %i est descendu\n",processusRequest.nproc);
    }
   
  }
  return 0;
}

