package com.example.jereczem.hasrpg.view.logic;

import android.app.Activity;
import android.content.Intent;
import android.widget.EditText;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.networking.HttpResponse;
import com.example.jereczem.hasrpg.networking.HttpResponseReceiver;
import com.example.jereczem.hasrpg.view.activities.LobbiesActivity;
import com.example.jereczem.hasrpg.view.activities.SignUpActivity;
import com.example.jereczem.hasrpg.view.dialogs.SignInAlerts;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;

/**
 * Created by jereczem on 01.08.15.
 */
public class SignInLogic{
    Activity a;

    public SignInLogic(Activity activity) {
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(cookieManager);
        this.a = activity;
    }

    public void signInClick(){
        EditText loginEditText = (EditText) a.findViewById(R.id.login_input);
        EditText passwordEditText = (EditText) a.findViewById(R.id.password_input);
        String login = loginEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        login = login.replace(" ", ""); //TODO zrobic porzadnego regexa

        HttpResponseReceiver httpResponseReceiver = new HttpResponseReceiver("signin");
        httpResponseReceiver.addParameter("login", login);
        httpResponseReceiver.addParameter("password", password);
        HttpResponse response = httpResponseReceiver.receive();
        handleSignInResponse(response);
    }

    public void signUpClick(){
        Intent intent = new Intent(a, SignUpActivity.class);
        a.startActivity(intent);
    }

    private void handleSignInResponse(HttpResponse response) {
        switch (response.getCode()) {
            case 200: {
                Intent intent = new Intent(a, LobbiesActivity.class);
                a.startActivity(intent);
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
