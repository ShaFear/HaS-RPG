package com.example.jereczem.hasrpg.sockets.events.runtime;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.CountDownTimer;
import android.widget.Toast;

import com.example.jereczem.hasrpg.sockets.SocketServerConnector;
import com.example.jereczem.hasrpg.sockets.events.EventName;
import com.example.jereczem.hasrpg.sockets.events.HandShakeEvent;
import com.example.jereczem.hasrpg.view.activities.ChaseGameActivity;
import com.example.jereczem.hasrpg.view.activities.GameActivity;
import com.example.jereczem.hasrpg.view.activities.HunterGameActivity;
import com.example.jereczem.hasrpg.view.dialogs.Alerts;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Michał on 2016-01-05.
 */
public class RunTimeEvent extends HandShakeEvent<GameActivity>{

    public RunTimeEvent(JSONObject eventInformation, SocketServerConnector sConnector, GameActivity activity) {
        super(eventInformation, sConnector, activity);
    }

    @Override
    protected JSONObject getHandShakingEvent() throws JSONException {
        return new JSONObject().put("name", EventName.RUN_TIME.name()).put("status", "FINISHED");
    }

    @Override
    protected void beforeHandShakeReaction(GameActivity activity) throws JSONException {
        if (activity instanceof ChaseGameActivity) {
            ChaseGameActivity chaseActivity = (ChaseGameActivity) activity;
            Integer seconds = super.eventInformation.getInt("seconds");
            if(seconds > 0)
                chaseActivity.handleRunTimeEvent(seconds);
        }
    }

    @Override
    protected void afterHandShakeReaction(GameActivity activity) throws JSONException {
        if (activity instanceof ChaseGameActivity) {
            ChaseGameActivity chaseActivity = (ChaseGameActivity) activity;
            chaseActivity.handleEndOfTimeEvent();
        }
    }

    private static void sentEvent(SocketServerConnector sConnector, long seconds, String status) {
        Socket socket = sConnector.getSocket();
        JSONObject eventInformation = new JSONObject();
        try {
            eventInformation.put("name", EventName.RUN_TIME.name());
            eventInformation.put("seconds", seconds);
            eventInformation.put("status", status);
            socket.emit("sentEvent", eventInformation);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void setRunTimer(final SocketServerConnector sConnector, Integer seconds,final HunterGameActivity activity){
        final ProgressDialog dialog = new ProgressDialog(activity);
        CountDownTimer countDownTimer = new CountDownTimer(seconds * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                dialog.setTitle("You're hunter!");
                dialog.setMessage("Your hunt will begin in " + millisUntilFinished/1000 + " seconds. Please still in place.");
                dialog.setCancelable(false);
                if(!dialog.isShowing())
                    dialog.show();
                if(millisUntilFinished/1000 > 0)
                    sentEvent(sConnector, millisUntilFinished / 1000, "RUNNING");
            }

            @Override
            public void onFinish() {
                dialog.dismiss();
                sentEvent(sConnector, 0, "FINISHED");
                Alerts.DialogGenerator.generateSimpleOKAlert(activity, "The hunting begins!", "You can hunt your chases now!").show();
            }
        };
        countDownTimer.start();
    }
}
