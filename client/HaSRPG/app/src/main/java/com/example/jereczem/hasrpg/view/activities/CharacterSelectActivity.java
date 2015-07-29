package com.example.jereczem.hasrpg.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.data.PlayerData;


public class CharacterSelectActivity extends AppCompatActivity{

    private PlayerData playerData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_select);
        Intent intent = getIntent();
        playerData = (PlayerData)intent.getSerializableExtra("playerData");
        Log.d("HASLOG", playerData.toString());
    }
}
