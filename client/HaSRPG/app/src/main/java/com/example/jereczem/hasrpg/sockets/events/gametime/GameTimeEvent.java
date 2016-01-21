package com.example.jereczem.hasrpg.sockets.events.gametime;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.playgame.GameStatus;
import com.example.jereczem.hasrpg.sockets.SocketServerConnector;
import com.example.jereczem.hasrpg.sockets.events.EventName;
import com.example.jereczem.hasrpg.sockets.events.NonHandShakeEvent;
import com.example.jereczem.hasrpg.view.activities.ChaseGameActivity;
import com.example.jereczem.hasrpg.view.activities.ChaseResultActivity;
import com.example.jereczem.hasrpg.view.activities.GameActivity;
import com.example.jereczem.hasrpg.view.activities.HunterResultActivity;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Michał on 2016-01-06.
 */
public class GameTimeEvent  extends NonHandShakeEvent<GameActivity>{
    public GameTimeEvent(JSONObject eventInformation, SocketServerConnector sConnector, GameActivity activity) {
        super(eventInformation, sConnector, activity);
    }

    @Override
    protected void afterHandShakeReaction(GameActivity activity) throws JSONException {
        if (activity instanceof ChaseGameActivity) {
            Integer seconds = super.eventInformation.getInt("seconds");
            activity.getGameData().setGameTime(seconds);
            if(seconds <= 0) {
                activity.getGameData().setStatus(GameStatus.CHASE_WINS);
                ChaseResultActivity.openChaseResultActivity((ChaseGameActivity)activity);
            }
        }
    }

    public static void sentGameTimeEvent(SocketServerConnector sConnector, long seconds){
        Socket socket = sConnector.getSocket();
        JSONObject eventInformation = new JSONObject();
        try {
            eventInformation.put("name", EventName.GAME_TIME.name());
            eventInformation.put("seconds", seconds);
            socket.emit("sentEvent", eventInformation);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
