Enter file contents here-- Games & Developer website

DROP DATABASE IF EXISTS gameDev;
CREATE DATABASE IF NOT EXISTS gameDev;
USE gameDev;

CREATE TABLE game
(
	gameID			SMALLINT		PRIMARY KEY,
	gameName		VARCHAR(35),
	gameReleased	YEAR
);
INSERT INTO game values
	(101,'Civilization',1992),1
	(102, 'Rise of Nations', 2003),1
	(103, 'Earthworm Jim', 1994),2,3
	(104,'Sonic Hedgehog', 1991)4,5
	;
CREATE TABLE developer
(
	developerID			SMALLINT		PRIMARY KEY,
	developerFName		VARCHAR(15),
	developerLName		VARCHAR(20)
);

INSERT INTO developer VALUES
	(1,'Brian','Reynolds'),
	(2,'David', 'Perry'),
	(3, 'Paulo','Sanchez'),
	(4,'Yuji', 'Naka'),
	(5,'Tetsu','Katano')
	;
CREATE TABLE gameDev
(
	gameID			SMALLINT,
	developerID		SMALLINT,
			PRIMARY KEY(gameID, developerID),
			
			CONSTRAINT gameFK
				FOREIGN KEY (gameID)
				REFERENCES game(gameID),

			CONSTRAINT developerFK
				FOREIGN KEY (developerID)
				REFERENCES developer(developerID)
);
