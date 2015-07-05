package com.example.jereczem.hasrpg.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.example.jereczem.hasrpg.R;

/**
 * Created by jereczem on 03.07.15.
 */
public class Alerts {
    public static AlertDialog wrongRepassword(Activity activity) {
        String wrong_repassword_input_title =
                activity.getResources().getString(R.string.wrong_repassword_input_title);
        String wrong_repassword_input_message =
                activity.getResources().getString(R.string.wrong_repassword_input_message);
        return MyAlerts.OK
                (activity, wrong_repassword_input_title, wrong_repassword_input_message);
    }

    public static AlertDialog wrongLoginLenght(Activity activity) {
        String wrong_login_lenght_title =
                activity.getResources().getString(R.string.wrong_login_lenght_title);
        String wrong_login_lenght_message =
                activity.getResources().getString(R.string.wrong_login_lenght_message);
        return MyAlerts.OK(
                activity, wrong_login_lenght_title, wrong_login_lenght_message);
    }

    public static AlertDialog wrongPasswordLenght(Activity activity) {
        String wrong_password_lenght_title =
                activity.getResources().getString(R.string.wrong_password_lenght_title);
        String wrong_password_lenght_message =
                activity.getResources().getString(R.string.wrong_password_lenght_message);
        return MyAlerts.OK
                (activity, wrong_password_lenght_title, wrong_password_lenght_message);
    }

    public static AlertDialog emptyInput(Activity activity){
        String empty_input_title =
                activity.getResources().getString(R.string.empty_input_title);
        String empty_input_message =
                activity.getResources().getString(R.string.empty_input_message);
        return MyAlerts.OK(activity, empty_input_title, empty_input_message);
    }

    public static AlertDialog errorAlert(Activity activity, String error_alert_message) {
        String error_alert_title =
                activity.getResources().getString(R.string.error_alert_title);
        return MyAlerts.OK(activity, error_alert_title, error_alert_message);
    }

    public static AlertDialog userAdded(Activity activity) {
        String user_added_title =
                activity.getResources().getString(R.string.user_added_title);
        String user_added_message=
                activity.getResources().getString(R.string.user_added_message);
        return MyAlerts.OK(activity, user_added_title, user_added_message);
    }

    public static AlertDialog userAlreadyExists(Activity activity) {
        String user_already_exists_title =
                activity.getResources().getString(R.string.user_already_exists_title);
        String user_already_exists_message=
                activity.getResources().getString(R.string.user_already_exists_message);
        return MyAlerts.OK(activity, user_already_exists_title, user_already_exists_message);
    }

    public static AlertDialog databaseError(Activity activity) {
        String db_error_title =
                activity.getResources().getString(R.string.db_error_title);
        String db_error_message=
                activity.getResources().getString(R.string.db_error_message);
        return MyAlerts.OK(activity, db_error_title, db_error_message);
    }
}
