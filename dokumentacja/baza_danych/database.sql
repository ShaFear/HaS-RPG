DROP TABLE IF EXISTS players;
DROP TABLE IF EXISTS characters_of_players;
DROP TABLE IF EXISTS characters;
DROP TABLE IF EXISTS skills_of_characters;

CREATE TABLE players
(
id 	 int 		NOT NULL 	AUTO_INCREMENT 	PRIMARY KEY,
login	 varchar(255) 	NOT NULL 	UNIQUE,
passwd	 varchar(255)	NOT NULL,
nick 	 varchar(255) 	NOT NULL	UNIQUE				
);

CREATE TABLE characters_of_players
(
player_id 	int	NOT NULL,
character_id	int	NOT NULL
);

CREATE TABLE characters
(
id	int		NOT NULL	AUTO_INCREMENT	PRIMARY KEY,
level	int		NOT NULL	DEFAULT 1,
role	varchar(255)	NOT NULL,
class	varchar(255)	NOT NULL,
experience	int	NOT NULL	DEFAULT 0,
skill_points	int	NOT NULL	DEFAULT 0			
);	

CREATE TABLE skills_of_characters
(
character_id	int	NOT NULL,
name	varchar(255)	NOT NULL,
level		int	NOT NULL	DEFAULT 0			
);

