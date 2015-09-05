package com.example.jereczem.hasrpg.view.logic;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.data.lobby.LobbyBaseData;
import com.example.jereczem.hasrpg.data.lobby.LobbyDataReceiver;
import com.example.jereczem.hasrpg.data.player.PlayerData;
import com.example.jereczem.hasrpg.data.player.PlayerDataReceiver;
import com.example.jereczem.hasrpg.game.lobbies.Lobby;
import com.example.jereczem.hasrpg.networking.HttpResponse;
import com.example.jereczem.hasrpg.networking.HttpResponseReceiver;
import com.example.jereczem.hasrpg.networking.rest.LobbyGetter;
import com.example.jereczem.hasrpg.networking.rest.RestException;
import com.example.jereczem.hasrpg.settings.LobbySettings;
import com.example.jereczem.hasrpg.view.dialogs.Alerts;
import com.example.jereczem.hasrpg.view.toolbar.ToolbarSetter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by jereczem on 04.09.15.
 */
public class LobbyActivityLogic {
    AppCompatActivity a;
    Integer lobbyId;
    Lobby lobby;

    public LobbyActivityLogic(AppCompatActivity a){
        this.a = a;
        lobbyId = 1;
        new ToolbarSetter(a, R.drawable.previous);
        try {
            downloadLobbyData(lobbyId);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (RestException e) {
            e.printStackTrace();
            handleRestException(e.getResponse());
        }
    }

    private void handleRestException(HttpResponse response) {

    }

    private void downloadLobbyData(final Integer lobbyId) throws JSONException, RestException {
        HttpResponse response = new HttpResponseReceiver("lobbies/" + lobbyId).receive();
        if (response.getCode().equals(200)) {
            lobby = new Lobby(LobbyDataReceiver.receiveBaseData(response.getMessage()));
            setViewFromLobbyData();
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
                Log.d("HASLOG", jsonArray.getJSONObject(j).toString());
                playerDatas.add(downloadPlayerData(jsonArray.getJSONObject(j).getInt("UserID")));
                String status = (jsonArray.getJSONObject(j).getString("Status"));
                if(status == "READY"){
                    playerDatas.get(j).setStatus(LobbySettings.Status.READY);
                }else
                    playerDatas.get(j).setStatus(LobbySettings.Status.WAIT);
            }
            lobby.setLobbyPlayer(playerDatas);
            Log.d("HASLOG", lobby.toString());
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


    private void setViewFromLobbyData(){
        TextView lobbyTitle = (TextView) a.findViewById(R.id.lobbyTitle);
        TextView playersTitle = (TextView) a.findViewById(R.id.lobbyPlayersNumber);
        TextView gameTime = (TextView) a.findViewById(R.id.lobbyGameTime);
        TextView runTime = (TextView) a.findViewById(R.id.lobbyRunTime);

        playersTitle.setText(R.string.lobby_players_number);
        gameTime.setText(R.string.lobby_game_time);
        runTime.setText(R.string.lobby_runtime);

        lobbyTitle.setText(lobby.getTitle());
        playersTitle.setText(
                playersTitle.getText().toString()
                        .replace("[magic]", lobby.getPlayersNO() + " / " + lobby.getPlayersMax())
        );
        gameTime.setText(
                gameTime.getText().toString().replace("[magic]", lobby.getGameTime().toString())
        );
        runTime.setText(
                runTime.getText().toString().replace("[magic]", lobby.getRunTime().toString())
        );
    }
}
