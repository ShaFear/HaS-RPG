package com.example.jereczem.hasrpg.view.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.data.lobby.LobbyBaseData;
import com.example.jereczem.hasrpg.data.lobby.LobbyBasePlayersData;
import com.example.jereczem.hasrpg.data.lobby.LobbyDataReceiver;
import com.example.jereczem.hasrpg.game.lobbies.Lobby;
import com.example.jereczem.hasrpg.networking.HttpResponse;
import com.example.jereczem.hasrpg.networking.HttpResponseReceiver;
import com.example.jereczem.hasrpg.view.dialogs.Alerts;
import com.example.jereczem.hasrpg.view.logic.LobbyActivityLogic;
import com.example.jereczem.hasrpg.view.toolbar.ToolbarSetter;

import org.json.JSONException;
import org.w3c.dom.Text;

public class LobbyActivity extends AppCompatActivity {
    LobbyActivityLogic lobbyActivityLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
        lobbyActivityLogic = new LobbyActivityLogic(this);
    }

}
