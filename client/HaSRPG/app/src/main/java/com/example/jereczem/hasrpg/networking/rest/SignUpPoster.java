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
    public static HttpResponse getResponse(AppCompatActivity a, String login, String password) {
        HttpResponseReceiver httpResponseReceiver = new HttpResponseReceiver("users");
        httpResponseReceiver.addParameter("login", login);
        httpResponseReceiver.addParameter("password", password);
        HttpResponse response = httpResponseReceiver.receive();
        handleSignUpResponse(response, a);
        return response;
    }

    private static void handleSignUpResponse(HttpResponse response, final AppCompatActivity a) {
        switch (response.getCode()){
            case 200:{
                break;
            }
            case 256:{
                SignUpAlerts.wrongLoginLenght(a).show(); break;
            }
            case 257:{
                SignUpAlerts.wrongPasswordLenght(a).show(); break;
            }
            case 258:{
                SignUpAlerts.emptyInput(a).show(); break;
            }
            case 259:{
                SignUpAlerts.userAlreadyExists(a).show(); break;
            }
            case 260:{
                Alerts.databaseError(a); break;
            }
            default:{
                Alerts.connectionError(a, response.getMessage()).show();
            }
        }
    }
}
