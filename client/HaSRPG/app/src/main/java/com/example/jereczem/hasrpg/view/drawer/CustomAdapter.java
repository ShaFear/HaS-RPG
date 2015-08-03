package com.example.jereczem.hasrpg.view.drawer;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jereczem.hasrpg.R;


/**
 * Created by jereczem on 03.08.15.
 */
public class CustomAdapter extends RecyclerView.Adapter<ViewHolder>{
    private ItemData[] itemsData;
    private AppCompatActivity a;
    private DrawerLayout drawerLayout;

    public CustomAdapter(AppCompatActivity activity, ItemData[] itemsData, DrawerLayout drawerLayout) {
        this.itemsData = itemsData;
        this.drawerLayout = drawerLayout;
        this.a = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_row, null);
        // create ViewHolder
        ViewHolder viewHolder = new ViewHolder(itemLayoutView, a, drawerLayout);
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


}
