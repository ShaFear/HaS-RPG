crypto = require("crypto");

hash = function(passwd){
  for( var j=0; j<3; j++)
    passwd = crypto.createHash("sha256").update(passwd).digest('hex');
  return passwd;
}
