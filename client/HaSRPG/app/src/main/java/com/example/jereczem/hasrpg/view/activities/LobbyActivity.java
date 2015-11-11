package com.example.jereczem.hasrpg.view.activities;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.networking.rest.StatusPoster;
import com.example.jereczem.hasrpg.view.logic.LobbyActivityLogic;

public class LobbyActivity extends AppCompatActivity {
    LobbyActivityLogic lobbyActivityLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
        lobbyActivityLogic = new LobbyActivityLogic(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        lobbyActivityLogic.onPause();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        NavUtils.navigateUpFromSameTask(this);
    }

    public void clickReadyButton(View view) {
        lobbyActivityLogic.clickReady();
    }
}
