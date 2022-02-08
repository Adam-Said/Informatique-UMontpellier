prompt "Insertion des tuples Troupe"
--INSERT INTO Troupe VALUES (ID,NOM,PV,DEGAT,PLACE,PRIX_ELIXIR,prix_noir);
INSERT INTO Troupe VALUES (1,'BARBARE',100,30,1,250,0);
INSERT INTO Troupe VALUES (2,'ARCHERE',50,25,1,500,0);
INSERT INTO Troupe VALUES (3,'GEANT',1250,100,5,3500,0);
INSERT INTO Troupe VALUES (4,'GOBELIN',75,27,1,150,0);
INSERT INTO Troupe VALUES (5,'SAPEUR',70,500,2,1500,0);
INSERT INTO Troupe VALUES (6,'BALLON',550,400,5,4500,0);
INSERT INTO Troupe VALUES (7,'SORCIER',200,300,4,3500,0);
INSERT INTO Troupe VALUES (8,'GUERISSEUSE',1200,70,14,10000,0);
INSERT INTO Troupe VALUES (9,'DRAGON',3100,500,20,18000,0);
INSERT INTO Troupe VALUES (10,'PEKKA',5200,1600,25,25000,0);
INSERT INTO Troupe VALUES (11,'BEBE DRAGON',1500,85,10,7000,0);
INSERT INTO Troupe VALUES (12,'MINEUR',700,100,6,5500,0);
INSERT INTO Troupe VALUES (13,'ELECTRO DRAGON',3200,1200,30,28000,0);
INSERT INTO Troupe VALUES (14,'YETI',2900,500,18,19000,0);
INSERT INTO Troupe VALUES (15,'CHEVAUCHEUR DE DRAGON',4100,550,25,22000,0);
INSERT INTO Troupe VALUES (16,'GARGOUILLES',75,50,2,0,30);
INSERT INTO Troupe VALUES (17,'CHEVAUCHEUR DE COCHON',480,80,5,0,65);
INSERT INTO Troupe VALUES (18,'VALKYRIE',900,70,8,0,100);
INSERT INTO Troupe VALUES (19,'GOLEM',6300,26,30,0,500);
INSERT INTO Troupe VALUES (20,'SORCIERE',400,100,12,0,200);
INSERT INTO Troupe VALUES (21,'MOLOSSE DE LAVE',6800,14,30,0,600);
INSERT INTO Troupe VALUES (22,'BOULISTE',310,50,6,0,90);
INSERT INTO Troupe VALUES (23,'GOLEM DE GLACE',2600,12,15,0,300);
INSERT INTO Troupe VALUES (24,'CHASSEUSE DE TETES',360,105,6,0,120);

prompt "Insertion des tuples Village"
--INSERT INTO Village VALUES (ID, NOM_village, lvl, capacité_camp, trophees, idClan);
INSERT INTO Village(idVillage, nomJoueur, niveauJoueur,capaciteeCampMax, trophees, idClan) VALUES (1, 'MATEODU13', 10, 12, 500, null);
INSERT INTO Village(idVillage, nomJoueur, niveauJoueur,capaciteeCampMax, trophees, idClan) VALUES (2, 'MAXIME', 125, 2800, 400, null);
INSERT INTO Village(idVillage, nomJoueur, niveauJoueur,capaciteeCampMax, trophees, idClan) VALUES (3, 'ADAM', 53, 600, 726, null);
INSERT INTO Village(idVillage, nomJoueur, niveauJoueur,capaciteeCampMax, trophees, idClan) VALUES (4, 'ARNAUD', 75, 800, 894, null);
INSERT INTO Village(idVillage, nomJoueur, niveauJoueur,capaciteeCampMax, trophees, idClan) VALUES (5, 'BLAISE', 47, 1200, 1300, null);
INSERT INTO Village(idVillage, nomJoueur, niveauJoueur,capaciteeCampMax, trophees, idClan) VALUES (6, 'DOOBY', 26, 50, 478, null);
INSERT INTO Village(idVillage, nomJoueur, niveauJoueur,capaciteeCampMax, trophees, idClan) VALUES (7, 'AROUF', 210, 4500, 365, null);
INSERT INTO Village(idVillage, nomJoueur, niveauJoueur,capaciteeCampMax, trophees, idClan) VALUES (8, 'LACRIM', 92, 2000, 957, null);
INSERT INTO Village(idVillage, nomJoueur, niveauJoueur,capaciteeCampMax, trophees, idClan) VALUES (9, 'DIAMS', 111, 2100, 1600, null);
INSERT INTO Village(idVillage, nomJoueur, niveauJoueur,capaciteeCampMax, trophees, idClan) VALUES (10, 'GAZO', 136, 2500, 1548, null);
INSERT INTO Village(idVillage, nomJoueur) VALUES (11, 'GATIEN');
INSERT INTO Village(idVillage, nomJoueur) VALUES (12, 'THOMAS');
INSERT INTO Village(idVillage, nomJoueur) VALUES (13, 'ECHARPE');
INSERT INTO Village(idVillage, nomJoueur) VALUES (14, 'CUISINE');
INSERT INTO Village(idVillage, nomJoueur) VALUES (15, 'DARK');
INSERT INTO Village(idVillage, nomJoueur) VALUES (16, 'WHITE');
INSERT INTO Village(idVillage, nomJoueur) VALUES (17, 'GREY');
INSERT INTO Village(idVillage, nomJoueur, niveauJoueur, trophees) VALUES (18, 'JEAN', 250, 4000);

prompt "Insertion des tuples Heros"
--INSERT INTO Heros VALUES (id,type,niveau,vie,idVillage);
INSERT INTO Heros VALUES (001,'Roi des Barbares',50,100,11);
INSERT INTO Heros VALUES (002,'Reine des Archers',50,100,11);
INSERT INTO Heros VALUES (003,'Grand Gardien',30,100,11);
INSERT INTO Heros VALUES (004,'Roi des Barbares',40,100,2);
INSERT INTO Heros VALUES (005,'Reine des Archers',40,100,2);
INSERT INTO Heros VALUES (006,'Grand Gardien',5,100,2);
INSERT INTO Heros VALUES (007,'Roi des Barbares',10,100,4);
INSERT INTO Heros VALUES (008,'Roi des Barbares',85,100,7);
INSERT INTO Heros VALUES (009,'Reine des Archers',85,100,7);
INSERT INTO Heros VALUES (010,'Grand Gardien',55,100,7);
INSERT INTO Heros VALUES (011,'Championne Royale',30,100,7);
INSERT INTO Heros VALUES (012,'Roi des Barbares',20,100,8);
INSERT INTO Heros VALUES (013,'Reine des Archers',10,100,8);
INSERT INTO Heros VALUES (014,'Roi des Barbares',35,100,9);
INSERT INTO Heros VALUES (015,'Reine des Archers',30,100,9);
INSERT INTO Heros VALUES (016,'Roi des Barbares',40,100,10);
INSERT INTO Heros VALUES (017,'Reine des Archers',45,100,10);
INSERT INTO Heros VALUES (018,'Grand Gardien',10,100,10);

prompt "Insertion des tuples Attaque"
--INSERT INTO Attaque VALUES (id,idAtt,idDef,trophees,etoiles,%,or,elix,noir,idGuerre);
INSERT INTO Attaque VALUES (1,2,11,14,2,67,928680,874315,8436,null);
INSERT INTO Attaque VALUES (2,11,7,5,1,52,125864,130221,589,null);
INSERT INTO Attaque VALUES (3,8,3,0,3,100,100000,100000,1000,1);
INSERT INTO Attaque VALUES (4,3,8,0,1,42,100000,100000,1000,1);
INSERT INTO Attaque VALUES (5,4,9,0,2,68,100000,100000,1000,1);
INSERT INTO Attaque VALUES (6,7,11,0,3,100,100000,100000,1000,1);
INSERT INTO Attaque VALUES (7,2,10,0,2,75,100000,100000,1000,1);
INSERT INTO Attaque VALUES (8,11,7,0,2,51,100000,100000,1000,1);
INSERT INTO Attaque VALUES (9,10,2,0,2,89,100000,100000,1000,1);
INSERT INTO Attaque VALUES (10,9,4,0,1,77,100000,100000,1000,1);
 -- TUPLES ATTAQUE A CORRIGER
prompt "Insertion des tuples Clan"

--INSERT INTO Clan VALUES (ID,Nom,region,niveau,chef) 
INSERT INTO Clan VALUES (1,'GNUMZ','FR',15,3);
INSERT INTO Clan VALUES (2,'FC GANGST','US',12,7);
INSERT INTO Clan VALUES (3,'TROPHY PUSH','US',20,10);
INSERT INTO Clan VALUES (4,'LES ZELUS','FR',1,1);
INSERT INTO Clan VALUES (5,'AROUF', 'ES', 15,12);
INSERT INTO Clan VALUES (6,'UM', 'FR', 7,13);
INSERT INTO Clan VALUES (7,'JAAJ', 'ES', 15,14);
INSERT INTO Clan VALUES (8,'MALEMORT', 'GI', 1,15);
INSERT INTO Clan VALUES (9,'DESPACITO', 'ES', 2,16);
INSERT INTO Clan VALUES (10,'FLAMENCO', 'ES', 7,17);

prompt "Ajout de membres à des clans"
-- Ajout de membres dans des clans (un trigger s'occupe déjà de rajouter le chef)
UPDATE Village SET idClan = 1 WHERE idVillage = 2;
UPDATE Village SET idClan = 1 WHERE idVillage = 11;
UPDATE Village SET idClan = 1 WHERE idVillage = 4;
UPDATE Village SET idClan = 4 WHERE idVillage = 5;
UPDATE Village SET idClan = 3 WHERE idVillage = 6;
UPDATE Village SET idClan = 2 WHERE idVillage = 8;
UPDATE Village SET idClan = 2 WHERE idVillage = 9;

prompt "Insertion des tuples GuerreDeClan"
--INSERT INTO GuerreDeClan VALUES (idGuerre,idClan1,idClan2,nbrAttaquesMax)
INSERT INTO GuerreDeClan VALUES (1,1,2,8);
INSERT INTO GuerreDeClan VALUES (2,2,4,7);
INSERT INTO GuerreDeClan VALUES (3,5,6,5);
INSERT INTO GuerreDeClan VALUES (4,7,8,5);
INSERT INTO GuerreDeClan VALUES (5,9,10,5);
INSERT INTO GuerreDeClan VALUES (6,10,1,5);
INSERT INTO GuerreDeClan VALUES (7,8,4,5);
INSERT INTO GuerreDeClan VALUES (8,6,2,7);
INSERT INTO GuerreDeClan VALUES (9,4,7,10);
INSERT INTO GuerreDeClan VALUES (10,10,2,5);

prompt "Insertion des tuples Reserves"
UPDATE Reserves SET quantite = 1000000 WHERE (idVillage = 2) AND (typeReserve = 'OR');
UPDATE Reserves SET quantite = 1000000 WHERE (idVillage = 2) AND (typeReserve = 'ELIXIR');
UPDATE Reserves SET quantite = 10000 WHERE (idVillage = 2) AND (typeReserve = 'ELIXIRNOIR');
UPDATE Reserves SET quantite = 100000 WHERE (idVillage = 3) AND (typeReserve = 'OR');
UPDATE Reserves SET quantite = 100000 WHERE (idVillage = 3) AND (typeReserve = 'ELIXIR');
UPDATE Reserves SET quantite = 1000 WHERE (idVillage = 3) AND (typeReserve = 'ELIXIRNOIR');
UPDATE Reserves SET quantite = 1000000 WHERE (idVillage = 4) AND (typeReserve = 'OR');
UPDATE Reserves SET quantite = 1000000 WHERE (idVillage = 4) AND (typeReserve = 'ELIXIR');
UPDATE Reserves SET quantite = 10000 WHERE (idVillage = 4) AND (typeReserve = 'ELIXIRNOIR');
UPDATE Reserves SET quantite = 1000000 WHERE (idVillage = 11) AND (typeReserve = 'OR');
UPDATE Reserves SET quantite = 1000000 WHERE (idVillage = 11) AND (typeReserve = 'ELIXIR');
UPDATE Reserves SET quantite = 10000 WHERE (idVillage = 11) AND (typeReserve = 'ELIXIRNOIR');

prompt "Insertion des tuples Camp" 
--(idCamp, idTroupe, idVillage, nbrTroupe)
INSERT INTO Camp VALUES (1, 2, 14, 40);
INSERT INTO Camp VALUES (2, 16, 2, 5);
INSERT INTO Camp VALUES (7, 14, 2, 3);
INSERT INTO Camp VALUES (23, 22, 2, 2);
INSERT INTO Camp VALUES (5, 5, 5, 3);
INSERT INTO Camp VALUES (3, 3, 3, 2);
INSERT INTO Camp VALUES (6, 6, 6, 1);
INSERT INTO Camp VALUES (4, 9, 4, 9);
INSERT INTO Camp VALUES (8, 10, 8, 2);
INSERT INTO Camp VALUES (9, 16, 9, 12);
INSERT INTO Camp VALUES (10, 22, 10, 8);


UPDATE Reserves SET quantite = quantite + 1000000 WHERE (idVillage = 18) AND (typeReserve = 'ELIXIR');
UPDATE Reserves SET quantite = quantite + 1000000 WHERE (idVillage = 18) AND (typeReserve = 'ELIXIRNOIR');

INSERT INTO Camp VALUES (11, 1, 18, 1);
INSERT INTO Camp VALUES (12, 2, 18, 1);
INSERT INTO Camp VALUES (13, 3, 18, 1);
INSERT INTO Camp VALUES (14, 4, 18, 1);
INSERT INTO Camp VALUES (15, 5, 18, 1);
INSERT INTO Camp VALUES (16, 6, 18, 1);
INSERT INTO Camp VALUES (17, 7, 18, 1);
INSERT INTO Camp VALUES (18, 8, 18, 1);
INSERT INTO Camp VALUES (19, 9, 18, 1);
INSERT INTO Camp VALUES (20, 10, 18, 1);
INSERT INTO Camp VALUES (21, 11, 18, 1);
INSERT INTO Camp VALUES (22, 12, 18, 1);
INSERT INTO Camp VALUES (24, 14, 18, 1);
INSERT INTO Camp VALUES (26, 16, 18, 1);
INSERT INTO Camp VALUES (27, 17, 18, 1);
INSERT INTO Camp VALUES (28, 18, 18, 1);
INSERT INTO Camp VALUES (29, 19, 18, 1);
INSERT INTO Camp VALUES (30, 20, 18, 1);
INSERT INTO Camp VALUES (31, 21, 18, 1);
INSERT INTO Camp VALUES (32, 22, 18, 1);
INSERT INTO Camp VALUES (33, 23, 18, 1);
INSERT INTO Camp VALUES (34, 24, 18, 1);
INSERT INTO Camp VALUES (35, 13, 18, 1);
INSERT INTO Camp VALUES (36, 15, 18, 1);