#include <stdio.h> 
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
#include <stdlib.h>
#include<arpa/inet.h>
#include<string.h>
#include "fonctionsTCP.c"

/* Programme client TCP */

int main(int argc, char *argv[]) {

    /* je passe en paramètre l'adresse de la socket du serveur (IP et
        numéro de port) et un numéro de port à donner à la socket créée plus loin.*/

    /* Je teste le passage de parametres. Le nombre et la nature des
        paramètres sont à adapter en fonction des besoins. Sans ces
        paramètres, l'exécution doit être arrétée, autrement, elle
        aboutira à des erreurs.*/
    if (argc != 5){
        printf("utilisation : %s ip_serveur port_serveur port_client port_serveur2\n", argv[0]);
        exit(1);
    }

    /* Etape 1 : créer une socket */   
  int ds = socket(PF_INET, SOCK_STREAM, 0);
   if (ds == -1){
    perror("[Client] : pb creation socket :\n");
    exit(1);
  }
  printf("[Client] : creation de la socket réussie \n");
  /* etape 1.2 : nommage de la socket client */
  struct sockaddr_in sock_clt;
  socklen_t size =sizeof(struct sockaddr_in);
  sock_clt.sin_family = AF_INET;
  sock_clt.sin_addr.s_addr = INADDR_ANY;
  sock_clt.sin_port = htons((short)atoi(argv[3]));
  
  int res = bind(ds, (struct sockaddr*) &sock_clt, size);
  if (res == -1){
      perror("[Client] : pb nommage socket :\n");
      exit(1);
  }
  /* etape 2 : designer la socket du serveur */
  struct sockaddr_in sock_srv;
  sock_srv.sin_family = AF_INET;
  sock_srv.sin_addr.s_addr = inet_addr(argv[1]);
  sock_srv.sin_port = htons(atoi(argv[2]));

  /* etape 3 : demander une connexion */
  int dsConnect = connect(ds, (struct sockaddr*)&sock_srv, sizeof(sock_srv));
  if (dsConnect == -1){
      perror("[Client] : pb connexion serveur :\n");
      exit(1);
  }
  printf("[Client] : connexion réussie\n");

    /* Etape 5 : recevoir un message du serveur (voir sujet pour plus de détails) */
    int tailleMsg=0;
    int servTaille = recv(ds, &tailleMsg, sizeof(int), 0);

    if (servTaille == -1) {
        perror("[CLIENT] Erreur lors de la réception du message du serveur \n");
        exit(5);
    }
    printf("[CLIENT] Taille msg : %i\n", tailleMsg);

    char msg[tailleMsg];
    int servMsg = recv(ds, msg, tailleMsg, 0);

    if (servMsg == -1) {
        perror("[CLIENT] Erreur lors de la réception du message du serveur \n");
        exit(5);
    }
    printf("[CLIENT] msg : %s\n", msg);
    /* Etape 5 : envoyer un message au serveur  (voir sujet pour plus de détails)*/

    //fgets(msgUser, tailleMsg, msg);
 
    if (send(ds, msg, tailleMsg, 0) == -1){
        perror("[Client] : problème envoi message :");
    }
    else
    {
        printf("Message bien envoyé...\n");
    }

    int tailleMsg3=0;
    int servTaille3 = recv(ds, &tailleMsg3, sizeof(int), 0);

    if (servTaille3 == -1) {
        perror("[CLIENT] Erreur lors de la réception du message du serveur \n");
        exit(5);
    }
    printf("[CLIENT] Taille msg : %i\n", tailleMsg3);

    char msg3[tailleMsg3];
    int servMsg3 = recv(ds, msg3, tailleMsg3, 0);

    if (servMsg3 == -1) {
        perror("[CLIENT] Erreur lors de la réception du message du serveur \n");
        exit(5);
    }
    printf("[CLIENT] msg : %s\n", msg3);


    short newPort = atoi(argv[4]);

    if (send(ds, &newPort, sizeof(short), 0) == -1){
        perror("[Client] : problème envoi port :");
    }
    else
    {
        printf("Port bien envoyée...\n");
    }
    printf("Port : %i",newPort);
    shutdown(ds, SHUT_RDWR); //free(msgUser);
    /* Etape 6 : fermer la socket (lorsqu'elle n'est plus utilisée)*/


    int srv = socket(PF_INET, SOCK_STREAM, 0);
    if (srv == -1){
        perror("[Client] : pb creation socket serveur :\n");
        exit(1);
    }
    printf("[Client] : creation de la socket réussie \n");
    /* etape 1.2 : nommage de la socket serveur */
    struct sockaddr_in socket_lst;
    socket_lst.sin_family = AF_INET;
    socket_lst.sin_addr.s_addr = INADDR_ANY;
    socket_lst.sin_port = htons((short)atoi(argv[4]));
    res = bind(srv, (struct sockaddr *)&socket_lst, sizeof(socket_lst));

    if (res == -1){
        perror("[Client] : pb nommage socket :\n");
        exit(1);
    }
    /* etape 2 : designer la socket du client */
    struct sockaddr_in sock_clt2;

    int srvListen = listen(srv, 10);
    if (srvListen == -1) {
        perror("Serveur : problème lors de la mise en écoute de la socket");
        exit(1);
    }
    printf("Serveur : socket serveur sur écoute.\n");



    int newConnetion = accept(srv, (struct sockaddr *)&sock_clt2, &size);
    if(newConnetion == -1) {
        perror("[SERVEUR] Erreur lors d'une connexion entrante");
        exit(1);
    }
    printf("Serveur : connexion du client (%s:%i)\n", inet_ntoa(sock_clt2.sin_addr), ntohs((short) sock_clt2.sin_port));

    int msgSize = 0;
    char* msg4;

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

        printf("[Serveur] : taille du message %i octets\n", msgSize);
        msg4 = (char*) malloc(msgSize);
        res = recv(newConnetion,msg4,msgSize,0);
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
        printf("[Serveur] : nombre d'octet : %i, message reçu : %s\n", res, msg4);

    int msgSize5 = 0;
    char* msg5;

    resTaille = recv(newConnetion, &msgSize5, sizeof(int),0);
    if (resTaille == -1) {
        perror("[SERVEUR] Erreur lors de la réception de la taille ");
        exit(1);
    }
    else if (resTaille == 0)
    {
        printf("[SERVEUR] Le client a fermé la connexion");
        exit(1);
    }

        printf("[Serveur] : taille du message %i octets\n", msgSize5);
        msg5 = (char*) malloc(msgSize5);
        res = recv(newConnetion,msg5,msgSize5,0);
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
        printf("[Serveur] : nombre d'octet : %i, message reçu : %s\n", res, msg5);

printf("###################  PARTIE SERVEUR ITERATIF  ######################\n");
while (1) {
    struct sockaddr_in sock_cltTCP;
    int newConnectionTCP = accept(srv, (struct sockaddr*)&sock_cltTCP, &size);

    if (newConnectionTCP == -1 || newConnectionTCP == 0) {
        perror("[Serveur] : problème lors de la connection d'un client");
    }

    int pid = fork();

    if (pid == 0) {
        
        printf("[SERVEUR] Le client connecté est %s:%i.\n", inet_ntoa(sock_cltTCP.sin_addr), ntohs(sock_cltTCP.sin_port));
        
        int tailleTCP =0;
        int resTailleTCP = recvTCP(newConnectionTCP,&tailleTCP,sizeof(int));
        if (resTailleTCP == -1 || resTailleTCP == 0){
            perror("[Serveur] : pb réception taille msg ou client fermé:\n");
            
        }
        else
        {
            char msgTCP[tailleTCP];
            int resMsgTCP = recvTCP(newConnectionTCP,msgTCP,tailleTCP);
            if (resMsgTCP == -1 || resMsgTCP == 0){
                perror("[Serveur] : pb réception msg ou client fermé :\n");
                
            }
            else
            {
                printf("[Serveur] : nombre d'octet : %i, message reçu : %s\n", resTailleTCP, msgTCP);
            }
        }

        int tailleTCP2 =0;
        int resTailleTCP2 = recvTCP(newConnectionTCP,&tailleTCP2,sizeof(int));
        if (resTailleTCP2 == -1 || resTailleTCP2 == 0){
            perror("[Serveur] : pb réception taille msg ou client fermé:\n");
            
        }
        else
        {
            char msgTCP2[tailleTCP2];
            int resMsgTCP2 = recvTCP(newConnectionTCP,msgTCP2,tailleTCP2);
            if (resMsgTCP2 == -1 || resMsgTCP2 == 0){
                perror("[Serveur] : pb réception msg ou client fermé :\n");
                
            }
            else
            {
                printf("[Serveur] : nombre d'octet : %i, message reçu : %s\n", resTailleTCP2, msgTCP2);
            }
        }
        
        close(newConnectionTCP);
    }
    else
    {
        close(newConnectionTCP);
    }
    
  }
    close(srv);
    printf("[CLIENT] Sortie.\n");
    return 0;
}
