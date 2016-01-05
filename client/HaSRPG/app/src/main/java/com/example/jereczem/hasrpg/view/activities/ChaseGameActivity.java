package com.example.jereczem.hasrpg.view.activities;

import android.app.ProgressDialog;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.example.jereczem.hasrpg.R;

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

    public void handleRunTimeEvent(Integer seconds){
        if(runTimeProgressDialog == null){
            runTimeProgressDialog =  new ProgressDialog(this);
        }
        runTimeProgressDialog.setTitle("You're chase!");
        runTimeProgressDialog.setMessage("You have: " + seconds + "seconds to run away!");
        runTimeProgressDialog.setCancelable(false);
        if(!runTimeProgressDialog.isShowing())
            runTimeProgressDialog.show();
    }

    public void handleEndOfTimeEvent(){
        if(runTimeProgressDialog != null){
            if(runTimeProgressDialog.isShowing()) {
                runTimeProgressDialog.dismiss();
                Toast.makeText(this, "Time ends, please stay in place.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
