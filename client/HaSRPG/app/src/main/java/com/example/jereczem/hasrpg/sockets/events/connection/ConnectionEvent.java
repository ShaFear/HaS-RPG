package com.example.jereczem.hasrpg.sockets.events.connection;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.jereczem.hasrpg.sockets.SocketServerConnector;
import com.example.jereczem.hasrpg.sockets.events.NonHandShakeEvent;
import com.example.jereczem.hasrpg.view.activities.GameActivity;

import org.json.JSONException;
import org.json.JSONObject;


public class ConnectionEvent extends NonHandShakeEvent<GameActivity> {
    public ConnectionEvent(JSONObject eventInformation, SocketServerConnector sConnector, GameActivity activity) {
        super(eventInformation, sConnector, activity);
    }

    @Override
    protected void afterHandShakeReaction(AppCompatActivity activity) throws JSONException {
        Toast.makeText(activity, "lol", Toast.LENGTH_SHORT).show();
    }
}

