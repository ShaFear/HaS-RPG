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

    public DrawerLogic(AppCompatActivity a){
        drawerLayout = (DrawerLayout)a.findViewById(R.id.drawer_layout);
        recyclerView = (RecyclerView)a.findViewById(R.id.RecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(a);
        recyclerView.setLayoutManager(layoutManager);

        ItemData itemsData[] = { new ItemData("Help", R.drawable.abc_list_selector_holo_light),
                new ItemData("Help", R.drawable.abc_list_selector_holo_light),
        };

        recyclerView.setAdapter(new CustomAdapter(itemsData));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public void openDrawerClick(){
        drawerLayout.openDrawer(Gravity.LEFT);
    }
}
