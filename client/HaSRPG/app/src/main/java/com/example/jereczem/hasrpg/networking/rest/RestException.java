package com.example.jereczem.hasrpg.networking.rest;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.example.jereczem.hasrpg.networking.HttpResponse;
import com.example.jereczem.hasrpg.view.dialogs.Alerts;
import com.example.jereczem.hasrpg.view.dialogs.LobbyAlerts;
import com.example.jereczem.hasrpg.view.dialogs.SignInAlerts;
import com.example.jereczem.hasrpg.view.dialogs.SignUpAlerts;

/**
 * Created by jereczem on 05.09.15.
 */
public class RestException extends Exception {

    private HttpResponse response;

    public RestException(HttpResponse response) {
        super("Error code: " + response.getCode().toString() + "\n" + response.getMessage());
        this.response = response;
    }

    public HttpResponse getResponse() {
        return response;
    }

    public AlertDialog getErrorAlert(AppCompatActivity activity){
        switch(response.getCode()){
            case 256:
                return Alerts.notLoggedError(activity);
            case 257:
                return LobbyAlerts.wrongDataError(activity);
            case 258:
                return SignInAlerts.wrongLoginAndPassword(activity);
            case 259:
                return SignUpAlerts.userAlreadyExists(activity);
            case 260:
                return Alerts.databaseError(activity);
            case 261:
                return SignUpAlerts.wrongLoginLenght(activity);
            case 262:
                return SignUpAlerts.wrongPasswordLenght(activity);
            case 263:
                return SignUpAlerts.emptyInput(activity);
            default:
                return Alerts.connectionError(activity, response.getMessage());
        }
    }
}
