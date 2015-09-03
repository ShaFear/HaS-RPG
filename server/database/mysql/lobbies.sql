DROP TABLE IF EXISTS lobbies;
CREATE TABLE lobbies
(
LobbyID 	      int 		      NOT NULL 	AUTO_INCREMENT 	PRIMARY KEY,
Title 	        varchar(255) 	NOT NULL,
PlayersNO       int           NOT NULL,
GameTime        int           NOT NULL,
RunTime         int           NOT NULL,
Status          varchar(255)  NOT NULL
);

DROP TABLE IF EXISTS lobbies_users;
CREATE TABLE lobbies_users
(
  UserID int NOT NULL,
  LobbyID int NOT NULL,
  Status varchar(255) NOT NULL
)
