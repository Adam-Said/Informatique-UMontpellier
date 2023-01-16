#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include <stdlib.h>

void calcul() {
    int wait = rand()%10 + 1;
    sleep(wait);
}

int main(int argc, char * argv[]){

    if (argc!=5) {
        printf("Nbre d'args invalide, utilisation :\n");
        printf("%s nombre-semaphores valeur-initiale fichier-pour-cle-ipc entier-pour-clé-ipc\n", argv[0]);
        exit(0);
    }

    int clesem = ftok(argv[3], atoi(argv[4]));
    if (clesem==-1) {
        perror("Erreur ftok : ");
        exit(2);
    }
        
    int nbSem = atoi(argv[1]);

    int idSem=semget(clesem, nbSem, IPC_CREAT | IPC_EXCL | 0600);
    if(idSem == -1){
        perror("erreur semget : ");
        exit(-1);
    }

    printf("sem id : %d \n", idSem);

    // initialisation des sémaphores a la valeur passée en parametre (faire autrement pour des valeurs différentes ):
    ushort tabinit[nbSem];
    for (int i = 0; i < nbSem; i++) tabinit[i] = atoi(argv[2]);


    union semun{
        int val;
        struct semid_ds * buf;
        ushort * array;
    } valinit;

    valinit.array = tabinit;

    if (semctl(idSem, nbSem, SETALL, valinit) == -1){
        perror("erreur initialisation sémaphores : ");
        exit(1);
    }

    /* test affichage valeurs des sémaphores du tableau */
    valinit.array = (ushort*)malloc(nbSem * sizeof(ushort)); // pour montrer qu'on récupére bien un nouveau tableau dans la suite

    if (semctl(idSem, nbSem, GETALL, valinit) == -1){
        perror("erreur initialisation sem : ");
        exit(1);
    } 

    printf("Valeurs des sempahores apres initialisation [ "); 
    for(int i=0; i < nbSem-1; i++){
        printf("%d, ", valinit.array[i]);
    }
    printf("%d ] \n", valinit.array[nbSem-1]);
    
    struct sembuf op[] = {{0,0,0}};  
    if (semop(idSem, op, 1) == -1) {
        perror("Erreur op : ");
    }

    // destruction :
    printf("Destruction sem\n");
    if (semctl(idSem, 0, IPC_RMID, NULL)==-1)
        perror(" erreur semctl : ");

    free(valinit.array);
    return 0;
}
