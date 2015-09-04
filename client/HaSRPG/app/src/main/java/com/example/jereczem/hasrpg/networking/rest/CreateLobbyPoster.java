package com.example.jereczem.hasrpg.networking.rest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.example.jereczem.hasrpg.networking.HttpResponse;
import com.example.jereczem.hasrpg.networking.HttpResponseReceiver;
import com.example.jereczem.hasrpg.view.activities.LobbyActivity;
import com.example.jereczem.hasrpg.view.dialogs.Alerts;
import com.example.jereczem.hasrpg.view.dialogs.LobbyAlerts;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jereczem on 04.09.15.
 */
public class CreateLobbyPoster {
    public static HttpResponse getResponse(String title, String player_no, String game_limit, String run_time, AppCompatActivity a){
        HttpResponseReceiver httpResponseReceiver = new HttpResponseReceiver("lobbies");
        httpResponseReceiver.addParameter("title", title);
        httpResponseReceiver.addParameter("player_no", player_no);
        httpResponseReceiver.addParameter("game_limit", game_limit);
        httpResponseReceiver.addParameter("run_time", run_time);
        HttpResponse response = httpResponseReceiver.receive();
        handleHttpResponse(response, a);
        return response;
    }

    private static void handleHttpResponse(HttpResponse response, AppCompatActivity a) {
        switch(response.getCode()){
            case 200:{
                break;
            }
            case 260:{
                Alerts.databaseError(a).show();
                break;
            }
            case 256:{
                Alerts.notLoggedError(a).show();
                break;
            }
            case 257:{
                LobbyAlerts.wrongDataError(a).show();
                break;
            }
            default:{
                Alerts.connectionError(a, response.getMessage());
            }
        }
    }
}
