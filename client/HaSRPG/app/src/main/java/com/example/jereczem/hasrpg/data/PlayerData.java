package com.example.jereczem.hasrpg.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by jereczem on 09.07.15.
 */
public class PlayerData implements Serializable{
    private Integer UserID;
    private String login;
    private ArrayList<CharacterData> characters = new ArrayList<CharacterData>();

    public PlayerData(Integer userID, String login) {
        UserID = userID;
        this.login = login;
    }

    public Integer getUserID() {
        return UserID;
    }

    public String getLogin() {
        return login;
    }

    public ArrayList<CharacterData> getCharacters() {
        return characters;
    }

    @Override
    public String toString() {
        return "PlayerData{" +
                "UserID=" + UserID +
                ", login='" + login + '\'' + "\n" +
                ", characters=" + characters.toString() +
                "}\n";
    }
}
