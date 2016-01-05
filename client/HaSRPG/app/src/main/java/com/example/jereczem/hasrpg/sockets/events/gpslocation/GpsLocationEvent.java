package com.example.jereczem.hasrpg.sockets.events.gpslocation;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.jereczem.hasrpg.sockets.SocketServerConnector;
import com.example.jereczem.hasrpg.sockets.events.EventName;
import com.example.jereczem.hasrpg.sockets.events.NonHandShakeEvent;
import com.example.jereczem.hasrpg.view.activities.ChaseGameActivity;
import com.example.jereczem.hasrpg.view.activities.GameActivity;
import com.example.jereczem.hasrpg.view.activities.HunterGameActivity;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by Michał on 2016-01-05.
 */
public class GpsLocationEvent extends NonHandShakeEvent<GameActivity> {
    public GpsLocationEvent(JSONObject eventInformation, SocketServerConnector sConnector, GameActivity gameActivity) {
        super(eventInformation, sConnector, gameActivity);
    }

    @Override
    protected void afterHandShakeReaction(AppCompatActivity activity) throws JSONException {
        Integer userID = this.eventInformation.getInt("userID");
        Double latitude = this.eventInformation.getDouble("latitude");
        Double longitude = this.eventInformation.getDouble("longitude");
        Toast.makeText(activity, "User: " + userID + "GPS: " + latitude + ", " + longitude, Toast.LENGTH_SHORT).show();
    }

    public static void sentEvent(SocketServerConnector sConnector, GameActivity gameActivity, Location location) {
        Socket socket = sConnector.getSocket();
        JSONObject eventInformation = new JSONObject();
        try {
            eventInformation.put("name", EventName.GPS_LOCATION.toString());
            eventInformation.put("userID", gameActivity.playerData.getUserID());
            eventInformation.put("latitude", location.getLatitude());
            eventInformation.put("longitude", location.getLongitude());
            socket.emit("sentEvent", eventInformation);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
