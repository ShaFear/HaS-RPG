package com.example.jereczem.hasrpg.data.player;

import com.example.jereczem.hasrpg.settings.GameSettings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jereczem on 09.07.15.
 */
public class PlayerDataReceiver {
    public static PlayerData fromString(String dataFromServer){
        PlayerData playerData = new PlayerData(-1, "error", -1, -1);
        try {
            JSONArray dataJSON = new JSONArray(dataFromServer);
            Integer userID = dataJSON.getJSONObject(0).getInt("UserID");
            String login = dataJSON.getJSONObject(0).getString("login");
            Integer hunterID = dataJSON.getJSONObject(0).getInt("HunterID");
            Integer chaseID = dataJSON.getJSONObject(0).getInt("ChaseID");
            playerData = new PlayerData(userID, login, hunterID, chaseID);
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
        playerData.set();
        return playerData;
    }
}
