package com.example.jereczem.hasrpg.view.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.view.logic.LobbiesActivityLogic;


public class LobbiesActivity extends AppCompatActivity{

    private LobbiesActivityLogic lobbiesActivityLogic;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobbies);
        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        toolbar.setNavigationIcon(R.drawable.list);
        setSupportActionBar(toolbar);
        lobbiesActivityLogic = new LobbiesActivityLogic(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        lobbiesActivityLogic.downloadPlayerData();
    }

    @Override
    public void onBackPressed() {
        lobbiesActivityLogic.moveTaskToBack();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                return true;
            }
            case R.id.action_create_new_lobby:  {
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void createNewLobby(View view) {

    }
}
