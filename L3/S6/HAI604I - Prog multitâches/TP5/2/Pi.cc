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
    pid_t nproc = getpid();
    if (argc!=4) {
        cout<<"Nbre d'args invalide, utilisation :"<< endl;
        cout << argv[0] << " fichier-pour-cle-ipc entier-pour-cle-ipc nb semaphores"<< endl;
        exit(0);
    }
        
    key_t cle=ftok(argv[1], atoi(argv[2]));

    if (cle==-1) {
        perror("Erreur ftok : ");
        exit(2);
    }

    cout << "ftok ok" << endl;
        
    int semid = semget(cle, atoi(argv[3]), 0666);
    if(semid==-1) {
        perror("erreur msgget récupération de la file : ");
        exit(2);
    }
    cout << "msgget ok" << endl;

    calcul();

    printf("Calcul terminé, mise à jour du point de rendez-vous.\n");
    // On retire 1 du tableau de sémaphores car ce processus est arrivé au RDV
    struct sembuf op[] = {
        {(ushort)0, (short)-1,0},
        {(ushort)0, (short)0,0},
    };
    if (semop(semid, op, 1) == -1)
        exit(EXIT_FAILURE);

    // Récupération de semaphores[i] pour affichage
    int semValue = semctl(semid, 0, GETVAL, (semun){ .val = 0 });
    if (semValue == -1) 
        exit(EXIT_FAILURE);
    printf("Valeur actuelle de la sémaphore : %i\n", semValue);

    // Tant que semaphores[i] n'est pas à 0, on attend.
    if (semop(semid, op + 1, 1) == -1)
        exit(EXIT_FAILURE);

    printf("Fin");
    return 0;
}
