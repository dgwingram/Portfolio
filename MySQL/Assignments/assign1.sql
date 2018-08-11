/*
	Assign 1
    Daniel Ingram
    2015-Jun-05
    This database is used by a Cruise Line for their ships and ship destinations
    
*/

DROP DATABASE IF EXISTS db_CruiseLine;
CREATE DATABASE db_CruiseLine;
USE db_CruiseLine;

CREATE TABLE ship
(
	shipID			char(2)			PRIMARY KEY,
	shipName		VARCHAR(20),
	shipMaxPassenger	SMALLINT
);

INSERT INTO ship VALUES
	('A1','Atlantic Star',1250),
    ('B1','Brilliance of the Seas',2580),
    ('C1','Caribbean Princess',3756),
    ('E1','Eurodam',2104),
    ('N1','Norwegian Breakaway',4000);


CREATE TABLE cruise
(
	cruiseID			CHAR(3)			PRIMARY KEY,
	cruiseStart			DATE,
	cruiseCost			DECIMAL(9,2),
	cruiseDestination	CHAR(30),
	cruiseAdultOnly		BOOLEAN,
	shipID				CHAR(2),
    CONSTRAINT ship_fk_cruise
		FOREIGN KEY (shipID)
        REFERENCES	ship(shipID)
);

INSERT INTO cruise VALUES
	('D01','2015-07-05',2136.75,'Canada-New England',TRUE,'A1'),
	('D02','2015-09-30',3450.41,'Caribbean',TRUE,'C1'),
    ('D03','2015-01-20',1542.11,'Alaska',FALSE,'B1'),
    ('D04','2015-03',4998.01,'Disney World',FALSE,'N1'),
    ('D05','2015-08-17',5000.00,'Africa',FALSE,'B1'),
    ('D06','2015-06-18',509.40,'Bahamas',TRUE,'C1'),
    ('D07','2015-09-01',2856.45,'Caymen Islands',TRUE,'C1'),
    ('D08','2015-12-14',4444.44,'New Zeland',FALSE,'E1'),
    ('D09','2015-07-05',3333.33,'Bermuda',TRUE,'C1'),
    ('D10','2015-07-05',2222.22,'Mexico',TRUE,'B1');

SELECT *
FROM cruise;
