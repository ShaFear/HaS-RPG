DROP TABLE IF EXISTS users;
CREATE TABLE users
(
UserID 	      int 		      NOT NULL 	AUTO_INCREMENT 	PRIMARY KEY,
login	        varchar(255) 	NOT NULL 	UNIQUE,
passwd	      varchar(255)	NOT NULL,
ChaseID       int          NOT NULL,
HunterID      int          NOT NULL
);
