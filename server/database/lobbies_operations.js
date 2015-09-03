require('./connection.js');

createLobby = function(title, player_no, game_limit, run_time, res){
  query = 'INSERT INTO lobbies (Title, PlayersNO, GameTime, RunTime)';
  query+= 'VALUES(?, ?, ?, ?)';
  values = [title, player_no, game_limit, run_time];
  connection.query(query, values, function(err, rows, fields){
    if(!err){
      res.status(260).send('Lobby added');
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
