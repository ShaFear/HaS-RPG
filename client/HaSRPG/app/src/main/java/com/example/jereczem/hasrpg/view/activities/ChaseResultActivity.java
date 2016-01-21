package com.example.jereczem.hasrpg.view.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.data.player.PlayerData;
import com.example.jereczem.hasrpg.playgame.GameData;
import com.example.jereczem.hasrpg.settings.GameSettings;

public class ChaseResultActivity extends AppCompatActivity {

    private PlayerData playerData;
    private GameData gameData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chase_activity_result);
        playerData = (PlayerData) this.getIntent().getExtras().getSerializable(GameSettings.CHASE_GAME_PLAYER_TO_RESULT_TAG);
        gameData = (GameData) this.getIntent().getExtras().getSerializable(GameSettings.CHASE_GAME_TO_RESULT_TAG);
    }

    public static void openChaseResultActivity(ChaseGameActivity a){
        Intent intent = new Intent(a, ChaseResultActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(GameSettings.CHASE_GAME_TO_RESULT_TAG, a.getGameData());
        bundle.putSerializable(GameSettings.CHASE_GAME_PLAYER_TO_RESULT_TAG, a.playerData);
        intent.putExtras(bundle);
        a.startActivity(intent);
        a.sConnector.getSocket().close();
        a.finish();
    }

    @Override
    public void onBackPressed() {

    }

    public void gotoLobbiesFromChase(View view) {
        Intent i = new Intent(this, MenuActivity.class);
        startActivity(i);
        this.finish();
    }
}
