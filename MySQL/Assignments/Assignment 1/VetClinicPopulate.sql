USE VetClinic;

LOAD DATA
	LOCAL INFILE 'vet.csv'
    INTO TABLE Vet
    COLUMNS TERMINATED BY ','
    LINES TERMINATED BY'\r\n';
    
LOAD DATA
	LOCAL INFILE 'pet.csv'
    INTO TABLE Pet
    COLUMNS TERMINATED BY ','
    LINES TERMINATED BY'\r\n';
    
LOAD DATA
	LOCAL INFILE 'owner.csv'
    INTO TABLE PetOwner
    COLUMNS TERMINATED BY ','
    LINES TERMINATED BY'\r\n';
    
INSERT INTO Appointment (vet_id,pet_id,appt_time) VALUES
	(1,3,'2015-01-20 10:30:00'),
    (5,9,'2015-04-07 17:00:00'),
    (9,4,'2015-12-12 09:45:00'),
    (7,4,'2015-02-24 16:15:00'),
    (10,9,'2015-07-10 13:30:00'),
    (4,6,'2015-11-30 14:45:00'),
    (8,7,'2015-04-24 09:15:00'),
    (11,10,'2015-08-14 16:45:00'),
    (1,8,'2015-05-19 17:00:00'),
    (2,1,'2015-03-27 10:45:00');
	
SELECT firstName, lastName, petName 
	FROM Owner
	INNER JOIN Pet
	ON Owner.owner_id =Pet.owner_id;

SELECT firstName, lastName, COUNT(petName)
	FROM Owner
	INNER JOIN Pet
	ON Owner.owner_id =Pet.owner_id;
