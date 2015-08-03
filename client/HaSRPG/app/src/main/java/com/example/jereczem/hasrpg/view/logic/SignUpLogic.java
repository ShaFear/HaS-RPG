package com.example.jereczem.hasrpg.view.logic;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.networking.HttpResponse;
import com.example.jereczem.hasrpg.networking.HttpResponseReceiver;
import com.example.jereczem.hasrpg.view.dialogs.Alerts;
import com.example.jereczem.hasrpg.view.dialogs.SignUpAlerts;
import com.example.jereczem.hasrpg.view.drawer.DrawerLogic;
import com.example.jereczem.hasrpg.view.toolbar.ToolbarSetter;

import java.util.concurrent.ExecutionException;

/**
 * Created by jereczem on 02.08.15.
 */
public class SignUpLogic {
    private AppCompatActivity a;

    public SignUpLogic(AppCompatActivity activity){
        this.a = activity;
        new ToolbarSetter(a, R.drawable.previous);
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
        HttpResponseReceiver httpResponseReceiver = new HttpResponseReceiver("users");
        httpResponseReceiver.addParameter("login", login);
        httpResponseReceiver.addParameter("password", password);
        HttpResponse response = httpResponseReceiver.receive();
        handleSignUpResponse(response);
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

    private void handleSignUpResponse(final HttpResponse response) {
        switch (response.getCode()){
            case 200:{
                AlertDialog alertDialog = SignUpAlerts.userAdded(a);
                alertDialog.show();
                alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        a.finish();
                    }
                });
                break;
            }
            case 256:{
                SignUpAlerts.wrongLoginLenght(a).show(); break;
            }
            case 257:{
                SignUpAlerts.wrongPasswordLenght(a).show(); break;
            }
            case 258:{
                SignUpAlerts.emptyInput(a).show(); break;
            }
            case 259:{
                SignUpAlerts.userAlreadyExists(a).show(); break;
            }
            case 260:{
                Alerts.databaseError(a); break;
            }
            default:{
                Alerts.connectionError(a, response.getMessage()).show();
                //TODO uzytkownik nie powinien widziec tak dokladnej wiadomosci
            }
        }
    }
}
