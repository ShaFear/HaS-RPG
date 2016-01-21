package com.example.jereczem.hasrpg.playgame;

import java.io.Serializable;

/**
 * Created by Michał on 2016-01-06.
 */
public enum GameStatus implements Serializable {
    RUNNING,
    HUNTER_WINS,
    CHASE_WINS,
    INTERRUPTED
}
