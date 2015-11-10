package com.example.jereczem.hasrpg.sockets;

import com.example.jereczem.hasrpg.settings.ServerSettings;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

/**
 * Created by jereczem on 10.11.15.
 */
public class SocketServerConnector {
    private Socket socket;
    {
        try {
            socket = IO.socket(ServerSettings.SOCKET_SERVER_URL);
        } catch (URISyntaxException e) {}
    }

    Integer roomID, userID;

    public SocketServerConnector(Integer roomID, Integer userID){
        this.roomID = roomID;
        this.userID = userID;
        final JSONObject jsonData = new JSONObject();
        try {
            jsonData.put("room", roomID);
            jsonData.put("userID", userID);
        } catch (JSONException e) {}

        socket.connect();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        socket.emit("joinRoom", jsonData.toString());
                        Thread.sleep(ServerSettings.SOCKET_REJOINING_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public Socket getSocket() {
        return socket;
    }

    public Integer getRoomID() {
        return roomID;
    }

    public Integer getUserID() {
        return userID;
    }
}
