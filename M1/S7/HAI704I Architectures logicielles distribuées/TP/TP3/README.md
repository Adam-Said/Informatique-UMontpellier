# TP3 REST : Comparateur d'hôtels

[![MIT License](https://img.shields.io/github/license/ArnaudCs/HAI704I-REST)](https://img.shields.io/github/license/ArnaudCs/HAI704I-REST/)
[![release](https://img.shields.io/github/v/release/ArnaudCs/HAI704I-REST)](https://github.com/ArnaudCs/HAI704I-REST/releases/tag/v1.0)

## Merci de bien lire tout le README et le rapport afin de comprendre comment utiliser le projet !

Ce projet implémente le TP3 de l'UE HAI704I de l'Université de Montpellier. Il s'agit de mettre en place un service de réservation et recherche d'hôtels en utilisant l’appel de méthodes distantes.
L'utilisateur final est un client qui utilise un comparateur d'offres. Ces offres sont proposées par plusieurs agences. Une agence est une entité possédant une liste d'hôtels partenaires avec lesquels elle communique ainsi qu'un pourcentage de réduction par hôtel. Le client peut donc effectuer des recherches et trouver un hôtel correspondant à sa demande et ainsi réserver une chambre au meilleur prix.
Un système de date fait en sorte qu'une même chambre ne soit pas réservable deux fois sur la même période.
Et pour communiquer avec les agences, qui elles communiquent avec les hôtels (qui sont des serveurs), le comparateur fait des appels à des méthodes distantes utilisant la technologie REST.

### Contenu

Ce projet contient :
- 6 Hôtels différents
- 3 Agences différentes
- 1 Comparateur
- Un Makefile afin de compiler et lancer les différents projets
- Un README
- Un rapport sur le développement du projet

### Pré-requis

Une version récente du `JDK` de `Java` (**11 ou ultérieure**) est nécessaire pour compiler et exécuter le projet. Avec évidemment la variable d'environnement `JAVA_HOME` correctement définie.

`Maven` doit être également installé sur votre machine afin de compiler. Il est possible de l'installer avec :
```bash
sudo apt install maven
```

`Make` doit être installé sur votre machine. Il est possible de l'installer avec :
```bash
sudo apt install make
```

### Utilisation

1. Commencer par installer le projet en le récupérant [`ici`](https://github.com/ArnaudCs/HAI704I-REST/releases).

2. Ouvrez un terminal à la racine du projet (à l'endroit du Makefile et du README)

3. Compiler les paquets en faisant la commande suivante.
```bash
make
```

Attention, chaque projet étant un serveur `SOAP`, ne lancer le maximum demande beaucoup de resource à votre ordinateur.
Si votre machine n'est pas puissante nous vous recommandons de n'en lancer que le minimum avec la commande suivante :

4. Vous pouvez en suite lancer 4 hôtels et 3 agences avec:
```bash
make min
```

Vous pouvez lancer le maximum de services disponibles avec la commande suivante:

5. Vous pouvez sinon lancer 6 hôtels et 3 agences avec :
```bash
make max
```
Un message "[BASH] Ready for use" devrait apparaitre au bout de quelques secondes


6. Vous pouvez en suite lancer le comparateur avec :
```bash
make comparator
```

7. Suivez les instructions ou options proposées par le client afin de lancer le programme en `CLI` ou `GUI`.

8. Une fois terminé vous pouvez arreter les projets et les nettoyer avec :
```bash
make clean
```

8. Ou seulement les arêter avec  :
```bash
make stop
```

### Authors

Ce projet a été fait par :

- [Said Adam](https://github.com/gaiko19)
- [Cossu Arnaud](https://github.com/ArnaudCs)

En M1 Génie Logiciel à l'Université de Montpellier
