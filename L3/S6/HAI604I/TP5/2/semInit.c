#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include <stdlib.h>
/*
 exemple de cr�taion d'un tableau de s�maphores, dont le nombre
 d'�l�ments et la valeur initiale est pass�e en param�tre du
 programme (dans cet exemple, les �lements sont initialis�s � la m�me valeur)

*/

int main(int argc, char * argv[]){
  
  if (argc!=5) {
    printf("Nbre d'args invalide, utilisation :\n");
    printf("%s nombre-semaphores valeur-initiale fichier-pour-cle-ipc entier-pour-cl�-ipc\n", argv[0]);
    exit(0);
  }
	  
  int clesem = ftok(argv[3], atoi(argv[4]));

  int nbSem = atoi(argv[1]);

  int idSem=semget(clesem, nbSem, IPC_CREAT | IPC_EXCL | 0600);
  
  if(idSem == -1){
    perror("erreur semget : ");
    exit(-1);
  }

  printf("sem id : %d \n", idSem);


  
  // initialisation des s�maphores a la valeur pass�e en parametre (faire autrement pour des valeurs diff�rentes ):
 
  ushort tabinit[nbSem];
  for (int i = 0; i < nbSem; i++) tabinit[i] = atoi(argv[2]);;
 

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

  /* test affichage valeurs des s�maphores du tableau */
  valinit.array = (ushort*)malloc(nbSem * sizeof(ushort)); // pour montrer qu'on r�cup�re bien un nouveau tableau dans la suite

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
