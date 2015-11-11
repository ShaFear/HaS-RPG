package com.example.jereczem.hasrpg.data.lobby;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jereczem on 04.09.15.
 */
public class LobbyDataReceiver {
    public static LobbyBaseData receiveBaseData(String data) throws JSONException {
        JSONObject jData = new JSONArray(data).getJSONObject(0);
        return new LobbyBaseData(
                jData.getInt("LobbyID"),
                jData.getString("Title"),
                jData.getInt("PlayersNO"),
                jData.getInt("GameTime"),
                jData.getInt("RunTime"),
                jData.getString("Status"),
                jData.getString("Hunter_login")
        );
    }

    public static LobbyBasePlayersData receivePlayerData(String data) throws JSONException {
        JSONObject jData = new JSONObject(data);
        return new LobbyBasePlayersData(
                jData.getInt("UserID"),
                jData.getString("Status")
        );
    }
}

