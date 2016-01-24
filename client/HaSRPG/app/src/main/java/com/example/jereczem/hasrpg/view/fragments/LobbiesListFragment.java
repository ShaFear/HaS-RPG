package com.example.jereczem.hasrpg.view.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.example.jereczem.hasrpg.R;
import com.example.jereczem.hasrpg.data.lobby.LobbyBaseData;
import com.example.jereczem.hasrpg.data.lobby.LobbyDataReceiver;
import com.example.jereczem.hasrpg.game.lobbies.Lobby;
import com.example.jereczem.hasrpg.networking.HttpResponse;
import com.example.jereczem.hasrpg.networking.rest.LobbiesGetter;
import com.example.jereczem.hasrpg.networking.rest.LobbyDataDownloader;
import com.example.jereczem.hasrpg.networking.rest.RestException;
import com.example.jereczem.hasrpg.view.activities.LobbyActivity;
import com.example.jereczem.hasrpg.view.adapters.LobbiesListAdapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 *
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class LobbiesListFragment extends ListFragment {

    private OnFragmentInteractionListener mListener;
    private AppCompatActivity activity;
    ArrayList<Lobby> lobbies;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public LobbiesListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (AppCompatActivity) getActivity();
    }

    public void setLobbies(){
        lobbies = new ArrayList<>();
        downloadLobbies();

        if(lobbies != null) {
            setListAdapter(new LobbiesListAdapter(activity, R.layout.item_lobby, lobbies));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setLobbies();
        final SwipeRefreshLayout r = (SwipeRefreshLayout) activity.findViewById(R.id.refresh_list_fragment);
        r.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setLobbies();
                r.setRefreshing(false);
            }
        });
    }

    private void downloadLobbies() {
        try {
            HttpResponse response = LobbiesGetter.getResponse();
            if(response.getCode().equals(200)){
                JSONArray jsonArray = new JSONArray(response.getMessage());
                for(int j=0; j<jsonArray.length(); j++){
                    LobbyBaseData lobbyBase = LobbyDataReceiver.receiveBaseData("[" + jsonArray.get(j).toString() + "]");
                    LobbyDataDownloader lobbyDataDownloader = new LobbyDataDownloader(activity, lobbyBase.getLobbyID());
                    Lobby lobby = lobbyDataDownloader.getLobby();
                    lobbies.add(lobby);
                }
            }
        } catch (RestException e) {
            e.printStackTrace();
            AlertDialog alertDialog = e.getErrorAlert(activity);
            alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    downloadLobbies();
                }
            });
            alertDialog.show();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(getActivity(), LobbyActivity.class);
        intent.putExtra("lobbyId", lobbies.get(position).getLobbyID());
        startActivity(intent);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }
}
