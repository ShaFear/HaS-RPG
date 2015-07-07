require("../app.js");
require("../database/user_operations.js");
require("../security/security");
sleep = require('sleep');

app.post('/signin', function(req, res){
  sleep.sleep(1);
  var login = req.body.login;
  var passwd = req.body.password;
  if(req.cookies.user_id){
    res.status(257).send('user arleady logged');
    return;
  }
  else{
    passwd = hash(passwd);
    signIn(login, passwd, res);
  }
});
