package com.example.jereczem.hasrpg.view.fragments;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.view.activities.CreateLobbyActivity;
import com.example.jereczem.hasrpg.view.dialogs.Alerts;


/**
 * A simple {@link Fragment} subclass.
 */
public class LobbiesFragment extends Fragment {

    private View activityView;

    public LobbiesFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activityView = inflater.inflate(R.layout.fragment_lobbies, container, false);
        setButtonListeners();

        return activityView;
    }

    private void setButtonListeners() {
        Button createLobbyButton = (Button) activityView.findViewById(R.id.createLobbyButton);
        createLobbyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activityView.getContext(), CreateLobbyActivity.class);
                startActivity(intent);
            }
        });
    }

}
