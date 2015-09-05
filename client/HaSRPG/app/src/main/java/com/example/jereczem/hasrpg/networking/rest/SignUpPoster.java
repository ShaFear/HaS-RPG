package com.example.jereczem.hasrpg.networking.rest;

import android.support.v7.app.AppCompatActivity;

import com.example.jereczem.hasrpg.networking.HttpResponse;
import com.example.jereczem.hasrpg.networking.HttpResponseReceiver;
import com.example.jereczem.hasrpg.view.dialogs.Alerts;
import com.example.jereczem.hasrpg.view.dialogs.SignUpAlerts;

/**
 * Created by jereczem on 04.09.15.
 */
public class SignUpPoster {
    public static HttpResponse getResponse(AppCompatActivity a, String login, String password) throws RestException {
        HttpResponseReceiver httpResponseReceiver = new HttpResponseReceiver("users");
        httpResponseReceiver.addParameter("login", login);
        httpResponseReceiver.addParameter("password", password);
        HttpResponse response = httpResponseReceiver.receive();
        if(!response.getCode().equals(200))
            throw new RestException(response);
        return response;
    }
}
