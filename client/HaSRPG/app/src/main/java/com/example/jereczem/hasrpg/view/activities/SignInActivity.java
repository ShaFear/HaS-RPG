package com.example.jereczem.hasrpg.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.networking.HttpResponse;
import com.example.jereczem.hasrpg.networking.HttpResponseReceiver;
import com.example.jereczem.hasrpg.view.dialogs.SignInAlerts;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.ExecutionException;


public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(cookieManager);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void signIn(View view) throws ExecutionException, InterruptedException {
        EditText loginEditText = (EditText) findViewById(R.id.login_input);
        EditText passwordEditText = (EditText) findViewById(R.id.password_input);
        String login = loginEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        login = login.replace(" ", ""); //TODO zrobic porzadnego regexa

        HttpResponseReceiver httpResponseReceiver = new HttpResponseReceiver("signin");
        httpResponseReceiver.addParameter("login", login);
        httpResponseReceiver.addParameter("password", password);
        HttpResponse response = httpResponseReceiver.receive();
        handleSignInResponse(response);
    }

    public void signUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    private void handleSignInResponse(HttpResponse response) {
        switch (response.getCode()) {
            case 200: {
                Intent intent = new Intent(this, LobbiesActivity.class);
                startActivity(intent);
                break;
            }
            case 256: {
                SignInAlerts.wrongLoginAndPassword(this).show();
                break;
            }
            case 260: {
                SignInAlerts.databaseError(this).show();
                break;
            }
            default: {
                SignInAlerts.connectionError(this, response.getMessage()).show();
                break;
            }
        }
    }

}
