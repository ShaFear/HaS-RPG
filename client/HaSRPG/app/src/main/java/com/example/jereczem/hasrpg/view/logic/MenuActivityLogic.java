package com.example.jereczem.hasrpg.view.logic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.settings.SerializableTags;
import com.example.jereczem.hasrpg.data.PlayerData;
import com.example.jereczem.hasrpg.view.activities.CharacterSelectFragment;
import com.example.jereczem.hasrpg.view.toolbar.ToolbarSetter;

/**
 * Created by jereczem on 02.08.15.
 */
public class MenuActivityLogic {
    private AppCompatActivity a;
    private PlayerData playerData;

    public MenuActivityLogic(AppCompatActivity activity){
        this.a = activity;
        new ToolbarSetter(a, R.drawable.menu_icon);
    }

    public void moveTaskToBack() {
        a.moveTaskToBack(true);
    }

    public void selectCharactersClick() {
        Intent intent = new Intent(a, CharacterSelectFragment.class);
        intent.putExtra(SerializableTags.PLAYER_DATA, playerData);
        a.startActivity(intent);
    }
}
