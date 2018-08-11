DROP DATABASE IF EXISTS VetClinic; 
2 CREATE DATABASE VetClinic; 
3 USE VetClinic; 
4 
 
5 CREATE TABLE Owner 
6 ( 
7 	owner_id			INT(10)			PRIMARY KEY, 
8 	firstName			VARCHAR(45), 
9 	lastName			VARCHAR(45), 
10 	phone 				VARCHAR(12), 
11 	street_address		VARCHAR(45), 
12 	city				VARCHAR(20), 
13 	postal_code			VARCHAR(8) 
14 ); 
15 
 
16 CREATE TABLE Vet 
17 ( 
18 	vet_id				INT(10)			PRIMARY KEY, 
19 	fistName			VARCHAR(45), 
20 	lastName			VARCHAR(45) 
21 ); 
22 
 
23 CREATE TABLE Pet 
24 ( 
25 	pet_id				INT(10)			PRIMARY KEY, 
26 	petName				VARCHAR(45), 
27 	owner_id			INT(10), 
28 	breed				VARCHAR(20), 
29 	CONSTRAINT fk_owner_id FOREIGN KEY(owner_id) REFERENCES Owner(owner_id) 
30 ); 
31 
 
32 CREATE TABLE Appointment 
33 ( 
34 	appointment_id		INT(10)			PRIMARY KEY, 
35 	vet_id 				INT(10), 
36 	pet_id				INT(10), 
37 	appt_time			DATETIME 
38 	CONSTRAINT fk_vet_id FOREIGN KEY (vet_id) REFERENCES Vet(vet_id), 
39 	CONSTRAINT fk_pet_id FOREIGN KEY (pet_id) REFERENCES Pet(pet_id) 
40 	 
41 ); 

CREATE USER 'Manager'@'localhost'
	IDENTIFIED BY 'theManager';

CREATE USER 'Receptionist'@'localhost'
	IDENTIFIED BY 'theIntake';

CREATE USER 'Vet'@'localhost'
	IDENTIFIED BY 'theDoc';
	
GRANT UPDATE, INSERT, DELETE, SELECT 
	ON VetClinic.* 
	TO 'Manager'@'localhost';

GRANT UPDATE, INSERT, SELECT, DELETE
	ON VetClinic.Appointment, VetClinic.Pet, VetClinic.Owner
	TO 'Receptionist'@'localhost';	

GRANT SELECT 
	ON VetClinic.Appointment 
	TO 'Vet'@'localhost';
