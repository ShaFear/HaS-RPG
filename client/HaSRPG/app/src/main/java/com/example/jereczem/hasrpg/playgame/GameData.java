package com.example.jereczem.hasrpg.playgame;

import com.example.jereczem.hasrpg.data.player.PlayerData;
import com.example.jereczem.hasrpg.game.lobbies.Lobby;

import java.util.Observable;
import java.util.TreeMap;

/**
 * Created by Michał on 2016-01-06.
 */
public class GameData extends Observable{
    /** time until end of the first run */
    private long runTime;

    /** time until end of the game */
    private long gameTime;

    /** Chases in playgame */
    private TreeMap<Integer, ChaseData> chases = new TreeMap<>();

    /** Game's hunter */
    private TreeMap<Integer, HunterData> hunters = new TreeMap<>();

    /** Game's status */
    private GameStatus status;

    /** Setting GameData from Lobbie's data */
    public GameData(Lobby lobby){
        for(PlayerData playerData : lobby.getLobbyPlayers()){
            if(playerData.getUserID().equals(lobby.getHunterID())){
                HunterData hunterData = new HunterData(playerData.getSelectedHunter());
                hunters.put(playerData.getUserID(), hunterData);
            } else{
                ChaseData chaseData = new ChaseData(playerData.getSelectedChase());
                chases.put(playerData.getUserID(), chaseData);
            }
        }
        runTime = lobby.getRunTime();
        gameTime = lobby.getGameTime();
        status = GameStatus.RUNNING;
    }

    public long getRunTime() {
        return runTime;
    }

    public void setRunTime(long runTime) {
        this.runTime = runTime;
        setChanged();
        notifyObservers(GameDataChanges.RUNTIME);
    }

    public long getGameTime() {
        return gameTime;
    }

    public void setGameTime(long gameTime) {
        this.gameTime = gameTime;
        setChanged();
        notifyObservers(GameDataChanges.GAME_TIME);
    }

    public TreeMap<Integer, ChaseData> getChases() {
        return chases;
    }

    public TreeMap<Integer, HunterData> getHunters() {
        return hunters;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
        setChanged();
        notifyObservers(GameDataChanges.GAME_STATUS);
    }

    @Override
    public String toString() {
        return "GameData{" +
                "\nrunTime=" + runTime +
                "\n, gameTime=" + gameTime +
                "\n, chases=" + chases.toString() +
                "\n, hunters=" + hunters.toString() +
                "\n, status=" + status +
                '}';
    }
}
