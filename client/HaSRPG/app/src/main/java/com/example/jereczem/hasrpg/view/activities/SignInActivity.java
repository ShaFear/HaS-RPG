package com.example.jereczem.hasrpg.view.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.networking.HttpConnection;
import com.example.jereczem.hasrpg.networking.HttpResponse;
import com.example.jereczem.hasrpg.settings.ServerSettings;
import com.example.jereczem.hasrpg.view.dialogs.SignInAlerts;

import java.io.IOException;
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

        String url = ServerSettings.SERVER_URL + "signin";
        StringBuilder params = new StringBuilder()
                .append("login=").append(login)
                .append("&password=").append(password);

        HttpResponse result = new SignInTask().execute(url, params.toString()).get();
        handleSignInResponse(result);
    }

    public void signUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    private class SignInTask extends AsyncTask<Object, Void, HttpResponse> {
        @Override
        protected HttpResponse doInBackground(Object... params) {
            String url = (String) params[0];
            String urlParameters = (String) params[1];
            try {
                return HttpConnection.post(url, urlParameters);
            } catch (IOException e) {
                return new HttpResponse(500, e.toString());
            }
        }
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
                SignInAlerts.otherError(this, response.getMessage()).show();
                break;
            }
        }
    }

}
