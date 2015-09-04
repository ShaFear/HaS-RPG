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
    public static HttpResponse getResponse(Integer lobbyId, AppCompatActivity a) throws JSONException {
        HttpResponseReceiver rReceiver = new HttpResponseReceiver("lobbies/" + lobbyId);
        HttpResponse response = rReceiver.receive();
        handleDownloadLobbyDataResponse(response, a);
        return response;
    }

    private static void handleDownloadLobbyDataResponse(HttpResponse response, AppCompatActivity a) throws JSONException {
        switch (response.getCode()){
            case 200:{
                break;
            }
            case 256:{
                Alerts.notLoggedError(a).show();
                break;
            }
            case 260:{
                Alerts.databaseError(a).show();
                break;
            }
            default:{
                Alerts.connectionError(a, response.getMessage());
            }
        }
    }
}
