package com.example.jereczem.hasrpg.view.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.playgame.ArrayData;
import com.example.jereczem.hasrpg.playgame.ChaseData;
import com.example.jereczem.hasrpg.playgame.ChaseStatus;
import com.example.jereczem.hasrpg.playgame.GameDataChanges;
import com.example.jereczem.hasrpg.playgame.HunterData;
import com.example.jereczem.hasrpg.sockets.events.attack.AttackEvent;
import com.example.jereczem.hasrpg.sockets.events.gametime.GameTimeTimer;
import com.example.jereczem.hasrpg.sockets.events.gametime.TimeParser;
import com.example.jereczem.hasrpg.sockets.events.runtime.RunTimeEvent;
import com.example.jereczem.hasrpg.view.adapters.AttackButton;
import com.example.jereczem.hasrpg.view.adapters.ChaseDataAdapter;
import com.example.jereczem.hasrpg.view.adapters.HunterDataAdapter;
import com.example.jereczem.hasrpg.view.events.ProgressDialogs;

import java.util.Observable;
import java.util.Observer;

public class HunterGameActivity extends GameActivity implements Observer {

    ChaseDataAdapter chaseDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hunter_game);
        getGameData().addObserver(this);
        for(ArrayData<ChaseData> chaseData : getGameData().getChaseArray()){
            ChaseData chase = chaseData.getData();
            chase.addObserver(this);
        }
        for(ArrayData<HunterData> hunterData : getGameData().getHunterArray()){
            HunterData hunter = hunterData.getData();
            hunter.addObserver(this);
        }
        startGame();
        startedHandler();

    }

    private void startedHandler() {
        handleGameStatus();
        handleGameTime();
        handleRunTime();
        handleListViews();
    }

    private void handleListViews() {
        ListView chaseListView = (ListView)findViewById(R.id.chaseDataListView);

        chaseDataAdapter = new ChaseDataAdapter(this, R.layout.item_hunter, getGameData().getChaseArray(), getGameData().getHunters().get(playerData.getUserID()));

        chaseDataAdapter.setMyLatitude(getGameData().getHunters().get(playerData.getUserID()).getLatitude());
        chaseDataAdapter.setMyLongitude(getGameData().getHunters().get(playerData.getUserID()).getLongitude());

        chaseListView.setAdapter(chaseDataAdapter);
    }

    private void startGame(){
        RunTimeEvent.setRunTimer(sConnector, lobby.getRunTime(), this);
    }

    GameTimeTimer gameTimeTimer;

    public void startGameStartTimer(){
        gameTimeTimer = new GameTimeTimer(this.lobby.getGameTime() * 1000, 1000, this, sConnector);
        gameTimeTimer.start();
    }

    public void stopGameTimeTimer(){
        if(gameTimeTimer != null)
            gameTimeTimer.cancel();
        gameTimeTimer = null;
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
                case CHASE:{
                    handleListViews();
                    break;
                }
                case HUNTER:{
                    handleListViews();
                    break;
                }
            }
        }
    }


    ProgressDialog runTimeProgressDialog;

    private void handleRunTime() {
        if(runTimeProgressDialog == null)
            runTimeProgressDialog= ProgressDialogs.runTimeHunter(this, getGameData().getRunTime());
        if(getGameData().getRunTime() > 0){
            ProgressDialogs.updateRunTimeHunter(runTimeProgressDialog, getGameData().getRunTime());
            runTimeProgressDialog.show();
        } else{
            runTimeProgressDialog.dismiss();
        }
    }

    private void handleGameTime() {
        TextView text = (TextView) findViewById(R.id.gameTimeHunter);
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
                HunterResultActivity.openHunterResultActivity(this);
                break;
            }
            case CHASE_WINS:{
                Log.d("HASHANDLE", "Handle CHASE_WINS game status");
                HunterResultActivity.openHunterResultActivity(this);
                break;

            }
            case INTERRUPTED:{
                Log.d("HASHANDLE", "Handle INTERRUPTED game status");
                break;
            }
        }
    }

    public void attackChase(View view) {
        AttackButton attackButton = (AttackButton) view;
        Integer attack  = attackButton.getAttackedId();
        if(!getGameData().getChases().get(attack).getStatus().equals(ChaseStatus.DEAD))
            AttackEvent.hunterSendsAttack(sConnector, this, attack);
    }
}
