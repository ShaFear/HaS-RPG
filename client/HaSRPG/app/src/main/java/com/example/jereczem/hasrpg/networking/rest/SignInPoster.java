package com.example.jereczem.hasrpg.networking.rest;

import android.support.v7.app.AppCompatActivity;

import com.example.jereczem.hasrpg.networking.HttpResponse;
import com.example.jereczem.hasrpg.networking.HttpResponseReceiver;
import com.example.jereczem.hasrpg.view.dialogs.SignInAlerts;

/**
 * Created by jereczem on 04.09.15.
 */
public class SignInPoster {

    public static HttpResponse getResponse(AppCompatActivity a, String login, String password){
        HttpResponseReceiver httpResponseReceiver = new HttpResponseReceiver("signin");
        httpResponseReceiver.addParameter("login", login);
        httpResponseReceiver.addParameter("password", password);
        HttpResponse response = httpResponseReceiver.receive();
        handleSignInResponse(response, a);
        return response;
    }

    private static void handleSignInResponse(HttpResponse response, AppCompatActivity a) {

        switch (response.getCode()) {
            case 200:{
                break;
            }
            case 256: {
                SignInAlerts.wrongLoginAndPassword(a).show();
                break;
            }
            case 260: {
                SignInAlerts.databaseError(a).show();
                break;
            }
            default: {
                SignInAlerts.connectionError(a, response.getMessage()).show();
                break;
            }
        }
    }
}
