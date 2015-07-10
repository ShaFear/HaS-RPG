package com.example.jereczem.hasrpg.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jereczem on 09.07.15.
 */
public class Character {
    private Integer CharacterID;
    private Integer lvl;
    private String role;
    private String profession;
    private Integer experience;
    private Integer skillPoints;
    private Integer skills[] = new Integer[5];

    public Character(Integer characterID, Integer lvl, String role, String profession, Integer experience, Integer skillPoints, Integer[] skills) {
        this.CharacterID = characterID;
        this.lvl = lvl;
        this.role = role;
        this.profession = profession;
        this.experience = experience;
        this.skillPoints = skillPoints;
        this.skills = skills;
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

    @Override
    public String toString() {
        return "Character{" + "\n" +
                "CharacterID=" + CharacterID + "\n" +
                ", lvl=" + lvl + "\n" +
                ", role='" + role + '\'' + "\n" +
                ", profession='" + profession + '\'' + "\n" +
                ", experience=" + experience + "\n" +
                ", skillPoints=" + skillPoints + "\n" +
                ", skills=" + Arrays.toString(skills) + "\n" +
                '}';
    }
}
