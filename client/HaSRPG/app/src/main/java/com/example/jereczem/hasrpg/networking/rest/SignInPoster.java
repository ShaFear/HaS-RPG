package com.example.jereczem.hasrpg.networking.rest;

import com.example.jereczem.hasrpg.networking.HttpResponse;
import com.example.jereczem.hasrpg.networking.HttpResponseReceiver;

/**
 * Created by jereczem on 04.09.15.
 */
public class SignInPoster {

    public static HttpResponse getResponse(String login, String password) throws RestException {
        HttpResponseReceiver httpResponseReceiver = new HttpResponseReceiver("signin");
        httpResponseReceiver.addParameter("login", login);
        httpResponseReceiver.addParameter("password", password);
        HttpResponse response = httpResponseReceiver.receive();
        if(!response.getCode().equals(200))
            throw new RestException(response);
        return response;
    }
}
