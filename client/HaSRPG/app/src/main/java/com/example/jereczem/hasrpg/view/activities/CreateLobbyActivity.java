package com.example.jereczem.hasrpg.view.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.networking.HttpResponse;
import com.example.jereczem.hasrpg.networking.HttpResponseReceiver;
import com.example.jereczem.hasrpg.view.dialogs.Alerts;
import com.example.jereczem.hasrpg.view.dialogs.LobbyAlerts;
import com.example.jereczem.hasrpg.view.toolbar.ToolbarSetter;

public class CreateLobbyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_lobby);
        new ToolbarSetter(this, R.drawable.previous);
    }

    public void createNewLobbyClick(View view) {
        HttpResponseReceiver httpResponseReceiver = new HttpResponseReceiver("lobbies");
        String title =
                ((EditText) findViewById(R.id.createLobbyTitleEditText)).getText().toString();
        String player_no =
                ((EditText) findViewById(R.id.createLobbyNumberOfPlayersEditText)).getText().toString();
        String game_limit =
                ((EditText) findViewById(R.id.createLobbyGameTimeEdit)).getText().toString();
        String run_time =
                ((EditText) findViewById(R.id.createLobbyRunTimeEdit)).getText().toString();
        httpResponseReceiver.addParameter("title", title);
        httpResponseReceiver.addParameter("player_no", player_no);
        httpResponseReceiver.addParameter("game_limit", game_limit);
        httpResponseReceiver.addParameter("run_time", run_time);
        HttpResponse response = httpResponseReceiver.receive();

        switch(response.getCode()){
            case 200:{
                finish();
                break;
            }
            case 260:{
                Alerts.databaseError(this).show();
                break;
            }
            case 256:{
                Alerts.notLoggedError(this).show();
                break;
            }
            case 257:{
                LobbyAlerts.wrongDataError(this).show();
                break;
            }
            default:{
                Alerts.connectionError(this, response.getMessage());
            }
        }
    }
}
