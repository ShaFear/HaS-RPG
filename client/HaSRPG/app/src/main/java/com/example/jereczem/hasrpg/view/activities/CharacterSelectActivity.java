package com.example.jereczem.hasrpg.view.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.data.CharacterData;
import com.example.jereczem.hasrpg.data.PlayerData;
import com.example.jereczem.hasrpg.game.Chase;
import com.example.jereczem.hasrpg.game.Hunter;
import com.example.jereczem.hasrpg.networking.HttpConnection;
import com.example.jereczem.hasrpg.networking.HttpResponse;
import com.example.jereczem.hasrpg.view.PlayerDataView;
import com.example.jereczem.hasrpg.view.dialogs.Alerts;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;


public class CharacterSelectActivity extends AppCompatActivity{
    private PlayerData playerData;
    private PlayerDataView playerDataView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_select);
        Intent intent = getIntent();
        playerData = (PlayerData)intent.getSerializableExtra("playerData");
        Log.d("HASLOG", playerData.toString());
        playerDataView = new PlayerDataView(this, playerData);
        playerData.addObserver(playerDataView);
    }

    public void rightChase(View view) {
        playerData.rightChase();
    }

    public void leftChase(View view) {
        playerData.leftChase();

    }

    public void rightHunter(View view) {
        playerData.rightHunter();
    }

    public void leftHunter(View view) {
        playerData.leftHunter();
    }

    public void saveSelectedCharacters(View view) {
    }

}
