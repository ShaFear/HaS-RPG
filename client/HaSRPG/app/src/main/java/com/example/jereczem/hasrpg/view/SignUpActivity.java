package com.example.jereczem.hasrpg.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.dialog.Alerts;
import com.example.jereczem.hasrpg.dialog.MyAlerts;
import com.example.jereczem.hasrpg.http.HttpUtils;
import com.example.jereczem.hasrpg.http.Response;
import com.example.jereczem.hasrpg.settings.G;

import java.io.IOException;


public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void signUp(View view){
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

    private void signUpNewUser(String login, String password) {
        String url = G.SERVER_URL +"users";
        StringBuilder params = new StringBuilder()
                .append("login=").append(login)
                .append("&password=").append(password);

        new SignUpTask().execute(url, params.toString(), this);
    }

    private boolean isInputDataOnClientSideValid(String login, String password, String repassword) {
        if(login.isEmpty()|| password.isEmpty()|| repassword.isEmpty()){
            Alerts.emptyInput(this).show();
            return false;
        }
        if (!password.equals(repassword)) {
            Alerts.wrongRepassword(this).show();
            return false;
        }
        if ((login.length() > 32) || (login.length()  < 3)){
            Alerts.wrongLoginLenght(this).show();
            return false;
        }
        if ((password.length() > 32) || (password.length()  < 8)) {
            Alerts.wrongPasswordLenght(this).show();
            return false;
        }
        return true;
    }

    private class SignUpTask extends AsyncTask<Object, Void, Response>{
        private Activity activity;

        @Override
        protected Response doInBackground(Object... params) {
            String url = (String)params[0];
            String urlParameters = (String)params[1];
            activity = (Activity)params[2];

            try {
                return HttpUtils.POST(url, urlParameters);
            } catch (IOException e) {
                return new Response(500, e.getMessage());
            }
        }

        protected void onPostExecute(final Response result) {
                    switch (result.getCode()){
                        case 200:{
                            AlertDialog alertDialog = Alerts.userAdded(activity);
                            alertDialog.show();
                            alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                @Override
                                public void onDismiss(DialogInterface dialog) {
                                    activity.finish();
                                }
                            });
                            break;
                        }
                        case 256:{
                            Alerts.wrongLoginLenght(activity).show(); break;
                        }
                        case 257:{
                            Alerts.wrongPasswordLenght(activity).show(); break;
                        }
                        case 258:{
                            Alerts.emptyInput(activity).show(); break;
                        }
                        case 259:{
                            Alerts.userAlreadyExists(activity).show(); break;
                        }
                        case 260:{
                            Alerts.databaseError(activity); break;
                        }
                        default:{
                            Alerts.errorAlert(activity, result.getMessage()).show();
                            //TODO uzytkownik nie powinien widziec tak dokladnej wiadomosci
                            //bo sie przestraszy ;___;
                        }
                    }
        }
    }
}
