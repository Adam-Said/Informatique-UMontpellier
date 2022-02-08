/* =======================================
  Groupe 2 - La Bataille des Villages
    21905365 SAID ADAM
    21901688 BOURRET MAXIME
    21903888 HADDAD GATIEN
    21901021 COSSU ARNAUD
  =========================================
*/

/*  ==============================
    |  Suppression des relations |
    ==============================
*/

prompt "Suppression des relations"

BEGIN
EXECUTE IMMEDIATE 'DROP TABLE Camp';
EXCEPTION
 WHEN OTHERS THEN
  IF SQLCODE != -942 THEN
  RAISE;
  END IF;
END;
/

BEGIN
EXECUTE IMMEDIATE 'DROP TABLE Clan CASCADE CONSTRAINT';
EXCEPTION
 WHEN OTHERS THEN
  IF SQLCODE != -942 THEN
  RAISE;
  END IF;
END;
/

BEGIN
EXECUTE IMMEDIATE 'DROP TABLE Village CASCADE CONSTRAINT';
EXCEPTION
 WHEN OTHERS THEN
  IF SQLCODE != -942 THEN
  RAISE;
  END IF;
END;
/

BEGIN
EXECUTE IMMEDIATE 'DROP TABLE GuerreDeClan';
EXCEPTION
 WHEN OTHERS THEN
  IF SQLCODE != -942 THEN
  RAISE;
  END IF;
END;
/

BEGIN
EXECUTE IMMEDIATE 'DROP TABLE Reserves';
EXCEPTION
 WHEN OTHERS THEN
  IF SQLCODE != -942 THEN
  RAISE;
  END IF;
END;
/

BEGIN
EXECUTE IMMEDIATE 'DROP TABLE Heros';
EXCEPTION
 WHEN OTHERS THEN
  IF SQLCODE != -942 THEN
  RAISE;
  END IF;
END;
/

BEGIN
EXECUTE IMMEDIATE 'DROP TABLE Troupe';
EXCEPTION
 WHEN OTHERS THEN
  IF SQLCODE != -942 THEN
  RAISE;
  END IF;
END;
/


BEGIN
EXECUTE IMMEDIATE 'DROP TABLE Attaque';
EXCEPTION
 WHEN OTHERS THEN
  IF SQLCODE != -942 THEN
  RAISE;
  END IF;
END;
/

-- *************************************************

/*  ===========================
    |  Création des relations |
    ===========================
*/  

prompt "Création des relations"

CREATE TABLE Village(
	idVillage NUMBER(10) NOT NULL, 
  nomJoueur VARCHAR(20) NOT NULL,
  niveauJoueur NUMBER(10) DEFAULT 1,
  capaciteeCampMax NUMBER(10) DEFAULT 100,
  trophees NUMBER(10) DEFAULT 100,
  idClan NUMBER(10),
  CONSTRAINT PK_IDVILLLAGE PRIMARY KEY (idVillage),
  CONSTRAINT UN_NOMJ UNIQUE (nomJoueur)
);

CREATE TABLE Clan(
  idClan NUMBER(10),
  nomClan VARCHAR(20) NOT NULL,
  regionClan VARCHAR(20),
  niveauClan NUMBER(10) DEFAULT 1 NOT NULL,
  idChefDeClan NUMBER(10) NOT NULL,
  CONSTRAINT PK_idClan PRIMARY KEY (idClan),
  CONSTRAINT FK_CHEFDECLAN FOREIGN KEY (idchefDeClan) REFERENCES Village(idVillage),
  CONSTRAINT UN_NOMC UNIQUE (nomClan)
);

CREATE TABLE Troupe(
  idTroupe NUMBER(10),
  nomTroupe VARCHAR(50) NOT NULL,
  PV NUMBER(10) NOT NULL,
  degats NUMBER(10) NOT NULL,
  placeOccupee NUMBER(10) NOT NULL,
  prixElixir NUMBER(10) NOT NULL,
  prixElixirNoir NUMBER(10) NOT NULL,
  CONSTRAINT PK_IDTROUPE PRIMARY KEY (idTroupe)
);

CREATE TABLE Camp(
  idCamp NUMBER(10),
  idTroupe NUMBER(10) NOT NULL,
  idVillage NUMBER(10) NOT NULL,
  nbrTroupe NUMBER(10) DEFAULT 1,
  CONSTRAINT PK_IDCAMP PRIMARY KEY (idCamp),
  CONSTRAINT FK_typeTroupe FOREIGN KEY (idTroupe) REFERENCES Troupe(idTroupe),
  CONSTRAINT FK_idVillageCamp FOREIGN KEY (idVillage) REFERENCES Village(idVillage)
);

CREATE TABLE Heros(
  idHeros NUMBER(10),
  typeHeros VARCHAR(50) CHECK(typeHeros IN ('Reine des Archers', 'Roi des Barbares', 'Grand Gardien', 'Championne Royale')),
  niveauHeros NUMBER(10) DEFAULT 1,
  vieHeros NUMBER(10),
  idVillage NUMBER(10) NOT NULL,
  CONSTRAINT PK_IDHEROS PRIMARY KEY (idHeros),
  CONSTRAINT FK_idVillageHeros FOREIGN KEY (idVillage) REFERENCES Village(idVillage)
);

CREATE TABLE Reserves(
  idVillage NUMBER(10) NOT NULL,
  typeReserve VARCHAR(10) CHECK(typeReserve IN ('OR', 'ELIXIR', 'ELIXIRNOIR')),
  quantiteMax NUMBER(10) NOT NULL,
  quantite NUMBER(10) DEFAULT 0,
  CONSTRAINT PK_IDVILLAGE PRIMARY KEY (idVillage, typeReserve),
  CONSTRAINT FK_idVillageReserve FOREIGN KEY (idVillage) REFERENCES Village(idVillage)
);

CREATE TABLE GuerreDeClan(
  idGuerre NUMBER(10),
  idClan1 NUMBER(10) NOT NULL,
  idClan2 NUMBER(10) NOT NULL,
  nombreAttaquesMax NUMBER(10) DEFAULT 10,
  CONSTRAINT PK_IDGUERRE PRIMARY KEY (idGuerre),
  CONSTRAINT FK_idClan1 FOREIGN KEY (idClan1) REFERENCES Clan(idClan),
  CONSTRAINT FK_idClan2 FOREIGN KEY (idClan2) REFERENCES Clan(idClan)
);

CREATE TABLE Attaque(
  idAttaque NUMBER(10) NOT NULL,
  idAttaquant NUMBER(10) NOT NULL,
  idDefenseur NUMBER(10) NOT NULL,
  tropheesPris NUMBER(10),
  etoiles NUMBER(10),
  pourcentage NUMBER(10),
  orRecolte NUMBER(10),
  elixirRecolte NUMBER(10),
  elixirNoirRecolte NUMBER(10),
  idGuerre NUMBER(10),
  CONSTRAINT PK_idAttaque PRIMARY KEY (idAttaque),
  CONSTRAINT FK_idAttaquant FOREIGN KEY (idAttaquant) REFERENCES Village(idVillage),
  CONSTRAINT FK_idDefenseur FOREIGN KEY (idDefenseur) REFERENCES Village(idVillage)
);

-- *************************************************

/*  ====================
    |  Clés Etrangères |
    ====================
*/  

prompt "Ajout des clés etrangères"

ALTER TABLE Village ADD CONSTRAINT FK_idClan FOREIGN KEY (idClan) REFERENCES Clan(idClan);

/*  ==============================
    |  Suppression des fonctions |
    ==============================
*/

prompt "Suppression des Fonctions"


begin
   execute immediate 'DROP PROCEDURE calculCapaciteMax';
exception when others then
   if sqlcode != -4043 then
      raise;
   end if;
end;
/

BEGIN
EXECUTE IMMEDIATE 'DROP FUNCTION calculQuantiteMax';
EXCEPTION
 WHEN OTHERS THEN
  IF SQLCODE != -4043 THEN
  RAISE;
  END IF;
END;
/


/*  ===========================
    |  Création des fonctions |
    ===========================
*/  

prompt "Création des fonctions"

-- Renvoie la capacité max du village en fonction du niveau du village
CREATE OR REPLACE PROCEDURE calculCapaciteMax (
  lvl IN INTEGER,
  nb OUT INTEGER) IS 
BEGIN
  IF lvl < 100 THEN nb := (100 + 2*lvl);
  ELSE nb := 300;
  END IF;
END;
/


-- Renvoie la quantité de ressource maximale du village en fonction du niveau du village
CREATE OR REPLACE FUNCTION calculQuantiteMax (id IN INTEGER) 
RETURN INTEGER IS nb INTEGER;
lvl INTEGER;
BEGIN
  SELECT niveauJoueur INTO lvl FROM Village WHERE idVillage=id;
  IF lvl < 100 THEN nb := 100000*lvl;
  ELSE nb := 10000000;
  END IF;
  RETURN (nb);
END; 
/

prompt "Fonctions créées"

/*  ===========================
    |  Création des triggers |
    ===========================
*/  

prompt "Lancement des Triggers" 
/*  ===========================
    |  Création des triggers |
    ===========================
*/  

prompt "Trigger nouveauVillage"
--[Trigger pour créer un nouveau village et calculer sa capcitée Max]
CREATE OR REPLACE TRIGGER nouveauVillage
BEFORE INSERT ON Village
FOR EACH ROW
BEGIN
  :new.nomJoueur := UPPER(:new.nomJoueur);
  IF (:new.niveauJoueur IS NULL) THEN :new.niveauJoueur := 1;
  END IF;
  IF (:new.capaciteeCampMax IS NULL) THEN
  calculCapaciteMax(:new.niveauJoueur,:new.capaciteeCampMax);
  END IF;
END;
/

prompt "Trigger nouvelleReserve"
--[trigger qui ajoute une reserve à chaque création d un village]
CREATE OR REPLACE TRIGGER nouvelleReserve
AFTER INSERT ON Village
DECLARE
  qMax INTEGER;
BEGIN
  FOR record IN (SELECT idVillage FROM Village WHERE idVillage NOT IN (SELECT idVillage FROM Reserves))
  LOOP
    qMax := calculQuantiteMax(record.idVillage);
    INSERT INTO Reserves(idVillage, typeReserve, quantiteMax, quantite) VALUES(record.idVillage, 'OR', qMax, 100000);
    INSERT INTO Reserves(idVillage, typeReserve, quantiteMax, quantite) VALUES(record.idVillage, 'ELIXIR', qMax, 100000);
    INSERT INTO Reserves(idVillage, typeReserve, quantiteMax, quantite) VALUES(record.idVillage, 'ELIXIRNOIR', qMax, 100000);
  END LOOP;
END;
/

prompt "Trigger supprimerClanSansChef"
--[trigger Si le chef de clan quittes le clan, le clan est supprimé]
CREATE OR REPLACE TRIGGER supprimerClanSansChef
AFTER UPDATE ON Village
FOR EACH ROW
DECLARE
  idChef INTEGER;
BEGIN
  dbms_output.put_line('Declared Value:');
  dbms_output.put_line(:new.idClan);
  dbms_output.put_line(:old.idClan);
  IF NOT(:old.idClan = :new.idClan) OR ((:new.idClan IS NULL) AND (:old.idClan IS NOT NULL)) THEN
    BEGIN
    SELECT idChefDeClan INTO idChef FROM Clan WHERE idClan = :old.idClan;
    IF (:new.idVillage = idChef) THEN 
      DELETE FROM Clan WHERE idClan = :old.idClan;
    END IF;
    END;
  END IF;
END;
/



prompt "Trigger calculTrophéesNegatifs"
--[trigger si les Trophées sont en négatif, ils passent à 0]
CREATE OR REPLACE TRIGGER calculTropheesNegatifs
BEFORE UPDATE ON Village
FOR EACH ROW
BEGIN
  IF :new.trophees < 0 
    THEN :new.trophees := 0;
  END IF;
END;
/



prompt "Trigger calculAttaque"
--[trigger pour ajouter à l'attaquant les ressources gagnées et les trophées après une attaque et les enlever au défenseur]
CREATE OR REPLACE TRIGGER calculAttaque
AFTER INSERT ON Attaque
FOR EACH ROW
BEGIN
  UPDATE Village SET trophees = trophees + :new.tropheesPris WHERE (idVillage=:new.idAttaquant); 
  UPDATE Reserves SET quantite = quantite + :new.orRecolte WHERE (idVillage=:new.idAttaquant AND typeReserve='OR');
  UPDATE Reserves SET quantite = quantite + :new.elixirRecolte WHERE (idVillage=:new.idAttaquant AND typeReserve='ELIXIR');
  UPDATE Reserves SET quantite = quantite + :new.elixirNoirRecolte WHERE (idVillage=:new.idAttaquant AND typeReserve='ELIXIRNOIR');
  UPDATE Village SET trophees = trophees - :new.tropheesPris WHERE (idVillage=:new.idDefenseur);
  UPDATE Reserves SET quantite = quantite - :new.orRecolte WHERE (idVillage=:new.idDefenseur AND typeReserve='OR');
  UPDATE Reserves SET quantite = quantite - :new.elixirRecolte WHERE (idVillage=:new.idDefenseur AND typeReserve='ELIXIR');
  UPDATE Reserves SET quantite = quantite - :new.elixirNoirRecolte WHERE (idVillage=:new.idDefenseur AND typeReserve='ELIXIRNOIR');
END;
/

prompt "Trigger nouvelleTroupe"
--[trigger pour créer une troupe en vérifiant qu'on a la place et les ressources nécessaires]
CREATE OR REPLACE TRIGGER nouvelleTroupe
BEFORE INSERT ON Camp
FOR EACH ROW
DECLARE
  placeTotalePrise INTEGER;
  elixirDispo INTEGER;
  elixirNoirDispo INTEGER;
  elixirPrix INTEGER;
  elixirNoirPrix INTEGER;
  capaMaxVillage INTEGER;
BEGIN
  SELECT placeOccupee * :new.nbrTroupe INTO placeTotalePrise FROM Troupe
  WHERE idTroupe = :new.idTroupe;
  SELECT quantite INTO elixirDispo FROM Reserves 
  WHERE Reserves.idVillage = :new.idVillage AND typeReserve = 'ELIXIR';
  SELECT quantite INTO elixirNoirDispo FROM Reserves 
  WHERE Reserves.idVillage = :new.idVillage AND typeReserve = 'ELIXIRNOIR';
  SELECT prixElixir * :new.nbrTroupe INTO elixirPrix FROM Troupe
  WHERE idTroupe = :new.idTroupe;
  SELECT prixElixirNoir * :new.nbrTroupe INTO elixirNoirPrix FROM Troupe
  WHERE idTroupe = :new.idTroupe;
  SELECT capaciteeCampMax INTO capaMaxVillage FROM Village
  WHERE idVillage = :new.idVillage;
  IF ((capaMaxVillage >= placeTotalePrise) AND (elixirDispo >= elixirPrix) AND (elixirNoirDispo >= elixirNoirPrix)) THEN BEGIN
    UPDATE Reserves SET quantite = quantite - elixirPrix WHERE (idVillage=:new.idVillage AND typeReserve='ELIXIR');
    UPDATE Reserves SET quantite = quantite - elixirNoirPrix WHERE (idVillage=:new.idVillage AND typeReserve='ELIXIRNOIR');
    END; 
  ELSE RAISE_APPLICATION_ERROR (-20500, 'Vous n avez pas assez de ressource pour creer la troupe.');
  END IF;
END;
/

prompt "Trigger RejoindreChefClan"
--[trigger pour ajouter l'id d'un clan à un Village qui en est le chef]
CREATE OR REPLACE TRIGGER RejoindreChefClan
AFTER INSERT ON Clan
FOR EACH ROW
BEGIN
  UPDATE Village SET idClan = :new.idClan WHERE idVillage = :new.idChefDeClan;
END;
/

prompt "Trigger calculReservesNegatives"
--[trigger si les réserves sont en négatif, elles passent à 0]
CREATE OR REPLACE TRIGGER calculReservesNegatives
BEFORE UPDATE ON Reserves
FOR EACH ROW
BEGIN
  IF :new.quantite < 0 
    THEN :new.quantite := 0;
  END IF;
END;
/

prompt -Triggers lancés

/*  ===========================
    |  Remplissage des tables |
    ===========================
*/  

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

/*  =============
    |  Requetes |
    =============
*/  

prompt
prompt "##########################################################"
prompt "Toutes les différentes troupes du village de 'MAXIME' (Requete Group By)"
prompt "##########################################################"
prompt

SELECT Troupe.nomTroupe FROM Troupe, Camp, Village
WHERE Troupe.idTroupe=Camp.idTroupe
AND Camp.idVillage=Village.idVillage
AND Village.nomJoueur='MAXIME'
GROUP BY Troupe.nomTroupe;

prompt 
prompt "##########################################################"
prompt "Or Moyen dans les réserves des joueurs du clan GNUMZ"
prompt "##########################################################"
prompt 

SELECT AVG(quantite) AS Or_Moyen FROM Reserves, Village, Clan
WHERE Reserves.idVillage = Village.idVillage AND Clan.idClan = Village.idClan 
AND Reserves.typeReserve='OR'
AND Reserves.idVillage=Village.idVillage
AND Village.idClan=Clan.idClan
AND Clan.nomClan='GNUMZ';

prompt 
prompt "##########################################################"
prompt "Requete group by : Nombre de troupes du village de 'JEAN'"
prompt "##########################################################"
prompt 

SELECT Village.nomJoueur, COUNT(*) FROM Camp, Village
WHERE Village.idVillage = Camp.idVillage
AND Camp.idVillage=Village.idVillage
AND Village.nomJoueur='JEAN'
GROUP BY Village.nomJoueur;

prompt 
prompt "##########################################################"
prompt "Requete avec division : Existe-t-il un village qui possède toutes les troupes ?"
prompt "Autrement dit : Existe-t-il un village tel qu'il n'existe aucune troupe qui ne soit pas formé par ce village ?"
prompt "##########################################################"
prompt 

SELECT Village.idVillage, Village.nomJoueur FROM Village
  WHERE NOT EXISTS
    (SELECT * FROM Troupe WHERE NOT EXISTS
      (SELECT * FROM Camp WHERE Camp.idVillage = Village.idVillage
                          AND Camp.idTroupe = Troupe.idTroupe));

prompt
prompt "##########################################################"
prompt "Les villages qui ne possèdent pas de troupe (Sous-requête)"
prompt "##########################################################"
prompt 

SELECT Village.idVillage FROM Village 
WHERE Village.idVillage NOT IN (SELECT idVillage FROM Camp GROUP BY idVillage);

prompt 
prompt "##########################################################"
prompt "Requete avec sous requete corrélative : Quels sont les villages qui possèdent des troupes"
prompt "##########################################################"
prompt 

SELECT idVillage, nomJoueur FROM Village Vil 
WHERE EXISTS(SELECT * FROM Camp WHERE Vil.idVillage = Camp.idVillage); 

prompt 
prompt "##########################################################"
prompt "Village(s) possédant le plus d'elixir Noir"
prompt "##########################################################"
prompt 

SELECT v.idVillage, v.nomJoueur, r.quantite FROM Village v, Reserves r
WHERE v.idVillage = r.idVillage
AND r.typeReserve = 'ELIXIRNOIR'
AND r.quantite >= (SELECT MAX(quantite) FROM Reserves
                  WHERE typeReserve='ELIXIRNOIR');

prompt 
prompt "##########################################################"
prompt "Requete group by : Nombre d'attaques des joueurs au dessus du niveau 50"
prompt "##########################################################"
prompt 

SELECT Village.idVillage, Village.nomJoueur, COUNT(*) FROM Attaque, Village
WHERE Village.idVillage = Attaque.idAttaquant
AND Village.niveauJoueur >= 50
GROUP BY Village.idVillage, Village.nomJoueur;