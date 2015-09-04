package com.example.jereczem.hasrpg.data.player;

import com.example.jereczem.hasrpg.game.Chase;
import com.example.jereczem.hasrpg.game.GameCharacter;
import com.example.jereczem.hasrpg.game.Hunter;
import com.example.jereczem.hasrpg.settings.GameSettings;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by jereczem on 09.07.15.
 */
public class CharacterData implements Serializable {
    private GameCharacter character;
    private Integer CharacterID;
    private Integer lvl;
    private String role;
    private String profession;
    private Integer experience;
    private Integer skillPoints;
    private Integer skills[] = new Integer[GameSettings.SKILLS_NUMBER];

    public CharacterData(Integer characterID, Integer lvl, String role, String profession, Integer experience, Integer skillPoints, Integer[] skills) {
        this.CharacterID = characterID;
        this.lvl = lvl;
        this.role = role;
        this.profession = profession;
        this.experience = experience;
        this.skillPoints = skillPoints;
        this.skills = skills;
        if(role.equals("hunter"))
            character = new Hunter(this);
        else
            character = new Chase(this);
    }

    public Integer getCharacterID() {
        return CharacterID;
    }

    public Integer getLvl() {
        return lvl;

    }

    public String getRole() {
        return role;
    }

    public String getProfession() {
        return profession;
    }

    public Integer getExperience() {
        return experience;
    }

    public Integer getSkillPoints() {
        return skillPoints;
    }

    public Integer[] getSkills() {
        return skills;
    }

    public GameCharacter getCharacter() {
        return character;
    }

    @Override
    public String toString() {
        return "\nCharacterData{" + "\n" +
                "character=" + character +
                "\n, CharacterID=" + CharacterID +
                ", lvl=" + lvl +
                ", role='" + role + '\'' +
                ", profession='" + profession + '\'' +
                ", experience=" + experience +
                ", skillPoints=" + skillPoints +
                ", skills=" + Arrays.toString(skills) +
                '}';
    }
}
