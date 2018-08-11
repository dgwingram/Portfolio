DELIMITER //
CREATE PROCEDURE createGame( HomeTeamName VARCHAR(20),  AwayTeamName VARCHAR(20), date DATE)
BEGIN
DECLARE duplicateDate TINYINT DEFAULT FALSE;

DECLARE homeDate date;
DECLARE awayDate date;
DECLARE homeDate2 date;
DECLARE awayDate2 date;

DECLARE homeId INT;
DECLARE awayId INT;

SET homeDate = SELECT GameDate FROM game JOIN team ON game.HomeTeamId = team.TeamId WHERE TeamName = HomeTeanName;
SET awayDate = SELECT GameDate FROM game JOIN team ON game.AwayTeamId = team.TeamId WHERE TeamName = HomeTeanName;
SET homeDate2 = SELECT GameDate FROM game JOIN team ON game.HomeTeamId = team.TeamId WHERE TeamName = AwayTeamName;
SET awayDate2 = SELECT GameDate FROM game JOIN team ON game.AwayTeamId = team.TeamId WHERE TeamName = AwayTeamName;

SET homeId = SELECT teamId FROM team WHERE TeanName = HomeTeamName;
SET awayId = SELECT teamId FROM team WHERE TeanName = AwayTeamName;

START TRANSACTION
    

    INSERT INTO game(GameDate,HomeTeamId,AwayTeamId,TicketPrice) VALUES
    (DATE, homeId, awayId, 40);

    IF homeDate = DATE OR awayDate = DATE
        SELECT "Home Team Already Booked";
        SET duplicateDate = TRUE;
    END IF;
    IF homeDate2=DATE OR awayDate2 = DATE
        SELECT "Away Team Already Booked";
        SET duplicateDate = TRUE;
    End IF;


END TRANSACTION

END//
DELIMITER ;  

/* SellTicket */

DELIMITER //

CREATE PROCEDURE SellTicket (HowMany INT, TicketType INT, GameId INT)
BEGIN

DECLARE ticketMaxError TINYINT DEFAULT FALSE;

DECLARE ticketMax INT default 0;

IF ticketType = "General"
    SET ticketMax = 10;
ELSEIF  ticketType = "Fan"
    SET ticketMax=4;
ELSEIF 
    SET ticketMax = 2;
ELSE 
    SET ticketMaxError;
END IF

END//
DELIMITER ;
