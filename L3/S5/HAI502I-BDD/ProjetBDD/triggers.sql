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
