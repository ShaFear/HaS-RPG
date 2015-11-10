package com.example.jereczem.hasrpg.sockets;

import com.example.jereczem.hasrpg.sockets.connection.ConnectionEvent;
import com.example.jereczem.hasrpg.sockets.disconnection.DisconnectionEvent;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jereczem on 10.11.15.
 */
public abstract class GameEvent {
    protected GameEvent(){

    }

    public static GameEvent createFromString(String event) throws NoGameEventException {
        try {
            JSONObject jsonObject = new JSONObject(event);
            if(jsonObject.getString("name").equals("connectionToRoom")){
                return new ConnectionEvent(jsonObject.getInt("userID"));
            } else if(jsonObject.getString("name").equals("disconnectionFromRoom")){
                return new DisconnectionEvent(jsonObject.getInt("userID"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        throw new NoGameEventException();
    }

    public static class NoGameEventException extends Throwable {
    }
}
