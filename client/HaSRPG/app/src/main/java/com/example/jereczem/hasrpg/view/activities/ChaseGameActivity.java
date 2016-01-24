package com.example.jereczem.hasrpg.view.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.game.users.Chase;
import com.example.jereczem.hasrpg.playgame.ArrayData;
import com.example.jereczem.hasrpg.playgame.ChaseData;
import com.example.jereczem.hasrpg.playgame.GameDataChanges;
import com.example.jereczem.hasrpg.playgame.HunterData;
import com.example.jereczem.hasrpg.sockets.events.gametime.TimeParser;
import com.example.jereczem.hasrpg.view.adapters.ChaseChaseDataAdapter;
import com.example.jereczem.hasrpg.view.adapters.ChaseDataAdapter;
import com.example.jereczem.hasrpg.view.adapters.HunterDataAdapter;
import com.example.jereczem.hasrpg.view.events.ProgressDialogs;
import com.example.jereczem.hasrpg.view.toolbar.ToolbarSetter;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ChaseGameActivity extends GameActivity implements Observer{

    HunterDataAdapter hunterDataAdapter;
    ChaseChaseDataAdapter chaseDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chase_game);
        ((Toolbar) findViewById(R.id.tool_bar)).setTitle(playerData.getLogin());
        new ToolbarSetter(this);
        getGameData().addObserver(this);
        for(ArrayData<ChaseData> chaseData : getGameData().getChaseArray()){
            ChaseData chase = chaseData.getData();
            chase.addObserver(this);
        }
        for(ArrayData<HunterData> hunterData : getGameData().getHunterArray()){
            HunterData hunter = hunterData.getData();
            hunter.addObserver(this);
        }
        startedHandler();
    }

    private void startedHandler() {
        handleGameStatus();
        handleGameTime();
        handleRunTime();
        handleListViews();
    }
    private void handleListViews() {
        ListView hunterListView = (ListView)findViewById(R.id.hunterDataListView);
        ListView chaseListView = (ListView)findViewById(R.id.chaseDataListView);

        ArrayList<ArrayData<ChaseData>> chases = getGameData().getChaseArray();
        for(int j=0; j<chases.size(); j++){
            if(chases.get(j).getUserID().equals(playerData.getUserID()))
                chases.remove(j);
        }

        hunterDataAdapter = new HunterDataAdapter(this, R.layout.item_hunter, getGameData().getHunterArray());
        chaseDataAdapter = new ChaseChaseDataAdapter(this, R.layout.item_chase_chase, chases);

        hunterDataAdapter.setMyLatitude(getGameData().getChases().get(playerData.getUserID()).getLatitude());
        hunterDataAdapter.setMyLongitude(getGameData().getChases().get(playerData.getUserID()).getLongitude());

        chaseDataAdapter.setMyLatitude(getGameData().getChases().get(playerData.getUserID()).getLatitude());
        chaseDataAdapter.setMyLongitude(getGameData().getChases().get(playerData.getUserID()).getLongitude());

        hunterListView.setAdapter(hunterDataAdapter);
        chaseListView.setAdapter(chaseDataAdapter);
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
        if(getGameData().getRunTime() > 0){
            if(runTimeProgressDialog == null)
                runTimeProgressDialog= ProgressDialogs.runTimeChase(this, getGameData().getRunTime());
            ProgressDialogs.updateRunTimeChase(runTimeProgressDialog, getGameData().getRunTime());
            if(!this.isFinishing())
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
                ChaseResultActivity.openChaseResultActivity(this);
                break;
            }
            case CHASE_WINS:{
                Log.d("HASHANDLE", "Handle CHASE_WINS game status");
                ChaseResultActivity.openChaseResultActivity(this);
                break;

            }
            case INTERRUPTED:{
                Log.d("HASHANDLE", "Handle INTERRUPTED game status");
                break;
            }
        }
    }
}
