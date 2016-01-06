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

    //TODO: Magic strings
    public static ProgressDialog runTimeHunter(Activity activity, long seconds){
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setTitle("You're hunter!");
        progressDialog.setMessage("Your hunt will begin in " + seconds + " seconds. Please still in place.");
        progressDialog.setCancelable(false);
        return progressDialog;
    }

    //TODO: Magic strings
    public static void updateRunTimeHunter(ProgressDialog progressDialog, long seconds){
        progressDialog.setMessage("Your hunt will begin in " + seconds + " seconds. Please still in place.");
    }

    //TODO: Magic strings
    public static ProgressDialog runTimeChase(Activity activity, long seconds){
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setTitle("You're chase!");
        progressDialog.setMessage("You have to run for " + seconds + " seconds. ");
        progressDialog.setCancelable(false);
        return progressDialog;
    }

    //TODO: Magic strings
    public static void updateRunTimeChase(ProgressDialog progressDialog, long seconds){
        progressDialog.setMessage("You have to run for " + seconds + " seconds. ");
    }
}
