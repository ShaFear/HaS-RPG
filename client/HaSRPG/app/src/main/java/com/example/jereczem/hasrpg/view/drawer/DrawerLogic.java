package com.example.jereczem.hasrpg.view.drawer;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;

import com.example.jereczem.hasrpg.R;

/**
 * Created by jereczem on 03.08.15.
 */
public class DrawerLogic {
    private RecyclerView recyclerView;
    private DrawerLayout drawerLayout;
    private AppCompatActivity a;

    public DrawerLogic(AppCompatActivity a){
        this.a = a;
        drawerLayout = (DrawerLayout)a.findViewById(R.id.drawer_layout);
        recyclerView = (RecyclerView)a.findViewById(R.id.RecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(a);
        recyclerView.setLayoutManager(layoutManager);

        ItemData itemsData[] = {
                new ItemData(a.getString(R.string.title_activity_menu), R.drawable.user_group_2_50 ),
                new ItemData(a.getString(R.string.title_activity_character_select), R.drawable.user_group_2_50 )};

        recyclerView.setAdapter(new CustomAdapter(a, itemsData, drawerLayout));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public void openDrawerClick(){
        drawerLayout.openDrawer(Gravity.LEFT);
    }
}
