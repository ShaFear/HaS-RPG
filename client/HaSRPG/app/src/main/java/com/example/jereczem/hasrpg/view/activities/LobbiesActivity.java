package com.example.jereczem.hasrpg.view.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.data.PlayerData;
import com.example.jereczem.hasrpg.data.PlayerDataReceiver;
import com.example.jereczem.hasrpg.networking.HttpResponseReceiver;
import com.example.jereczem.hasrpg.view.dialogs.Alerts;
import com.example.jereczem.hasrpg.view.fragments.LobbyFragment;
import com.example.jereczem.hasrpg.networking.HttpResponse;


public class LobbiesActivity extends AppCompatActivity implements LobbyFragment.OnFragmentInteractionListener {

    private PlayerData playerData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobbies);
    }

    @Override
    protected void onResume() {
        super.onResume();
        downloadPlayerData();
        Log.d("HASLOG", playerData.toString());
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public void onFragmentInteraction(String id) {

    }

    private void downloadPlayerData() {
        HttpResponse response = new HttpResponseReceiver("mycharacters").receive();
        if (response.getCode().equals(200)) {
            playerData = PlayerDataReceiver.fromString(response.getMessage());
        } else {
            AlertDialog alertDialog = Alerts.connectionError(this, response.getMessage());
            alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    downloadPlayerData();
                }
            });
            alertDialog.show();
        }
    }

    public void selectCharacters(View view) {
        Intent intent = new Intent(this, CharacterSelectActivity.class);
        intent.putExtra("playerData", playerData);
        startActivity(intent);
    }

    public void createNewLobby(View view) {

    }
}
