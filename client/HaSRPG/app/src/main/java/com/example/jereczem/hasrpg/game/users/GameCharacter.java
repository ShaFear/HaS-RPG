package com.example.jereczem.hasrpg.game.users;

import com.example.jereczem.hasrpg.data.player.CharacterData;
import com.example.jereczem.hasrpg.settings.GameSettings;

import java.io.Serializable;

/**
 * Created by jereczem on 29.07.15.
 */
public abstract class GameCharacter implements Serializable {
    protected CharacterData characterData;
    protected Integer level;
    protected Integer skillPoints;
    protected Integer experiencePoints;
    protected Integer experienceLimit;
    protected String characterClass;
    protected Integer characterID;

    public GameCharacter(CharacterData characterData) {
        this.characterData = characterData;
        this.level = characterData.getLvl();
        this.skillPoints = characterData.getSkillPoints();
        this.characterClass = characterData.getProfession();
        this.experiencePoints = characterData.getExperience();
        this.characterID = characterData.getCharacterID();
        buildCharacter();
    }

    protected void buildExperienceLimit(){
        experienceLimit = level * GameSettings.EXPERIENCE_PER_LEVEL;
    }

    abstract void buildCharacter();

    public CharacterData getCharacterData() {
        return characterData;
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getSkillPoints() {
        return skillPoints;
    }

    public Integer getExperiencePoints() {
        return experiencePoints;
    }

    public Integer getExperienceLimit() {
        return experienceLimit;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public Integer getCharacterID() {
        return characterID;
    }

    @Override
    public String toString() {
        return "GameCharacter{" +
                ", level=" + level +
                ", skillPoints=" + skillPoints +
                ", experiencePoints=" + experiencePoints +
                ", experienceLimit=" + experienceLimit +
                ", characterClass='" + characterClass + '\'' +
                ", characterID='" + characterID + '\'' +
                '}';
    }
}
