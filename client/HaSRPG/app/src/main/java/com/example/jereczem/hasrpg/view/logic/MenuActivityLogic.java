package com.example.jereczem.hasrpg.view.logic;

import android.support.v7.app.AppCompatActivity;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.view.toolbar.ToolbarSetter;

/**
 * Created by jereczem on 02.08.15.
 */
public class MenuActivityLogic {
    private AppCompatActivity a;

    public MenuActivityLogic(AppCompatActivity activity){
        this.a = activity;
        new ToolbarSetter(a, R.drawable.menu_icon);
    }

    public void moveTaskToBack() {
        a.moveTaskToBack(true);
    }
}
