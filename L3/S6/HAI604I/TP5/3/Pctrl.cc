#include <cstdlib>
#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include <sys/shm.h>
#include <stdlib.h>
/*
création et initialisation d'un tableau de sémaphores pour le
traitement synchronisé. Le nombre d'éléments correspond au nombre de
traitements -1 et les valeurs initiales sont à 0 (à la case i, la
valeur corerspond à la dernière zone traitée par le processus
P_(i+1))
*/

int main(int argc, char * argv[]){

    if (argc!=5) {
        printf("Nbre d'args invalide, utilisation :\n");
        printf("%s nombre-zones valeure-init fichier-pour-cle-ipc entier-pour-clé-ipc\n", argv[0]);
        exit(0);
    }

    int cle = ftok(argv[3], atoi(argv[4]));
    if (cle == -1) {
        perror("erreur création clé : ");
        exit(-1);
    }    

    int nbSem = atoi(argv[1]);

    // Création des sémaphores
    int idSem=semget(cle, nbSem, IPC_CREAT | IPC_EXCL | 0600);
    if (idSem == -1) {
        perror("erreur création semaphore : ");
        exit(-1);
    }

    printf("sem id : %d \n", idSem);

    // initialisation des sémaphores
    ushort tabinit[nbSem];
    for (int i = 0; i < nbSem; i++) tabinit[i] = atoi(argv[2]);


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
        perror("erreur récup sem : ");
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
