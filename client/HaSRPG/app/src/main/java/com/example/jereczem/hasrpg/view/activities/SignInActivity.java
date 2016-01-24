package com.example.jereczem.hasrpg.view.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.settings.ServerSettings;
import com.example.jereczem.hasrpg.view.activities.uitest.UiTestActivity;
import com.example.jereczem.hasrpg.view.logic.SignInLogic;


public class SignInActivity extends AppCompatActivity {

    private SignInLogic signInLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signInLogic = new SignInLogic(this);
    }



    public void signUp(View view) {
        signInLogic.signUpClick();
    }

    private class OnClickTask extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... params) {
            signInLogic.signInClick();
            return null;
        }
    }
}
