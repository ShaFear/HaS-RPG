package com.example.jereczem.hasrpg.view.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.view.dialogs.Alerts;

public class ChaseGameActivity extends GameActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chase_game);
    }

    @Override
    public void onLocationChanged(Location location) {
        super.onLocationChanged(location);
        Toast.makeText(this, "Location changed to: " + location.toString(), Toast.LENGTH_SHORT).show();
    }

    ProgressDialog runTimeProgressDialog;
    Boolean wasRunTimeHandled = false;

    public void handleRunTimeEvent(Integer seconds){
        if(runTimeProgressDialog == null){
            runTimeProgressDialog =  new ProgressDialog(this);
            runTimeProgressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                }
            });
        }
        runTimeProgressDialog.setTitle("You're chase!");
        runTimeProgressDialog.setMessage("You have: " + seconds + " seconds to run away!");
        runTimeProgressDialog.setCancelable(false);
        if(!runTimeProgressDialog.isShowing())
            runTimeProgressDialog.show();
    }

    public void handleEndOfTimeEvent(){
        if(runTimeProgressDialog != null && wasRunTimeHandled == false){
            if(runTimeProgressDialog.isShowing()) {
                runTimeProgressDialog.dismiss();
                wasRunTimeHandled = true;
                Alerts.DialogGenerator.generateSimpleOKAlert(this, "Time ends", "Time ends, please stay in place.").show();
            }
        }
    }
}
