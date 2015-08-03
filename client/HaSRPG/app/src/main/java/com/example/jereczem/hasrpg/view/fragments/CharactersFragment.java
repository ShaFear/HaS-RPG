package com.example.jereczem.hasrpg.view.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.data.PlayerData;
import com.example.jereczem.hasrpg.view.logic.CharacterSelectLogic;


/**
 * A simple {@link Fragment} subclass.
 */
public class CharactersFragment extends Fragment {

    private PlayerData playerData;
    private View activityView;
    private CharacterSelectLogic characterSelectLogic;

    public CharactersFragment(PlayerData playerData) {
        this.playerData = playerData;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activityView = inflater.inflate(R.layout.fragment_characters, container, false);
        characterSelectLogic = new CharacterSelectLogic(activityView, playerData);
        return activityView;
    }

}
