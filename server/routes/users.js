require("../app.js");
require("../database/user_operations.js");
require("../security/security");

function check(login, passwd, res){
  if(login && passwd){
    if((3 > login.length) || (32 < login.length)){
      res.status(261).send('Wrong login (min 3, max 32 letters)');
      return false;}
    if((7 > passwd.length)|| (32 < passwd.length)){
      res.status(262).send('Wrong password (min 8, max 32 letters)');
      return false;}
    return true;
  }
  res.status(263).send('Some fields are empty!');
  return false;
}

app.get('/users/:user_id', function(req, res){
  if(req.signedCookies.UserID){
    var user_id=req.params['user_id'];
    getCharacters(user_id, res);
    return;
  }else
    res.status(256).send('not logged');
});

app.post('/users', function(req, res){
  var login = req.body.login;
  var passwd = req.body.password;
  if(check(login, passwd, res)){
    passwd = hash(passwd);
    addUser(login, passwd, res);
  }
});

app.get('/', function(req, res){
  if(req.signedCookies.user_id){
    res.status(200).send('user ' + req.signedCookies.user_id + ' arleady logged');
    return;
  }else
    res.status(256).send('not logged');
});
