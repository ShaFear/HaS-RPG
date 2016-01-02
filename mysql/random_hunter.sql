CREATE FUNCTION `random_hunter` (ID int)
RETURNS INTEGER
BEGIN
set @random_hunter = (select * from (select users_login from lobbies l 
                    join lobbies_users lu on lu.lobbies_LobbyID=l.LobbyID 
                    where l.LobbyID=ID
                    order by Rand() 
                    LIMIT 1) wynik);
                    

update lobbies set 
                lobbies.Hunter_login =  @random_hunter
                where lobbies.LobbyID=ID;
RETURN 1;
END
