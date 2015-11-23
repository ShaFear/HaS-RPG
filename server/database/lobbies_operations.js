require('./connection.js');

setLobbyStatus = function (lobby_id, status, res) {
    query = 'UPDATE lobbies SET Status=? WHERE LobbyID=? AND Status=?';
    values = [status, lobby_id, "WAIT"];
    connection.query(query, values, function (err, rows, fields) {
        if (!err) {
            //res.status(200).send("succesfuly updated lobby status");
            return;
        }
        if (err) {
            res.status(260).send('Database error');
            console.log(err);
            return;
        }
    });
}

getLobbyStatus = function (lobby_id, res) {
    query = 'select count(*) as READY from lobbies where \
    (select count(lobbies_users.Status) from lobbies \
    join lobbies_users on lobbies_users.lobbies_LobbyID = lobbies.LobbyID \
    where lobbies_users.Status = "READY" and LobbyID=?) = lobbies.PlayersNO AND LobbyID=?;'
    values = [lobby_id, lobby_id];
    connection.query(query, values, function (err, rows, fields) {
        if (!err) {
            if (rows[0]["READY"] == 1)
                setLobbyStatus(lobby_id, "READY", res);
            else
                setLobbyStatus(lobby_id, "WAIT", res);
            return;
        }
        if (err) {
            res.status(260).send('Database error');
            console.log(err);
            return;
        }
    });
}

setUserStatus = function (user_id, lobby_id, status, res) {
    query = 'UPDATE lobbies_users SET Status=? WHERE users_login=(select login from users u where u.UserID=?) AND lobbies_LobbyID=?';
    values = [status, user_id, lobby_id];
    connection.query(query, values, function (err, rows, fields) {
        if (!err) {
            res.status(200).send("succesfuly updated user status");
            return;
        }
        if (err) {
            res.status(260).send('Database error');
            console.log(err);
            return;
        }
    });
}

getLobbyInformation = function (lobby_id, res) {
    getLobbyStatus(lobby_id, res);
    query = 'SELECT * FROM lobbies WHERE LobbyID=?'
    values = [lobby_id];
    connection.query(query, values, function (err, rows, fields) {
        if (!err) {
            res.status(200).send(JSON.stringify(rows));
            return;
        }
        if (err) {
            res.status(260).send('Database error');
            console.log(err);
            return;
        }
    });
}

getLobbyUsers = function (lobby_id, res) {
    query = 'SELECT UserID, Status FROM lobbies_users JOIN users on users.login=lobbies_users.users_login WHERE lobbies_LobbyID=?'
    values = [lobby_id];
    connection.query(query, values, function (err, rows, fields) {
        if (!err) {
            res.status(200).send(JSON.stringify(rows));
            if (rows.length == 0)
                lobbyDelete(lobby_id);
            return;
        }
        if (err) {
            res.status(260).send('Database error');
            console.log(err);
            return;
        }
    });
}

createLobby = function (title, player_no, game_limit, run_time, res) {
    query = 'INSERT INTO lobbies (Title, PlayersNO, GameTime, RunTime)';
    query += 'VALUES(?, ?, ?, ?)';
    values = [title, player_no, game_limit, run_time];
    connection.query(query, values, function (err, rows, fields) {
        if (!err) {
            res.status(200).send(JSON.stringify(rows));
            return;
        }
        if (err) {
            res.status(260).send('Database error');
            console.log(err);
            return;
        }
    });
}

lobbyLogin = function (user_id, lobby_id, res) {
    query = 'DELETE FROM lobbies_users WHERE users_login=(select login from users u where u.UserID=?)';
    values = [user_id];
    connection.query(query, values, function (err, rows, fields) {
        if (err) {
            res.status(260).send('Database error');
            console.log(err);
            return;
        }
    });
    query = ' INSERT INTO lobbies_users (users_login, lobbies_LobbyID) values((select login from users u where u.UserID=?) ,?)';
    values = [user_id, lobby_id];
    connection.query(query, values, function (err, rows, fields) {
        if (!err) {
            res.status(200).send('User succesfuly logged to lobby');
            return;
        }
        if (err) {
            res.status(260).send('Database error');
            console.log(err);
            return;
        }
    });
}

lobbyLogout = function (user_id, lobby_id, res) {
    query = 'DELETE FROM lobbies_users WHERE users_login=(select login from users u where u.UserID=?) AND lobbies_LobbyID=?';
    values = [user_id, lobby_id];
    connection.query(query, values, function (err, rows, fields) {
        if (!err) {
            //res.status(200).send('User succesfuly logged out');
            getLobbyUsers(lobby_id, res);
            return;
        }
        if (err) {
            //res.status(260).send('Database error');
            console.log(err);
            return;
        }
    });
}

lobbyDelete = function (lobby_id) {
    query = "DELETE FROM lobbies WHERE LobbyID=?";
    values = [lobby_id];
    connection.query(query, values, function (err, rows, fields) {
        if (err) {
            console.log(err);
        }
    });
}

getLobbies = function (res) {
    query = 'SELECT * FROM lobbies';
    values = [];
    connection.query(query, values, function (err, rows, fields) {
        if (!err) {
            res.status(200).send(JSON.stringify(rows));
            return;
        }
        if (err) {
            res.status(260).send('Database error');
            console.log(err);
            return;
        }
    });
}
