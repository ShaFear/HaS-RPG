package com.example.jereczem.hasrpg.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jereczem.hasrpg.R;


public class LobbiesActivity extends AppCompatActivity implements LobbyFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobbies);
    }

    @Override
    public void onFragmentInteraction(String id) {

    }

    public void selectCharacters(View view){
        Intent intent = new Intent(this, CharacterSelectActivity.class);
        startActivity(intent);
    }

    public void createNewLobby(View view){

    }
}
