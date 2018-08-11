DELIMITER // /* HOMETEAMNAME AWAYTEAMNAME DATEOFGAME */
CREATE PROCEDURE createGame
(
    IN homeTeamName VARCHAR(20),
    IN awayTeamName VARCHAR(20),
    IN dateOfGame DATE
)
BEGIN
    DECLARE duplicateDate TINYINT DEFAULT FALSE;
    DECLARE noSuchTeam TINYINT DEFAULT FALSE;
    DECLARE awayDateConflict DATE;
    DECLARE homeDateConflict DATE;
    DECLARE homeID;
    DECLARE awayID;
    
    SET homeID = SELECT teamId FROM team WHERE TeanName = homeTeamName;
    SET awayID = SELECT teamId FROM team WHERE TeanName = awayTeamName;
    
    SET awayDateConflict = SELECT GameDate FROM game WHERE GameDate = dateOfGame AND HomeTeamId = awayID OR AwayTeamId= awayID;
    SET homeDateConflict = SELECT GameDate FROM game WHERE GameDate = dateOfGame AND HomeTeamId = homeID OR AwayTeamId= homeID;
    
    START TRANSACTION
        INSERT INTO game (GameDate,HomeTeamId,AwayTeamId,FanTicketsSold,GroupTicketsSold,GeneralTicketsSold,TicketPrice) VALUES
            (dateOfGame,homeID,awayID,0,0,0,0);
            
        IF homeTeamName != SELECT TeamName FROM team WHERE TeamName = homeTeamName
            SELECT "HOME TEAM NOT FOUND";
            SET noSuchTeam = TRUE;
        ELSEIF awayTeamName != SELECT TeamName FROM team WHERE TeamName = awayTeamName
            SELECT "AWAY TEAM NOT FOUND";
            SET noSuchTeam = TRUE;
        ELSEIF awayDateConflict = dateOfGame
            SELECT "Away TEAM ALREADY BOOKED";
            SET duplicateDate = TRUE;
        ELSEIF homeDateConflict = dateOfGame
            SELECT "HOME TEAM ALREADY BOOKED";
            SET duplicateDate = TRUE;
        END IF;    
        IF duplicateDate = TRUE OR noSuchTeam = TRUE
            ROLLBACK;
        ELSE 
            SELECT "GAME CREATED";
    END TRANSACTION
END//

CREATE FUNCTION SellTicket
(
    IN howMany INT,
    IN ticketType VARCHAR(7),
    IN gameID INT
)
BEGIN
    DECLARE ticketMaxError TINYINT DEFAULT FALSE;
    DECLARE ticketMax INT default 0;

    -- VALIDATE ticketType
    IF ticketType = "General"
        SET ticketMax = 10;
    ELSEIF  ticketType = "Fan"
        SET ticketMax=4;
    ELSEIF ticketType = "Group";
        SET ticketMax = 2;
    ELSE 
        SET ticketMaxError;
    END IF
    
    -- VALIDATE howMany
        -- 2 genereal @ reg Price
        -- 3 to 10 group @ 10%
        -- 4 fan @ 25%
    --
END//
DELIMITER ;

2.	SellTicket(HowMany,TicketType, GameId)
/*
This is a function that updates the game table for a given game id.  This 
function should validate the ticket request based on limits per ticket type
 as outlined below.  The function should then update the game table 
 (i.e. update one of the tickets sold fields) and calculate the total price 
 of the ticket purchase (including any discounts that apply).  The value 
 returned by the function should be the total purchase price of the tickets. 
 (Note: the base ticket price is set by the CreateTournament procedure 
 defined below.) 

Details: 
•	The function should handle the sale of general admission, group, or fan
    tickets.   
•	Up to 2 general admission tickets can be purchased at a time (at the 
    regular ticket price),
•	Between 3 and 10 group admission tickets can be purchased at a time (at 
    10% discount from regular ticket price).
•	Up to 4 fan tickets can be purchased at a time (at 25% discount from 
    regular ticket price)
•	When tickets are purchased, the corresponding number of ticket records 
    are created and initialized, and the appropriate XXXTicketsSold field 
    for the corresponding game is updated.
•	The function should ensure that a valid number of tickets is requested 
    (i.e. 1-2 for general admission, 3-10 per group, 1-4 per fan).
•	The function should ensure that after the procedure has completed, the 
    data in the database is accurate and valid.

*/


3.	CreateTournament(TournamentName,  NumberOfTeams, StartDate, TicketPrice). 
/*
This procedure populates the tournament table with the ids of teams playing 
in a tournament.   Each tournament is given a name (e.g. ‘Kingston Cup’).   
The procedure selects the teams with the highest point score to play in the 
tournament.  (The number of teams selected to play is given by the 
NumberOfTeams parameter).   The qualifying teams are paired up so that the 
team with the highest point score plays the team with the lowest point score.
(E.g. if there are six teams in the tournament, #1 plays #6, #2 plays #5, 
and #3 plays #4.)        

The procedure schedules one game per day.   The first pair of teams play on 
the specified start date.  The next pair plays the following day, etc.   
E.g. if the start date is Nov 1, and there are six teams in the tournament, 
game 1 is played on Nov 1, game 2 on Nov 2, and game 3 on Nov 3.

This procedure also sets the base ticket price for all games in the 
tournament.

*/
4.	ShowTournament(TournamentName)
/*  Show the details of a tournament – including tournament name, team names, 
    game dates, and ticket price. 
*/
