package com.example.jereczem.hasrpg.view.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.playgame.GameDataChanges;
import com.example.jereczem.hasrpg.sockets.events.gametime.TimeParser;
import com.example.jereczem.hasrpg.view.events.ProgressDialogs;

import java.util.Observable;
import java.util.Observer;

public class ChaseGameActivity extends GameActivity implements Observer{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chase_game);
        getGameData().addObserver(this);
        startedHandler();
    }

    private void startedHandler() {
        handleGameStatus();
        handleGameTime();
        handleRunTime();
    }

    @Override
    public void update(Observable observable, Object data) {
        if (data instanceof GameDataChanges){
            GameDataChanges gameDataChanges = (GameDataChanges)data;
            switch (gameDataChanges){
                case RUNTIME:{
                    handleRunTime();
                    break;
                }
                case GAME_TIME:{
                    handleGameTime();
                    break;
                }
                case GAME_STATUS:{
                    handleGameStatus();
                    break;
                }
            }
        }
    }

    ProgressDialog runTimeProgressDialog;

    private void handleRunTime() {
        if(getGameData().getRunTime() > 0){
            if(runTimeProgressDialog == null)
                runTimeProgressDialog= ProgressDialogs.runTimeChase(this, getGameData().getRunTime());
            ProgressDialogs.updateRunTimeChase(runTimeProgressDialog, getGameData().getRunTime());
            runTimeProgressDialog.show();
        } else{
            runTimeProgressDialog.dismiss();
        }
    }

    private void handleGameTime() {
        TextView text = (TextView) findViewById(R.id.gameTimeChase);
        text.setText(TimeParser.fromLong(getGameData().getGameTime()));
    }

    private void handleGameStatus() {
        switch(getGameData().getStatus()){
            case RUNNING:{
                Log.d("HASHANDLE", "Handle RUNNING game status");
                break;
            }
            case HUNTER_WINS:{
                Log.d("HASHANDLE", "Handle HUNTER_WINS game status");
                break;
            }
            case CHASE_WINS:{
                Log.d("HASHANDLE", "Handle CHASE_WINS game status");
                break;

            }
            case INTERRUPTED:{
                Log.d("HASHANDLE", "Handle INTERRUPTED game status");
                break;
            }
        }
    }
}
