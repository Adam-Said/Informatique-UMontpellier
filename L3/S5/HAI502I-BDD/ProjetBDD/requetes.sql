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