package com.example.jereczem.hasrpg.view.drawer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.data.PlayerData;
import com.example.jereczem.hasrpg.data.PlayerDataReceiver;
import com.example.jereczem.hasrpg.networking.HttpResponse;
import com.example.jereczem.hasrpg.networking.HttpResponseReceiver;
import com.example.jereczem.hasrpg.view.dialogs.Alerts;

/**
 * Created by jereczem on 03.08.15.
 */
public class DrawerLogic {
    private RecyclerView recyclerView;
    private DrawerLayout drawerLayout;
    private AppCompatActivity a;
    private PlayerData playerData;

    public DrawerLogic(AppCompatActivity a){
        this.a = a;
        downloadPlayerData();
        drawerLayout = (DrawerLayout)a.findViewById(R.id.drawer_layout);
        recyclerView = (RecyclerView)a.findViewById(R.id.RecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(a);
        recyclerView.setLayoutManager(layoutManager);

        ItemData itemsData[] = {
                new ItemData(a.getString(R.string.title_activity_lobbies), R.drawable.user_group_2_50 ),
                new ItemData(a.getString(R.string.title_activity_character_select), R.drawable.user_group_2_50 )};

        recyclerView.setAdapter(new CustomAdapter(a, itemsData, playerData));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public void openDrawerClick(){
        drawerLayout.openDrawer(Gravity.LEFT);
    }

    public void downloadPlayerData() {
        HttpResponse response = new HttpResponseReceiver("mycharacters").receive();
        if (response.getCode().equals(200)) {
            playerData = PlayerDataReceiver.fromString(response.getMessage());
        } else {
            AlertDialog alertDialog = Alerts.connectionError(a, response.getMessage());
            alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    downloadPlayerData();
                }
            });
            alertDialog.show();
        }
    }
}
