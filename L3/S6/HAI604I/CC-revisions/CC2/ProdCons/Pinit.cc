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
    int data;
};

struct access_request {
    long mtype;
    int nproc;
};

int main(int argc, char * argv[]){
    key_t cle = ftok("pourCle.txt", 10);
    if (cle==-1) {
        perror("Erreur ftok : ");
        exit(2);
    }
    int msgid = msgget(cle, IPC_CREAT| IPC_EXCL| 0666);
    if(msgid==-1) {
        perror("erreur msgget création de la file : ");
        exit(2);
    }
    cout << "msgget ok" << endl;

    int buffer;

    while(1){
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

        sMsg data;
        res = msgrcv(msgid, (void *)&data.data, sizeof(data.data), 1, 0);
        if (res == -1) {
            perror("Erreur lors de la réception de la variable partagée ");
            exit(EXIT_FAILURE);
        }
        printf("Var partagée : %d\n",data.data);
    } 
    return 0;
} 