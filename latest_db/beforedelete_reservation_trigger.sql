
USE moviereservation;

DROP TRIGGER IF EXISTS reservations_trigger;
DELIMITER $$
CREATE TRIGGER reservations_trigger
	BEFORE DELETE ON reservations
	FOR EACH ROW
	BEGIN
		UPDATE seats 
        SET seats.reservation_id = NULL
        WHERE seats.reservation_id = OLD.reservation_id;
	END
	$$
	DELIMITER ;