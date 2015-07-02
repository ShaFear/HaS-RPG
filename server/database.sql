DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS characters_of_users;
DROP TABLE IF EXISTS characters;
DROP TABLE IF EXISTS skills_of_characters;

CREATE TABLE users
(
id 	          int 		      NOT NULL 	AUTO_INCREMENT 	PRIMARY KEY,
login	        varchar(255) 	NOT NULL 	UNIQUE,
passwd	      varchar(255)	NOT NULL
);

CREATE TABLE characters_of_users
(
user_id 	    int	         NOT NULL,
character_id	int	         NOT NULL
);

CREATE TABLE characters
(
id	          int		       NOT NULL	  AUTO_INCREMENT	PRIMARY KEY,
lvl	          int		       NOT NULL	  DEFAULT 1,
role	        varchar(255) NOT NULL,
class	        varchar(255) NOT NULL,
experience	  int	         NOT NULL	 DEFAULT 0,
skill_points	int	         NOT NULL	 DEFAULT 0
);

CREATE TABLE skills_of_characters
(
character_id	int	         NOT NULL,
name	        varchar(255) NOT NULL,
lvl		        int	         NOT NULL	 DEFAULT 0
);
