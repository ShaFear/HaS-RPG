package com.example.jereczem.hasrpg.view.dialogs;

import android.app.Activity;
import android.app.AlertDialog;

import com.example.jereczem.hasrpg.R;

/**
 * Created by jereczem on 03.07.15.
 */
public class CharacterSelectAlerts extends Alerts{
    public static AlertDialog charactersSaved(Activity activity) {
        String characters_saved_title =
                activity.getResources().getString(R.string.characters_saved_title);
        String characters_saved_message=
                activity.getResources().getString(R.string.characters_saved_message);
        return DialogGenerator.generateSimpleOKAlert(activity, characters_saved_title,
                characters_saved_message);
    }
}


