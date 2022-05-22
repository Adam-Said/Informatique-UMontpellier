#include <stdlib.h>
#include <sys/types.h>
#include <iostream>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <string.h>
#include <stdio.h>//perror
#include <unistd.h>
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
    pid_t nproc = getpid();
    key_t cle = ftok("pourCle.txt", 10);
    if (cle==-1) {
        perror("Erreur ftok : ");
        exit(2);
    }
    int msgid = msgget(cle, 0666);
    if(msgid==-1) {
        perror("erreur msgget création de la file : ");
        exit(2);
    }
    cout << "msgget ok" << endl;

    
    while(1){
        int message;
        const access_request request = (access_request){.mtype = 1, .nproc = nproc};
        ssize_t res = msgsnd(msgid, (const void *)&request, sizeof(request.nproc), 0);
        if (res == -1) {
            perror("Erreur lors de la demande d'accès de la variable partagée ");
            exit(EXIT_FAILURE);
        }
        printf("Accès reçu\n ");
        // Réception de la variable partagée
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