package com.example.jereczem.hasrpg.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.dialog.Alerts;
import com.example.jereczem.hasrpg.dialog.MyAlerts;
import com.example.jereczem.hasrpg.http.HttpUtils;
import com.example.jereczem.hasrpg.http.Response;

import java.io.IOException;


public class SignUpActivity extends ActionBarActivity {

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

        if(login.isEmpty()|| password.isEmpty()|| repassword.isEmpty()){
            Alerts.emptyInput(this);
            return;
        }
        if (!password.equals(repassword)) {
            Alerts.wrongRepassword(this);
            return;
        }
        if ((login.length() > 32) || (login.length()  < 3)){
            Alerts.wrongLoginLenght(this);
            return;
        }
        if ((password.length() > 32) || (password.length()  < 8)) {
            Alerts.wrongPasswordLenght(this);
            return;
        }

        String url = "http://192.168.43.128/users";
        StringBuilder params = new StringBuilder()
                .append("login=").append(login)
                .append("&password=").append(password);

        new SignUpTask().execute(url, params.toString(), this);
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
            AlertDialog alertDialog =
                    MyAlerts.OK
                            (activity, result.getCode().toString(), result.getMessage());
            alertDialog.show();
            alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    if(result.getCode().equals(200))
                        activity.finish();
                }
            });
        }
    }
}
