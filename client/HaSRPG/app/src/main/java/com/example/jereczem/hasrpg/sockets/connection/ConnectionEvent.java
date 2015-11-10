package com.example.jereczem.hasrpg.sockets.connection;

import com.example.jereczem.hasrpg.sockets.GameEvent;

/**
 * Created by jereczem on 10.11.15.
 */
public class ConnectionEvent extends GameEvent {
    Integer userID;

    public ConnectionEvent(Integer userID) {
        super();
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "User " + userID + " connected to room";
    }

    public Integer getUserID() {
        return userID;
    }
}
