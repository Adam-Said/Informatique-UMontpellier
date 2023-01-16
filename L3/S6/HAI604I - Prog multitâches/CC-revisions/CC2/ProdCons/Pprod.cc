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

    
    int msg=0;
    while(1){
        
        msg = 45486;
        const access_request request = (access_request){.mtype = 1, .nproc = nproc};
        ssize_t res = msgsnd(msgid, (const void *)&request, sizeof(request.nproc), 0);
        if (res == -1) {
            perror("Erreur lors de la demande d'accès de la variable partagée ");
            exit(EXIT_FAILURE);
        }

        res = msgsnd(msgid, &msg, sizeof(int), 0);
        if (res == -1) {
            perror("Erreur lors de la modification de la variable partagée ");
            exit(EXIT_FAILURE);
        }
        printf("Data produite...\n ");
        sleep(3);

    } 
    return 0;
} 