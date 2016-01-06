package com.example.jereczem.hasrpg.sockets.events;

import android.support.v7.app.AppCompatActivity;

import com.example.jereczem.hasrpg.sockets.SocketServerConnector;
import com.example.jereczem.hasrpg.view.activities.GameActivity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jereczem on 10.11.15.
 */
public abstract class NonHandShakeEvent<T extends GameActivity> extends HandShakeEvent{

    public NonHandShakeEvent(JSONObject eventInformation, SocketServerConnector sConnector, T activity) {
        super(eventInformation, sConnector, activity);
    }

    @Override
    protected JSONObject getHandShakingEvent() throws JSONException {
        return null;
    }

    @Override
    protected void beforeHandShakeReaction(GameActivity activity) throws JSONException {

    }

    public void runEvent(){
        EventTask eventTask = new EventTask(false);
        eventTask.execute();
    }
}
