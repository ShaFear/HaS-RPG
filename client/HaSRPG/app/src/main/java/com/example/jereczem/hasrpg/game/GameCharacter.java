package com.example.jereczem.hasrpg.game;

import com.example.jereczem.hasrpg.data.CharacterData;
import com.example.jereczem.hasrpg.settings.GameSettings;

/**
 * Created by jereczem on 29.07.15.
 */
public abstract class GameCharacter {
    protected CharacterData characterData;
    protected Integer level;
    protected Integer skillPoints;
    protected Integer experiencePoints;
    protected Integer experienceLimit;
    protected String characterClass;

    public GameCharacter(CharacterData characterData) {
        this.characterData = characterData;
        this.level = characterData.getLvl();
        this.skillPoints = characterData.getSkillPoints();
        this.characterClass = characterData.getProfession();
        this.experiencePoints = characterData.getExperience();
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
}
