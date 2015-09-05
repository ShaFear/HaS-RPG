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
    public static HttpResponse getResponse(String title, String player_no, String game_limit, String run_time) throws RestException {
        HttpResponseReceiver httpResponseReceiver = new HttpResponseReceiver("lobbies");
        httpResponseReceiver.addParameter("title", title);
        httpResponseReceiver.addParameter("player_no", player_no);
        httpResponseReceiver.addParameter("game_limit", game_limit);
        httpResponseReceiver.addParameter("run_time", run_time);
        HttpResponse response = httpResponseReceiver.receive();
        if(!response.getCode().equals(200))
            throw new RestException(response);
        return response;
    }
}
