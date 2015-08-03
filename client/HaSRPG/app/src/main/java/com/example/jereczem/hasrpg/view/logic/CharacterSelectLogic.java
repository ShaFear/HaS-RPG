package com.example.jereczem.hasrpg.view.logic;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.settings.SerializableTags;
import com.example.jereczem.hasrpg.data.PlayerData;
import com.example.jereczem.hasrpg.game.Chase;
import com.example.jereczem.hasrpg.game.Hunter;
import com.example.jereczem.hasrpg.view.toolbar.ToolbarSetter;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by jereczem on 01.08.15.
 */
public class CharacterSelectLogic implements Observer {
    private PlayerData playerData;
    private AppCompatActivity a;
    //chase
    private TextView chaseTitleTextView;
    private TextView chaseLevelTextView;
    private TextView chaseClassTextView;
    private TextView chaseExperienceTextView;
    private TextView chaseFeelRangeTextView;
    private TextView chaseSkillPointsTextView;
    //hunter
    private TextView hunterTitleTextView;
    private TextView hunterLevelTextView;
    private TextView hunterClassTextView;
    private TextView hunterExperienceTextView;
    private TextView hunterStartAttackRangeTextView;
    private TextView hunterFinalAttackRangeTextView;
    private TextView hunterSkillPointsTextView;

    public CharacterSelectLogic(AppCompatActivity activity){
        this.a = activity;
        new ToolbarSetter(a, R.drawable.menu_icon);

        Intent intent = a.getIntent();
        playerData = (PlayerData)intent.getSerializableExtra(SerializableTags.PLAYER_DATA);
        playerData.addObserver(this);

        //chase
        chaseLevelTextView = (TextView)a.findViewById(R.id.chaseLevelTextView);
        chaseClassTextView = (TextView)a.findViewById(R.id.chaseClassTextView);
        chaseExperienceTextView = (TextView)a.findViewById(R.id.chaseExperienceTextView);
        chaseFeelRangeTextView = (TextView)a.findViewById(R.id.chaseFeelRangeTextView);
        chaseSkillPointsTextView = (TextView)a.findViewById(R.id.chaseSkillPointsTextView);
        chaseTitleTextView = (TextView)a.findViewById(R.id.chase_title);
        //hunter
        hunterLevelTextView = (TextView)a.findViewById(R.id.hunterLevelTextView);
        hunterClassTextView = (TextView)a.findViewById(R.id.hunterClassTextView);
        hunterExperienceTextView = (TextView)a.findViewById(R.id.hunterExperienceTextView);
        hunterStartAttackRangeTextView = (TextView)a.findViewById(R.id.hunterStartAttackRangeTextView);
        hunterFinalAttackRangeTextView =
                (TextView)a.findViewById(R.id.hunterFinalAttackRangeTextView);
        hunterSkillPointsTextView = (TextView)a.findViewById(R.id.hunterSkillPointsTextView);
        hunterTitleTextView = (TextView)a.findViewById(R.id.hunter_title);
        setHunterText();
        setChaseText();
    }

    private void setHunterText(){
        Hunter hunter = playerData.getSelectedHunter();
        hunterClassTextView.setText(hunter.getCharacterClass());
        hunterLevelTextView.setText(hunter.getLevel().toString());
        hunterExperienceTextView.setText(hunter.getExperiencePoints().toString() + "/"
                + hunter.getExperienceLimit().toString());
        hunterStartAttackRangeTextView.setText(hunter.getAttackRange().toString());
        hunterFinalAttackRangeTextView.setText(hunter.getFinalAttackRange().toString());
        hunterSkillPointsTextView.setText(hunter.getSkillPoints().toString());
        hunterTitleTextView.setText(a.getResources().getString(R.string.hunter_title) + " "
                + playerData.getHunterNumber().toString());
    }

    private void setChaseText(){
        Chase chase = playerData.getSelectedChase();
        chaseClassTextView.setText(chase.getCharacterClass());
        chaseLevelTextView.setText(chase.getLevel().toString());
        chaseExperienceTextView.setText(chase.getExperiencePoints().toString() + "/"
                + chase.getExperienceLimit().toString());
        chaseSkillPointsTextView.setText(chase.getSkillPoints().toString());
        chaseFeelRangeTextView.setText(chase.getFeelRange().toString());
        chaseTitleTextView.setText(a.getResources().getString(R.string.chase_title) + " "
                + playerData.getChaseNumber().toString());
    }

    public void rightChaseClick(){
        playerData.rightChase();
    }

    public void leftChaseClick(){
        playerData.leftChase();
    }

    public void rightHunterClick(){
        playerData.rightHunter();
    }

    public void leftHunterClick(){
        playerData.leftHunter();
    }

    @Override
    public void update(Observable observable, Object data) {
        setHunterText();
        setChaseText();
    }
}

