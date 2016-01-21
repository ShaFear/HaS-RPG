package com.example.jereczem.hasrpg.sockets.events.disconnection;

import android.app.ProgressDialog;

import com.example.jereczem.hasrpg.data.player.PlayerData;
import com.example.jereczem.hasrpg.sockets.SocketServerConnector;
import com.example.jereczem.hasrpg.sockets.events.EventName;
import com.example.jereczem.hasrpg.sockets.events.HandShakeEvent;
import com.example.jereczem.hasrpg.view.activities.GameActivity;
import com.example.jereczem.hasrpg.view.events.ProgressDialogs;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by jereczem on 10.11.15.
 */

public class DisconnectionEvent extends HandShakeEvent<GameActivity> {
    ProgressDialog dialog;

    public DisconnectionEvent(JSONObject eventInformation, SocketServerConnector sConnector, GameActivity activity) {
        super(eventInformation, sConnector, activity);
    }

    @Override
    protected JSONObject getHandShakingEvent() throws JSONException {
        return new JSONObject().put("name", EventName.CONNECTION.name()).put("userID", eventInformation.get("userID"));
    }

    @Override
    protected void beforeHandShakeReaction(GameActivity activity) throws JSONException {
        Integer missedUser = eventInformation.getInt("userID");
        String login = "none";
        ArrayList<PlayerData> players = activity.lobby.getLobbyPlayers();
        for(PlayerData player : players)
            if(player.getUserID().equals(missedUser)){
                login = player.getLogin();
                break;
            }
        dialog = ProgressDialogs.waitingForUser(activity, login);
        try{
            dialog.show();
        }
        catch (Exception e){

        }
    }

    @Override
    protected void afterHandShakeReaction(GameActivity activity) throws JSONException {
        try{
                dialog.dismiss();
        }
        catch (Exception e){

        }
    }
}

