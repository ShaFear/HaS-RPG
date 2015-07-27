package com.example.jereczem.hasrpg.view.dialogs;

import android.app.Activity;
import android.app.AlertDialog;

import com.example.jereczem.hasrpg.R;

/**
 * Created by jereczem on 03.07.15.
 */
public class SignInAlerts extends Alerts{
    public static AlertDialog userLogged(Activity activity) {
        String user_logged_title =
                activity.getResources().getString(R.string.user_logged_title);
        String user_logged_message=
                activity.getResources().getString(R.string.user_logged_message);
        return DialogGenerator.generateSimpleOKAlert(activity, user_logged_title, user_logged_message);
    }

    public static AlertDialog wrongLoginAndPassword(Activity activity) {
        String wrong_login_and_password_title =
                activity.getResources().getString(R.string.wrong_login_and_password_title);
        String wrong_login_and_password_message=
                activity.getResources().getString(R.string.wrong_login_and_password_message);
        return DialogGenerator.generateSimpleOKAlert
                (activity, wrong_login_and_password_title, wrong_login_and_password_message);
    }
}


