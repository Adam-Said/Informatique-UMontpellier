#include <string.h>
#include <stdlib.h>
#include <sys/types.h>
#include <iostream>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <stdio.h>//perror
#include <unistd.h>
using namespace std;

struct strMonMsg {
    long monetiquette ;
    char data[1024] ;
} ;

struct access_request {
    long mtype;
    int nproc;
};


int main(int argc, char * argv[]){
    pid_t nproc = getpid();
    if (argc!=3) {
        cout<<"Nbre d'args invalide, utilisation :"<< endl;
        cout << argv[0] << " fichier-pour-cle-ipc entier-pour-cle-ipc"<< endl;
        exit(0);
    }

    key_t cle=ftok(argv[1], atoi(argv[2]));

    if (cle==-1) {
        perror("Erreur ftok : ");
        exit(2);
    }

    cout << "ftok ok" << endl;

    int msgid = msgget(cle, 0666);
    if(msgid==-1) {
        perror("erreur msgget récupération de la file : ");
        exit(2);
    }
    cout << "msgget ok" << endl;

    while (1) {
        printf("Entrez un message: ");
        char message[1024];
        fgets(message, 1024, stdin);

        // Demande d'accès à la variable de messages
        const access_request request = (access_request){.mtype = 1, .nproc = nproc};
        ssize_t res = msgsnd(msgid, (const void *)&request, sizeof(request.nproc), 0);
        if (res == -1) {
            perror("Erreur lors de la demande d'accès de la variable partagée ");
            exit(EXIT_FAILURE);
        }

        // Réception de la variable partagée
        strMonMsg data;
        res = msgrcv(msgid, (void *)&data, sizeof(data.data), nproc, 0);
        if (res == -1) {
            perror("Erreur lors de la réception de la variable partagée ");
            exit(EXIT_FAILURE);
        }
        printf("Var partagée : %s\n",data.data);
        strcpy(data.data, message);

        data.monetiquette = 2;
        res = msgsnd(msgid, (const void *)&data, strlen(data.data), 0);
        if (res == -1) {
            perror("Erreur lors de la modification de la variable partagée ");
            exit(EXIT_FAILURE);
        }

        printf("Variable partagée: %s\n", message);
    }
    
    return 0;
}
