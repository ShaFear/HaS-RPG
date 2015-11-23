package com.example.jereczem.hasrpg.view.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.data.player.PlayerData;
import com.example.jereczem.hasrpg.data.player.PlayerDataReceiver;
import com.example.jereczem.hasrpg.game.lobbies.Lobby;
import com.example.jereczem.hasrpg.networking.HttpResponse;
import com.example.jereczem.hasrpg.networking.HttpResponseReceiver;
import com.example.jereczem.hasrpg.networking.rest.RestException;
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

public class GameActivity extends AppCompatActivity {
    public Lobby lobby;
    public PlayerData playerData;
    SocketServerConnector sConnector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        new ToolbarSetter(this);
        setSocketConnection();
        setSocketListener();
    }

    @Override
    public void onBackPressed() {
        //do nothing
    }

    private void setSocketListener() {
        sConnector.getSocket().on(ServerSettings.SOCKET_EVENT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                callGameEvent(args[0].toString());
            }
        });
    }

    private void setSocketConnection() {
        playerData = PlayerDataReceiver.fromString(new HttpResponseReceiver("mycharacters").receive().getMessage());
        lobby = (Lobby)this.getIntent().getExtras().getSerializable(LobbySettings.LOBBY_TO_GAME_INTENT_TAG);
        sConnector = new SocketServerConnector(lobby.getLobbyID(), playerData.getUserID());
    }

    private void callGameEvent(String arg) {
        try {
            JSONObject eventInformation = new JSONObject(arg);
            String name = eventInformation.getString("name");
            EventName event = EventName.getEventName(name);
            switch (event){
                case CONNECTION:{
                    new ConnectionEvent(eventInformation, sConnector, this).runEvent();
                    break;
                }
                case DISCONNECTION:{
                    new DisconnectionEvent(eventInformation, sConnector, this).runEvent();
                    break;
                }
                case NONE:{
                    Log.d("HASLOG", eventInformation.toString());
                    break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
