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

    socket.on('joinRoom', function (data) {
        try {
            var jsonData = JSON.parse(data);
            room = jsonData['room'];
            userID = jsonData['userID'];
            socket.join(room);
            var event = {
                name: 'CONNECTION',
                userID: userID
            }
            socket.to(room).emit('event', JSON.stringify(event));
        } catch (err) {
            console.log(new Date().toUTCString() + ": " + err);
        };
    });


    socket.on('disconnect', function(){
        var event = {
            name: 'DISCONNECTION',
            userID: userID
        }
        socket.to(room).emit('event', JSON.stringify(event));
    })
});

http.listen(3000, function () {
    console.log('listening on *:3000');
});
