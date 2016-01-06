package com.example.jereczem.hasrpg.sockets.events.attack;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.jereczem.hasrpg.sockets.SocketServerConnector;
import com.example.jereczem.hasrpg.sockets.events.EventName;
import com.example.jereczem.hasrpg.sockets.events.HandShakeEvent;
import com.example.jereczem.hasrpg.sockets.events.NonHandShakeEvent;
import com.example.jereczem.hasrpg.view.activities.ChaseGameActivity;
import com.example.jereczem.hasrpg.view.activities.GameActivity;
import com.example.jereczem.hasrpg.view.activities.HunterGameActivity;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;


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
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void afterHandShakeReaction(AppCompatActivity activity) throws JSONException {
        if (activity instanceof ChaseGameActivity){
            ChaseGameActivity gameActivity = (ChaseGameActivity) activity;
            Integer attackedId = eventInformation.getInt("userID");
            if(gameActivity.playerData.getUserID().equals(attackedId)){
                gameActivity.handleWhenImKilled();
            } else{
                gameActivity.handleWhenOtherIsKilled(attackedId);
            }
        }
    }
}
