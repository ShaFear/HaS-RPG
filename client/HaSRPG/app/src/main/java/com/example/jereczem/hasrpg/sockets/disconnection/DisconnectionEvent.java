package com.example.jereczem.hasrpg.sockets.disconnection;

import com.example.jereczem.hasrpg.sockets.GameEvent;

/**
 * Created by jereczem on 10.11.15.
 */
public class DisconnectionEvent extends GameEvent {
    Integer userID;

    public DisconnectionEvent(Integer userID) {
        super();
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "User " + userID + " disconnected from room";
    }

    public Integer getUserID() {
        return userID;
    }
}
