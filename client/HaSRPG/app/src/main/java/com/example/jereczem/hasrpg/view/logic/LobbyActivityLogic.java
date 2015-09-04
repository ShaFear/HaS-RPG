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
        //lobbyId = a.getIntent().getIntExtra("lobbyId", 0);
        new ToolbarSetter(a, R.drawable.previous);
        try {
            downloadLobbyData(lobbyId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void downloadLobbyData(final Integer lobbyId) throws JSONException {
        HttpResponse response = new HttpResponseReceiver("lobbies/" + lobbyId).receive();
        if (response.getCode().equals(200)) {
            lobby = new Lobby(LobbyDataReceiver.receiveBaseData(response.getMessage()));
            setViewFromLobbyData();
            downloadLobbyPlayersData(lobbyId);
        } else {
            AlertDialog alertDialog;
            switch (response.getCode()){
                case 256:{
                    alertDialog = Alerts.notLoggedError(a);
                    break;
                }
                case 260:{
                    alertDialog = Alerts.databaseError(a);
                    break;
                }
                default:{
                    alertDialog = Alerts.connectionError(a, response.getMessage());
                }
            }
            alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    try {
                        downloadLobbyData(lobbyId);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            alertDialog.show();
        }
    }

    private void downloadLobbyPlayersData(final Integer lobbyId){
        HttpResponse response = new HttpResponseReceiver("lobbies/" + lobbyId + "/users").receive();
        if (response.getCode().equals(200)) {
            downloadWholeLobbyPlayersData(response.getMessage());
        } else {
            AlertDialog alertDialog;
            switch (response.getCode()){
                case 256:{
                    alertDialog = Alerts.notLoggedError(a);
                    break;
                }
                case 260:{
                    alertDialog = Alerts.databaseError(a);
                    break;
                }
                default:{
                    alertDialog = Alerts.connectionError(a, response.getMessage());
                }
            }
            alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    downloadLobbyPlayersData(lobbyId);
                }
            });
            alertDialog.show();
        }
    }

    private void downloadWholeLobbyPlayersData(String message) {
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

    private PlayerData downloadPlayerData(final Integer userID){
        HttpResponse response = new HttpResponseReceiver("users/" + userID).receive();
        if (response.getCode().equals(200)) {
            return PlayerDataReceiver.fromString(response.getMessage());
        } else {
            AlertDialog alertDialog;
            switch (response.getCode()){
                case 256:{
                    alertDialog = Alerts.notLoggedError(a);
                    break;
                }
                case 600:{
                    alertDialog = Alerts.databaseError(a);
                    break;
                }
                default:{
                    alertDialog = Alerts.connectionError(a, response.getMessage());
                    break;
                }
            }
            alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                        downloadPlayerData(userID);
                }
            });
            alertDialog.show();
        }
        return null;
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
