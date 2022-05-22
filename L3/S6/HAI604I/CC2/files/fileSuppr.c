/*
  Description : 
  Le programme dertruit une file de messages si elle existe.
  
*/

#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <stdlib.h>

int main(int argc, char * argv[]){

  if (argc!=3) {
    printf("Nbre d'args invalide, utilisation :\n");
    printf("%s fichier-pour-cle-ipc entier-pour-cle-ipc\n", argv[0]);
    exit(0);
  }
	  
  int cleFile=ftok(argv[1], atoi(argv[2]));

  int idFile = msgget(cleFile, 0666);

  // j'utilise msgget de sorte a s'assurer que la file existe.
  if (idFile==-1){
    perror("erreur  msgget");
    exit(1);
  }

  printf("Id file de messages : %d \n", idFile);

  // destruction :
  if (msgctl(idFile, IPC_RMID, NULL)==-1)
    perror(" erreur msgctl : ");
  else	
    printf("File detruite \n");

  return 0;
}

