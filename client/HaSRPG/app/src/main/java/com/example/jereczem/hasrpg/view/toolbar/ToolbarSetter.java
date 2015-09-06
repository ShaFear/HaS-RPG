package com.example.jereczem.hasrpg.view.toolbar;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.jereczem.hasrpg.R;

/**
 * Created by jereczem on 03.08.15.
 */
public class ToolbarSetter {
    Toolbar toolbar;

    public ToolbarSetter(AppCompatActivity a){
        toolbar = (Toolbar) a.findViewById(R.id.tool_bar);
        a.setSupportActionBar(toolbar);
    }

    public ToolbarSetter(final AppCompatActivity a, Integer resourceID){
        toolbar = (Toolbar) a.findViewById(R.id.tool_bar);
        toolbar.setNavigationIcon(resourceID);
        a.setSupportActionBar(toolbar);
    }
}
