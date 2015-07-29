package com.example.jereczem.hasrpg.game;

import com.example.jereczem.hasrpg.data.CharacterData;
import com.example.jereczem.hasrpg.settings.GameSettings;

/**
 * Created by jereczem on 29.07.15.
 */
public class Chase extends GameCharacter{
    private Integer feelRange;

    public Chase(CharacterData characterData) {
        super(characterData);
    }

    private void buildFeelRange(){
        feelRange = GameSettings.FEEL_RANGE_START + level * GameSettings.FEEL_RANGE_PER_LEVEL;
    }

    @Override
    void buildCharacter() {
        buildExperienceLimit();
        buildFeelRange();
    }

    public Integer getFeelRange() {
        return feelRange;
    }
}
