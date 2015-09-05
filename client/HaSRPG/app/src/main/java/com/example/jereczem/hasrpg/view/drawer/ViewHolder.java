package com.example.jereczem.hasrpg.view.drawer;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.data.player.PlayerData;
import com.example.jereczem.hasrpg.data.player.PlayerDataReceiver;
import com.example.jereczem.hasrpg.networking.HttpResponse;
import com.example.jereczem.hasrpg.networking.HttpResponseReceiver;
import com.example.jereczem.hasrpg.networking.rest.RestException;
import com.example.jereczem.hasrpg.view.dialogs.Alerts;
import com.example.jereczem.hasrpg.view.fragments.CharactersFragment;
import com.example.jereczem.hasrpg.view.fragments.LobbiesFragment;

/**
 * Created by jereczem on 03.08.15.
 */
// inner class to hold a reference to each item of RecyclerView
public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtViewTitle;
    public ImageView imgViewIcon;
    private AppCompatActivity a;
    private PlayerData playerData;
    private DrawerLayout drawerLayout;

    public ViewHolder(View itemLayoutView, AppCompatActivity activity, DrawerLayout drawerLayout) {
        super(itemLayoutView);
        itemLayoutView.setClickable(true);
        itemLayoutView.setOnClickListener(this);
        txtViewTitle = (TextView) itemLayoutView.findViewById(R.id.rowText);
        imgViewIcon = (ImageView) itemLayoutView.findViewById(R.id.rowIcon);
        this.a = activity;
        this.drawerLayout = drawerLayout;
    }

    public void downloadPlayerData() throws RestException {
        HttpResponse response = new HttpResponseReceiver("mycharacters").receive();
        if (response.getCode().equals(200)) {
            playerData = PlayerDataReceiver.fromString(response.getMessage());
        } else {
            throw new RestException(response);
        }
    }

    @Override
    public void onClick(View v) {
        if(getPosition() == 0){
            selectLobbiesClick();
        }
        if(getPosition() == 1){
            selectCharactersClick();
        }
    }

    private void selectLobbiesClick() {
        a.getSupportActionBar().setTitle(R.string.title_activity_menu);
        FragmentManager fragmentManager = a.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        LobbiesFragment fragment = new LobbiesFragment();
        fragmentTransaction.replace(R.id.menu_fragment, fragment);
        fragmentTransaction.commit();
        drawerLayout.closeDrawer(Gravity.LEFT);
    }

    public void selectCharactersClick() {
        try {
            downloadPlayerData();
            a.getSupportActionBar().setTitle(R.string.title_activity_character_select);
            FragmentManager fragmentManager = a.getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            CharactersFragment fragment = new CharactersFragment(playerData);
            fragmentTransaction.replace(R.id.menu_fragment, fragment);
            fragmentTransaction.commit();
            drawerLayout.closeDrawer(Gravity.LEFT);
        } catch (RestException e) {
            e.printStackTrace();
            AlertDialog alertDialog = e.getErrorAlert(a);
            alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    selectCharactersClick();
                }
            });
            alertDialog.show();
        }
    }
}
