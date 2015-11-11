delete from characters where 1=1;
delete from lobbies_users where 1=1;
delete from lobbies where 1=1;
delete from users where 1=1;


SELECT add_user('Shafear', '74c61aafb526b93a9e75690d70c48404cb4c90d079d79b3f7063dd3ef9949abf');
SELECT add_user('Threexe', '74c61aafb526b93a9e75690d70c48404cb4c90d079d79b3f7063dd3ef9949abf');

select * from lobbies_users;

update lobbies set Hunter_login="Shafear";

