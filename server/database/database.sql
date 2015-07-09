DROP TABLE IF EXISTS users;
CREATE TABLE users
(
UserID 	      int 		      NOT NULL 	AUTO_INCREMENT 	PRIMARY KEY,
login	        varchar(255) 	NOT NULL 	UNIQUE,
passwd	      varchar(255)	NOT NULL
);

DROP TABLE IF EXISTS characters_of_users;
CREATE TABLE characters_of_users
(
UserID 	    int	         NOT NULL,
CharacterID	int	         NOT NULL
);

DROP TABLE IF EXISTS characters;
CREATE TABLE characters
(
CharacterID	  int		       NOT NULL	  AUTO_INCREMENT	PRIMARY KEY,
lvl	          int		       NOT NULL	  DEFAULT 1,
role	        int          NOT NULL   DEFAULT 0,   -- 0 - chase, 1 - hunter --
class	        int          NOT NULL   DEFAULT 100, -- 100 - 1st, 110 - 2st 1 wybor, 122 -3st, 2 wybor --
experience	  int	         NOT NULL	 DEFAULT 0,
skill_points	int	         NOT NULL	 DEFAULT 0
);

DROP TABLE IF EXISTS skills_of_characters;
CREATE TABLE skills_of_characters
(
CharacterID	  int	         NOT NULL,
name	        varchar(255) NOT NULL,
lvl		        int	         NOT NULL	 DEFAULT 0
);

DROP FUNCTION IF EXISTS add_user;
DELIMITER $$
CREATE FUNCTION add_user(log TEXT, pas TEXT)
  RETURNS TEXT
BEGIN
  INSERT INTO users (login, passwd) VALUES(log, pas);
  INSERT INTO characters(role) VALUES(1);
  INSERT INTO characters_of_users (UserID, CharacterID)
       SELECT users.UserID, characters.CharacterID
       FROM users, characters
       WHERE users.login=log
       AND characters.CharacterID ORDER BY CharacterID DESC
       LIMIT 1;
  INSERT INTO characters(role) VALUES(0);
  INSERT INTO characters_of_users (UserID, CharacterID)
      SELECT users.UserID, characters.CharacterID
      FROM users, characters
      WHERE users.login=log
      AND characters.CharacterID ORDER BY CharacterID DESC
      LIMIT 1;
  RETURN CONCAT("New user added: ", log);
END;
$$
DELIMITER ;



SELECT add_user("Shafear", "74c61aafb526b93a9e75690d70c48404cb4c90d079d79b3f7063dd3ef9949abf");
