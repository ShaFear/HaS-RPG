DROP TABLE IF EXISTS lobbies;
CREATE TABLE lobbies
(
LobbyID 	      int 		      NOT NULL 	AUTO_INCREMENT 	PRIMARY KEY,
Title 	        varchar(255) 	NOT NULL DEFAULT 'New lobby',
PlayersNO       int           NOT NULL DEFAULT 10,
GameTime        int           NOT NULL DEFAULT 30,
RunTime         int           NOT NULL DEFAULT 5,
Status          varchar(255)  NOT NULL DEFAULT "WAIT"
);

DROP TABLE IF EXISTS lobbies_users;
CREATE TABLE lobbies_users
(
  UserID int NOT NULL,
  LobbyID int NOT NULL,
  Status varchar(255) NOT NULL DEFAULT "WAIT"
)
