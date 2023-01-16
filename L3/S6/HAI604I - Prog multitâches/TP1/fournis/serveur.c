#include <netinet/in.h>
#include <stdio.h> 
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
#include <stdlib.h>
#include <arpa/inet.h>
#include <string.h>

/* Programme serveur */

int main(int argc, char *argv[]) {
   /* Je passe en paramètre le numéro de port qui sera donné à la socket créée plus loin.*/

   /* Je teste le passage de parametres. Le nombre et la nature des
      paramètres sont à adapter en fonction des besoins. Sans ces
      paramètres, l'exécution doit être arrétée, autrement, elle
      aboutira à des erreurs.*/
   if (argc != 2){
      printf("Utilisation : %s [port_serveur]\n", argv[0]);
      exit(1);
   }

   /* Etape 1 : créer une socket */   
   int ds = socket(PF_INET, SOCK_DGRAM, 0);

   /* /!\ : Il est indispensable de tester les valeurs de retour de
      toutes les fonctions et agir en fonction des valeurs
      possibles. Voici un exemple */
   if (ds == -1) {
      perror("[SERVEUR] Erreur lors de la création de la socket ");
      exit(1); // je choisis ici d'arrêter le programme car le reste
      // dépendent de la réussite de la création de la socket.
   }

   /* J'ajoute des traces pour comprendre l'exécution et savoir
      localiser des éventuelles erreurs */
   printf("[SERVEUR] Création de la socket réussie.\n");

   // Je peux tester l'exécution de cette étape avant de passer à la
   // suite. Faire de même pour la suite : n'attendez pas de tout faire
   // avant de tester.

   /* Etape 2 : Nommer la socket du serveur */
   struct sockaddr_in ad;
   socklen_t len = sizeof(ad);
   ad.sin_family = AF_INET;            // IPv4
   ad.sin_addr.s_addr = INADDR_ANY;

   // Nommage manuel
   ad.sin_port = htons(atoi(argv[1]));

   int res = bind(ds, (struct sockaddr *)&ad, sizeof(ad));
   if (res == -1) {
      perror("[SERVEUR] Erreur lors du nommage de la socket ");
      exit(1);
   }

   // Récupération de l'adresse et du numéro de port
   if (getsockname(ds, (struct sockaddr *)&ad, &len) == -1) {
      perror("[SERVEUR] Erreur lors du nommage automatique de la socket ");
      exit(1);
   }

   printf("[SERVEUR] En cours d'exécution : %s:%d\n", inet_ntoa(ad.sin_addr), ntohs(ad.sin_port));
   
   while (1) {
      struct sockaddr_in sockClient;
      socklen_t lgAdr = sizeof(sockClient);
      /* Etape 4 : recevoir un message du client (voir sujet pour plus de détails)*/
      int msgSize = 100;
      char msg[100];
      ssize_t res = recvfrom(ds, msg, msgSize, 0, (struct sockaddr*)&sockClient, &lgAdr);
      if (res == -1) {
        perror("[SERVEUR] Erreur lors de la réception du message ");
        exit(1);
      }

      if (msg == NULL) {
         perror("[SERVEUR] Erreur lors de la réception du message ");
         exit(1);
      }
      printf("[SERVEUR] Message reçu : %s\n", msg);
      printf("[SERVEUR] Adresse du client : %s:%i\n", inet_ntoa(sockClient.sin_addr), ntohs(sockClient.sin_port));
    
        /* Etape 5 : envoyer un message au serveur (voir sujet pour plus de détails) */
        char len[100];
        sprintf(len, "Taille du message reçu par le serveur : %zu\n", strlen(msg));
        if (sendto(ds, len, strlen(len) + 1, 0, (const struct sockaddr*)&sockClient, lgAdr) == -1) {
            perror("[SERVEUR] Erreur lors du retour au client ");
            exit(5);
        }
    }

   /* Etape 6 : fermer la socket (lorsqu'elle n'est plus utilisée)*/
   
   // On pourrait aussi faire close() mais shutdown() est plus sécurisée et plus pratique à manipuler.
   shutdown(ds, SHUT_RDWR);

   printf("[SERVEUR] Sortie.\n");
   return 0;
}