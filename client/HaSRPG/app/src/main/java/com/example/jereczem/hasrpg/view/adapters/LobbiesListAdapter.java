package com.example.jereczem.hasrpg.view.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.data.player.PlayerData;
import com.example.jereczem.hasrpg.game.lobbies.Lobby;

import java.util.ArrayList;

/**
 * Created by jereczem on 05.09.15.
 */
public class LobbiesListAdapter extends ArrayAdapter<Lobby> {
    public LobbiesListAdapter(Context context, int resource, ArrayList<Lobby> lobbyDatas) {
        super(context, resource, lobbyDatas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.item_lobby, null);
        }

        Lobby lobbyData = getItem(position);

        if(lobbyData != null){
            setViewFromLobbyData(lobbyData, v);
        }
        return v;
    }

    private void setViewFromLobbyData(Lobby lobby, View a){
        TextView lobbyTitle = (TextView) a.findViewById(R.id.lobbyTitleI);
        TextView playersTitle = (TextView) a.findViewById(R.id.lobbyPlayersNumberI);
        TextView gameTime = (TextView) a.findViewById(R.id.lobbyGameTimeI);
        TextView runTime = (TextView) a.findViewById(R.id.lobbyRunTimeI);

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
