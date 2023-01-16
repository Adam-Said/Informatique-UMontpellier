

DECLARE
	nb_emp NUMBER(3,0);
	numero NUMBER(6,0);
	numero2 ABONNE.NUM_AB%type;

BEGIN
	numero := &numero_abo;
	SELECT num_ab INTO numero2 FROM ABONNE
		WHERE num_ab = numero;

	SELECT COUNT(*) INTO nb_emp FROM EMPRUNT
		WHERE num_ab = numero;

	IF nb_emp = 0 THEN 
		RAISE no_data_found;
	ELSE
		INSERT INTO AB_NB VALUES (numero, nb_emp);
	END IF;

EXCEPTION
WHEN no_data_found then INSERT INTO AB_NB VALUES (numero, -1);


END;
/

CREATE OR REPLACE FUNCTION nbprets(abo INTEGER)
RETURN INTEGER IS
	nb INTEGER := 0;
BEGIN
	SELECT COUNT(*) INTO nb
	FROM EMPRUNT
	WHERE NUM_AB = abo;

	RETURN nb;
END;
/

SELECT nbprets(num_ab) FROM ABONNE;




CREATE OR REPLACE TRIGGER test_exemplaire
BEFORE DELETE ON EXEMPLAIRE
FOR EACH ROW
DECLARE
	state VARCHAR(15);
	deletion_impossible EXCEPTION;
BEGIN
	SELECT ETAT INTO state FROM EXEMPLAIRE WHERE EXEMPLAIRE.NUMERO = :OLD.NUMERO;
	IF(state = 'BON') THEN
		RAISE deletion_impossible;
	END IF;
EXCEPTION
WHEN deletion_impossible THEN  DBMS_OUTPUT.put_line('Suppression impossible, l état du livre est BON');

END;
/