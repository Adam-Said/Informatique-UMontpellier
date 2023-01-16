#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <stdlib.h>
/*
 création d'une file de messages
*/

int main(int argc, char * argv[]){
  
  if (argc!=3) {
    printf("Nbre d'args invalide, utilisation :\n");
    printf("%s fichier-pour-cle-ipc entier-pour-cle-ipc\n", argv[0]);
    exit(0);
  }
	  
  int cle = ftok(argv[1], atoi(argv[2]));

  int idFile = msgget(cle, IPC_CREAT | IPC_EXCL | 0666);
  
  if(idFile == -1){
    perror("erreur msgget : ");
    exit(-1);
  }

  printf("Id file de messages creee : %d \n", idFile);

  
  return 0;
}
