#include <stdlib.h>
#include <sys/types.h>
#include <iostream>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <stdio.h>//perror
using namespace std;

// pour supprimer la file ayant la clé passée en parametre.

// une autre solution est d'utiliser la commande ipcrm -q <id_file>
// après avoir obtenue l'id de la file via la commande ipcs.

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
    
  int msgid = msgget(cle, 0666);
  if(msgid==-1) {
    perror("erreur msgget : ");
    exit(2);
  }
  
  cout << "msgget ok" << endl;

 
  if (msgctl(msgid, IPC_RMID, NULL) == -1)
    perror("erreur suppression file de message :");

   cout << "suppression file ok" << endl;
  
  return 0;
}
