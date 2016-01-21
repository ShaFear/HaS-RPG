package com.example.jereczem.hasrpg.playgame;

import com.example.jereczem.hasrpg.data.player.PlayerData;
import com.example.jereczem.hasrpg.game.users.Chase;
import com.example.jereczem.hasrpg.game.users.Hunter;
import com.example.jereczem.hasrpg.networking.rest.CharacterPoster;
import com.example.jereczem.hasrpg.networking.rest.RestException;
import com.example.jereczem.hasrpg.settings.GameSettings;

/**
 * Created by Michał on 2016-01-21.
 */
public class Results {
    /**
     * true if get new level
     *
     * @param gameData
     * @param playerData
     * @return
     */
    public static boolean setForHunter(GameData gameData, PlayerData playerData) throws RestException {
        Hunter hunter = playerData.getSelectedHunter();
        Integer currentLevel = hunter.getLevel();
        Integer currentExperience = hunter.getExperiencePoints();
        Boolean newLevel = false;

        Integer newExperience = currentExperience;

        if(gameData.getStatus().equals(GameStatus.CHASE_WINS)){
            newExperience += ((Double)(0.1 * GameSettings.EXPERIENCE_PER_LEVEL)).intValue();
        } else {
            newExperience += ((Double)(0.4 * GameSettings.EXPERIENCE_PER_LEVEL)).intValue();
        }

        if(newExperience >= hunter.getExperienceLimit()){
            currentLevel++;
            newExperience -= GameSettings.EXPERIENCE_PER_LEVEL;
            newLevel = true;
        }

        CharacterPoster.getResponse(hunter.getCharacterID(), newExperience, currentLevel);

        return newLevel;
    }

    public static boolean setForChase(GameData gameData, PlayerData playerData) throws RestException {
        Chase chase = playerData.getSelectedChase();
        Integer currentLevel = chase.getLevel();
        Integer currentExperience = chase.getExperiencePoints();
        Boolean newLevel = false;

        Integer newExperience = currentExperience;

        if(gameData.getStatus().equals(GameStatus.HUNTER_WINS)){
            newExperience += ((Double)(0.1 * GameSettings.EXPERIENCE_PER_LEVEL)).intValue();
        } else {
            newExperience += ((Double)(0.4 * GameSettings.EXPERIENCE_PER_LEVEL)).intValue();
        }

        if(newExperience >= chase.getExperienceLimit()){
            currentLevel++;
            newExperience -= GameSettings.EXPERIENCE_PER_LEVEL;
            newLevel = true;
        }

        CharacterPoster.getResponse(chase.getCharacterID(), newExperience, currentLevel);

        return newLevel;
    }
}
