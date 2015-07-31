package com.example.jereczem.hasrpg.view.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.data.PlayerData;
import com.example.jereczem.hasrpg.data.PlayerDataReceiver;
import com.example.jereczem.hasrpg.view.fragments.LobbyFragment;
import com.example.jereczem.hasrpg.networking.HttpConnection;
import com.example.jereczem.hasrpg.networking.HttpResponse;
import com.example.jereczem.hasrpg.settings.ServerSettings;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.ExecutionException;


public class LobbiesActivity extends AppCompatActivity implements LobbyFragment.OnFragmentInteractionListener {

    private PlayerData playerData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobbies);
        String url = ServerSettings.SERVER_URL + "mycharacters";

        try {
            HttpResponse httpResponse = new GetCharactersDataTask().execute(url, this).get();
            playerData = PlayerDataReceiver.fromString(httpResponse.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFragmentInteraction(String id) {

    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void selectCharacters(View view) {
        Intent intent = new Intent(this, CharacterSelectActivity.class);
        intent.putExtra("playerData", playerData);
        startActivity(intent);
    }

    public void createNewLobby(View view) {

    }

    private class GetCharactersDataTask extends AsyncTask<Object, Void, HttpResponse> {
        private Activity activity;

        @Override
        protected HttpResponse doInBackground(Object... params) {
            String url = (String) params[0];
            activity = (Activity) params[1];

            try {
                return HttpConnection.get(url);
            } catch (IOException e) {
                return new HttpResponse(500, e.getMessage());
            }
        }
    }

}
