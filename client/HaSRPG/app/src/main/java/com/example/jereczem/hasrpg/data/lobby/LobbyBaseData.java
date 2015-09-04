package com.example.jereczem.hasrpg.data.lobby;

/**
 * Created by jereczem on 04.09.15.
 */
public class LobbyBaseData {
    private Integer lobbyID;
    private String title;
    private Integer playersNO;
    private Integer gameTime;
    private Integer runTime;
    private String status;

    public LobbyBaseData(Integer lobbyID, String title, Integer playersNO, Integer gameTime, Integer runTime, String status) {
        this.lobbyID = lobbyID;
        this.title = title;
        this.playersNO = playersNO;
        this.gameTime = gameTime;
        this.runTime = runTime;
        this.status = status;
    }

    public Integer getLobbyID() {
        return lobbyID;
    }

    public String getTitle() {
        return title;
    }

    public Integer getPlayersNO() {
        return playersNO;
    }

    public Integer getGameTime() {
        return gameTime;
    }

    public Integer getRunTime() {
        return runTime;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "LobbyBaseData{" +
                "lobbyID=" + lobbyID +
                ", title='" + title + '\'' +
                ", playersNO=" + playersNO +
                ", gameTime=" + gameTime +
                ", runTime=" + runTime +
                ", status='" + status + '\'' +
                '}';
    }
}
