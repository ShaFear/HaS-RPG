package com.example.jereczem.hasrpg.view.logic;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.networking.HttpResponse;
import com.example.jereczem.hasrpg.networking.HttpResponseReceiver;
import com.example.jereczem.hasrpg.data.player.PlayerData;
import com.example.jereczem.hasrpg.game.users.Chase;
import com.example.jereczem.hasrpg.game.users.Hunter;
import com.example.jereczem.hasrpg.networking.rest.ChangeCharacterStatusPoster;
import com.example.jereczem.hasrpg.view.dialogs.Alerts;
import com.example.jereczem.hasrpg.view.dialogs.CharacterSelectAlerts;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by jereczem on 01.08.15.
 */
public class CharacterSelectLogic implements Observer {
    private PlayerData playerData;
    private View a;
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

    private Activity activity;

    public CharacterSelectLogic(View view, PlayerData playerData, Activity activity){
        playerData.addObserver(this);
        this.playerData = playerData;
        this.a = view;
        this.activity = activity;
        setTextBoxes();
    }

    private void setTextBoxes(){
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

    public void saveClick(){
        HttpResponse response =
                ChangeCharacterStatusPoster.getResponse(playerData, (AppCompatActivity) activity);
        if(response.getCode().equals(200))
            CharacterSelectAlerts.charactersSaved(activity).show();
    }
}

