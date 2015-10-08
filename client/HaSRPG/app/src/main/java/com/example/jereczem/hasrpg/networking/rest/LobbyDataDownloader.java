package com.example.jereczem.hasrpg.networking.rest;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.jereczem.hasrpg.data.lobby.LobbyDataReceiver;
import com.example.jereczem.hasrpg.data.player.PlayerData;
import com.example.jereczem.hasrpg.data.player.PlayerDataReceiver;
import com.example.jereczem.hasrpg.game.lobbies.Lobby;
import com.example.jereczem.hasrpg.networking.HttpResponse;
import com.example.jereczem.hasrpg.networking.HttpResponseReceiver;
import com.example.jereczem.hasrpg.settings.LobbySettings;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by jereczem on 05.09.15.
 */
public class LobbyDataDownloader {
    AppCompatActivity a;

    public Lobby getLobby() {
        return lobby;
    }

    Lobby lobby;

    public LobbyDataDownloader(AppCompatActivity a, Integer lobbyId) throws JSONException, RestException {
        this.a = a;
        downloadLobbyData(lobbyId);
    }

    private void downloadLobbyData(final Integer lobbyId) throws JSONException, RestException {
        HttpResponse response = new HttpResponseReceiver("lobbies/" + lobbyId).receive();
        if (response.getCode().equals(200)) {
            lobby = new Lobby(LobbyDataReceiver.receiveBaseData(response.getMessage()));
            downloadLobbyPlayersData(lobbyId);
        } else {
            throw new RestException(response);
        }
    }

    private void downloadLobbyPlayersData(final Integer lobbyId) throws RestException {
        HttpResponse response = new HttpResponseReceiver("lobbies/" + lobbyId + "/users").receive();
        if (response.getCode().equals(200)) {
            downloadWholeLobbyPlayersData(response.getMessage());
        } else {
            throw new RestException(response);
        }
    }

    private void downloadWholeLobbyPlayersData(String message) throws RestException {
        try {
            JSONArray jsonArray = new JSONArray(message);
            ArrayList<PlayerData> playerDatas = new ArrayList<>();
            for(int j=0; j<jsonArray.length(); j++){
                playerDatas.add(downloadPlayerData(jsonArray.getJSONObject(j).getInt("UserID")));
                String status = (jsonArray.getJSONObject(j).getString("Status"));
                if(status == "READY"){
                    playerDatas.get(j).setStatus(LobbySettings.Status.READY);
                }else
                    playerDatas.get(j).setStatus(LobbySettings.Status.WAIT);
            }
            lobby.setLobbyPlayer(playerDatas);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private PlayerData downloadPlayerData(final Integer userID) throws RestException {
        HttpResponse response = new HttpResponseReceiver("users/" + userID).receive();
        if (response.getCode().equals(200)) {
            return PlayerDataReceiver.fromString(response.getMessage());
        } else {
            throw new RestException(response);
        }
    }
}
