package com.example.jereczem.hasrpg.networking.rest;

import com.example.jereczem.hasrpg.networking.HttpResponse;
import com.example.jereczem.hasrpg.networking.HttpResponseReceiver;

/**
 * Created by jereczem on 04.09.15.
 */
public class StatusPoster {

    public static HttpResponse getResponse(Integer lobbyID, Boolean isReady) throws RestException {
        HttpResponseReceiver httpResponseReceiver = new HttpResponseReceiver("lobbies/"+ lobbyID +"/my_status");
        if(isReady)
            httpResponseReceiver.addParameter("status", "READY");
        else
            httpResponseReceiver.addParameter("status", "WAIT");
        HttpResponse response = httpResponseReceiver.receive();
        if(!response.getCode().equals(200))
            throw new RestException(response);
        return response;
    }
}
