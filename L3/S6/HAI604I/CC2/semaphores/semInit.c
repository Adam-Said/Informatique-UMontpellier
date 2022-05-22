#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include <stdlib.h>
/*
 création et initialisation d'un tableau de sémaphores. Le nombre de sémaphores est à passer en paramètre et les valeurs initiales seront à saisir au clavier.
*/

int main(int argc, char * argv[]){
  
  if (argc!=4) {
    printf("Nbre d'args invalide, utilisation :\n");
    printf("%s nombre-de-sempahores-du-tableau fichier-pour-cle-ipc entier-pour-cle-ipc\n", argv[0]);
    exit(0);
  }
	  
  int cle = ftok(argv[2], atoi(argv[3]));

  int nbSem = atoi(argv[1]);

  int idSem=semget(cle, nbSem, IPC_CREAT | IPC_EXCL | 0666);
  
  if(idSem == -1){
    perror("erreur semget : ");
    exit(-1);
  }

  printf("Id tableau de semaphores : %d \n", idSem);


  
  // initialisation des sémaphores à 0
 
  ushort tabinit[nbSem];
  printf("Entrer les valeurs initiales pour %d sémaphores dans l'ordre\n", nbSem);
  for (int i = 0; i < nbSem; i++)
   scanf("%hd", &(tabinit[i]));
 

  union semun{
    int val;
    struct semid_ds * buf;
    ushort * array;
  } valinit;
  
  valinit.array = tabinit;

  if (semctl(idSem, nbSem, SETALL, valinit) == -1){
    perror("erreur initialisation sem : ");
    exit(1);
  }

  /* test affichage des valeurs des sémaphores du tableau */
  valinit.array = (ushort*)malloc(nbSem * sizeof(ushort));

  if (semctl(idSem, nbSem, GETALL, valinit) == -1){
    perror("erreur initialisation sem : ");
    exit(1);
  } 
   
  printf("Valeurs des sempahores apres initialisation [ "); 
  for(int i=0; i < nbSem-1; i++){
    printf("%d, ", valinit.array[i]);
  }
  printf("%d ] \n", valinit.array[nbSem-1]);

  free(valinit.array);

  return 0;
}
