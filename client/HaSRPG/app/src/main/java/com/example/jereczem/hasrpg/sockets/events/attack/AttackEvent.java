package com.example.jereczem.hasrpg.sockets.events.attack;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.jereczem.hasrpg.playgame.ChaseData;
import com.example.jereczem.hasrpg.playgame.ChaseStatus;
import com.example.jereczem.hasrpg.playgame.GameStatus;
import com.example.jereczem.hasrpg.sockets.SocketServerConnector;
import com.example.jereczem.hasrpg.sockets.events.EventName;
import com.example.jereczem.hasrpg.sockets.events.HandShakeEvent;
import com.example.jereczem.hasrpg.sockets.events.NonHandShakeEvent;
import com.example.jereczem.hasrpg.view.activities.ChaseGameActivity;
import com.example.jereczem.hasrpg.view.activities.ChaseResultActivity;
import com.example.jereczem.hasrpg.view.activities.GameActivity;
import com.example.jereczem.hasrpg.view.activities.HunterGameActivity;
import com.example.jereczem.hasrpg.view.activities.HunterResultActivity;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;


/**
 * Created by Michał on 2016-01-06.
 */
public class AttackEvent extends NonHandShakeEvent<GameActivity> {

    public AttackEvent(JSONObject eventInformation, SocketServerConnector sConnector, GameActivity activity) {
        super(eventInformation, sConnector, activity);
    }

    public static void hunterSendsAttack(SocketServerConnector sConnector, HunterGameActivity activity, Integer userID){
        Socket socket = sConnector.getSocket();
        JSONObject eventInformation = new JSONObject();
        try {
            eventInformation.put("name", EventName.ATTACK_EVENT.name());
            eventInformation.put("userID", userID);
            socket.emit("sentEvent", eventInformation);
            activity.getGameData().getChases().get(userID).setStatus(ChaseStatus.DEAD);

            /**
             * Check if all chases are DEAD, if so, than goto Result
             */
            for (Map.Entry<Integer, ChaseData> entry : activity.getGameData().getChases().entrySet()) {
                if(entry.getValue().getStatus().equals(ChaseStatus.ALIVE))
                    return;
            }
            activity.getGameData().setStatus(GameStatus.HUNTER_WINS);
            activity.stopGameTimeTimer();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void afterHandShakeReaction(GameActivity activity) throws JSONException {
        if (activity instanceof ChaseGameActivity){
            ChaseGameActivity gameActivity = (ChaseGameActivity) activity;
            Integer userID = eventInformation.getInt("userID");
            ChaseData attackedChase = gameActivity.getGameData().getChases().get(userID);
            attackedChase.setStatus(ChaseStatus.DEAD);
            Toast.makeText(gameActivity, attackedChase.getName() + " was killed!", Toast.LENGTH_LONG).show();

            /**
             * Check if all chases are DEAD, if so, than goto Result
             */
            for (Map.Entry<Integer, ChaseData> entry : gameActivity.getGameData().getChases().entrySet()) {
                if(entry.getValue().getStatus().equals(ChaseStatus.ALIVE))
                    return;
            }
            gameActivity.getGameData().setStatus(GameStatus.HUNTER_WINS);
        }
    }
}
