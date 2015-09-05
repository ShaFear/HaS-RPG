package com.example.jereczem.hasrpg.networking.rest;

import android.support.v7.app.AppCompatActivity;

import com.example.jereczem.hasrpg.data.player.PlayerData;
import com.example.jereczem.hasrpg.networking.HttpResponse;
import com.example.jereczem.hasrpg.networking.HttpResponseReceiver;

/**
 * Created by jereczem on 04.09.15.
 */
public class ChangeCharacterStatusPoster {
    public static HttpResponse getResponse(PlayerData playerData) throws RestException {
        HttpResponseReceiver httpResponseReceiver =
                new HttpResponseReceiver("mycharacters/ChaseID");
        httpResponseReceiver.addParameter("chase_id",
                playerData.getSelectedChase().getCharacterID().toString());
        HttpResponse response = httpResponseReceiver.receive();

        if(!response.getCode().equals(200))
           throw new RestException(response);

        httpResponseReceiver =
                new HttpResponseReceiver("mycharacters/HunterID");
        httpResponseReceiver.addParameter("hunter_id",
                playerData.getSelectedHunter().getCharacterID().toString());
        response = httpResponseReceiver.receive();

        if(!response.getCode().equals(200))
            throw new RestException(response);

        return response;
    }
}
