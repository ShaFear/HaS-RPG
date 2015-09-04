package com.example.jereczem.hasrpg.networking.rest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;

import com.example.jereczem.hasrpg.networking.HttpResponse;
import com.example.jereczem.hasrpg.networking.HttpResponseReceiver;
import com.example.jereczem.hasrpg.view.dialogs.Alerts;

/**
 * Created by jereczem on 04.09.15.
 */
public class PlayerDataGetter {
    public static HttpResponse getResponse(final AppCompatActivity a){
        HttpResponse response = new HttpResponseReceiver("mycharacters").receive();
        if (response.getCode().equals(200)) {
            return response;
        } else {
            AlertDialog alertDialog;
            switch (response.getCode()){
                case 256:{
                    Alerts.notLoggedError(a).show();
                    break;
                }
                case 600:{
                    Alerts.databaseError(a).show();
                    break;
                }
                default:{
                    Alerts.connectionError(a, response.getMessage()).show();
                    break;
                }
            }
        }
        return response;
    }
}
