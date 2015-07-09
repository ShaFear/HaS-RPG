require("../app.js");
require("../database/user_operations.js");


app.get('/mycharacters', function(req, res){
  if(req.signedCookies.UserID){
    getCharacters(req.signedCookies.UserID, res);
    return;
  }else
    res.status(256).send('not logged');
});
