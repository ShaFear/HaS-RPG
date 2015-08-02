package com.example.jereczem.hasrpg.view.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.view.fragments.LobbyFragment;
import com.example.jereczem.hasrpg.view.logic.LobbiesActivityLogic;


public class LobbiesActivity extends AppCompatActivity implements LobbyFragment.OnFragmentInteractionListener {

    private LobbiesActivityLogic lobbiesActivityLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobbies);
        lobbiesActivityLogic = new LobbiesActivityLogic(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        lobbiesActivityLogic.downloadPlayerData();
    }

    @Override
    public void onBackPressed() {
        lobbiesActivityLogic.moveTaskToBack();
    }

    @Override
    public void onFragmentInteraction(String id) {

    }

    public void selectCharacters(View view) {
        lobbiesActivityLogic.selectCharactersClick();
    }

    public void createNewLobby(View view) {

    }
}
