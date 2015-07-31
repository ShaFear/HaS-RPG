DROP TABLE IF EXISTS users;
CREATE TABLE users
(
UserID 	      int 		      NOT NULL 	AUTO_INCREMENT 	PRIMARY KEY,
login	        varchar(255) 	NOT NULL 	UNIQUE,
passwd	      varchar(255)	NOT NULL,
ChaseID       int          NOT NULL,
HunterID      int          NOT NULL
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
CharacterID	  int		       NOT NULL	 AUTO_INCREMENT	PRIMARY KEY,
lvl	          int		       NOT NULL	 DEFAULT 1,
role	        varchar(255) NOT NULL,
class	        varchar(255) NOT NULL  DEFAULT 'basic',
experience	  int	         NOT NULL	 DEFAULT 0,
skill_points	int	         NOT NULL	 DEFAULT 0,
skill_1_lvl   int          NOT NULL  DEFAULT 0,
skill_2_lvl   int          NOT NULL  DEFAULT 0,
skill_3_lvl   int          NOT NULL  DEFAULT 0,
skill_4_lvl   int          NOT NULL  DEFAULT 0,
skill_5_lvl   int          NOT NULL  DEFAULT 0
);

DROP FUNCTION IF EXISTS add_user;
DELIMITER $$
CREATE FUNCTION add_user(log TEXT, pas TEXT)
  RETURNS TEXT
BEGIN
  INSERT INTO users (login, passwd, ChaseID, HunterID) VALUES(log, pas, 0, 0);
  INSERT INTO characters(role) VALUES('hunter');
  INSERT INTO characters_of_users (UserID, CharacterID)
       SELECT users.UserID, characters.CharacterID
       FROM users, characters
       WHERE users.login=log
       AND characters.CharacterID ORDER BY CharacterID DESC
       LIMIT 1;
 UPDATE characters_of_users a
       JOIN users b
          ON a.UserID = b.UserID
       JOIN characters c
          ON a.CharacterID = c.CharacterID
       SET b.HunterID = c.CharacterID
       WHERE c.role='hunter';
 INSERT INTO characters(role) VALUES('hunter');
 INSERT INTO characters_of_users (UserID, CharacterID)
      SELECT users.UserID, characters.CharacterID
      FROM users, characters
      WHERE users.login=log
      AND characters.CharacterID ORDER BY CharacterID DESC
      LIMIT 1;
  INSERT INTO characters(role) VALUES('chase');
  INSERT INTO characters_of_users (UserID, CharacterID)
      SELECT users.UserID, characters.CharacterID
      FROM users, characters
      WHERE users.login=log
      AND characters.CharacterID ORDER BY CharacterID DESC
      LIMIT 1;
  UPDATE characters_of_users a
        JOIN users b
           ON a.UserID = b.UserID
        JOIN characters c
           ON a.CharacterID = c.CharacterID
        SET b.ChaseID = c.CharacterID
        WHERE c.role='chase';
  INSERT INTO characters(role) VALUES('chase');
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
