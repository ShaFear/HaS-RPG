package com.example.jereczem.hasrpg.networking.rest;

import android.support.v7.app.AppCompatActivity;

import com.example.jereczem.hasrpg.data.player.PlayerData;
import com.example.jereczem.hasrpg.networking.HttpResponse;
import com.example.jereczem.hasrpg.networking.HttpResponseReceiver;
import com.example.jereczem.hasrpg.view.dialogs.Alerts;

/**
 * Created by jereczem on 04.09.15.
 */
public class ChangeCharacterStatusPoster {
    public static HttpResponse getResponse(PlayerData playerData, AppCompatActivity activity){
        HttpResponseReceiver httpResponseReceiver =
                new HttpResponseReceiver("mycharacters/ChaseID");
        httpResponseReceiver.addParameter("chase_id",
                playerData.getSelectedChase().getCharacterID().toString());
        HttpResponse response = httpResponseReceiver.receive();

        if (!response.getCode().equals(200))
            handleSetIDResponse(response, activity);

        httpResponseReceiver =
                new HttpResponseReceiver("mycharacters/HunterID");
        httpResponseReceiver.addParameter("hunter_id",
                playerData.getSelectedHunter().getCharacterID().toString());
        response = httpResponseReceiver.receive();
        handleSetIDResponse(response, activity);
        return response;
    }

    private static void handleSetIDResponse(HttpResponse response, AppCompatActivity activity) {
        switch (response.getCode()){
            case 200:{
                break;
            }
            case 260:{
                Alerts.databaseError(activity).show();
                break;
            }
            case 256:{
                Alerts.notLoggedError(activity).show();
                break;
            }
            default:{
                Alerts.connectionError(activity, response.getMessage()).show();
            }
        }
    }
}
