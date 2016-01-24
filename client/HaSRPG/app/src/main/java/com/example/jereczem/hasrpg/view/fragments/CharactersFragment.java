package com.example.jereczem.hasrpg.view.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.data.player.PlayerData;
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
        characterSelectLogic = new CharacterSelectLogic(activityView, playerData, this.getActivity());
        setButtonListeners();
        return activityView;
    }

    private void setButtonListeners(){
        ImageButton leftChaseButton = (ImageButton) activityView.findViewById(R.id.chaseLeftButton);
        leftChaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                characterSelectLogic.leftChaseClick();
            }
        });
        ImageButton rightChaseButton = (ImageButton) activityView.findViewById(R.id.chaseRightButton);
        rightChaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                characterSelectLogic.rightChaseClick();
            }
        });
        ImageButton leftHunterButton = (ImageButton) activityView.findViewById(R.id.hunterLeftButton);
        leftHunterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                characterSelectLogic.leftHunterClick();
            }
        });
        ImageButton rightHunterButton = (ImageButton) activityView.findViewById(R.id.hunterRightButton);
        rightHunterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                characterSelectLogic.rightHunterClick();
            }
        });
        final Button saveButton = (Button) activityView.findViewById(R.id.saveCharacterButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                characterSelectLogic.saveClick();
            }
        });
    }

}
