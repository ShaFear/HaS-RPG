package com.example.jereczem.hasrpg.view.logic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.networking.HttpResponse;
import com.example.jereczem.hasrpg.networking.HttpResponseReceiver;
import com.example.jereczem.hasrpg.networking.rest.RestException;
import com.example.jereczem.hasrpg.networking.rest.SignInPoster;
import com.example.jereczem.hasrpg.view.activities.MenuActivity;
import com.example.jereczem.hasrpg.view.activities.SignUpActivity;
import com.example.jereczem.hasrpg.view.dialogs.SignInAlerts;
import com.example.jereczem.hasrpg.view.toolbar.ToolbarSetter;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;

/**
 * Created by jereczem on 01.08.15.
 */
public class SignInLogic{
    AppCompatActivity a;

    public SignInLogic(AppCompatActivity activity) {
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(cookieManager);
        this.a = activity;
        new ToolbarSetter(a);
    }

    public void signInClick(){
        EditText loginEditText = (EditText) a.findViewById(R.id.login_input);
        EditText passwordEditText = (EditText) a.findViewById(R.id.password_input);
        String login = loginEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        login = login.replace(" ", ""); //TODO zrobic porzadnego regexa

        HttpResponse response = null;
        try {
            response = SignInPoster.getResponse(login, password);
            if(response.getCode().equals(200)){
                Intent intent = new Intent(a, MenuActivity.class);
                a.startActivity(intent);
            }
        } catch (RestException e) {
            e.printStackTrace();
            e.getErrorAlert(a).show();
        }
    }

    public void signUpClick(){
        Intent intent = new Intent(a, SignUpActivity.class);
        a.startActivity(intent);
    }


}
