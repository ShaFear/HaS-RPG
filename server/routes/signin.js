require("../app.js");
require("../database/user_operations.js");
require("../security/security");
sleep = require('sleep');

app.post('/signin', function(req, res){
  sleep.sleep(1);
  var login = req.body.login;
  var passwd = req.body.password;
  /*Tak się handluje zaszyfrowanymi ciasteczkami ;-)
  if(req.signedCookies.user_id){
    res.status(200).send('user ' + req.signedCookies.user_id + ' arleady logged');
    return;
  }*/
  passwd = hash(passwd);
  signIn(login, passwd, res);
});
