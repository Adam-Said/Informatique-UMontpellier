/*
  Programme visiteur à compléter : les zones à compléter sont indiquées et il n'est pas nécessaire d'ajouter de nouvelles traces d'exécution. 
  
 Vous devez expliquer en commentaires : le sens donné au messages echangés et aux étiquettes.
 
  Attention : dans ce programme, il n'y a aucun besoin de simuler un temps de visite. Le temps de visite est géré par le programme bus.
   
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
    printf("%s monNumero fichier-pour-cle-ipc entier-pour-cle-ipc\n", argv[0]);
    exit(0);
  }
	 
  initDefault(50); // ne pas modifier ni déplacer cet appel.
  
  int cle =ftok(argv[2], atoi(argv[3]));
  if (cle==-1) {
      perror("Erreur ftok : ");
      exit(2);
  }
  int idFile = msgget(cle, 0666);

  // j'utilise msgget de sorte a s'assurer que la file existe.
  if (idFile == -1){
    perror("erreur  msgget");
    exit(-1);
  }

  int monNum = atoi(argv[1]);
  
  printf("Visiteur %d : Id file msg : %d \n", monNum, idFile);
  printf("Visiteur %d: je demande a monter \n", monNum);
  struct sMsg dataSent;
  dataSent.etiq =1;
  dataSent.places--;
  int res = msgsnd(idFile, (const void *)&dataSent, sizeof(dataSent.places), 0);
      if (res == -1) {
          perror("Erreur lors de l'envoie du message dans la file de messages ");
          if (msgctl(idFile, IPC_RMID, NULL) == -1) {
              perror("erreur suppression file de message :");
          }
          perror("suppression file et sortie");
      }

   printf("Visiteur %d : je monte ...\n", monNum);
   monterOuDescendre();
 
  printf("Visiteur %d : je suis a bord et bien installe\n", monNum);
  

  struct sMsg dataReceived;
  res = msgrcv(idFile, (void *)&dataReceived, sizeof(dataReceived.places), 2, 0);
  if (res == -1) {
      perror("Erreur lors de la récupération de la variable partagée ");
      // Destruction de la file et au revoir.
      if (msgctl(idFile, IPC_RMID, NULL) == -1) {
          perror("erreur suppression file de message :");
      }
      perror("suppression file et sortie");
  }
  /* ... zone à compléter pour la mise en place de la descente.
    Quelque part dans cette zone, vous devez inclure les deux lignes suivantes :
   

  
  */
  printf("Visiteur %d : visite terminee, je descends ...\n", monNum);
  monterOuDescendre();
  dataSent.etiq =3;
  dataSent.places++;
  res = msgsnd(idFile, (const void *)&dataSent, sizeof(dataSent.places), 0);
      if (res == -1) {
          perror("Erreur lors de l'envoie du message dans la file de messages ");
          if (msgctl(idFile, IPC_RMID, NULL) == -1) {
              perror("erreur suppression file de message :");
          }
          perror("suppression file et sortie");
      }
  printf("Visiteur %d : je suis descendu.\n", monNum);  
 
  return 0;
}

