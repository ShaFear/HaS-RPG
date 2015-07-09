require('./connection.js');

signIn = function(login, passwd, res){
  query = 'SELECT UserID FROM users WHERE login=? and passwd=?';
  values = [login, passwd];
  connection.query(query, values, function(err, rows, fields){
    if(!err){
      try{
          res.cookie('UserID' , rows[0]['UserID'], { signed: true });
          res.status(200).send('Login and password are correct');
          return;
      }
      catch(e){
          res.status(256).send('Wrong password or username');
          return;
      }
    }
    if(err){
      res.status(260).send('Database error');
      console.log(err);
      return;
    }
  });
}

addUser = function(login, passwd, res){
  query = "SELECT add_user(?)"
  values = [[login, passwd]];
  connection.query(query, values, function(err, rows, fields) {
    if(!err){
      res.status(200).send('User added');
    }else if (err.code == 'ER_DUP_ENTRY')
      res.status(259).send('User arleady exists');
    else {
      res.status(260).send('Database error');
      console.log(err);}});
}

getCharacters = function(user_id, res){
  query = "SELECT * FROM characters_of_users "
  query +="INNER JOIN characters ON characters_of_users.CharacterID"
  query +="= characters.CharacterID WHERE UserID = ?;"
  values = [user_id];
  connection.query(query, values, function(err, rows, fields) {
    if(!err){
      res.status(200).send(JSON.stringify(rows));
    }else {
      res.status(260).send('Database error');
      console.log(err);}});
}
