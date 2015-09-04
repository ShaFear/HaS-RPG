package com.example.jereczem.hasrpg.view.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.view.logic.CreateLobbyLogic;

public class CreateLobbyActivity extends AppCompatActivity {

    private CreateLobbyLogic createLobbyLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_lobby);
        createLobbyLogic = new CreateLobbyLogic(this);
    }

    public void createNewLobbyClick(View view) {
        createLobbyLogic.createNewLobbyClicked();
    }
}
