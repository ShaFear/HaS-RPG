package com.example.jereczem.hasrpg.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jereczem on 09.07.15.
 */
public class PlayerData {
    private Integer UserID;
    private String login;
    private ArrayList<Character> characters = new ArrayList<Character>();

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

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    @Override
    public String toString() {
        return "PlayerData{" +
                "UserID=" + UserID +
                ", login='" + login + '\'' +
                ", characters=" + characters.toString() +
                '}';
    }
}
