package com.example.jereczem.hasrpg.view.activities;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.view.drawer.CustomAdapter;
import com.example.jereczem.hasrpg.view.drawer.ItemData;
import com.example.jereczem.hasrpg.view.logic.LobbiesActivityLogic;


public class LobbiesActivity extends AppCompatActivity{

    private LobbiesActivityLogic lobbiesActivityLogic;
    private RecyclerView recyclerView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobbies);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        recyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ItemData itemsData[] = { new ItemData("Help", R.drawable.menu_add),
                new ItemData("Help", R.drawable.menu_add),
        } ;

        recyclerView.setAdapter(new CustomAdapter(itemsData));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

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

}
