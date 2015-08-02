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

        ItemData itemsData[] = { new ItemData("Help", R.drawable.list),
                new ItemData("Help", R.drawable.list),
                new ItemData("Help", R.drawable.list),
                new ItemData("Help", R.drawable.list),
                new ItemData("Help", R.drawable.list),
                new ItemData("Help", R.drawable.list),
                new ItemData("Help", R.drawable.list),
                new ItemData("Help", R.drawable.list),
                new ItemData("Help", R.drawable.list),
                new ItemData("Help", R.drawable.list),
                new ItemData("Help", R.drawable.list),
                new ItemData("Help", R.drawable.list),
                new ItemData("Help", R.drawable.list),
                new ItemData("Help", R.drawable.list),
                new ItemData("Help", R.drawable.list),
                new ItemData("Help", R.drawable.list),
                new ItemData("Help", R.drawable.list),
                new ItemData("Help", R.drawable.list),
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

    private class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{
        private ItemData[] itemsData;

        public CustomAdapter(ItemData[] itemsData) {
            this.itemsData = itemsData;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            // create a new view
            View itemLayoutView = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_row, null);
            // create ViewHolder
            ViewHolder viewHolder = new ViewHolder(itemLayoutView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            // - get data from your itemsData at this position
            // - replace the contents of the view with that itemsData

            viewHolder.txtViewTitle.setText(itemsData[i].getTitle());
            viewHolder.imgViewIcon.setImageResource(itemsData[i].getImageUrl());

        }

        @Override
        public int getItemCount() {
            return itemsData.length;
        }

        // inner class to hold a reference to each item of RecyclerView
        public class ViewHolder extends RecyclerView.ViewHolder {

            public TextView txtViewTitle;
            public ImageView imgViewIcon;

            public ViewHolder(View itemLayoutView) {
                super(itemLayoutView);
                txtViewTitle = (TextView) itemLayoutView.findViewById(R.id.rowText);
                imgViewIcon = (ImageView) itemLayoutView.findViewById(R.id.rowIcon);
            }
        }
    }


}
