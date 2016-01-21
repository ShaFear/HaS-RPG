package com.example.jereczem.hasrpg.view.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.data.player.PlayerData;
import com.example.jereczem.hasrpg.playgame.GameData;
import com.example.jereczem.hasrpg.settings.GameSettings;

public class HunterResultActivity extends AppCompatActivity {

    private PlayerData playerData;
    private GameData gameData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hunter_result);
        playerData = (PlayerData) this.getIntent().getExtras().getSerializable(GameSettings.HUNTER_GAME_PLAYER_TO_RESULT_TAG);
        gameData = (GameData) this.getIntent().getExtras().getSerializable(GameSettings.HUNTER_GAME_TO_RESULT_TAG);
    }

    public static void openHunterResultActivity(HunterGameActivity a){
        Intent intent = new Intent(a, ChaseResultActivity.class);
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
}
