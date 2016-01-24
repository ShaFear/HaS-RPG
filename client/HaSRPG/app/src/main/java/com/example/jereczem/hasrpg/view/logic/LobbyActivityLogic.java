package com.example.jereczem.hasrpg.view.logic;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.data.player.PlayerData;
import com.example.jereczem.hasrpg.data.player.PlayerDataReceiver;
import com.example.jereczem.hasrpg.game.lobbies.Lobby;
import com.example.jereczem.hasrpg.networking.HttpResponseReceiver;
import com.example.jereczem.hasrpg.networking.rest.LobbyDataDownloader;
import com.example.jereczem.hasrpg.networking.rest.LobbyLoginGetter;
import com.example.jereczem.hasrpg.networking.rest.LobbyLogoutGetter;
import com.example.jereczem.hasrpg.networking.rest.RestException;
import com.example.jereczem.hasrpg.networking.rest.StatusPoster;
import com.example.jereczem.hasrpg.settings.LobbySettings;
import com.example.jereczem.hasrpg.view.activities.ChaseGameActivity;
import com.example.jereczem.hasrpg.view.activities.GameActivity;
import com.example.jereczem.hasrpg.view.activities.HunterGameActivity;
import com.example.jereczem.hasrpg.view.adapters.LobbiesListAdapter;
import com.example.jereczem.hasrpg.view.adapters.PlayersListAdapter;
import com.example.jereczem.hasrpg.view.dialogs.Alerts;
import com.example.jereczem.hasrpg.view.toolbar.ToolbarSetter;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by jereczem on 04.09.15.
 */
public class LobbyActivityLogic {
    AppCompatActivity a;
    Integer lobbyId;
    Lobby lobby;
    CountDownTimer countDownTimer;
    Boolean ready = false;

    public LobbyActivityLogic(final AppCompatActivity a) {

        this.a = a;
        lobbyId = a.getIntent().getIntExtra("lobbyId", 0);
        new ToolbarSetter(a, R.drawable.previous);
        loginToLobby();
        if(!downloadDataAndSetViews())
            return;
        try {
            ListView playerListView = (ListView) a.findViewById(R.id.playerListView);
            playerListView.setAdapter(new PlayersListAdapter(a, R.layout.item_player, lobby.getLobbyPlayers()));
        } catch (NullPointerException e) {
            AlertDialog alertDialog = Alerts.connectionError(a, e.getMessage());
            alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    a.finish();
                }
            });
            alertDialog.show();
        }

        countDownTimer = new CountDownTimer(30000, 4000) {
            public void onTick(long millisUntilFinished) {
                if(!downloadDataAndSetViews())
                    return;
                try {
                    ListView playerListView = (ListView) a.findViewById(R.id.playerListView);
                    playerListView.setAdapter(new PlayersListAdapter(a, R.layout.item_player, lobby.getLobbyPlayers()));
                } catch (NullPointerException e) {
                    AlertDialog alertDialog = Alerts.connectionError(a, e.getMessage());
                    alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            a.finish();
                        }
                    });
                    alertDialog.show();
                }
            }
            public void onFinish() {
                this.start();
            }
        }.start();
    }


    public void logoutFromLobby() {
        try {
            LobbyLogoutGetter.getResponse(lobbyId);
        } catch (RestException e) {
            e.printStackTrace();
            AlertDialog alertDialog = e.getErrorAlert(a);
            alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    loginToLobby();
                }
            });
            alertDialog.show();
        }
    }

    private void loginToLobby() {
        try {
            LobbyLoginGetter.getResponse(lobbyId);
        } catch (RestException e) {
            e.printStackTrace();
            AlertDialog alertDialog = e.getErrorAlert(a);
            alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    loginToLobby();
                }
            });
            alertDialog.show();
        }
    }

    private Boolean downloadDataAndSetViews() {
        try {
            LobbyDataDownloader dataDownloader = new LobbyDataDownloader(a, lobbyId);
            lobby = dataDownloader.getLobby();
            if(lobby.getStatus().equals(LobbySettings.Status.READY)) {
                openGameActivity(lobby);
                return false;
            }
            setViewFromLobbyData(lobby);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (RestException e) {
            e.printStackTrace();
            AlertDialog alertDialog = e.getErrorAlert(a);
            alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    downloadDataAndSetViews();
                }
            });
            alertDialog.show();
        }
        return true;
    }

    private void openGameActivity(Lobby lobby){
        Intent intent;
        PlayerData playerData = PlayerDataReceiver.fromString(new HttpResponseReceiver("mycharacters").receive().getMessage());
        if(lobby.getHunterID().equals(playerData.getUserID()))
            intent = new Intent(a, HunterGameActivity.class);
        else
            intent = new Intent(a, ChaseGameActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(LobbySettings.LOBBY_TO_GAME_INTENT_TAG, lobby);
        intent.putExtras(bundle);
        a.startActivity(intent);
        if(countDownTimer != null)
            countDownTimer.cancel();
        a.finish();
    }

    private void setViewFromLobbyData(Lobby lobby) {
        TextView lobbyTitle = (TextView) a.findViewById(R.id.lobbyTitle);
        TextView playersTitle = (TextView) a.findViewById(R.id.lobbyPlayersNumber);

        playersTitle.setText(R.string.lobby_players_number);


        lobbyTitle.setText(lobby.getTitle());
        playersTitle.setText(
                playersTitle.getText().toString()
                        .replace("[magic]", lobby.getPlayersNO() + " / " + lobby.getPlayersMax())
        );

    }

    public void onPause() {
        try {
            countDownTimer.cancel();
            countDownTimer = null;
        }
        catch (NullPointerException e){

        }
    }

    public void clickReady() {
        try {
            if (!ready) {
                ((Button) a.findViewById(R.id.readyButton)).setText(R.string.ready_button);
                ready = !ready;
                StatusPoster.getResponse(lobbyId, true);
            } else {
                ((Button) a.findViewById(R.id.readyButton)).setText(R.string.not_ready_button);
                ready = !ready;
                StatusPoster.getResponse(lobbyId, false);
            }
        }
        catch (RestException e){
            e.printStackTrace();
            e.getErrorAlert(a).show();
        }
    }
}
