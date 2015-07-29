package com.example.jereczem.hasrpg.data;

import com.example.jereczem.hasrpg.settings.GameSettings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jereczem on 09.07.15.
 */
public class PlayerDataReceiver {
    public static PlayerData fromString(String dataFromServer){
        Integer userID = 1;
        String login = "Shafear";
        PlayerData playerData = new PlayerData(userID, login);
        try {
            JSONArray dataJSON = new JSONArray(dataFromServer);
            userID = dataJSON.getJSONObject(0).getInt("UserID");
            login = dataJSON.getJSONObject(0).getString("login");
            playerData = new PlayerData(userID, login);
            for(int j=0; j<dataJSON.length(); j++){
                JSONObject jsonObject = dataJSON.getJSONObject(j);
                Integer skills[] = new Integer[GameSettings.SKILLS_NUMBER];
                for(int i = 0; i < GameSettings.SKILLS_NUMBER; i++){
                    skills[i] = jsonObject.getInt("skill_" + (i + 1) + "_lvl");
                }
                CharacterData character = new CharacterData(
                        jsonObject.getInt("CharacterID"),
                        jsonObject.getInt("lvl"),
                        jsonObject.getString("role"),
                        jsonObject.getString("class"),
                        jsonObject.getInt("experience"),
                        jsonObject.getInt("skill_points"),
                        skills
                );
                playerData.getCharacters().add(character);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return playerData;
    }
}
