package com.example.jereczem.hasrpg.networking.rest;

import com.example.jereczem.hasrpg.networking.HttpResponse;
import com.example.jereczem.hasrpg.networking.HttpResponseReceiver;

/**
 * Created by jereczem on 05.09.15.
 */
public class LobbiesGetter {
    public static HttpResponse getResponse() throws RestException {
        HttpResponseReceiver rReceiver = new HttpResponseReceiver("lobbies");
        HttpResponse response = rReceiver.receive();
        if(!response.getCode().equals(200))
            throw new RestException(response);
        return response;
    }
}
