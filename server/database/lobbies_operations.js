require('./connection.js');

setLobbyStatus =function(lobby_id, status, res){
  query = 'UPDATE lobbies SET Status=? WHERE LobbyID=?';
  values = [status, lobby_id];
  connection.query(query, values, function(err, rows, fields){
    if(!err){
      res.status(200).send("succesfuly updated lobby status");
      return;
    }
    if(err){
      res.status(260).send('Database error');
      console.log(err);
      return;
    }
  });
}

getLobbyStatus = function(lobby_id, res){
  query = 'SELECT Status FROM lobbies WHERE LobbyID=?';
  values = [lobby_id];
  connection.query(query, values, function(err, rows, fields){
    if(!err){
      res.status(200).send(JSON.stringify(rows));
      return;
    }
    if(err){
      res.status(260).send('Database error');
      console.log(err);
      return;
    }
  });
}

setUserStatus = function(user_id, lobby_id, status, res){
  query = 'UPDATE lobbies_users SET Status=? WHERE UserID=? AND LobbyID=?';
  values = [status, user_id, lobby_id];
  connection.query(query, values, function(err, rows, fields){
    if(!err){
      res.status(200).send("succesfuly updated user status");
      return;
    }
    if(err){
      res.status(260).send('Database error');
      console.log(err);
      return;
    }
  });
}

getLobbyInformation =function(lobby_id, res){
  query = 'SELECT * FROM lobbies WHERE LobbyID=?'
  values = [lobby_id];
  connection.query(query, values, function(err, rows, fields){
    if(!err){
      res.status(200).send(JSON.stringify(rows));
      return;
    }
    if(err){
      res.status(260).send('Database error');
      console.log(err);
      return;
    }
  });
}

getLobbyUsers = function(lobby_id, res){
  query = 'SELECT UserID, Status FROM lobbies_users WHERE LobbyID=?'
  values = [lobby_id];
  connection.query(query, values, function(err, rows, fields){
    if(!err){
      res.status(200).send(JSON.stringify(rows));
      return;
    }
    if(err){
      res.status(260).send('Database error');
      console.log(err);
      return;
    }
  });
}

createLobby = function(title, player_no, game_limit, run_time, res){
  query = 'INSERT INTO lobbies (Title, PlayersNO, GameTime, RunTime)';
  query+= 'VALUES(?, ?, ?, ?)';
  values = [title, player_no, game_limit, run_time];
  connection.query(query, values, function(err, rows, fields){
    if(!err){
      res.status(200).send(JSON.stringify(rows));
      return;
    }
    if(err){
      res.status(260).send('Database error');
      console.log(err);
      return;
    }
  });
}

lobbyLogin = function(user_id, lobby_id, res){
  query = 'INSERT INTO lobbies_users (UserID, LobbyID) values(?,?)';
  values = [user_id, lobby_id];
  connection.query(query, values, function(err, rows, fields){
    if(!err){
      res.status(200).send('User succesfuly logged to lobby');
      return;
    }
    if(err){
      res.status(260).send('Database error');
      console.log(err);
      return;
    }
  });
}

lobbyLogout = function(user_id, lobby_id, res){
  query = 'DELETE FROM lobbies_users WHERE UserID=? AND LobbyID=?';
  values = [user_id, lobby_id];
  connection.query(query, values, function(err, rows, fields){
    if(!err){
      res.status(200).send('User succesfuly logged out');
      return;
    }
    if(err){
      res.status(260).send('Database error');
      console.log(err);
      return;
    }
  });
}

getLobbies = function(res) {
  query = 'SELECT * FROM lobbies';
  values = [];
  connection.query(query, values, function(err, rows, fields){
    if(!err){
      res.status(200).send(JSON.stringify(rows));
      return;
    }
    if(err){
      res.status(260).send('Database error');
      console.log(err);
      return;
    }
  });
}
