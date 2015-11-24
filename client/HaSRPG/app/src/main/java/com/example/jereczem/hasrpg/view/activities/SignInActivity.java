package com.example.jereczem.hasrpg.view.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.settings.ServerSettings;
import com.example.jereczem.hasrpg.view.logic.SignInLogic;


public class SignInActivity extends AppCompatActivity {

    private SignInLogic signInLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signInLogic = new SignInLogic(this);
    }

    public void signIn(View view){
        signInLogic.signInClick();
    }

    public void signUp(View view) {
        signInLogic.signUpClick();
    }

    public void clickServer1(View view) {
        ServerSettings.SERVER_URL = "http://192.168.1.128:8080/";
        ServerSettings.SOCKET_SERVER_URL = "http://192.168.1.128:3000/";
        Toast.makeText(this, "Settings changed to server 1", Toast.LENGTH_SHORT).show();
    }

    public void clickServer2(View view) {
        ServerSettings.SERVER_URL = "http://192.168.43.128:8080/";
        ServerSettings.SOCKET_SERVER_URL = "http://192.168.43.128:3000/";
        Toast.makeText(this, "Settings changed to server 2", Toast.LENGTH_SHORT).show();
    }
}
