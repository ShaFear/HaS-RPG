package com.example.jereczem.hasrpg.view.drawer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.data.PlayerData;
import com.example.jereczem.hasrpg.settings.SerializableTags;
import com.example.jereczem.hasrpg.view.activities.CharacterSelectFragment;
import com.example.jereczem.hasrpg.view.activities.MenuActivity;

/**
 * Created by jereczem on 03.08.15.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{
    private ItemData[] itemsData;
    private AppCompatActivity a;
    private PlayerData playerData;

    public CustomAdapter(AppCompatActivity activity, ItemData[] itemsData, PlayerData playerData) {
        this.itemsData = itemsData;
        this.a = activity;
        this.playerData = playerData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_row, null);
        // create ViewHolder
        ViewHolder viewHolder = new ViewHolder(itemLayoutView, a, playerData);
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
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtViewTitle;
        public ImageView imgViewIcon;
        private AppCompatActivity a;
        private PlayerData playerData;

        public ViewHolder(View itemLayoutView, AppCompatActivity activity, PlayerData playerData) {
            super(itemLayoutView);
            itemLayoutView.setClickable(true);
            itemLayoutView.setOnClickListener(this);
            txtViewTitle = (TextView) itemLayoutView.findViewById(R.id.rowText);
            imgViewIcon = (ImageView) itemLayoutView.findViewById(R.id.rowIcon);
            this.a = activity;
            this.playerData = playerData;
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
            Intent intent = new Intent(a, MenuActivity.class);
            a.startActivity(intent);
        }

        public void selectCharactersClick() {
            Intent intent = new Intent(a, CharacterSelectFragment.class);
            intent.putExtra(SerializableTags.PLAYER_DATA, playerData);
            a.startActivity(intent);
        }
    }
}
