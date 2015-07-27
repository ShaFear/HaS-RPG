package com.example.jereczem.hasrpg.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.data.PlayerData;
import com.example.jereczem.hasrpg.data.PlayerDataReceiver;
import com.example.jereczem.hasrpg.dialog.Alerts;
import com.example.jereczem.hasrpg.http.HttpUtils;
import com.example.jereczem.hasrpg.http.Response;
import com.example.jereczem.hasrpg.settings.G;

import java.io.IOException;


public class LobbiesActivity extends AppCompatActivity implements LobbyFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobbies);
        String url = G.SERVER_URL + "mycharacters";

        new GetCharactersDataTask().execute(url, this);
    }

    @Override
    public void onFragmentInteraction(String id) {

    }

    public void selectCharacters(View view) {
        Intent intent = new Intent(this, CharacterSelectActivity.class);
        startActivity(intent);
    }

    public void createNewLobby(View view) {

    }

    private class GetCharactersDataTask extends AsyncTask<Object, Void, Response> {
        private Activity activity;

        @Override
        protected Response doInBackground(Object... params) {
            String url = (String) params[0];
            activity = (Activity) params[1];

            try {
                return HttpUtils.GET(url);
            } catch (IOException e) {
                return new Response(500, e.getMessage());
            }
        }

        protected void onPostExecute(final Response result) {
            //TODO pobralismy dane, wiec mozna zaczac zabawe ^^
            PlayerData playerData = PlayerDataReceiver.fromString(result.getMessage());
        }
    }

}
