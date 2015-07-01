package com.example.jereczem.hasrpg;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.jereczem.hasrpg.dialog.SimpleAlert;


public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void signIn(View view){
        if(isLoginAndPasswordCorrect()){
            //TODO [GET] domain.com/yourplay - sprawdza czy uzytkownik jest w aktywnej grze
            if(true){
                //TODO otworz LobbiesActivity
            }
            else{
                //TODO otworz YourPlayActivity
            }
        }else{
            new SimpleAlert(this,
                    getString(R.string.wrong_login_or_password_title),
                    getString(R.string.wrong_login_or_password_message));
        }
    }

    public void signUp(View view){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    private boolean isLoginAndPasswordCorrect() {
        EditText loginEditText = (EditText)findViewById(R.id.login_input);
        EditText passwordEditText = (EditText)findViewById(R.id.password_input);
        String login = loginEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        //TODO [POST] domain.com/signin
        if(login.equals("admin"))
            return true;
        return false;
    }
}
