package com.example.jereczem.hasrpg.dialog;

import android.app.Activity;
import android.app.AlertDialog;

/**
 * Created by jereczem on 01.07.15.
 */
public class SimpleAlert {
    public SimpleAlert(Activity activity, String title, String message){
        AlertDialog alertDialog= new AlertDialog.Builder(activity).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.show();
    }
}
