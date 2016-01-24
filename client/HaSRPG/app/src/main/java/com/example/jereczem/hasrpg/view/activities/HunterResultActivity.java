package com.example.jereczem.hasrpg.view.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.data.player.PlayerData;
import com.example.jereczem.hasrpg.game.lobbies.Lobby;
import com.example.jereczem.hasrpg.networking.rest.RestException;
import com.example.jereczem.hasrpg.playgame.GameData;
import com.example.jereczem.hasrpg.playgame.GameStatus;
import com.example.jereczem.hasrpg.playgame.Results;
import com.example.jereczem.hasrpg.settings.GameSettings;
import com.example.jereczem.hasrpg.view.logic.SignInLogic;
import com.example.jereczem.hasrpg.view.toolbar.ToolbarSetter;

public class HunterResultActivity extends AppCompatActivity {

    private PlayerData playerData;
    private GameData gameData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hunter_result);
        new ToolbarSetter(this);
        playerData = (PlayerData) this.getIntent().getExtras().getSerializable(GameSettings.HUNTER_GAME_PLAYER_TO_RESULT_TAG);
        gameData = (GameData) this.getIntent().getExtras().getSerializable(GameSettings.HUNTER_GAME_TO_RESULT_TAG);
        try {
            Results.setForHunter(gameData, playerData);
        } catch (RestException e) {
            e.printStackTrace();
            e.getErrorAlert(this).show();
        }
        TextView result = (TextView) findViewById(R.id.results2TextView);
        if(gameData.getStatus().equals(GameStatus.CHASE_WINS)){
            result.setText("You LOSE!\n There were some chases still alive..");
        } else{
            result.setText("You WIN!\n All chases are dead!");
        }
    }

    public static void openHunterResultActivity(HunterGameActivity a){
        Intent intent = new Intent(a, HunterResultActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(GameSettings.HUNTER_GAME_TO_RESULT_TAG, a.getGameData());
        bundle.putSerializable(GameSettings.HUNTER_GAME_PLAYER_TO_RESULT_TAG, a.playerData);
        intent.putExtras(bundle);
        a.startActivity(intent);
        a.sConnector.getSocket().close();
        a.finish();
    }

    @Override
    public void onBackPressed() {

    }

    public void gotoLobbiesFromHunter(View view) {
        Intent i = new Intent(this, MenuActivity.class);
        startActivity(i);
        this.finish();
    }
}
