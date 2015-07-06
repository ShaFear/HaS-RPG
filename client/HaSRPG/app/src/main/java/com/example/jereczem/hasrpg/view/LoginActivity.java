package com.example.jereczem.hasrpg.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void signIn(View view){
        Intent intent = new Intent(this, LobbiesActivity.class);
        startActivity(intent);
        /*
        EditText loginEditText = (EditText)findViewById(R.id.login_input);
        EditText passwordEditText = (EditText)findViewById(R.id.password_input);
        String login = loginEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        login = login.replace(" ", ""); //TODO zrobic porzadnego regexa

        String url = G.SERVER_URL +"signin";
        StringBuilder params = new StringBuilder()
                .append("login=").append(login)
                .append("&password=").append(password);

        new SignInTask().execute(url, params.toString(), this);
        */
    }

    public void signUp(View view){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    private class SignInTask extends AsyncTask<Object, Void, Response> {
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
                    AlertDialog alertDialog = Alerts.userLogged(activity);
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
                    Alerts.wrongLoginAndPasswordAlert(activity).show();
                    break;
                }
                default:{
                    Alerts.errorAlert(activity, result.getMessage()).show();
                    // TODO zmien bo strach
                }
            }
        }
    }

}
