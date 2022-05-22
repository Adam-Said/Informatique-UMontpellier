#include <netinet/in.h>
#include <stdio.h> 
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
#include <stdlib.h>
#include <arpa/inet.h>
#include <string.h>

/* Programme serveur TCP */

int main(int argc, char *argv[]) {
    /* etape 0 : gestion des paramètres si vous souhaitez en passer */
    if (argc > 2) {
        printf("Utilisation : [port_serveur]\n");
        exit(1);
    }
    struct sockaddr_in socket_srv;
    struct sockaddr_in socket_clt;
    socklen_t size =sizeof(struct sockaddr_in);
    /* etape 1 : creer une socket d'écoute des demandes de connexions*/
    int srv = socket(PF_INET, SOCK_STREAM, 0);
    if (srv == -1) {
        perror("Serveur : problème lors de la création de la socket");
        exit(1);
    }
    printf("Serveur : création de la socket réussi !\n");
    /* etape 2 : nommage de la socket */
    socket_srv.sin_family = AF_INET;
    socket_srv.sin_addr.s_addr = INADDR_ANY;
    socket_srv.sin_port = htons((short)atoi(argv[1]));
    int res = bind(srv, (struct sockaddr *)&socket_srv, sizeof(socket_srv));

    if (res == -1) {
        perror("Serveur : problème lors du nommage de la socket");
        exit(1);
    }

    /* etape 3 : mise en ecoute des demandes de connexions */
    int srvListen = listen(srv, 10);
    if (srvListen == -1) {
        perror("Serveur : problème lors de la mise en écoute de la socket");
        exit(1);
    }
    printf("Serveur : socket serveur sur écoute.\n");

        int newConnetion = accept(srv, (struct sockaddr *)&socket_clt, &size);
        if(newConnetion == -1) {
            perror("[SERVEUR] Erreur lors d'une connexion entrante");
            exit(1);
        }
        printf("Serveur : connexion du client (%s:%i)\n", inet_ntoa(socket_clt.sin_addr), ntohs((short) socket_clt.sin_port));
        /* Etape 5 : recevoir un message du client (voir sujet pour plus de détails)*/
        int msgSize;
        char* msg;
        int resTaille = recv(newConnetion, &msgSize, sizeof(int),0);
        if (resTaille == -1) {
            perror("[SERVEUR] Erreur lors de la réception de la taille ");
            exit(1);
        }
        else if (resTaille == 0)
        {
            printf("[SERVEUR] Le client a fermé la connexion");
            exit(1);
        }
        else {
            printf("[Serveur] : taille du message %i octets\n", msgSize);
            msg = (char*) malloc(msgSize);
            res = recv(newConnetion,msg,msgSize,0);
            if (res == -1)
            {
                perror("[SERVEUR] Erreur lors de la réception du message ");
                exit(1);
            }
            else if (res == 0)
            {
                printf("[SERVEUR] Le client a fermé la connexion");
                exit(1);
            }
            printf("[Serveur] : nombre d'octet : %i, message reçu : %s\n", res, msg);
        }
        
        /* Etape 6 : envoyer un message au client (voir sujet pour plus de détails) */
        char len[1000];
        sprintf(len, "Taille du message reçu par le serveur : %zu\n", strlen(msg));
        if (send(newConnetion, len, strlen(len) + 1, 0) == -1) {
            perror("[SERVEUR] Erreur lors du retour au client ");
            exit(5);
        }

    /* Etape 7 : fermer la socket (lorsqu'elle n'est plus utilisée)*/
    shutdown(srv, SHUT_RDWR);
    free(msg);

    printf("[SERVEUR] Sortie.\n");
    return 0;
}
