package com.example.jereczem.hasrpg.view.logic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.networking.HttpResponse;
import com.example.jereczem.hasrpg.networking.HttpResponseReceiver;
import com.example.jereczem.hasrpg.networking.rest.CreateLobbyPoster;
import com.example.jereczem.hasrpg.networking.rest.RestException;
import com.example.jereczem.hasrpg.view.activities.LobbyActivity;
import com.example.jereczem.hasrpg.view.dialogs.Alerts;
import com.example.jereczem.hasrpg.view.dialogs.LobbyAlerts;
import com.example.jereczem.hasrpg.view.toolbar.ToolbarSetter;

import org.json.JSONException;
import org.json.JSONObject;

//TODO weryfikacja inputów

/**
 * Created by jereczem on 04.09.15.
 */
public class CreateLobbyLogic {
    private AppCompatActivity a;

    public CreateLobbyLogic(AppCompatActivity a){
        this.a = a;
        new ToolbarSetter(a, R.drawable.previous);
    }

    public void createNewLobbyClicked(){
        String title =
                ((EditText) a.findViewById(R.id.createLobbyTitleEditText)).getText().toString();
        String player_no =
                ((EditText) a.findViewById(R.id.createLobbyNumberOfPlayersEditText)).getText().toString();
        String game_limit =
                ((EditText) a.findViewById(R.id.createLobbyGameTimeEdit)).getText().toString();
        String run_time =
                ((EditText) a.findViewById(R.id.createLobbyRunTimeEdit)).getText().toString();

        try {
            HttpResponse response = CreateLobbyPoster.getResponse(title, player_no, game_limit, run_time);
            if(response.getCode().equals(200)){
                try {
                    Integer lobbyID = new JSONObject(response.getMessage()).getInt("insertId");
                    Intent intent = new Intent(a, LobbyActivity.class);
                    intent.putExtra("lobbyId", lobbyID);
                    a.startActivity(intent);
                    a.finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (RestException e) {
            e.printStackTrace();
            e.getErrorAlert(a).show();
        }
    }
}
