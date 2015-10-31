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
