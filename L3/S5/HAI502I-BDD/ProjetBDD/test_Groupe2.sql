--Test trigger Nom de village en majuscule lors de l'ajout et calcul capamax (fonctionnel)
prompt
prompt "##########################################################"
prompt "Test du Trigger NomVillageMajuscule et MiseANiveauNouveauVillage"
prompt "##########################################################"
prompt 
INSERT INTO Village(idVillage, nomJoueur, niveauJoueur,capaciteeCampMax, trophees, idClan) VALUES (60, 'martin', 45, null, 1300, null);
prompt "Vérification que le nom a bien été passé en majuscule"
SELECT nomJoueur FROM Village WHERE idVillage = 60;
prompt "Affichage du niveau"
SELECT niveauJoueur, capaciteeCampMax FROM Village WHERE idVillage = 60;

--Test trigger calcul Attaque (fonctionnel)
prompt
prompt "##########################################################"
prompt "Test du Trigger calculAttaque"
prompt "##########################################################"
prompt 

prompt "Ajout de l'attaquant"
INSERT INTO Village(idVillage, nomJoueur, niveauJoueur,capaciteeCampMax, trophees, idClan) VALUES (19, 'attaquant', 45, 1200, 1300, null);
prompt "Ajout du defenseur"
INSERT INTO Village(idVillage, nomJoueur, niveauJoueur,capaciteeCampMax, trophees, idClan) VALUES (20, 'defenseur', 55, 1200, 1350, null);
--////////////////////////////////////////////////////////////////////////////--

prompt "Affichage des trophees du village attaquant avant l'attaque"
SELECT Trophees FROM Village where idVillage = 19;
prompt "Affichage des trophees du village defenseur avant l'attaque"
SELECT Trophees FROM Village where idVillage = 20;

prompt
prompt "Affichage des ressources du village attaquant avant l'attaque"
SELECT typeReserve, quantite FROM Reserves WHERE idVillage = 19 GROUP BY typeReserve, quantite;
prompt "Affichage des ressources du village defenseur avant l'attaque"
SELECT typeReserve, quantite FROM Reserves WHERE idVillage = 20 GROUP BY typeReserve, quantite;
--////////////////////////////////////////////////////////////////////////////--

INSERT INTO Attaque VALUES (21, 19, 20, 15, 3, 100, 12000, 14000, 10000, null);

--////////////////////////////////////////////////////////////////////////////--
prompt -Attaque terminée (perte de trophées)

prompt "Affichage des trophees du village attaquant après l'attaque"
SELECT Trophees FROM Village where idVillage = 19;
prompt "Affichage des trophees du village defenseur après l'attaque"
SELECT Trophees FROM Village where idVillage = 20;

prompt -Attaque terminée (perte de ressources)
prompt "Affichage des ressources du village attaquant après l'attaque (Gagné  = 12000 Or, 14000 Elixir, 10000 elixir Noir"
SELECT typeReserve, quantite FROM Reserves WHERE idVillage = 19 GROUP BY typeReserve, quantite;
prompt "Affichage des ressources du village defenseur après l'attaque (Perdu  = 12000 Or, 14000 Elixir, 10000 elixir Noir"
SELECT typeReserve, quantite FROM Reserves WHERE idVillage = 20 GROUP BY typeReserve, quantite;

--Test trigger ajout de troupe si on à la place prompt "Insertion des tuples Camp" --(idCamp, idTroupe, idVillage, nbrTroupe) (fonctionnel)
prompt
prompt "##########################################################"
prompt "Test du Trigger NouvelleTroupe"
prompt "##########################################################"
prompt 

prompt -Nombre de troupes et leurs id pour le village de Martin avant insertion de 40 archères
SELECT nbrTroupe, idTroupe FROM Camp where idVillage = 60 GROUP BY idTroupe, nbrTroupe;
prompt

prompt -Insertion de 40 archères dans le village de martin
INSERT INTO Camp VALUES (37, 2, 60, 40);
prompt

prompt -Nombre de troupes et leurs id pour le village de Martin
SELECT nbrTroupe, idTroupe FROM Camp where idVillage = 60 GROUP BY idTroupe, nbrTroupe; 

prompt "Tentative d'insertion de 300 archères dans le village de martin"
INSERT INTO Camp VALUES (38, 2, 60, 300);

--Test trigger passage des reserves en négatif à 0 (fonctionnel)
prompt
prompt "##########################################################"
prompt "Test passage des reserves en négatif à 0"
prompt "##########################################################"
prompt 
prompt "Modification d'une reserve avec -2 en quantité d'or pour le village d'id 2"
UPDATE Reserves SET quantite = -2 WHERE (idVillage = 2) AND (typeReserve = 'OR');
SELECT quantite FROM Reserves WHERE idVillage = 2 AND typeReserve = 'OR';

--Test trigger SupprimerClanVide (fonctionnel)
prompt
prompt "##########################################################"
prompt "Test du trigger SupprimerClanVide"
prompt "##########################################################"
prompt
--////////////////////////////////////////////////////////////////////////////--
prompt "Création d'un village 'MARLON' qui sera chef de clan"
INSERT INTO Village(idVillage, nomJoueur, niveauJoueur,capaciteeCampMax, trophees, idClan) VALUES (61, 'Marlon', 45, null, 1300, null);
prompt "Création d'un clan 'TEST2' avec Marlon comme chef"
INSERT INTO Clan VALUES (34,'TEST2','FR', 15, 61);
--////////////////////////////////////////////////////////////////////////////--
prompt "Affichage du chef de clan"
SELECT idChefDeClan, nomJoueur FROM Clan, Village WHERE Clan.idClan = Village.idClan AND Village.idClan = 34;
prompt "Affichage de tous les clans"
SELECT idClan, nomClan FROM Clan GROUP BY idClan, nomClan;
--////////////////////////////////////////////////////////////////////////////--
prompt "Suppression du chef qui est l'unique membre"
UPDATE Village SET idClan = null WHERE idVillage = 61;
--////////////////////////////////////////////////////////////////////////////--
prompt "Affichage de tous les clans"
SELECT idClan, nomClan FROM Clan GROUP BY idClan, nomClan;
prompt "Le clan a bien été supprimé"

--Test trigger Trophées négatifs (fonctionnel)
prompt
prompt "##########################################################"
prompt "Test du trigger Trophées négatifs"
prompt "##########################################################"
prompt 
prompt "Insertion d'un Village 'AGATHE' avec 10 trophées"
INSERT INTO Village(idVillage, nomJoueur, niveauJoueur,capaciteeCampMax, trophees, idClan) VALUES (62, 'AGATHE', 22, null, 10, null);
prompt "Perte de 25 trophees pour Agathe (Passage à -15)"
UPDATE Village SET trophees =  trophees - 25 WHERE idVillage = 62;
prompt "Affichage du nombre de trophées remis à 0"
SELECT trophees FROM Village WHERE idVillage = 62;

--Test trigger RejoindreChefClan (fonctionnel)
prompt
prompt "##########################################################"
prompt "Test du trigger RejoindreChefClan"
prompt "##########################################################"
prompt 
prompt "Affichage de l'idClan de Agathe"
SELECT nomJoueur, idClan FROM Village WHERE idVillage = 62;
prompt "Création d'un clan avec Agathe comme chef"
INSERT INTO Clan VALUES (35,'TEST3','FR', 15, 62);
prompt "Affichage de l'idClan de Agathe après création du clan et affectation de l'id"
SELECT nomJoueur, idClan FROM Village WHERE idVillage = 62;

--Test trigger NouvelleRéserve (fonctionnel)
prompt
prompt "##########################################################"
prompt "Test du trigger NouvelleRéserve"
prompt "##########################################################"
prompt 
prompt "Création d'un nouveau village 'LAURENT'"
INSERT INTO Village(idVillage, nomJoueur, niveauJoueur,capaciteeCampMax, trophees, idClan) VALUES (63, 'LAURENT', 12, null, 10, null);
prompt "Affichage des ressources du village de Laurent (Initialisées à 100000 pour chaque type)"
SELECT typeReserve, quantite FROM Reserves WHERE idVillage = 63 GROUP BY typeReserve, quantite;