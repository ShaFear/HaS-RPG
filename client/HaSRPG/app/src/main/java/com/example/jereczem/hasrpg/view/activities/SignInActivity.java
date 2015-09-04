package com.example.jereczem.hasrpg.view.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jereczem.hasrpg.R;
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
}
