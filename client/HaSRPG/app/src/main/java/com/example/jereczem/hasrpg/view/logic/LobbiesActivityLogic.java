package com.example.jereczem.hasrpg.view.logic;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import com.example.jereczem.hasrpg.settings.SerializableTags;
import com.example.jereczem.hasrpg.data.PlayerData;
import com.example.jereczem.hasrpg.data.PlayerDataReceiver;
import com.example.jereczem.hasrpg.networking.HttpResponse;
import com.example.jereczem.hasrpg.networking.HttpResponseReceiver;
import com.example.jereczem.hasrpg.view.activities.CharacterSelectActivity;
import com.example.jereczem.hasrpg.view.dialogs.Alerts;

/**
 * Created by jereczem on 02.08.15.
 */
public class LobbiesActivityLogic {
    private Activity a;
    private PlayerData playerData;

    public LobbiesActivityLogic(Activity activity){
        this.a = activity;
    }

    public void downloadPlayerData() {
        HttpResponse response = new HttpResponseReceiver("mycharacters").receive();
        if (response.getCode().equals(200)) {
            playerData = PlayerDataReceiver.fromString(response.getMessage());
        } else {
            AlertDialog alertDialog = Alerts.connectionError(a, response.getMessage());
            alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    downloadPlayerData();
                }
            });
            alertDialog.show();
        }
    }

    public void moveTaskToBack() {
        a.moveTaskToBack(true);
    }

    public void selectCharactersClick() {
        Intent intent = new Intent(a, CharacterSelectActivity.class);
        intent.putExtra(SerializableTags.PLAYER_DATA, playerData);
        a.startActivity(intent);
    }
}
