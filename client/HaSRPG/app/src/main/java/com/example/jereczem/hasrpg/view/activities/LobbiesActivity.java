package com.example.jereczem.hasrpg.view.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.view.logic.LobbiesActivityLogic;


public class LobbiesActivity extends AppCompatActivity{

    private LobbiesActivityLogic lobbiesActivityLogic;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobbies);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setNavigationIcon(R.drawable.list);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        recyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new CustomAdapter());

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
                drawerLayout.openDrawer(Gravity.LEFT);
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

    private class CustomAdapter extends RecyclerView.Adapter {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
}
