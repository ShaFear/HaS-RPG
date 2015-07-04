package com.example.jereczem.hasrpg.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * Created by jereczem on 01.07.15.
 */
public class MyAlerts {
    public static AlertDialog OK(Activity activity, String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
// Add the buttons
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        return alertDialog;
    }
}
