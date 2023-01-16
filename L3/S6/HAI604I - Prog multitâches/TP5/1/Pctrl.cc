#include <stdlib.h>
#include <sys/types.h>
#include <iostream>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <string.h>
#include <stdio.h>//perror
using namespace std;

struct sMsg {
    long etiq ; 
    char mot[1024];
};

struct access_request {
    long mtype;
    int nproc;
};

int main(int argc, char * argv[]){

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
        
    int msgid = msgget(cle, IPC_CREAT| IPC_EXCL| 0666);
    if(msgid==-1) {
        perror("erreur msgget création de la file : ");
        exit(2);
    }
    cout << "msgget ok" << endl;

    char message[1024] = "Msg";

    while (1) {
        // Attente d'une demande d'accès
        access_request processusRequest;
        ssize_t res = msgrcv(msgid, (void *)&processusRequest, sizeof(processusRequest.nproc), 1, 0);
        if (res == -1) {
            perror("Erreur lors de la demande d'accès à la variable partagée ");
            // Destruction de la file et au revoir.
            if (msgctl(msgid, IPC_RMID, NULL) == -1) {
                perror("erreur suppression file de message :");
            }
            cout << "suppression file et sortie" << endl;
        }

        printf("Accès donné au processus %i\n", processusRequest.nproc);

        // Envoie de la donnée au processus
        sMsg dataSent = (sMsg){.etiq = processusRequest.nproc};
        strcpy(dataSent.mot, message);
        res = msgsnd(msgid, (const void *)&dataSent, sizeof(dataSent.mot), 0);
        if (res == -1) {
            perror("Erreur lors de l'envoie du message dans la file de messages ");
            if (msgctl(msgid, IPC_RMID, NULL) == -1) {
                perror("erreur suppression file de message :");
            }
            cout << "suppression file et sortie" << endl;
        }

        printf("Variable envoyée.\n");

        // Attente de la réception de la « finition » de la consultation ou modification du message.
        sMsg dataReceived;
        res = msgrcv(msgid, (void *)&dataReceived, sizeof(dataReceived.mot), 2, 0);
        if (res == -1) {
            perror("Erreur lors de la récupération de la variable partagée ");
            // Destruction de la file et au revoir.
            if (msgctl(msgid, IPC_RMID, NULL) == -1) {
                perror("erreur suppression file de message :");
            }
            cout << "suppression file et sortie" << endl;
        }

        // Copie dans la variable locale de message
        printf("Message: %s\n", dataReceived.mot);
    }

    if (msgctl(msgid, IPC_RMID, NULL) == -1) {
        perror("erreur suppression file de message :");
    }
    cout << "suppression file ok" << endl;


    return 0;
}
