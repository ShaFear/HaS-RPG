package com.example.jereczem.hasrpg.view.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.example.jereczem.hasrpg.R;

/**
 * Created by jereczem on 03.07.15.
 */
public class SignUpAlerts extends Alerts{

    public static AlertDialog wrongRepassword(Activity activity) {
        String wrong_repassword_input_title =
                activity.getResources().getString(R.string.wrong_repassword_input_title);
        String wrong_repassword_input_message =
                activity.getResources().getString(R.string.wrong_repassword_input_message);
        return DialogGenerator.generateSimpleOKAlert
                (activity, wrong_repassword_input_title, wrong_repassword_input_message);
    }

    public static AlertDialog wrongLoginLenght(Activity activity) {
        String wrong_login_lenght_title =
                activity.getResources().getString(R.string.wrong_login_lenght_title);
        String wrong_login_lenght_message =
                activity.getResources().getString(R.string.wrong_login_lenght_message);
        return DialogGenerator.generateSimpleOKAlert(
                activity, wrong_login_lenght_title, wrong_login_lenght_message);
    }

    public static AlertDialog wrongPasswordLenght(Activity activity) {
        String wrong_password_lenght_title =
                activity.getResources().getString(R.string.wrong_password_lenght_title);
        String wrong_password_lenght_message =
                activity.getResources().getString(R.string.wrong_password_lenght_message);
        return DialogGenerator.generateSimpleOKAlert
                (activity, wrong_password_lenght_title, wrong_password_lenght_message);
    }

    public static AlertDialog emptyInput(Activity activity){
        String empty_input_title =
                activity.getResources().getString(R.string.empty_input_title);
        String empty_input_message =
                activity.getResources().getString(R.string.empty_input_message);
        return DialogGenerator.generateSimpleOKAlert(activity, empty_input_title, empty_input_message);
    }

    public static AlertDialog userAdded(Activity activity) {
        String user_added_title =
                activity.getResources().getString(R.string.user_added_title);
        String user_added_message=
                activity.getResources().getString(R.string.user_added_message);
        return DialogGenerator.generateSimpleOKAlert(activity, user_added_title, user_added_message);
    }

    public static AlertDialog userAlreadyExists(Activity activity) {
        String user_already_exists_title =
                activity.getResources().getString(R.string.user_already_exists_title);
        String user_already_exists_message=
                activity.getResources().getString(R.string.user_already_exists_message);
        return DialogGenerator.generateSimpleOKAlert(activity, user_already_exists_title, user_already_exists_message);
    }
}


