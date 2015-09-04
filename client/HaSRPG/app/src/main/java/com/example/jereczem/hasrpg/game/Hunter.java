package com.example.jereczem.hasrpg.game;

import com.example.jereczem.hasrpg.data.player.CharacterData;
import com.example.jereczem.hasrpg.settings.GameSettings;

/**
 * Created by jereczem on 29.07.15.
 */
public class Hunter extends GameCharacter{
    private Integer attackRange;
    private Integer finalAttackRange;

    private void buildAttackRange(){
        attackRange = GameSettings.ATTACK_RANGE_START + (level - 1)  * GameSettings.ATTACK_RANGE_PER_LEVEL;
    }

    private void buildFinalAttackRange(){
        finalAttackRange = attackRange * GameSettings.FINAL_ATTACK_RANGE_MODIFIER;
    }

    private void buildAttackRanges(){
        buildAttackRange();
        buildFinalAttackRange();
    }

    public Hunter(CharacterData characterData) {
        super(characterData);
    }

    @Override
    void buildCharacter() {
        buildExperienceLimit();
        buildAttackRanges();
    }

    public Integer getAttackRange() {
        return attackRange;
    }

    public Integer getFinalAttackRange() {
        return finalAttackRange;
    }

    @Override
    public String toString() {
        return super.toString() + "Hunter{" +
                "attackRange=" + attackRange +
                ", finalAttackRange=" + finalAttackRange +
                '}';
    }
}
