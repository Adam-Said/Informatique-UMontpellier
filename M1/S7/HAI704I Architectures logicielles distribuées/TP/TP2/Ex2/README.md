# TP2 SOAP : Agence d'hôtels

[![MIT License](https://img.shields.io/github/license/Gaiko19/HAI704I-SOAP)](https://img.shields.io/github/license/Gaiko19/HAI704I-SOAP/)
[![release](https://img.shields.io/github/v/release/Gaiko19/HAI704I-SOAP)](https://github.com/Gaiko19/HAI704I-SOAP/releases/tag/v1.0)

## Merci de bien lire tout le README et le rapport afin de comprendre comment utiliser le projet !

Ce projet implémente le TP2 de l'UE HAI704I de l'Université de Montpellier. Il s'agit de mettre en place un service de réservation et recherche d'hôtels en utilisant l’appel de méthodes distantes.
L'utilisateur est un client qui possède une carte de crédit et compte dans l'une des agences de voyages disponibles (il y'en a trois). Une agence est une entité possédant une liste d'hôtels partenaires ainsi qu'un pourcentage de réduction par hôtel. Le client peut donc effectuer des recherches et trouver un hôtel correspondant à sa demande et ainsi réserver une chambre.
Un système de date fait en sorte qu'une même chambre ne soit pas réservable deux fois sur la même période.
Afin de récupérer leurs informations propre, les agences et les hôtels font des appels à une base de données distante qui contient tous les clients, agences, hôtels, chambres et réservations.
Et pour communiquer avec les hôtels (qui sont des serveurs), les agences font des appels à des méthodes distantes utilisant la technologie SOAP.

### Contenu

Ce projet contient :
- Deux paquets : un client, un serveur.
- Un Makefile afin de compiler et lancer les différents projets
- Un README
- Un rapport sur le développement du projet

### Pré-requis

Le projet a été fait pour fonctionner sur `Linux` et `OSX`, il peut potentiellement et normalement fonctionner sur `Windows` mais nous ne garantissons pas que tous fonctionne à 100%.

Une version récente du `JDK` de `Java` (**11 ou ultérieure**) possédant encore `WSIMPORT` (**donc pas les toutes dernières**) est nécessaire pour compiler et exécuter le projet. Avec évidemment la variable d'environnement `JAVA_HOME` de correctement définie.

`Maven` doit être également installé sur votre machine afin de compiler. Il est possible de l'installer avec :
```bash
sudo apt install maven
```

`Make` doit être installé sur votre machine. Il est possible de l'installer avec :
```bash
sudo apt install make
```

### Utilisation

1. Commencer par installer le projet en le récupérant [`ici`](https://github.com/Gaiko19/HAI704I-SOAP/releases).
(il existe deux release, une non distribuée et une distribuée)
2. Ouvrez un terminal à la racine du projet (à l'endroit du Makefile et du README)

3. Compiler les deux paquets en faisant la commande suivante.
```bash
make
```

3. Lancer le serveur. Un message "Server ready!" devrait apparaitre au bout de quelques secondes
```bash
make server
```

4. Lancer votre client `CLI` avec
```bash
make client
```

5. Vous pouvez lancer le client en version graphique (préférable) avec
```bash
make gui
```

6. Suivez les instructions ou options proposées par le client.

7. Une fois terminé vous pouvez nettoyer les fichiers avec
```bash
make clean
```

### Authors

Ce projet a été fait par :

- [Said Adam](https://github.com/gaiko19)
- [Cossu Arnaud](https://github.com/ArnaudCs)

En M1 Génie Logiciel à l'Université de Montpellier
