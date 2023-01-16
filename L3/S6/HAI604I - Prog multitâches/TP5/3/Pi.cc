#include <cstdlib>
#include <iostream>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include <stdlib.h>
using namespace std;

void calcul() {
    int wait = rand()%10 + 1;
    sleep(wait);
}

union semun{
    int val;
    struct semid_ds * buf;
    ushort * array;
} valinit;

int main(int argc, char * argv[]){

    if (argc!=5) {
        cout<<"Nbre d'args invalide, utilisation :"<< endl;
        cout << argv[0] << " fichier-pour-cle-ipc entier-pour-cle-ipc nb-zones num-traitement"<< endl;
        exit(0);
    }

    int nbSem = atoi(argv[3]);
    int numProc = atoi(argv[4]);
    key_t cle=ftok(argv[1], atoi(argv[2]));
    if (cle==-1) {
        perror("Erreur ftok : ");
        exit(2);
    }

    cout << "ftok ok" << endl;

    int semid = semget(cle, nbSem, 0666);
    if(semid==-1) {
        perror("erreur msgget récupération de la file : ");
        exit(2);
    }
    cout << "msgget ok" << endl;

    valinit.array = (ushort*)malloc(nbSem * sizeof(ushort));
    if (semctl(semid, nbSem, GETALL, valinit) == -1){
        perror("erreur récup sem : ");
        exit(1);
    } 

    printf("Valeurs des sempahores avant travail [ "); 
    for(int i=0; i < nbSem-1; i++){
        printf("%d, ", valinit.array[i]);
    }
    printf("%d ] \n", valinit.array[nbSem-1]);

    for (int i =0; i < nbSem; i++) {
        struct sembuf op[] = {
            {(ushort)i, (short)(-(numProc-1)),0},
            {(ushort)i, (short)(numProc),0},
        };
        // Vérif de l'accés à la ressource
        if (semop(semid,op, 1) == -1) {
            perror("Vous n'avez pas accés\n");
            exit(-1);
        }
        printf("Calcul n°%i sur la zone %i.\n", numProc, i);
        calcul();

        printf("Calcul n°%i terminé sur la zone %i.\n", numProc, i);
        // On ajoute 1 au tableau de sémaphores car ce processus a terminé son traitement
        if (semop(semid, op+1, 1) == -1) {
            exit(-1);
        }

    }
    

    if (semctl(semid, nbSem, GETALL, valinit) == -1){
        perror("erreur récup sem : ");
        exit(1);
    } 

    printf("Valeurs des sempahores apres travail [ "); 
    for(int i=0; i < nbSem-1; i++){
        printf("%d, ", valinit.array[i]);
    }
    printf("%d ] \n", valinit.array[nbSem-1]);

    free(valinit.array);

    return 0;
}
