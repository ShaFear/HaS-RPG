//modules
var express = require('express');
var multer = require('multer');
var bodyParser = require('body-parser');

//express
app = express();

//bodyParser + multer
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
app.use(multer());

//routes
require("./routes/test.js");
require("./routes/users.js");

//server start
app.listen(80); //zmien na 8080 w przyszlosci
