#include <netinet/in.h>
#include <stdio.h> //perror
#include <sys/types.h>
#include <netdb.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <unistd.h> //close
#include <stdlib.h>
#include <string.h>
#include <sys/stat.h>
#include "fonctionsTCP.c"

#define MAX_BUFFER_SIZE 146980

int main(int argc, char *argv[])
{
  /* etape 0 : gestion des paramètres si vous souhaitez en passer */
  if (argc > 2)
  {
    printf("Utilisation : [port_serveur]\n");
    exit(1);
  }
  struct sockaddr_in socket_srv;
  socklen_t size = sizeof(struct sockaddr_in);
  /* etape 1 : creer une socket d'écoute des demandes de connexions*/
  int srv = socket(PF_INET, SOCK_STREAM, 0);
  if (srv == -1)
  {
    perror("Serveur : problème lors de la création de la socket");
    exit(1);
  }
  printf("Serveur : création de la socket réussi !\n");
  /* etape 2 : nommage de la socket */
  socket_srv.sin_family = AF_INET;
  socket_srv.sin_addr.s_addr = INADDR_ANY;
  socket_srv.sin_port = htons((short)atoi(argv[1]));
  int res = bind(srv, (struct sockaddr *)&socket_srv, sizeof(socket_srv));

  if (res == -1)
  {
    perror("Serveur : problème lors du nommage de la socket");
    exit(1);
  }

  /* etape 3 : mise en ecoute des demandes de connexions */
  int srvListen = listen(srv, 2);
  if (srvListen == -1)
  {
    perror("Serveur : problème lors de la mise en écoute de la socket");
    exit(1);
  }
  printf("Serveur : socket serveur sur écoute.\n");
  /* etape 4 : plus qu'a attendre la demadne d'un client */

  while (1)
  {
    struct sockaddr_in sock_clt;
    int newConnection = accept(srv, (struct sockaddr *)&sock_clt, &size);

    if (newConnection == -1)
    {
      perror("[Serveur] : problème lors de la connection d'un client");
      exit(1);
    }

    int pid = fork();

    if (pid == 0)
    {
      close(srv);
      printf("[SERVEUR] Le client connecté est %s:%i.\n", inet_ntoa(sock_clt.sin_addr), ntohs(sock_clt.sin_port));

      int fileName_size = 0;
      int file_size = 0;

      int resNameSize = recv(newConnection, &fileName_size, sizeof(int), 0);
      if (resNameSize == -1)
      {
        perror("[Serveur] : pb réception taille nom fichier :\n");
        exit(1);
      }
      char filename[MAX_BUFFER_SIZE];
      printf("Taille nom fichier : %i\n", fileName_size);

      int resName = recvTCP(newConnection, filename, fileName_size);
      if (resName == -1)
      {
        perror("[Serveur] : pb réception nom fichier :\n");
        exit(1);
      }

      printf("Filename : %s\n", filename);
      char *filePath = malloc(strlen(filename) + 30);
      sprintf(filePath, "../reception/client-%i/", getpid());

      struct stat attributes;
      if (stat(filePath, &attributes) == -1 && mkdir(filePath, 0700) == -1)
      {
        perror("[SERVEUR] Erreur lors de la création du dossier d'un client ");
        exit(1);
      }

      strcat(filePath, filename);
      if (filePath == NULL)
      {
        printf("[SERVEUR] Erreur lors de la lecture du nom de fichier.\n");
        exit(1);
      }

      FILE *file = fopen(filePath, "wb");
      printf("[SERVEUR] Écriture dans %s\n", filePath);
      free(filePath);
      if (file == NULL)
      {
        perror("[SERVEUR] Erreur lors de l'ouverture du fichier ");
        exit(1);
      }

      int resSize = recvTCP(newConnection, &file_size, sizeof(int));
      if (resSize == -1)
      {
        perror("[Serveur] : pb réception taille fichier :\n");
        exit(1);
      }
      printf("Taille fichier : %i\n", file_size);

      int totalReceived = 0;
      while (totalReceived < file_size)
      {
        char buffer[MAX_BUFFER_SIZE];
        int reception = recv(newConnection, buffer, min(MAX_BUFFER_SIZE, file_size - totalReceived), 0);
        if (reception == -1)
        {
          perror("[Serveur] : pb réception fichier :\n");
          exit(1);
        }
        if (reception == 0)
        {
          printf("[SERVEUR] Socket client fermée\n");
          exit(1);
        }

        int totalWritten = 0;
        while (totalWritten < reception)
        {
          int written = fwrite(buffer + totalWritten, sizeof(char), reception - totalWritten, file);
          totalWritten += written;
        }
        totalReceived += reception;
      }

      printf("[SERVEUR] Fichier du client reçu. Fermeture de la connexion.\n");
      close(newConnection);
      fclose(file);
      break;
    }
    close(newConnection);
  }

  printf("Serveur : ecriture dans fichier reussie. Vous pouvez vérifier la création du fichier et son contenu.\n");
  // fermeture du fichier

  printf("Serveur : c'est fini\n");
}
