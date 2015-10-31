CREATE FUNCTION add_user(log TEXT, pas TEXT)
RETURNS INTEGER
BEGIN
	insert into users(login, passwd) values(log, pas);
	insert into characters(role, users_UserID) values('hunter',(select UserID from users where login='Shafear'));
	insert into characters(role, users_UserID) values('hunter',(select UserID from users where login='Shafear'));
	insert into characters(role, users_UserID) values('chase',(select UserID from users where login='Shafear'));
	insert into characters(role, users_UserID) values('chase',(select UserID from users where login='Shafear'));
	update users u
		join characters c on c.users_UserID = u.UserID
		set u.ChaseID=c.CharacterID
		where c.role='chase' and u.login=log;
	update users u
		join characters c on c.users_UserID = u.UserID
		set u.HunterID=c.CharacterID
		where c.role='hunter' and u.login=log;
RETURN 1;
END