package com.example.jereczem.hasrpg.view.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.view.dialogs.Alerts;
import com.example.jereczem.hasrpg.networking.HttpConnection;
import com.example.jereczem.hasrpg.networking.HttpResponse;
import com.example.jereczem.hasrpg.settings.ServerSettings;
import com.example.jereczem.hasrpg.view.dialogs.SignUpAlerts;

import java.io.IOException;
import java.util.concurrent.ExecutionException;


public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void signUp(View view) throws ExecutionException, InterruptedException {
        EditText loginEditText = (EditText)findViewById(R.id.login_input);
        EditText passwordEditText = (EditText)findViewById(R.id.password_input);
        EditText repasswordEditText = (EditText)findViewById(R.id.repassword_input);
        String login = loginEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String repassword = repasswordEditText.getText().toString();
        login = login.replace(" ", ""); //TODO zrobic porzadnego regexa

        if(isInputDataOnClientSideValid(login, password, repassword))
            signUpNewUser(login, password);
    }

    private void signUpNewUser(String login, String password) throws ExecutionException, InterruptedException {
        String url = ServerSettings.SERVER_URL +"users";
        StringBuilder params = new StringBuilder()
                .append("login=").append(login)
                .append("&password=").append(password);

        HttpResponse response = new SignUpTask().execute(url, params.toString()).get();
        handleSignUpResponse(response);
    }

    private boolean isInputDataOnClientSideValid(String login, String password, String repassword) {
        if(login.isEmpty()|| password.isEmpty()|| repassword.isEmpty()){
            SignUpAlerts.emptyInput(this).show();
            return false;
        }
        if (!password.equals(repassword)) {
            SignUpAlerts.wrongRepassword(this).show();
            return false;
        }
        if ((login.length() > 32) || (login.length()  < 3)){
            SignUpAlerts.wrongLoginLenght(this).show();
            return false;
        }
        if ((password.length() > 32) || (password.length()  < 8)) {
            SignUpAlerts.wrongPasswordLenght(this).show();
            return false;
        }
        return true;
    }

    private class SignUpTask extends AsyncTask<Object, Void, HttpResponse>{
        @Override
        protected HttpResponse doInBackground(Object... params) {
            String url = (String)params[0];
            String urlParameters = (String)params[1];
            try {
                return HttpConnection.post(url, urlParameters);
            } catch (IOException e) {
                return new HttpResponse(500, e.getMessage());
            }
        }
    }

    private void handleSignUpResponse(final HttpResponse response) {
        switch (response.getCode()){
            case 200:{
                AlertDialog alertDialog = SignUpAlerts.userAdded(this);
                alertDialog.show();
                alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        finish();
                    }
                });
                break;
            }
            case 256:{
                SignUpAlerts.wrongLoginLenght(this).show(); break;
            }
            case 257:{
                SignUpAlerts.wrongPasswordLenght(this).show(); break;
            }
            case 258:{
                SignUpAlerts.emptyInput(this).show(); break;
            }
            case 259:{
                SignUpAlerts.userAlreadyExists(this).show(); break;
            }
            case 260:{
                Alerts.databaseError(this); break;
            }
            default:{
                Alerts.otherError(this, response.getMessage()).show();
                //TODO uzytkownik nie powinien widziec tak dokladnej wiadomosci
                //bo sie przestraszy ;___;
            }
        }
    }
}
