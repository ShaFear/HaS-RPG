package com.example.jereczem.hasrpg.settings;

/**
 * Created by jereczem on 05.07.15.
 */
public class GameSettings {
    public static final Integer SKILLS_NUMBER = 5;

    //all player settings
    public static final Integer EXPERIENCE_PER_LEVEL = 100;

    //chase
    public static final Integer FEEL_RANGE_START = 100;
    public static final Integer FEEL_RANGE_PER_LEVEL = 10;

    //hunter
    public static final Integer ATTACK_RANGE_START = 50;
    public static final Integer ATTACK_RANGE_PER_LEVEL = 10;
    public static final Integer FINAL_ATTACK_RANGE_MODIFIER = 2;

    public static final String CHASE_GAME_TO_RESULT_TAG = "CHASE_GAME_TO_RESULT_TAG";

    public static final String HUNTER_GAME_TO_RESULT_TAG = "HUNTER_GAME_TO_RESULT_TAG";

    public static final String CHASE_GAME_PLAYER_TO_RESULT_TAG = "CHASE_GAME_PLAYER_TO_RESULT_TAG";

    public static final String HUNTER_GAME_PLAYER_TO_RESULT_TAG = "HUNTER_GAME_PLAYER_TO_RESULT_TAG";

}
