package com.example.jereczem.hasrpg.sockets.events.disconnection;

import android.app.ProgressDialog;

import com.example.jereczem.hasrpg.sockets.SocketServerConnector;
import com.example.jereczem.hasrpg.sockets.events.EventName;
import com.example.jereczem.hasrpg.sockets.events.HandShakeEvent;
import com.example.jereczem.hasrpg.view.activities.GameActivity;

import org.json.JSONException;
import org.json.JSONObject;

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
        return new JSONObject().put("name", EventName.CONNECTION.name()).put("userID", super.eventInformation.get("userID"));
    }

    @Override
    protected void beforeHandShakeReaction(GameActivity activity) throws JSONException {
        dialog = ProgressDialog.show(activity, super.eventInformation.getString("userID"), "waiting", true);
    }

    @Override
    protected void afterHandShakeReaction(GameActivity activity) throws JSONException {
        dialog.dismiss();
    }
}

