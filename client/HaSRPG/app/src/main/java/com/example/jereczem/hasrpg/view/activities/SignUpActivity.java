package com.example.jereczem.hasrpg.view.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.networking.HttpResponseReceiver;
import com.example.jereczem.hasrpg.view.dialogs.Alerts;
import com.example.jereczem.hasrpg.networking.HttpResponse;
import com.example.jereczem.hasrpg.view.dialogs.SignUpAlerts;
import com.example.jereczem.hasrpg.view.logic.SignUpLogic;

import java.util.concurrent.ExecutionException;


public class SignUpActivity extends AppCompatActivity {

    private SignUpLogic signUpLogic;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signUpLogic = new SignUpLogic(this);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
    }

    public void signUp(View view) {
        signUpLogic.signUpClick();
    }
}
