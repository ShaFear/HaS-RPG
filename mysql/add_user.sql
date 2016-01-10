CREATE FUNCTION add_user(log TEXT, pas TEXT)
RETURNS INTEGER
BEGIN
	insert into users(login, passwd) values(log, pas);
	insert into characters(role, users_login) values('hunter', log);
	insert into characters(role, users_login) values('hunter', log);
	insert into characters(role, users_login) values('chase', log);
	insert into characters(role, users_login) values('chase', log);
	update users u
		join characters c on c.users_login = u.login
		set u.ChaseID=c.CharacterID
		where c.role='chase' and u.login=log;
	update users u
		join characters c on c.users_login = u.login
		set u.HunterID=c.CharacterID
		where c.role='hunter' and u.login=log;
RETURN 1;
END