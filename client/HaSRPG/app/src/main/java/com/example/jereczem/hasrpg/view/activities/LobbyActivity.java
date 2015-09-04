package com.example.jereczem.hasrpg.view.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.networking.HttpResponse;
import com.example.jereczem.hasrpg.networking.HttpResponseReceiver;
import com.example.jereczem.hasrpg.view.dialogs.Alerts;

public class LobbyActivity extends AppCompatActivity {
    Integer lobbyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lobbyId = getIntent().getIntExtra("lobbyId", 0);
        setContentView(R.layout.activity_lobby);
        downloadLobbyData();
    }

    private void downloadLobbyData(){
        HttpResponseReceiver rReceiver = new HttpResponseReceiver("lobbies/" + lobbyId);
        HttpResponse response= rReceiver.receive();
    }
}
