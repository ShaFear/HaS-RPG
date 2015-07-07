require('./connection.js');
sleep = require('sleep');

saveUserIdInSessionAndReturn200 = function(login, res){
  query = "SELECT id FROM users WHERE login=?";
  values = [login];
  return connection.query(query, values, function(err, rows, fields){
    if(!err){
      res.cookie('user_id' , rows[0]['id'], { signed: true });
      res.status(200).send('Login and password are correct');
    }
    else{
      console.log(err);
    }
  });
}

signIn = function(login, passwd, res){
  query = 'SELECT passwd FROM users WHERE login=?';
  values = [login];
  connection.query(query, values, function(err, rows, fields){
    if(!err){
      try{
        if(passwd == rows[0]['passwd']){
            saveUserIdInSessionAndReturn200(login, res);
            return;
        }
        else{
            res.status(256).send('Wrong password or username');
            return;
        }
      }
    catch(err){
      res.status(256).send('Wrong password or username');
      console.log(err);
      return;
    }
    }else{
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
