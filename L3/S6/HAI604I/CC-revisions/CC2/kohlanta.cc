#include <sys/types.h>
#include <pthread.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>

void calcul() {
    int wait = rand()%10 + 1;
    sleep(wait);
}

struct paramsEquipes {
  int numEquipe;
  int bassine = 0;
  pthread_cond_t* cond;
  pthread_mutex_t* lock;
};

struct paramsJoueur{
    paramsEquipes Equipe;
    int numJoueur;
    pthread_cond_t* cond;
    pthread_mutex_t* lock;
} ;

void * tour (void * params){
    struct paramsJoueur *arg = (struct paramsJoueur*) params;
    struct paramsEquipes eq = (struct paramsEquipes) arg->Equipe;
    printf("Début joueur %i équipe %i",arg->numJoueur, eq.numEquipe);
    while (eq.bassine < 10) {
        printf("[Equipe %i][Joueur %i] attend de partir\n",eq.numEquipe,arg->numJoueur);
        pthread_mutex_lock(eq.lock);
        printf("[Equipe %i][Joueur %i] Démarre\n",eq.numEquipe,arg->numJoueur);
        calcul();
        printf("[Equipe %i][Joueur %i] Revient\n",eq.numEquipe,arg->numJoueur);
        eq.bassine += 3;
        pthread_mutex_unlock(eq.lock);
        pthread_cond_broadcast(eq.cond);
    }
    pthread_exit(NULL);
}; 

void * jeu (void * params){
    struct paramsEquipes *arg = (struct paramsEquipes*) params;
    printf("Départ équipe n%i\n",arg->numEquipe);
    pthread_t joueurs[4];
    struct paramsJoueur paramJ[4];
    for (int i = 0; i < 4; i++){
        paramJ[i].Equipe = *arg;
        paramJ[i].numJoueur = i+1;
        paramJ[i].cond = arg->cond;
        paramJ[i].lock = arg->lock; 
        if (pthread_create(&joueurs[i], NULL, tour, &paramJ[i]) != 0){
        perror("erreur creation joueur");
        exit(1);
        }
    } 
    pthread_exit(NULL);
};

int main(int argc, char * argv[]){
    
    if (argc != 1) {
        printf("argument requis\n");
        printf("%s 0\n", argv[0]);
        exit(1);
    }

    pthread_t equipes[2];
    struct paramsEquipes param[2];

    for (int i = 0; i < 2; i++){
        param[i].numEquipe = i+1;
        pthread_mutex_t lock;
        pthread_cond_t cond;
        pthread_mutex_init(&lock, NULL);
        pthread_cond_init(&cond, NULL);
        param[i].lock = &lock;
        param[i].cond = &cond;
        if (pthread_create(&equipes[i], NULL, jeu, &param[i]) != 0){
            perror("erreur creation thread");
            exit(1);
        }
    }
    for (int i = 0; i < atoi(argv[1]); i++) {
        pthread_join(equipes[i], NULL);
    }
}
