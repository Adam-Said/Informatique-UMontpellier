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

création d'un segment de memoire partagé qui sera un tableau d'entier (un élélement correspondraàune zone)
*/

int main(int argc, char * argv[]){

    if (argc!=5) {
        printf("Nbre d'args invalide, utilisation :\n");
        printf("%s nombre-traitements nombre-zones fichier-pour-cle-ipc entier-pour-clé-ipc\n", argv[0]);
        exit(0);
    }

    int cle = ftok(argv[3], atoi(argv[4]));
    if (cle == -1) {
        perror("erreur création clé : ");
        exit(-1);
    }    

    int nbSem = atoi(argv[2]);

    // Création des sémaphores
    int idSem=semget(cle, nbSem, IPC_CREAT | IPC_EXCL | 0600);
    if (idSem == -1) {
        perror("erreur création semaphore : ");
        exit(-1);
    }

    printf("sem id : %d \n", idSem);

    // initialisation des sémaphores
    ushort tabinit[nbSem];
    for (int i = 0; i < nbSem; i++) tabinit[i] = 0;


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



    // création et initialisation du segment de mémoire partagé :

    // on réutilise la méme clé puisque la numérotation des IPC dépend du type d'objet.
    int laMem = shmget(cle, atoi(argv[1])*sizeof(int), IPC_CREAT | IPC_EXCL | 0600);
    if (laMem == -1){
        perror("erreur shmget : ");
        exit(-1);
    }

    printf("creation segment de mémoire ok. mem id : %d \n", laMem);


    //attachement au segment pour pouvoir y accéder
    int * p_att = (int *)shmat(laMem, NULL, 0);
    if (p_att== (int *)-1){
        perror("erreur shmat : ");
        exit(-1);
    }

    // j'ai un pointeur sur le segment, j'initialise le tableau 

    for(int i=0; i < atoi(argv[2]); i++){
        p_att[i] = 0;
    }


    // détachement pour signaler au systéme la fin de l'utilisation du segment

    if (shmdt(p_att) == -1){
        perror("erreur shmdt : ");
        exit(-1);
    }

    return 0;
}
