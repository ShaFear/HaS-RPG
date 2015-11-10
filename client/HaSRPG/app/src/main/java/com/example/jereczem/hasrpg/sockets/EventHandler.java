package com.example.jereczem.hasrpg.sockets;

import android.support.v7.app.AppCompatActivity;

import com.example.jereczem.hasrpg.sockets.connection.ConnectionEvent;
import com.example.jereczem.hasrpg.sockets.connection.ConnectionEventHandler;
import com.example.jereczem.hasrpg.sockets.disconnection.DisconnectionEvent;
import com.example.jereczem.hasrpg.sockets.disconnection.DisconnectionEventHandler;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;

/**
 * Created by jereczem on 10.11.15.
 */
public class EventHandler {
    public EventHandler(final Socket socket, final AppCompatActivity activity){
        socket.on("event", new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                try {
                    GameEvent gameEvent = GameEvent.createFromString(args[0].toString());
                    if(gameEvent instanceof ConnectionEvent){
                        new ConnectionEventHandler((ConnectionEvent)gameEvent, activity);
                    } else if(gameEvent instanceof DisconnectionEvent){
                        new DisconnectionEventHandler((DisconnectionEvent)gameEvent, activity, socket);
                    }
                } catch (GameEvent.NoGameEventException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
