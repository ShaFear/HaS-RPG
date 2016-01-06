package com.example.jereczem.hasrpg.view.activities;

import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.data.player.PlayerData;
import com.example.jereczem.hasrpg.sockets.events.attack.AttackEvent;
import com.example.jereczem.hasrpg.sockets.events.gametime.GameTimeTimer;
import com.example.jereczem.hasrpg.sockets.events.gametime.TimeParser;
import com.example.jereczem.hasrpg.sockets.events.runtime.RunTimeEvent;
import com.example.jereczem.hasrpg.view.dialogs.Alerts;

import java.util.ArrayList;

public class HunterGameActivity extends GameActivity {

    public GameTimeTimer gameTimeTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hunter_game);
        ((TextView)findViewById(R.id.gameTimeHunter)).setText(TimeParser.fromLong(lobby.getGameTime()));
        RunTimeEvent.setRunTimer(sConnector, lobby.getRunTime(), this);
        gameTimeTimer = new GameTimeTimer(this.lobby.getGameTime() * 1000, 1000, this, sConnector);
    }

    @Override
    public void onLocationChanged(Location location) {
        super.onLocationChanged(location);
        Toast.makeText(this, "Location changed to: " + location.toString(), Toast.LENGTH_SHORT).show();
    }

    public void killSomeChase(View view) {
        ArrayList<PlayerData> lobbyPlayers = lobby.getLobbyPlayers();
        for(PlayerData data : lobbyPlayers){
            if(!data.getUserID().equals(playerData.getUserID())){
                Integer userID = data.getUserID();
                AttackEvent.hunterSendsAttack(sConnector, this, userID);
                Alerts.DialogGenerator.generateSimpleOKAlert(this, "Killed", "Killed user: " + data.getLogin()).show();
            }
        }
    }
}
