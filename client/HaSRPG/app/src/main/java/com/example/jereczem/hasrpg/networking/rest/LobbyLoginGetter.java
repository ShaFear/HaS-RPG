package com.example.jereczem.hasrpg.networking.rest;

import com.example.jereczem.hasrpg.networking.HttpResponse;
import com.example.jereczem.hasrpg.networking.HttpResponseReceiver;

import org.json.JSONException;

/**
 * Created by jereczem on 05.09.15.
 */
public class LobbyLoginGetter {
    public static HttpResponse getResponse(Integer lobbyId) throws RestException {
        HttpResponseReceiver rReceiver = new HttpResponseReceiver("lobbies/" + lobbyId + "/login");
        HttpResponse response = rReceiver.receive();
        if(!response.getCode().equals(200))
            throw new RestException(response);
        return response;
    }
}
