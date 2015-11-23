package com.example.jereczem.hasrpg.view.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.data.player.PlayerData;
import com.example.jereczem.hasrpg.data.player.PlayerDataReceiver;
import com.example.jereczem.hasrpg.game.lobbies.Lobby;
import com.example.jereczem.hasrpg.networking.HttpResponseReceiver;
import com.example.jereczem.hasrpg.settings.LobbySettings;
import com.example.jereczem.hasrpg.settings.ServerSettings;
import com.example.jereczem.hasrpg.sockets.SocketServerConnector;
import com.example.jereczem.hasrpg.sockets.events.EventName;
import com.example.jereczem.hasrpg.sockets.events.connection.ConnectionEvent;
import com.example.jereczem.hasrpg.sockets.events.disconnection.DisconnectionEvent;
import com.example.jereczem.hasrpg.view.toolbar.ToolbarSetter;
import com.github.nkzawa.emitter.Emitter;

import org.json.JSONException;
import org.json.JSONObject;

public class HunterGameActivity extends GameActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hunter_game);
    }
}
