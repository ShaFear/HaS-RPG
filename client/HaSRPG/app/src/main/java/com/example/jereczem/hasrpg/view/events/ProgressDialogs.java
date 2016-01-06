package com.example.jereczem.hasrpg.view.events;

import android.app.Activity;
import android.app.ProgressDialog;

import com.example.jereczem.hasrpg.R;

/**
 * Created by Michał on 2016-01-06.
 */
public class ProgressDialogs {
    public static ProgressDialog waitingForUser(Activity activity, String userName){
        String title = activity.getString(R.string.wait_for_user_title);
        String message = activity.getString(R.string.wait_for_user_message);
        message = message.replace("[magic]", userName);
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setCancelable(false);
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        return progressDialog;
    }
}
