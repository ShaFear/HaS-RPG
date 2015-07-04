package com.example.jereczem.hasrpg.dialog;

import android.app.Activity;

import com.example.jereczem.hasrpg.R;

/**
 * Created by jereczem on 03.07.15.
 */
public class Alerts {
    public static void wrongRepassword(Activity activity) {
        String wrong_repassword_input_title =
                activity.getResources().getString(R.string.wrong_repassword_input_title);
        String wrong_repassword_input_message =
                activity.getResources().getString(R.string.wrong_repassword_input_message);
        MyAlerts.OK
                (activity, wrong_repassword_input_title, wrong_repassword_input_message).show();
    }

    public static void wrongLoginLenght(Activity activity) {
        String wrong_login_lenght_title =
                activity.getResources().getString(R.string.wrong_login_lenght_title);
        String wrong_login_lenght_message =
                activity.getResources().getString(R.string.wrong_login_lenght_message);
        MyAlerts.OK(
                activity, wrong_login_lenght_title, wrong_login_lenght_message).show();
    }

    public static void wrongPasswordLenght(Activity activity) {
        String wrong_password_lenght_title =
                activity.getResources().getString(R.string.wrong_password_lenght_title);
        String wrong_password_lenght_message =
                activity.getResources().getString(R.string.wrong_password_lenght_message);
        MyAlerts.OK
                (activity, wrong_password_lenght_title, wrong_password_lenght_message).show();
    }

    public static void emptyInput(Activity activity){
        String empty_input_title =
                activity.getResources().getString(R.string.empty_input_title);
        String empty_input_message =
                activity.getResources().getString(R.string.empty_input_message);
        MyAlerts.OK(activity, empty_input_title, empty_input_message).show();
    }

    public static void errorAlert(Activity activity, Exception e) {
        String error_alert_title =
                activity.getResources().getString(R.string.error_alert_title);
        MyAlerts.OK(activity, error_alert_title, e.getMessage()).show();
    }
}
