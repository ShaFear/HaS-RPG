package com.example.jereczem.hasrpg.networking.rest;

import android.support.v7.app.AppCompatActivity;

import com.example.jereczem.hasrpg.networking.HttpResponse;
import com.example.jereczem.hasrpg.networking.HttpResponseReceiver;
import com.example.jereczem.hasrpg.view.dialogs.Alerts;

import org.json.JSONException;

/**
 * Created by jereczem on 04.09.15.
 */
public class LobbyGetter {
    public static HttpResponse getResponse(Integer lobbyId) throws JSONException, RestException {
        HttpResponseReceiver rReceiver = new HttpResponseReceiver("lobbies/" + lobbyId);
        HttpResponse response = rReceiver.receive();
        if(!response.getCode().equals(200))
            throw new RestException(response);
        return response;
    }
}
