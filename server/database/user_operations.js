require('./connection.js');

signIn = function(login, passwd, res){
  query = 'SELECT id FROM users WHERE login=? and passwd=?';
  values = [login, passwd];
  connection.query(query, values, function(err, rows, fields){
    if(!err){
      try{
          res.cookie('user_id' , rows[0]['id'], { signed: true });
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
  query = 'INSERT INTO users (login, passwd) VALUES(?)';
  values = [[login, passwd]];
  connection.query(query, values, function(err, rows, fields) {
    if(!err)
      res.status(200).send('User added');
    else if (err.code == 'ER_DUP_ENTRY')
      res.status(259).send('User arleady exists');
    else {
      res.status(260).send('Database error');
      console.log(err);}});
}
