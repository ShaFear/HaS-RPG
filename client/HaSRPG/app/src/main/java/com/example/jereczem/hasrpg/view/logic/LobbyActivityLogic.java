package com.example.jereczem.hasrpg.view.logic;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.data.lobby.LobbyBaseData;
import com.example.jereczem.hasrpg.data.lobby.LobbyDataReceiver;
import com.example.jereczem.hasrpg.game.lobbies.Lobby;
import com.example.jereczem.hasrpg.networking.HttpResponse;
import com.example.jereczem.hasrpg.networking.HttpResponseReceiver;
import com.example.jereczem.hasrpg.view.dialogs.Alerts;
import com.example.jereczem.hasrpg.view.toolbar.ToolbarSetter;

import org.json.JSONException;

/**
 * Created by jereczem on 04.09.15.
 */
public class LobbyActivityLogic {
    AppCompatActivity a;
    Integer lobbyId;
    Lobby lobby;

    public LobbyActivityLogic(AppCompatActivity a){
        this.a = a;
        lobbyId = a.getIntent().getIntExtra("lobbyId", 0);
        new ToolbarSetter(a, R.drawable.previous);
        try {
            downloadLobbyData();
        } catch (JSONException e) {
            Log.d("HASLOG", e.toString());
        }
    }

    private void downloadLobbyData() throws JSONException {
        HttpResponseReceiver rReceiver = new HttpResponseReceiver("lobbies/" + lobbyId);
        HttpResponse response = rReceiver.receive();
        handleDownloadLobbyDataResponse(response);
    }

    private void handleDownloadLobbyDataResponse(HttpResponse response) throws JSONException {
        switch (response.getCode()){
            case 200:{
                LobbyBaseData baseData = LobbyDataReceiver.receiveBaseData(response.getMessage());
                lobby = new Lobby(baseData);
                setViewFromLobbyData();
                break;
            }
            case 256:{
                Alerts.notLoggedError(a).show();
                break;
            }
            case 260:{
                Alerts.databaseError(a).show();
                break;
            }
            default:{
                Alerts.connectionError(a, response.getMessage());
            }
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
