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