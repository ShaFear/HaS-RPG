var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);



app.get('/', function(req, res){
  res.status(200).send('Chuck testa');
});

io.on('connection', function(socket){
  console.log('a user connected');
  socket.on('disconnect', function(){
    console.log('user disconnected');
  });
});

io.on('connection', function(socket){
  socket.on('chat message', function(msg){
    console.log('message: ' + msg);
    io.emit('chat message', msg); //wysyla do wszystkich
  });
});

http.listen(3000, function(){
  console.log('listening on *:3000');
});

io.use(function(socket, next) {
  var handshakeData = socket.request;
  console.log("middleware:", handshakeData._query['foo']);
  next();
});
