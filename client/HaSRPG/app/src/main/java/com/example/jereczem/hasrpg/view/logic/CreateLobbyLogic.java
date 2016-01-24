package com.example.jereczem.hasrpg.view.logic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.networking.HttpResponse;
import com.example.jereczem.hasrpg.networking.rest.LobbyPoster;
import com.example.jereczem.hasrpg.networking.rest.RestException;
import com.example.jereczem.hasrpg.view.activities.LobbyActivity;
import com.example.jereczem.hasrpg.view.toolbar.ToolbarSetter;
import com.wefika.horizontalpicker.HorizontalPicker;

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

    public void createFastGame(){

        String title =
                ((EditText) a.findViewById(R.id.createLobbyTitleEditText)).getText().toString();
        String player_no = String.valueOf(((HorizontalPicker) a.findViewById(R.id.picker)).getSelectedItem() + 3);

        String game_limit = "720";

        String run_time = "180";


        try {
            HttpResponse response = LobbyPoster.getResponse(title, player_no, game_limit, run_time);
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

    public void createMediumGame(){

        String title =
                ((EditText) a.findViewById(R.id.createLobbyTitleEditText)).getText().toString();
        String player_no = String.valueOf(((HorizontalPicker) a.findViewById(R.id.picker)).getSelectedItem() + 3);

        String game_limit = "1440";

        String run_time = "360";


        try {
            HttpResponse response = LobbyPoster.getResponse(title, player_no, game_limit, run_time);
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

    public void createLongGame(){

        String title =
                ((EditText) a.findViewById(R.id.createLobbyTitleEditText)).getText().toString();
        String player_no = String.valueOf(((HorizontalPicker) a.findViewById(R.id.picker)).getSelectedItem() + 3);

        String game_limit = "2880";

        String run_time = "720";


        try {
            HttpResponse response = LobbyPoster.getResponse(title, player_no, game_limit, run_time);
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
