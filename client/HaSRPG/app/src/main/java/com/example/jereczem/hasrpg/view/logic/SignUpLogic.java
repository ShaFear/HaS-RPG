package com.example.jereczem.hasrpg.view.logic;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.networking.HttpResponse;
import com.example.jereczem.hasrpg.networking.HttpResponseReceiver;
import com.example.jereczem.hasrpg.networking.rest.RestException;
import com.example.jereczem.hasrpg.networking.rest.SignUpPoster;
import com.example.jereczem.hasrpg.view.dialogs.Alerts;
import com.example.jereczem.hasrpg.view.dialogs.SignUpAlerts;
import com.example.jereczem.hasrpg.view.toolbar.ToolbarSetter;

/**
 * Created by jereczem on 02.08.15.
 */
public class SignUpLogic {
    private AppCompatActivity a;

    public SignUpLogic(AppCompatActivity activity){
        this.a = activity;
        new ToolbarSetter(a, R.drawable.previous);

        final ProgressDialog progressDialog = new ProgressDialog(a);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);

        Button signInButton = (Button) a.findViewById(R.id.signup_button);
        signInButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    progressDialog.show();
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    signUpClick();
                    progressDialog.dismiss();
                }
                return false;
            }
        });
    }

    public void signUpClick(){
        EditText loginEditText = (EditText)a.findViewById(R.id.login_input);
        EditText passwordEditText = (EditText)a.findViewById(R.id.password_input);
        EditText repasswordEditText = (EditText)a.findViewById(R.id.repassword_input);
        String login = loginEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String repassword = repasswordEditText.getText().toString();
        login = login.replace(" ", ""); //TODO zrobic porzadnego regexa

        if(isInputDataOnClientSideValid(login, password, repassword))
            signUpNewUser(login, password);
    }

    private void signUpNewUser(String login, String password){
        try {
            HttpResponse response = SignUpPoster.getResponse(a, login, password);
            if(response.getCode().equals(200)){
                AlertDialog alertDialog = SignUpAlerts.userAdded(a);
                alertDialog.show();
                alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        a.finish();
                    }
                });
            }
        } catch (RestException e) {
            e.printStackTrace();
            e.getErrorAlert(a).show();
        }
    }

    private boolean isInputDataOnClientSideValid(String login, String password, String repassword) {
        if(login.isEmpty()|| password.isEmpty()|| repassword.isEmpty()){
            SignUpAlerts.emptyInput(a).show();
            return false;
        }
        if (!password.equals(repassword)) {
            SignUpAlerts.wrongRepassword(a).show();
            return false;
        }
        if ((login.length() > 32) || (login.length()  < 3)){
            SignUpAlerts.wrongLoginLenght(a).show();
            return false;
        }
        if ((password.length() > 32) || (password.length()  < 8)) {
            SignUpAlerts.wrongPasswordLenght(a).show();
            return false;
        }
        return true;
    }
}
