package com.example.jereczem.hasrpg.data.lobby;

/**
 * Created by jereczem on 04.09.15.
 */
public class LobbyBasePlayersData {
    private Integer userID;
    private String status;

    public LobbyBasePlayersData(Integer userID, String status) {
        this.userID = userID;
        this.status = status;
    }

    public Integer getUserID() {
        return userID;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "LobbyBasePlayersData{" +
                "userID=" + userID +
                ", status='" + status + '\'' +
                '}';
    }
}
