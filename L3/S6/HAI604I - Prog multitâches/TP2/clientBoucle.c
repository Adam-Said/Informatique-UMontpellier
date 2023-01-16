#include <stdio.h> 
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
#include <stdlib.h>
#include<arpa/inet.h>
#include<string.h>

/* Programme client TCP */

int main(int argc, char *argv[]) {

    /* je passe en paramètre l'adresse de la socket du serveur (IP et
        numéro de port) et un numéro de port à donner à la socket créée plus loin.*/

    /* Je teste le passage de parametres. Le nombre et la nature des
        paramètres sont à adapter en fonction des besoins. Sans ces
        paramètres, l'exécution doit être arrétée, autrement, elle
        aboutira à des erreurs.*/
    if (argc != 5){
        printf("utilisation : %s ip_serveur port_serveur port_client nb_iteration\n", argv[0]);
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
  sock_clt.sin_family = AF_INET;
  sock_clt.sin_addr.s_addr = INADDR_ANY;
  sock_clt.sin_port = htons((short)atoi(argv[3]));
  
  int res = bind(ds, (struct sockaddr*) &sock_clt, sizeof(sock_clt));
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
    /* Etape 5 : envoyer un message au serveur  (voir sujet pour plus de détails)*/
    char msgUser[1500];

    printf("Entrer un message : ");
    fgets(msgUser, 1500, stdin);
    int taille_msg = strlen(msgUser);
    for (int i = 0; i < *argv[4]; i++)
    {
        if (send(ds, &taille_msg, sizeof(int), 0) == -1){
        perror("[Client] : problème envoi taille message :");
        }
        else
        {
            printf("Taille bien envoyée...\n");
        }

        if (send(ds, msgUser, strlen(msgUser), 0) == -1){
            perror("[Client] : problème envoi message :");
        }
        else
        {
            printf("Message bien envoyé...\n");
        }
    }
    
    /* Etape 5 : recevoir un message du serveur (voir sujet pour plus de détails) */
    socklen_t servAdr = sizeof(sock_srv);
    char bytesSent[1000];
    int servRes = recv(ds, bytesSent, servAdr, 0);

    if (servRes == -1) {
        perror("[CLIENT] Erreur lors de la réception du message du serveur ");
        exit(5);
    }
    printf("[CLIENT] %s", bytesSent);

    /* Etape 6 : fermer la socket (lorsqu'elle n'est plus utilisée)*/
    shutdown(ds, SHUT_RDWR); //free(msgUser);

    printf("[CLIENT] Sortie.\n");
    return 0;
}
