/*
  /lobbies GET (lista aktywnych lobby) - ZROBIONE
  /lobbies POST (dodanie nowego lobby) - ZROBIONE
  /lobbies/{LobbyID}/login GET (zalogowanie do lobby) - ZROBIONE
  /lobbies/{LobbyID}/logout GET (wylogowanie z lobby) - ZROBIONE
  /lobbies/{LobbyID/} GET {lista graczy - lista id} - ZROBIONE (pierwsza osoba na liscie jest adminem - moze kliknac start )
  /lobbies/{LobbyID}/mystatus POST - ZROBIONE
  /lobbies/{LobbyID}/status GET (sprawdzenie statusu lobby) - ZROBIONE
  /lobbies/{LobbyID}/status POST (zmiana statusu lobby) - ZROBIONE
*/

require("../app.js");
require("../database/lobbies_operations.js");

function check_data(title, player_no, game_limit, run_time, status){
  try {
      if(
        ( 0 < title.length ) && (title.length < 256 ) &&
        ( 2 < player_no ) && ( player_no < 128 ) &&
        ( 9 < game_limit ) && (game_limit < 10000) &&
        ( 2 < run_time ) && ( run_time < 5000)
      )
        return true;
      return false;
  }
  catch(err) {
      return false;
  }
}

app.post('/lobbies/:lobby_id/status', function(req, res){
  if(req.signedCookies.UserID){
    var lobby_id=req.params['lobby_id'];
    var status = req.body.status;
    if(status == "WAIT" || status == "READY")
      setLobbyStatus(lobby_id, status, res);
    else
      res.status(257).send('status should be READY or WAIT');
    return;
  }else
      res.status(256).send('not logged');
});

app.get('/lobbies/:lobby_id/status', function(req, res){
  if(req.signedCookies.UserID){
    var lobby_id=req.params['lobby_id'];
    getLobbyStatus(lobby_id, res);
    return;
  }else
      res.status(256).send('not logged');
});

app.post('/lobbies/:lobby_id/my_status', function(req, res){
  if(req.signedCookies.UserID){
    var lobby_id=req.params['lobby_id'];
    var status = req.body.status;
    if(status == "WAIT" || status == "READY")
      setUserStatus(req.signedCookies.UserID, lobby_id, status, res);
    else
      res.status(257).send('status should be READY or WAIT');
    return;
  }else
      res.status(256).send('not logged');
});

app.get('/lobbies/:lobby_id/', function(req, res){
  if(req.signedCookies.UserID){
    var lobby_id=req.params['lobby_id'];
    getLobbyUsers(lobby_id, res);
  }else
      res.status(256).send('not logged');
});

app.get('/lobbies/:lobby_id/login', function(req, res){
  if(req.signedCookies.UserID){
    var lobby_id=req.params['lobby_id'];
    lobbyLogin(req.signedCookies.UserID, lobby_id, res);
  }else
      res.status(256).send('not logged');
});

app.get('/lobbies/:lobby_id/logout', function(req, res){
  if(req.signedCookies.UserID){
    var lobby_id=req.params['lobby_id'];
    lobbyLogout(req.signedCookies.UserID, lobby_id, res);
  }else
      res.status(256).send('not logged');
});

app.post('/lobbies', function(req, res){
  if(req.signedCookies.UserID){
    var title = req.body.title;
    var player_no = req.body.player_no;
    var game_limit = req.body.game_limit;
    var run_time = req.body.run_time;

    if(!check_data(title, player_no, game_limit, run_time)){
      res.status(257).send('wrong data');
      return;
    }
    createLobby(title, player_no, game_limit, run_time, res);
    return;
  }else
    res.status(256).send('not logged');
});

app.get('/lobbies', function(req, res){
  if(req.signedCookies.UserID)
    getLobbies(res);
    else
      res.status(256).send('not logged');
});
