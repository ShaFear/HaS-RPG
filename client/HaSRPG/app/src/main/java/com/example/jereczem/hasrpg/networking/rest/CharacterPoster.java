package com.example.jereczem.hasrpg.networking.rest;

import com.example.jereczem.hasrpg.networking.HttpResponse;
import com.example.jereczem.hasrpg.networking.HttpResponseReceiver;

/**
 * Created by jereczem on 04.09.15.
 */
public class CharacterPoster {

    public static HttpResponse getResponse(Integer characterId, Integer experience, Integer level) throws RestException {
        HttpResponseReceiver httpResponseReceiver = new HttpResponseReceiver("character/"+ characterId);
        httpResponseReceiver.addParameter("experience", experience.toString());
        httpResponseReceiver.addParameter("level", level.toString());
        HttpResponse response = httpResponseReceiver.receive();
        if(!response.getCode().equals(200))
            throw new RestException(response);
        return response;
    }
}
