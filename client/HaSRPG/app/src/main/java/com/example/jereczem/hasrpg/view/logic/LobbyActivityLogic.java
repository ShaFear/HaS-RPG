package com.example.jereczem.hasrpg.view.logic;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.game.lobbies.Lobby;
import com.example.jereczem.hasrpg.networking.rest.LobbyDataDownloader;
import com.example.jereczem.hasrpg.networking.rest.RestException;
import com.example.jereczem.hasrpg.view.toolbar.ToolbarSetter;

import org.json.JSONException;

/**
 * Created by jereczem on 04.09.15.
 */
public class LobbyActivityLogic {
    AppCompatActivity a;
    Integer lobbyId;

    public LobbyActivityLogic(AppCompatActivity a){
        this.a = a;
        lobbyId = a.getIntent().getIntExtra("lobbyId", 0);
        new ToolbarSetter(a, R.drawable.previous);
        downloadDataAndSetViews();
    }

    private void downloadDataAndSetViews(){
        try {
            LobbyDataDownloader dataDownloader = new LobbyDataDownloader(a, lobbyId);
            setViewFromLobbyData(dataDownloader.getLobby());
            Log.d("HASLOG", dataDownloader.getLobby().toString());
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
    }

    private void setViewFromLobbyData(Lobby lobby){
        TextView lobbyTitle = (TextView) a.findViewById(R.id.lobbyTitle);
        TextView playersTitle = (TextView) a.findViewById(R.id.lobbyPlayersNumber);
        TextView gameTime = (TextView) a.findViewById(R.id.lobbyGameTime);
        TextView runTime = (TextView) a.findViewById(R.id.lobbyRunTime);

        playersTitle.setText(R.string.lobby_players_number);
        gameTime.setText(R.string.lobby_game_time);
        runTime.setText(R.string.lobby_runtime);

        lobbyTitle.setText(lobby.getTitle());
        playersTitle.setText(
                playersTitle.getText().toString()
                        .replace("[magic]", lobby.getPlayersNO() + " / " + lobby.getPlayersMax())
        );
        gameTime.setText(
                gameTime.getText().toString().replace("[magic]", lobby.getGameTime().toString())
        );
        runTime.setText(
                runTime.getText().toString().replace("[magic]", lobby.getRunTime().toString())
        );
    }
}
