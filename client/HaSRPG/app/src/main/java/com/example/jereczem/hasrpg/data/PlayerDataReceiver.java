package com.example.jereczem.hasrpg.data;

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
                Integer skills[] = new Integer[5];
                skills[0] = jsonObject.getInt("skill_1_lvl");
                skills[1] = jsonObject.getInt("skill_2_lvl");
                skills[2] = jsonObject.getInt("skill_3_lvl");
                skills[3] = jsonObject.getInt("skill_4_lvl");
                skills[4] = jsonObject.getInt("skill_5_lvl");
                Character character = new Character(
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
        System.out.print(playerData.toString());
        return playerData;
    }
}
