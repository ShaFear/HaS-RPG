package com.example.jereczem.hasrpg.view.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.data.CharacterData;
import com.example.jereczem.hasrpg.data.PlayerData;
import com.example.jereczem.hasrpg.game.Chase;
import com.example.jereczem.hasrpg.game.Hunter;
import com.example.jereczem.hasrpg.networking.HttpConnection;
import com.example.jereczem.hasrpg.networking.HttpResponse;
import com.example.jereczem.hasrpg.view.dialogs.Alerts;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;


public class CharacterSelectActivity extends AppCompatActivity implements Observer{

    private PlayerData playerData;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_select);
        Intent intent = getIntent();
        playerData = (PlayerData)intent.getSerializableExtra("playerData");
        Log.d("HASLOG", playerData.toString());
        setPlayerDataToDisplay();
        playerData.addObserver(this);
    }

    private void setPlayerDataToDisplay(){
        //chase
        chaseLevelTextView = (TextView)findViewById(R.id.chaseLevelTextView);
        chaseClassTextView = (TextView)findViewById(R.id.chaseClassTextView);
        chaseExperienceTextView = (TextView)findViewById(R.id.chaseExperienceTextView);
        chaseFeelRangeTextView = (TextView)findViewById(R.id.chaseFeelRangeTextView);
        chaseSkillPointsTextView = (TextView)findViewById(R.id.chaseSkillPointsTextView);
        chaseTitleTextView = (TextView)findViewById(R.id.chase_title);
        //hunter
        hunterLevelTextView = (TextView)findViewById(R.id.hunterLevelTextView);
        hunterClassTextView = (TextView)findViewById(R.id.hunterClassTextView);
        hunterExperienceTextView = (TextView)findViewById(R.id.hunterExperienceTextView);
        hunterStartAttackRangeTextView = (TextView)findViewById(R.id.hunterStartAttackRangeTextView);
        hunterFinalAttackRangeTextView =
                (TextView)findViewById(R.id.hunterFinalAttackRangeTextView);
        hunterSkillPointsTextView = (TextView)findViewById(R.id.hunterSkillPointsTextView);
        hunterTitleTextView = (TextView)findViewById(R.id.hunter_title);
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
        hunterTitleTextView.setText(getResources().getString(R.string.hunter_title) + " "
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
        chaseTitleTextView.setText(getResources().getString(R.string.chase_title) + " "
                + playerData.getChaseNumber().toString());
    }

    @Override
    public void update(Observable observable, Object data) {
        setHunterText();
        setChaseText();
    }

    public void rightChase(View view) {
        playerData.rightChase();
    }

    public void leftChase(View view) {
        playerData.leftChase();

    }

    public void leftHunter(View view) {
        playerData.leftHunter();
    }

    public void rightHunter(View view) {
        playerData.rightHunter();
    }

    public void saveSelectedCharacters(View view) {
    }

    private class saveSelectedCharactersTask extends AsyncTask<Object, Void, HttpResponse>{

        @Override
        protected HttpResponse doInBackground(Object... params) {
            String url = (String) params[0];
            String urlParameters = (String) params[1];
            try {
                return HttpConnection.post(url, urlParameters);
            } catch (IOException e) {
                return new HttpResponse(500, e.toString());
            }
        }
    }
}
