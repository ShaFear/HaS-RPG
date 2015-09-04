package com.example.jereczem.hasrpg.game.lobbies;

import com.example.jereczem.hasrpg.data.lobby.LobbyBaseData;
import com.example.jereczem.hasrpg.data.player.PlayerData;
import com.example.jereczem.hasrpg.settings.LobbySettings;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by jereczem on 04.09.15.
 */
public class Lobby implements Serializable{

    private ArrayList<PlayerData> lobbyPlayers;
    private Integer lobbyID;
    private String title;
    private Integer playersMax;
    private Integer playersNO;
    private Integer gameTime;
    private Integer runTime;
    private LobbySettings.Status status;

    public Lobby(LobbyBaseData baseData){
        lobbyID = baseData.getLobbyID();
        title = baseData.getTitle();
        playersMax = baseData.getPlayersNO();
        playersNO = 0;
        gameTime = baseData.getGameTime();
        runTime = baseData.getRunTime();
        if(baseData.getStatus().equals("WAIT"))
            status = LobbySettings.Status.WAIT;
        else
            status = LobbySettings.Status.READY;
        lobbyPlayers = new ArrayList<>();
    }

    public void setLobbyPlayers(ArrayList<PlayerData> playerDatas) {
        playersNO = playerDatas.size();
    }

    @Override
    public String toString() {
        return "Lobby{" +
                ", lobbyID=" + lobbyID +
                ", title='" + title + '\'' +
                ", playersMax=" + playersMax +
                ", playersNO=" + playersNO +
                ", gameTime=" + gameTime +
                ", runTime=" + runTime +
                ", status=" + status +
                ", lobbyPlayers=" + lobbyPlayers.toString() +
                '}';
    }
}
