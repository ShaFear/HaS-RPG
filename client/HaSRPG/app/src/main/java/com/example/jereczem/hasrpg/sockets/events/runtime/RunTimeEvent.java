package com.example.jereczem.hasrpg.sockets.events.runtime;

import android.os.CountDownTimer;

import com.example.jereczem.hasrpg.playgame.GameStatus;
import com.example.jereczem.hasrpg.sockets.SocketServerConnector;
import com.example.jereczem.hasrpg.sockets.events.EventName;
import com.example.jereczem.hasrpg.sockets.events.HandShakeEvent;
import com.example.jereczem.hasrpg.view.activities.ChaseGameActivity;
import com.example.jereczem.hasrpg.view.activities.GameActivity;
import com.example.jereczem.hasrpg.view.activities.HunterGameActivity;
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
            Integer seconds = eventInformation.getInt("seconds");
            activity.getGameData().setRunTime(seconds);
        }
    }

    @Override
    protected void afterHandShakeReaction(GameActivity activity) throws JSONException {
        if (activity instanceof ChaseGameActivity) {
            activity.getGameData().setRunTime(0);
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
        CountDownTimer countDownTimer = new CountDownTimer(seconds * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(millisUntilFinished/1000 > 0) {
                    activity.getGameData().setRunTime(millisUntilFinished / 1000);
                    sentEvent(sConnector, millisUntilFinished / 1000, "RUNNING");
                }
            }

            @Override
            public void onFinish() {
                sentEvent(sConnector, 0, "FINISHED");
                activity.getGameData().setRunTime(0);
                activity.startGameStartTimer();
            }
        };
        countDownTimer.start();
    }
}
