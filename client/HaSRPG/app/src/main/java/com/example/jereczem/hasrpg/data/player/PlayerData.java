package com.example.jereczem.hasrpg.data.player;

import com.example.jereczem.hasrpg.game.users.Chase;
import com.example.jereczem.hasrpg.game.users.Hunter;
import com.example.jereczem.hasrpg.settings.LobbySettings;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by jereczem on 09.07.15.
 */
public class PlayerData extends Observable implements Serializable{
    private Integer userID;
    private String login;
    private Integer hunterID;
    private Integer chaseID;
    private ArrayList<CharacterData> characters = new ArrayList<CharacterData>();
    private ArrayList<Chase> chases;
    private ArrayList<Hunter> hunters;
    private Hunter selectedHunter;
    private Chase selectedChase;
    protected Integer chaseNumber;
    protected Integer hunterNumber;

    public LobbySettings.Status getStatus() {
        return status;
    }

    public void setStatus(LobbySettings.Status status) {
        this.status = status;
    }

    private LobbySettings.Status status;

    public PlayerData(Integer userID, String login, Integer hunterID, Integer chaseID) {
        this.userID = userID;
        this.login = login;
        this.hunterID = hunterID;
        this.chaseID = chaseID;
        chases = new ArrayList<Chase>();
        hunters = new ArrayList<Hunter>();
    }

    public Integer getUserID() {
        return userID;
    }

    public String getLogin() {
        return login;
    }

    public ArrayList<CharacterData> getCharacters() {
        return characters;
    }

    @Override
    public String toString() {
        try{
            return "PlayerData{" +
                "userID=" + userID +
                ", login='" + login + '\'' +
                ", hunterID=" + hunterID +
                    ", status=" + status.name() +
                ", chaseID=" + chaseID +
                ", characters=" + characters.toString() +
                "\n, chases=" + chases.toString() +
                "\n, hunters=" + hunters.toString() +
                "\n, selectedHunter=" + selectedHunter.toString() +
                "\n, selectedChase=" + selectedChase.toString() +
                '}';
        }catch (NullPointerException e){
            return "brak kompletnych danych postaci";
        }
    }

    public ArrayList<Chase> getChases() {
        return chases;
    }

    public ArrayList<Hunter> getHunters() {
        return hunters;
    }

    public Hunter getSelectedHunter() {
        return selectedHunter;
    }

    public Chase getSelectedChase() {
        return selectedChase;
    }

    public void set(){
        for(CharacterData characterData : characters){
            if(characterData.getRole().equals("hunter")){
                hunters.add((Hunter)characterData.getCharacter());
                if(characterData.getCharacterID().equals(hunterID)) {
                    selectedHunter = (Hunter) characterData.getCharacter();
                    hunterNumber = hunters.size();
                }
            }else{
                chases.add((Chase)characterData.getCharacter());
                if(characterData.getCharacterID().equals(chaseID)){
                    selectedChase = (Chase)characterData.getCharacter();
                    chaseNumber = chases.size();
                }
            }
        }
    }

    public void rightChase() {
        Integer selectedIndex = chases.indexOf(selectedChase);
        if(chases.size() > 1) {
            if (selectedIndex < chases.size() - 1) {
                selectedChase = chases.get(selectedIndex + 1);
                chaseID = selectedChase.getCharacterID();
            } else {
                selectedChase = chases.get(selectedIndex - 1);
                chaseID = selectedChase.getCharacterID();
            }
        }
        changedSelection();
    }

    public void leftChase() {
        Integer selectedIndex = chases.indexOf(selectedChase);
        if(chases.size() > 1) {
            if (selectedIndex > 0) {
                selectedChase = chases.get(selectedIndex - 1);
                chaseID = selectedChase.getCharacterID();
            } else {
                selectedChase = chases.get(selectedIndex + 1);
                chaseID = selectedChase.getCharacterID();
            }
        }
        changedSelection();
    }

    public void rightHunter() {
        Integer selectedIndex = hunters.indexOf(selectedHunter);
        if(hunters.size() > 1) {
            if (selectedIndex < hunters.size() - 1) {
                selectedHunter = hunters.get(selectedIndex + 1);
                hunterID = selectedHunter.getCharacterID();
            } else {
                selectedHunter = hunters.get(selectedIndex - 1);
                hunterID = selectedHunter.getCharacterID();
            }
        }
        changedSelection();
    }

    public void leftHunter() {
        Integer selectedIndex = hunters.indexOf(selectedHunter);
        if(hunters.size() > 1) {
            if (selectedIndex > 0) {
                selectedHunter = hunters.get(selectedIndex - 1);
                hunterID = selectedHunter.getCharacterID();
            } else {
                selectedHunter = hunters.get(selectedIndex + 1);
                hunterID = selectedHunter.getCharacterID();
            }
        }
        changedSelection();
    }

    private void changedSelection(){
        chaseNumber = chases.indexOf(selectedChase) + 1;
        hunterNumber = hunters.indexOf(selectedHunter) + 1;
        setChanged();
        notifyObservers();
    }

    public Integer getHunterNumber() {
        return hunterNumber;
    }


    public Integer getChaseNumber() {
        return chaseNumber;
    }
}
