package com.example.jereczem.hasrpg.view.activities;

import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.example.jereczem.hasrpg.R;

public class HunterGameActivity extends GameActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hunter_game);
    }

    @Override
    public void onLocationChanged(Location location) {
        super.onLocationChanged(location);
        Toast.makeText(this, "Location changed to: " + location.toString(), Toast.LENGTH_SHORT).show();
    }
}
