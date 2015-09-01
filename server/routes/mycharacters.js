require("../app.js");
require("../database/user_operations.js");


app.get('/mycharacters', function(req, res){
  if(req.signedCookies.UserID){
    getCharacters(req.signedCookies.UserID, res);
    return;
  }else
    res.status(256).send('not logged');
});

app.post('/mycharacters/ChaseID', function(req, res){
  if(req.signedCookies.UserID){
    var chaseID = req.body.chase_id;
    setChaseID(req.signedCookies.UserID, chaseID, res)
    return;
  }else
    res.status(256).send('not logged');
});

app.post('/mycharacters/HunterID', function(req, res){
  if(req.signedCookies.UserID){
    var hunterID = req.body.hunter_id;
    setHunterID(req.signedCookies.UserID, hunterID, res)
    return;
  }else
    res.status(256).send('not logged');
});
