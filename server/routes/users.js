require("../app.js");
require("../database/user_operations.js");
require("../security/security");

function check(login, passwd, res){
  if(login && passwd){
    if((3 > login.length) || (32 < login.length)){
      res.status(201).send('Wrong login (min 3, max 32 letters)');
      return false;}
    if((7 > passwd.length)|| (32 < passwd.length)){
      res.status(202).send('Wrong password (min 8, max 32 letters)');
      return false;}
    return true;
  }
  res.status(203).send('Some fields are empty!');
  return false;
}

app.post('/users', function(req, res){
  var login = req.body.login;
  var passwd = req.body.password;
  if(check(login, passwd, res)){
    passwd = hash(passwd);
  addUser(login, passwd, res);}
});

app.get('/', function(req, res){
  res.send("testx");
});
