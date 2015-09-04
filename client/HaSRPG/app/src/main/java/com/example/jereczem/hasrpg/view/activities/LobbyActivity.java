package com.example.jereczem.hasrpg.view.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.data.lobby.LobbyBaseData;
import com.example.jereczem.hasrpg.data.lobby.LobbyBasePlayersData;
import com.example.jereczem.hasrpg.data.lobby.LobbyDataReceiver;
import com.example.jereczem.hasrpg.game.lobbies.Lobby;
import com.example.jereczem.hasrpg.networking.HttpResponse;
import com.example.jereczem.hasrpg.networking.HttpResponseReceiver;
import com.example.jereczem.hasrpg.view.dialogs.Alerts;
import com.example.jereczem.hasrpg.view.toolbar.ToolbarSetter;

import org.json.JSONException;

public class LobbyActivity extends AppCompatActivity {
    Integer lobbyId;
    Lobby lobby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
        lobbyId = getIntent().getIntExtra("lobbyId", 0);
        new ToolbarSetter(this, R.drawable.previous);
        try {
            downloadLobbyData();
        } catch (JSONException e) {
            Log.d("HASLOG", e.toString());
        }
    }

    private void downloadLobbyData() throws JSONException {
        HttpResponseReceiver rReceiver = new HttpResponseReceiver("lobbies/" + lobbyId);
        HttpResponse response = rReceiver.receive();
        Log.d("HASLOG",response.getCode() + " " + response.getMessage() );
        LobbyBaseData baseData = LobbyDataReceiver.receiveBaseData(response.getMessage());
        lobby = new Lobby(baseData);
        Log.d("HASLOG", lobby.toString());
    }
}
