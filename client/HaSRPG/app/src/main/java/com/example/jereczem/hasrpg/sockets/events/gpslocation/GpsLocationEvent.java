package com.example.jereczem.hasrpg.sockets.events.gpslocation;

import android.location.Location;

import com.example.jereczem.hasrpg.playgame.GameData;
import com.example.jereczem.hasrpg.sockets.SocketServerConnector;
import com.example.jereczem.hasrpg.sockets.events.EventName;
import com.example.jereczem.hasrpg.sockets.events.NonHandShakeEvent;
import com.example.jereczem.hasrpg.view.activities.GameActivity;
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
    protected void afterHandShakeReaction(GameActivity activity) throws JSONException {
        Integer userID = this.eventInformation.getInt("userID");
        Double latitude = this.eventInformation.getDouble("latitude");
        Double longitude = this.eventInformation.getDouble("longitude");
        setLocation(activity.getGameData(), latitude, longitude, userID);
    }

    private static void setLocation(GameData gameData, Double latitude, Double longitude, Integer userID) {
        if(gameData.getChases().containsKey(userID)){
            gameData.getChases().get(userID).setLatitude(latitude);
            gameData.getChases().get(userID).setLongitude(longitude);
        } else{
            gameData.getHunters().get(userID).setLatitude(latitude);
            gameData.getHunters().get(userID).setLongitude(longitude);
        }
    }

    public static void sentEvent(SocketServerConnector sConnector, GameActivity gameActivity, Location location) {
        Socket socket = sConnector.getSocket();
        JSONObject eventInformation = new JSONObject();
        try {
            eventInformation.put("name", EventName.GPS_LOCATION.name());
            eventInformation.put("userID", gameActivity.playerData.getUserID());
            eventInformation.put("latitude", location.getLatitude());
            eventInformation.put("longitude", location.getLongitude());
            socket.emit("sentEvent", eventInformation);
            setLocation(gameActivity.getGameData(), location.getLatitude(), location.getLongitude(), gameActivity.playerData.getUserID());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
