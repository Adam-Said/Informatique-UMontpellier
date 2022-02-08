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
