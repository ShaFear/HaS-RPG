package com.example.jereczem.hasrpg.view.dialogs;

import android.app.Activity;
import android.app.AlertDialog;

import com.example.jereczem.hasrpg.R;

/**
 * Created by jereczem on 04.09.15.
 */
public class LobbyAlerts extends Alerts{
    public static AlertDialog wrongDataError(Activity activity) {
        String wrong_data_title =
                activity.getResources().getString(R.string.wrong_data_title);
        String wrong_data_message=
                activity.getResources().getString(R.string.wrong_data_message);
        return DialogGenerator.generateSimpleOKAlert(activity, wrong_data_title, wrong_data_message);
    }
}
