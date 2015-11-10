var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);

function getClient(roomId) {
    var res = [],
        room = io.sockets.adapter.rooms[roomId];
    if (room) {
        for (var id in room) {
            res.push(io.sockets.adapter.nsp.connected[id]);
        }
    }
    return res;
}

io.on('connection', function (socket) {

    var userID = -1;
    var room = -1;

    console.log(new Date().toUTCString() + ': user connected');

    socket.on('joinRoom', function (data) {
        try {
            var jsonData = JSON.parse(data);
            room = jsonData['room'];
            userID = jsonData['userID'];
            socket.join(room);
            console.log(new Date().toUTCString() + ': user ' + userID + ' joined room ' + room);
            socket.to(room).emit('user_connected', 'user ' + userID + ' joined room');
        } catch (err) {
            console.log(new Date().toUTCString() + ": " + err);
        };
    });


    socket.on('disconnect', function(){
        console.log(new Date().toUTCString() + ': user ' + userID + ' disconnected from room ' + room);
        socket.to(room).emit('user_disconnected','user ' + userID + ' disconnected from room ');
    })
});

http.listen(3000, function () {
    console.log('listening on *:3000');
});
