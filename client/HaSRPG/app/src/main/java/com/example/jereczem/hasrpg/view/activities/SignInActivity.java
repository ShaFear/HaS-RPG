package com.example.jereczem.hasrpg.view.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.networking.HttpResponse;
import com.example.jereczem.hasrpg.networking.HttpResponseReceiver;
import com.example.jereczem.hasrpg.view.dialogs.SignInAlerts;
import com.example.jereczem.hasrpg.view.logic.SignInLogic;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.ExecutionException;


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
}
