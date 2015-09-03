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
