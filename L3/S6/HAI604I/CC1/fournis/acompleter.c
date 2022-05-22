#include <netinet/in.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
#include <stdlib.h>
#include<arpa/inet.h>
#include<string.h>

#define min(a,b) \
   ({ __typeof__ (a) _a = (a); \
       __typeof__ (b) _b = (b); \
     _a < _b ? _a : _b; })

int sendTCP(int sock, void* msg, int sizeMsg) {
    int remaining = sizeMsg;

    while (remaining > 0) {
        printf("Send remaining : %i\n", remaining);
        int res = send(sock, msg + sizeMsg - remaining, remaining, 0);
        if (res <= 0) {
            return res;
        }
        remaining -= res;
    }

    return 1;
}

int recvTCP(int sock, void* msg, int sizeMsg) {
    int remaining = sizeMsg;

    while (remaining > 0) {
        printf("Receive remaining : %i\n", remaining);
        int res = recv(sock, msg + sizeMsg - remaining, remaining, 0);
        if (res <= 0) {
            return res;
        }
        remaining -= res;
    }

    return 1;
}

/* Ce programme est à compléter et à déposer sur Moodle (c'est le seul fichier à déposer).

   Lire attentivement les instructions en commentaires pour compléter correctement ce programme.
 
   Les principales étapes de ce programme sont :
 
   1) demander à un serveur UDP une liste d'adresses de serveurs TCP existants.
 
   2) se connecter à l'un de ces serveurs TCP et échanger avec ce serveur.
 
   3) faire évoluer votre programme pour ajouter un serveur TCP qui échangera avec un exécutable client fourni.
 
   4) modifier votre programme pour qu'il soit capable de traiter plusieurs clients de manière itérative.
 
   5) modifier votre programme pour qu'il soit capable de traiter plusieurs clients  simultanéments (en utilisant la fonction fork()).
 
   Attention : vous devez déposer un code qui compile. Exemple : si vous êtes à l'étape 4 qui n'est pas fonctionnelle, la mettre en commentaire pour que toutes les étapes d'avant soient validées.
  
*/

#define MAXSRV 100


// cette structure est le type du message à envoyer au serveur UDP
struct nomClient{
  short type; // doit être égal à 1
  char hostname[50];
};

typedef struct nomClient sNomClient;

int main(int argc, char *argv[]) {

  if (argc != 4){
    printf("Utilisation : %s ip_serveurUDP port_serveurUDP param3 \n param3 est un entier dont la signification sera fournie par une question. En attendant cette question, passer n'importe quelle valeur\n", argv[0]);
    exit(0);
  }
  
  /* Etape 1 : envoyer un message au serveur UDP et recevoir une réponse :
  
     1) le message à envoyer est de type sNomClient. Il permet d'envoyer le nom de la machine sur laquelle s'exécute votre client. Voir plus loin le code fourni pour vous aider.
   
     2) le message à recevoir est une liste d'élements de type sock_addr_in. Chaque élément représente l'adresse d'un serveur TCP existant.
   
  */
  
  int dSUDP= socket(PF_INET, SOCK_DGRAM, 0);
  
  if (dSUDP < 0){
    perror("Erreur a la creation de la socket :");
    return 1;
  }     
 
  // designer la socket distante du serveur UDP
   struct sockaddr_in srv;
   srv.sin_family = AF_INET;
   srv.sin_addr.s_addr = inet_addr(argv[1]);
   srv.sin_port = htons(atoi(argv[2]));
  
  struct sockaddr_in aD; 
                
   aD.sin_family = AF_INET; // complétez
   aD.sin_addr.s_addr = INADDR_ANY;
   aD.sin_port = htons(atoi(argv[3])); 
   int res = bind(dSUDP, (struct sockaddr *)&aD, sizeof(aD));
   if (res == -1){
      perror("[Client] : pb nommage socket :");
      exit(1);
   } 
  
  //...
 
  // Le code suivant prépare le message à envoyer au serveur UDP.
  sNomClient nom;
  nom.type = 1;
  nom.hostname[0]='\0';
  char h[50];
  gethostname(h, 50); // récupère le nom de la machine que vous utilisez.
  strcat(nom.hostname, h);
  // Le message à envoyer au serveur UDP est construit.
  // Envoyer ce message au serveur UDP
 
   ssize_t msg = sendto(dSUDP, &nom, 50, 0, (struct sockaddr*)&srv,  sizeof(srv));

   if (msg == -1){
      perror("[Client] : pb envoi message :");
      exit(1);
   }
     printf("Message bien envoyé...");

  // la réponse à recevoir est a stocker dans le tableau suivant. votre programme recevra au plus 50 adresses de serveurs TCP
  struct sockaddr_in reponse[50];
   socklen_t servAdr = sizeof(srv);
   ssize_t servRes = recvfrom(dSUDP, reponse, 50, 0, (struct sockaddr*)&srv, &servAdr);

   if (servRes == -1) {
      perror("[CLIENT] Erreur lors de la réception du message du serveur ");
      exit(5);
   }
   
   /*printf("[CLIENT] Liste des serveurs :\n");
   for (int i=0; i < sizeof(reponse)/sizeof(reponse[0]); i++) {
      printf("%s\n", inet_ntoa(reponse[i].sin_addr));
   }*/
   
 

  /* affichage de la liste des adresses recues et demande de choix d'un
     serveur. On suppose qu'au moins un serveur tourne. En l'absence d'un serveur, on termine proprement */

  printf("Les serveurs TCP existants : \n"); 
  for(int i = 0; i < sizeof(reponse)/sizeof(reponse[0]); i++) // Attention : une modification doit être faite ici.
      printf("%d. IP %s, port %d \n", i+1, inet_ntoa(reponse[i].sin_addr),  ntohs(reponse[i].sin_port));
    
  printf("Choisir un numero de serveur (1 ou 2 ou ... ): \n");
  int numS;
  scanf("%d", &numS);

  
  // A présent, plus besoin d'échanger avec le serveur UDP.
   shutdown(dSUDP, SHUT_RDWR);
 //...
 
  /* Etape 2 : Echanger avec le serveur TCP choisi.
   
    
     Après connexion :
 
     1) recevoir une chaîne de caractères dont la taille est connue par le serveur (à vous d'en déduire ce qui est nécecessaire de faire) puis afficher le message reçu.
   
     2) recevoir un entier. 
     
     Remarque : le serveur peut mettre fin à l'échange avec votre client à n'importe quel moment. Il faut donc bien prévoir ce cas.
   
     3) termine les échanges avec le serveur TCP.
      
  */ 
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
  sock_clt.sin_port = htons((short)atoi(argv[2]));
  
  int nommage = bind(ds, (struct sockaddr*) &sock_clt, size);
  if (nommage == -1){
      perror("[Client] : pb nommage socket :\n");
      exit(1);
  }
  /* etape 2 : designer la socket du serveur */
  /*numS = numS -1;
  struct sockaddr_in sock_srv;
  sock_srv.sin_family = AF_INET;
  sock_srv.sin_addr.s_addr = inet_addr(inet_ntoa(reponse[numS].sin_addr));
  sock_srv.sin_port = htons(ntohs(reponse[numS].sin_port));
  printf("Choix : IP: %s PORT : %d\n",inet_ntoa(reponse[numS].sin_addr),(ntohs(reponse[numS].sin_port)));
*/
  /* etape 3 : demander une connexion */
  /*int dsConnect = connect(ds, (struct sockaddr*)&sock_srv, sizeof(sock_srv));
  if (dsConnect == -1){
      perror("[Client] : pb connexion serveur :\n");
      exit(1);
  }
  printf("[Client] : connexion réussie\n");*/
 
 
 // A vous de jouer
 // ...
   int tailleMsg=0;
   int servTaille = recv(ds, &tailleMsg, sizeof(int), 0);

   if (servTaille == -1 || servTaille == 0) {
      perror("[CLIENT] Erreur lors de la réception du message du serveur ou connexion fermée\n");
      exit(5);
   }
   printf("[CLIENT] Taille msg : %i\n", tailleMsg);

   char msgString[tailleMsg];
   int servMsg = recv(ds, msgString, tailleMsg, 0);

   if (servMsg == -1 || servTaille || 0) {
      perror("[CLIENT] Erreur lors de la réception du message du serveur ou connexion fermée \n");
      exit(5);
   }
   printf("[CLIENT] msg : %s\n", msgString);
   shutdown(ds, SHUT_RDWR);

  // L'étape suivante est indépendante de la précédente. Donc, elle peut s'exécuter même en cas d'erreurs / fermetures de scokets lors des précédents échange.
  
  /* Etape 3 : Mettre en place un serveur TCP. Ensuite passer à l'étape 4 : Modifier ce serveur pour qu'il soit itératif. Enfin, l'étape 5 : modifier le serveur pour qu'il soit concurrent (en utilisant la fonction fork()). 
 
     Pour l'étape 3, le serveur a un numéro de port qui est le dernier paramètre de votre programme. Ce serveur doit faire ce qui suit pour chaque client :
 
     1) recevoir un tableau de 320000 entiers (int) envoyé par ce client
     2) renvoyer le tableau reçu au client.
     3) recevoir en réponse une chaine de caractères dont la taille est connue par le client (à vous de savoir ce qu'il est nécessaire de faire pour l'obtenir) 
     4) terminer l'échange avec ce client
     
     Pour tester votre serveur, executez le le binaire ./bin/clients fourni. Ce dernier lancera 5 clients.
  */ 
  
 // A vous de jouer.
   struct sockaddr_in socket_srv;
   /* etape 1 : creer une socket d'écoute des demandes de connexions*/
   int ds2 = socket(PF_INET, SOCK_STREAM, 0);
   if (ds2 == -1) {
      perror("Serveur : problème lors de la création de la socket");
      exit(1);
   }
   printf("Serveur : création de la socket réussi !\n");
   /* etape 2 : nommage de la socket */
   socket_srv.sin_family = AF_INET;
   socket_srv.sin_addr.s_addr = INADDR_ANY;
   socket_srv.sin_port = htons((short)atoi(argv[3]));
   socklen_t size_srv =sizeof(struct sockaddr_in);
   int nommage2 = bind(ds2, (struct sockaddr *)&socket_srv, sizeof(socket_srv));

   if (nommage2 == -1) {
      perror("Serveur : problème lors du nommage de la socket");
      exit(1);
   }

   /* etape 3 : mise en ecoute des demandes de connexions */
   int srvListen = listen(ds2, 10);
   if (srvListen == -1) {
      perror("Serveur : problème lors de la mise en écoute de la socket");
      exit(1);
   }
   printf("Serveur : socket serveur sur écoute.\n");

printf("###################  PARTIE SERVEUR ITERATIF  ######################\n");
while (1) {
    struct sockaddr_in sock_cltTCP;
    int newConnectionTCP = accept(ds2, (struct sockaddr*)&sock_cltTCP, &size_srv);

    if (newConnectionTCP == -1 || newConnectionTCP == 0) {
        perror("[Serveur] : problème lors de la connection d'un client");
    }

    int pid = fork();

    if (pid == 0) {
        
        printf("[SERVEUR] Le client connecté est %s:%i.\n", inet_ntoa(sock_cltTCP.sin_addr), ntohs(sock_cltTCP.sin_port));
        // Tableau
        int tailleTCP =320000;
         int *msgTCP = (int*) malloc(320000 * sizeof(int));
            int resMsgTCP = recvTCP(newConnectionTCP,msgTCP,tailleTCP);
            if (resMsgTCP == -1 || resMsgTCP == 0){
                perror("[Serveur] : pb réception msg ou client fermé :\n");
                
            }
            else
            {
                printf("[Serveur] : Tableau du client bien reçu \n");
            }
         // Renvoi tableau
            int sendtab = sendTCP(ds, msgTCP, tailleTCP);
               if (sendtab == -1 || sendtab == 0){
                  perror("[Client] : problème envoi message :");
               }
               else
               {
                  printf("Message bien envoyé...\n");
               }
         // Chaine
        int tailleTCP2 =0;
        int resTailleTCP2 = recvTCP(newConnectionTCP,&tailleTCP2,sizeof(int));
        if (resTailleTCP2 == -1 || resTailleTCP2 == 0){
            perror("[Serveur] : pb réception taille msg ou client fermé:\n");
            
        }
        else
        {
            char msgTCP2[tailleTCP2];
            int resMsgTCP2 = recvTCP(newConnectionTCP,msgTCP2+1,tailleTCP2);
            if (resMsgTCP2 == -1 || resMsgTCP2 == 0){
                perror("[Serveur] : pb réception msg ou client fermé :\n");
                
            }
            else
            {
                printf("[Serveur] : Message du serveur bien reçu\n");
            }
        }
        
        close(newConnectionTCP);
    }
    else
    {
        close(newConnectionTCP);
    }
    
  }
    close(ds2);
    printf("[CLIENT] Sortie.\n");

}
  
